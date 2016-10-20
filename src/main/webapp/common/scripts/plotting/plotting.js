(function ($) {
    var defaultOptions = {
            /**
             * 当前的操作
             *
             * plotting - 标绘
             * show - 展示
             * @property action {String}
             * @default plotting
             */
            action: 'plotting',

            /**
             * 鼠标工作模式
             *
             * normal - 正常
             * pan - 平移
             * point - 点
             * polygon - 多边形
             * circle - 圆
             * rectangle - 矩形
             * select - 选择
             * delete - 删除
             *
             * @property mode {String}
             * @default pan
             */
            mode: 'pan',

            /**
             * 是否使用默认的选择、删除事件
             *
             * @property useDefaultEvent
             */
            useDefaultEvent: true,

            /**
             * 新添加图形的默认属性
             *
             * @property attrs
             * @property attrs.fill-opacity {Number} 透明度
             * @property attrs.stroke {String} 边框颜色
             * @property attrs.fill {String} 填充颜色
             */
            attrs: {
                'fill-opacity': 0.3,
                stroke: '#ccc',
                fill: '#0f0'
            },

            /**
             * 标记点的图标属性
             *
             * @property icon
             * @property icon.size {Number} 大小
             * @property icon.offset {Object} 偏移
             * @property icon.url {String} 图片地址
             */
            icon: {
                size: 32,
                offset: {
                    left: 0,
                    top: 0
                },
                url: 'icon.png'
            },
        
            /**
             * 是否支持触控
             * @property supportTouch {boolean}
             * @default true
             */
            supportTouch: true
        },
        SELECT = 'select',
        SELECTED = 'selected',
        DELETE = 'delete',
        PLOTTING = 'plotting';

    /**
     * 标绘主类
     *
     * 支持标绘、展示，兼容IE8-11(IE8下无法实现缩放), Chrome, Firefox,
     *
     * @class
     * @param el 容器
     * @param options 选项
     * @constructor
     */
    function Plotting(el, options) {
        this.el = el;
        this.options = $.extend({}, defaultOptions, options);
        this._init();
    }

    Plotting.prototype = $.extend(Plotting.prototype, {
        constructor: Plotting,

        /**
         * 初始化
         * @private
         */
        _init: function () {
            var that = this,
                options = that.options;

            that.setBackground(options.bg);
            that._offset = that.el.offset();
            that.mode(options.mode || 'pan', true);

            that._attachEvent();
        },

        setBackground: function (bg) {
            var that = this,
                options = that.options,
                width = options.width,
                height = options.height,
                imgWidth, imgHeight,
                paper = that.paper,
                $img = $('<img>').appendTo(that.el).attr({src: bg});

            // 检测图片尺寸
            $img.load(function () {
                imgWidth = that._originWidth = $img[0].naturalWidth;
                imgHeight = that._originHeight = $img[0].naturalHeight;
                $img.remove();

                if (that.paper) {
                    paper = that.paper;
                    that._bg.attr({src: bg, width: imgWidth, height: imgHeight});
                } else {
                    //初始化画布大小
                    paper = that.paper = Raphael(that.el.attr('id'), width, height);
                    that._bg = paper.image(bg, 0, 0, imgWidth, imgHeight).data('ignore', true).toBack();
                }

                paper.setViewBox(0, 0, imgWidth, imgHeight, false);

                //that.zoom(Math.min(width / imgWidth, height / imgHeight));
                that.zoom(1);

                // 添加forEach方法
                that.forEach = $.proxy(paper.forEach, paper);

                that.el.trigger('init');
            });
        },

        /**
         * 切换或是获取当前模式
         *
         * @param [mode]
         * @param [silent]
         */
        mode: function (mode, silent) {
            var that = this;

            if (mode) {
                that.el.one('switchMode', function (e, mode) {
                    if (!e.isDefaultPrevented()) {
                        that._mode = mode;

                        if (mode === 'polygon') {
                            that._points = [];
                            that._current = null;
                        }
                    }
                });

                if (!silent) {
                    that.el.trigger('switchMode', [mode]);
                }
            } else {
                return that._mode;
            }
        },

        /**
         * 根据属性获取标绘的图形
         *
         * 要求图形中有`attrs`属性
         *
         * @param key {String} 属性名称
         * @param value {*} 属性值
         * @returns {Array} 所有符合条件的图形
         */
        getByAttr: function (key, value) {
            var result = [], attrs;
            this.paper.forEach(function (el) {
                attrs = el.data('attrs');

                if (!attrs || el.data('ignore') || el.attrs['stroke-linecap']) {
                    return true;
                }

                if (attrs[key] === value) {
                    result.push(el);
                }
            });

            return result;
        },

        /**
         * 根据id属性查找标绘的图形
         *
         * @param id {String|Number} ID值
         * @returns {Element|null} 如果要查找的图形不存在时，返回`null`
         */
        getById: function (id) {
            var result = this.getByAttr('id', id);

            return result.length > 0 ? result[0] : null;
        },

        /**
         * 获取或是设置当前的数据
         *
         * @param [data] {Object} 要加载的数据
         * @param [data.type] {String} 类型：image - 图像, rect - 矩形, circle - 圆, path - 路径（多边形）
         * @param [data.attrs] {Object} 自定义属性`getByAttr`方法会从这个对象中查找属性
         * @param [data.x] {Number} 横坐标
         * @param [data.y] {Number} 纵坐标
         * @param [data.width] {Number} 宽度
         * @param [data.height] {Number} 高度
         * @param [data.fill] {String} 填充颜色，可以是`#FFFFFF`，`#FFF`或颜色名称
         * @param [data.stroke] {String} 边框颜色，可以是`#FFFFFF`，`#FFF`或颜色名称
         * @param [data.r] {Number} 圆半径
         * @param [data.cx] {Number} 圆心横坐标
         * @param [data.cy] {Number} 圆心纵坐标
         * @param [data.src] {String} 图片地址
         * @param [data.rx] {Number} 矩形圆角横向半径
         * @param [data.ry] {Number} 矩形圆角纵向半径
         *
         * @return 当前标绘的图形的JSON
         */
        data: function (data) {
            var that = this,
                args;

            if (data) {
                // load the data
                $.each(data, function (k, v) {
                    switch (v.type) {
                        case 'image':
                            args = [v.src, v.x, v.y, v.width, v.height];
                            break;
                        case 'path':
                            args = [v.path];
                            break;
                        case 'circle':
                            args = [v.cx, v.cy, v.r];
                            break;
                        case 'rect':
                            args = [v.x, v.y, v.width, v.height];
                            break;
                    }
                    var shape = that.paper[v.type].apply(that.paper, args);
                    shape.data('attrs', v.attrs);
                    if ('none' !== v.fill) {
                        shape.attr('fill', v.fill);
                    }
                    shape.attr('fill-opacity', v['fill-opacity']);
                    shape.attr('stroke', v.stroke);
                });
            } else {
                // return the date
                var shapes = [];
                that.paper.forEach(function (el) {
                    if (el.data('ignore') || el.attrs['stroke-linecap']) {
                        return true;
                    }

                    var attr = $.extend({}, el.attrs);
                    attr.type = el.type;
                    attr.attrs = el.data('attrs');
                    if (attr.type === 'path') {
                        var path = [];
                        $.each(attr.path, function (k, p) {
                            path.push(p[0] + p[1], p[2]);
                        });
                        attr.path = path.join(' ');
                    }
                    shapes.push(attr);
                });

                return shapes;
            }
        },

        /**
         * 设置参数
         * @param options {Object} 参数
         */
        setOptions: function (options) {
            this.options = $.extend(true, this.options, options);
            attachEvents(this);
        },

        /**
         * 删除图形
         *
         * @param el {Element} 要删除的图形
         */
        remove: function (el) {
            if (!el) {
                return;
            }

            $.each(el.data('shadow') || [], function (k, v) {
                v.remove();
            });

            el.remove();

            this.el.trigger('removeShape', [el]);
        },

        /**
         * 选择图形
         *
         * @param [el] {Element} 要选择的图形
         * @param [select] {boolean} 是否选中
         * @returns {Array|undefined} 返回选择的图形
         */
        select: function (el, select) {
            if (!el) {
                return;
            }

            var isSelected = el.data(SELECTED);
            if (arguments.length == 1) {
                select = !isSelected;
            }

            if (el) {
                var shadow = el.data('shadow') || [];

                if (select) {
                    // 如果当前没有被选中
                    if (!isSelected) {
                        shadow = el.glow({color: '#f00'});
                    }
                } else {
                    $.each(shadow, function (k, v) {
                        v.remove();
                    });
                    shadow = null;
                }

                el.data(SELECTED, select);
                el.data('shadow', shadow);
            } else {
                var selected = [];
                this.paper.forEach(function (el) {
                    if (el.data(SELECTED)) {
                        selected.push(el);
                    }
                });
                return selected;
            }
        },

        /**
         * 闪动
         *
         * @param el {Element} 要闪动的图形
         * @param [color] {String} 闪动的颜色，默认为红色
         * @param [interval] {Number} 时间间隔，单位为毫秒, 默认为600
         */
        flash: function (el, color, interval) {
            color = color || 'red';
            interval = interval || 600;

            var flashShadow = '__flash-shadow',
                flashId = '__flashId',
                id,
                flash = function () {
                    var shadow = el.data(flashShadow);

                    if (shadow) {
                        $.each(shadow, function (k, el) {
                            el.remove();
                        });
                        shadow = null;
                    } else {
                        shadow = el.glow({color: color});
                    }

                    el.data(flashShadow, shadow);
                };

            id = setInterval(flash, interval);
            el.data(flashId, id);
        },

        /**
         * 停止闪动
         *
         * @param el {Element} 要停止闪动的图形
         */
        stopFlash: function (el) {
            var flashId = el.data('__flashId');

            if (flashId) {
                // 停止闪动时取消选择
                this.select(el, false);
                clearInterval(flashId);
            }
        },

        /**
         * 缩放
         *
         * @param level {Number} 缩放的等级，取值为（0..+∞]时对原图进行缩放，为0时缩放为size大小
         */
        zoom: function (level) {
            var that = this,
                paper = that.paper,
                options = that.options,
                viewBox = paper._viewBox,
                width = that._originWidth,
                height = that._originHeight;

            if (level === 0) {
                level = Math.min(options.width / width, options.height / height);
            }

            that._zoomLevel = level;
            that.paper.setViewBox(0, 0, width / level, height / level, viewBox[4]);
            //that.paper.setViewBox((width - width / level) / 2, (height - height / level) / 2, width / level, height / level, viewBox[4]);
        },

        _getPosition: function (e) {
            var that = this,
                offset = that._offset,
                el = $('body'),
                docEL = document.documentElement;

            return {
                x: e.clientX - offset.left + (el.scrollLeft() || docEL.scrollLeft),
                y: e.clientY - offset.top + (el.scrollTop() || docEL.scrollTop)
            };
        },

        _getPoint: function (pos) {
            var that = this,
                zoomLevel = that._zoomLevel,
                paper = that.paper,
                viewBox = paper ? paper._viewBox : [0, 0];
            return {
                x: pos.x / zoomLevel + viewBox[0],
                y: pos.y / zoomLevel + viewBox[1]
            }
        },

        _attachEvent: function () {
            var that = this,
                proxy = $.proxy;
            this.el.on({
                mousedown: proxy(that._mousedown, that),
                mousemove: proxy(that._mousemove, that),
                mouseup: proxy(that._mouseup, that),
                contextmenu: proxy(that._contextmenu, that)
            });
        },

        _mousedown: function (e) {
            var that = this,
                attrs = that.options.attrs,
                pos = that._getPosition(e),
                point = that._getPoint(pos),
                shape;

            if (e.which !== 1) {
                return;
            }

            that._last = pos;
            switch (that._mode) {
                case 'point':
                    var icon = that.options.icon;

                    shape = that.paper.image(icon.url,
                        point.x - icon.size / 2 + icon.offset.left,
                        point.y - icon.size / 2 + icon.offset.top,
                        icon.size, icon.size);
                    break;

                case 'polygon':
                    // 将当前点加入
                    that._points.push(point.x, point.y);
                    if (that._current) {
                        that._current.remove();
                    }

                    that._current = that.paper.path('M' + that._points.join(' '));
                    break;

                case 'circle':
                    if (that._origin) {
                        // 当存在中心点时，画一个圆
                        var o = that._origin,
                            radius = Math.sqrt(Math.pow(point.x - o.x, 2) + Math.pow(point.y - o.y, 2));

                        that._current && that._current.remove();
                        shape = that.paper.circle(o.x, o.y, radius).attr(attrs);

                        // 清理数据
                        delete that._origin;
                        delete that._current;
                    } else {
                        // 开始画圆，记录圆心
                        that._origin = point;
                    }
                    break;

                case 'rectangle':
                    if (that._origin) {
                        // 存在点，画一个矩形
                        o = that._origin;

                        that._current && that._current.remove();
                        var rect = that._getRectangle(o, point);
                        shape = that.paper.rect(rect.l, rect.t, rect.w, rect.h).attr(attrs);

                        // 清理数据
                        delete that._origin;
                        delete that._current;
                        delete that._last;
                    } else {
                        that._origin = point;
                    }
                    break;

                case 'pan':
                    that.el.css('cursor', 'pointer');
                    that._origin = point;
                    break;


            }

            if (shape) {
                that.el.trigger(PLOTTING, [shape]);
            }
        },

        _mousemove: function (e) {
            var that = this,
                pos = that._getPosition(e),
                point = that._getPoint(pos),
                o = that._origin,
                l = that._last,
                c = that._current,
                distance = l && Math.sqrt(Math.pow(pos.x - l.x, 2) + Math.pow(pos.y - l.y, 2));

            switch (that._mode) {
                case 'circle':
                    if (o && distance > 3) {
                        var radius = Math.sqrt(Math.pow(point.x - o.x, 2) + Math.pow(point.y - o.y, 2));
                        c || (that._current = that.paper.circle(o.x, o.y, radius));

                        if (c && Math.abs(radius - c.attr().r) > 3) {
                            c.remove();
                            that._current = that.paper.circle(o.x, o.y, radius);
                        }

                        that._last = pos;
                    }
                    break;

                case 'rectangle':
                    if (o && distance > 3) {
                        c && c.remove();

                        var rect = that._getRectangle(o, point);
                        that._current = that.paper.rect(rect.l, rect.t, rect.w, rect.h);

                        that._last = pos;
                    }
                    break;

                case 'pan':
                    if (o && distance > 5) {
                        that._pan(pos, l);
                        that._last = pos;
                    }
            }

        },

        _pan: function (pos, last) {
            var that = this,
                paper = that.paper,
                viewBox = paper._viewBox,
                zoomLevel = that._zoomLevel,
                left = viewBox[0] + (last.x - pos.x) / zoomLevel,
                top = viewBox[1] + (last.y - pos.y) / zoomLevel,
                maxLeft = that._originWidth - viewBox[2],
                maxTop = that._originHeight - viewBox[3];

            left = validate(0, maxLeft, left);
            top = validate(0, maxTop, top);
            paper.setViewBox(left, top, viewBox[2], viewBox[3], viewBox[4]);
        },

        _mouseup: function (e) {
            var that = this,
                pos = that._getPosition(e),
                point = that._getPoint(pos);

            switch (that._mode) {
                case SELECT:
                case DELETE:
                    var el = that.paper.getElementsByPoint(point.x, point.y),
                        shadow = [], current;
                    $.each(el, function (k, v) {
                        if (!v.data('ignore')) {
                            if (v.attrs['stroke-linecap']) {
                                shadow.push(v);
                            } else {
                                current = v;
                            }
                        }
                    });
                    if (current) {
                        that.el.trigger(that._mode, [current, shadow]);
                    }
                    break;
                case 'pan':
                    delete that._last;
                    delete that._origin;
                    that.el.css('cursor', 'auto');
                    break;

            }
        },

        _contextmenu: function (e) {
            var that = this,
                c = that._current,
                shape;
            switch (that._mode) {
                case 'polygon':
                    // 点击右键时封口
                    if (c) {
                        c.remove();
                        shape = that.paper.path('M' + that._points.join(' ') + ' ' + that._points.slice(0, 2).join(' ')).attr(that.options.attrs);
                        that.el.trigger(PLOTTING, [shape]);
                        that._points = that._points.slice(0, 0);
                        delete that._current;
                        e.preventDefault();
                    }
                    break;
            }
        },

        _getRectangle: function (p1, p2) {
            var left, top;

            if (p1.x >= p2.x) {
                left = p2.x;
                if (p1.y >= p2.y) {
                    top = p2.y;
                } else if (p1.y < p2.y) {
                    top = p1.y;
                }
            } else {
                left = p1.x;
                if (p1.y >= p2.y) {
                    top = p2.y;
                } else if (p1.y < p2.y) {
                    top = p1.y;
                }
            }

            return {l: left, t: top, w: Math.abs(p1.x - p2.x), h: Math.abs(p1.y - p2.y)};
        }
    });


    function attachEvents(plotting) {
        plotting.el.off(SELECT).off(DELETE);

        if (plotting.options.useDefaultEvent) {
            plotting.el.on(SELECT, function (e, el) {
                plotting.select(el);
            }).on(DELETE, function (e, el) {
                plotting.remove(el);
            });
        }

        if (plotting.options.supportTouch) {
            plotting.el.off('touchstart').off('touchmove').off('touchend');

            var math = Math,
                // stand for "distance between two points"
                db2p = function (touch0, touch1) {
                    return math.sqrt(math.pow(touch0.clientX - touch1.clientX, 2) + math.pow(touch0.clientY - touch1.clientY, 2));
                },
                distance,
                start, last, pos;

            plotting.el.on({
                    'touchstart': function (e) {
                        var oe = e.originalEvent,
                            touches = oe.targetTouches;
                        if (touches.length === 2) {
                            // 计算缩放开始时两点的距离
                            distance = db2p.apply(oe, touches);
                        } else if (touches.length === 1) {
                            console.log('start', touches[0].clientX, touches[0].clientY, plotting._zoomLevel)
                            start = touches[0];
                            last = plotting._getPosition(start);
                        }

                    },

                    'touchmove': function (e) {
                        var oe = e.originalEvent,
                            touches = oe.targetTouches,
                            newDistance,
                            level;

                        if (touches.length === 2) {
                            oe.preventDefault();

                            // zoom
                            newDistance = db2p.apply(oe, touches);
                            if (math.abs(newDistance - distance) > 10) {
                                level = plotting._zoomLevel * newDistance / distance;
                                distance = newDistance;
                                plotting.zoom(level);
                            }
                        } else if (touches.length === 1) {
                            oe.preventDefault();

                            pos = plotting._getPosition(touches[0]);
                            // pan
                            if (start && db2p.call(oe, start, touches[0]) > 5) {
                                start = touches[0];
                                plotting._pan(pos, last);
                                last = pos;
                            }
                        }

                    }
                }
            );
        }
    }

    $.fn.plotting = function (options) {
        var $this = $(this),
            plotting = new Plotting($this, options);
        $this.data('Plotting', plotting);

        attachEvents(plotting);

        return this;
    };

    /**
     * 获取或设置属性
     *
     * @param key {String | Object} 属性名称或K-V对象
     * @param [value] {*} 属性值
     * @param [overwrite] {boolean} 是否覆盖，默认参数为K-V对象时为false，是单个属性时为true
     */
    Raphael.el.attribute = function (key, value, overwrite) {
        var attrs = this.data('attrs');

        if (!attrs) {
            attrs = {};
        }

        if (arguments.length === 1 && typeof key === 'string') {
            return attrs[key];
        }

        if (typeof key === 'object') {
            overwrite = value || false;

            if (overwrite) {
                attrs = key;
            } else {
                $.extend(attrs, key);
            }
        } else {
            overwrite = arguments.length == 2 ? true : overwrite;

            if (overwrite || !attrs.hasOwnProperty(key)) {
                attrs[key] = value;
            }
        }

        this.data('attrs', attrs);
    };

    function validate(min, max, value) {
        var tmp = min, math = Math;
        min = math.min(tmp, max);
        max = math.max(tmp, max);

        return value < min ? min : (value > max ? max : value);
    }
}(jQuery));

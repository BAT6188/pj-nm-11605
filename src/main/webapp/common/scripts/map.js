/**
 * Created by liuyifan on 2016/10/12.
 * 提供类似于java中map容器的功能
 * 提供向容器中放键值对，获取键值对，移除键值对，判断容器中是否有存在一个键值对
 * put：放进去key value组合。key支持数字类型，如map.put(1,1)
 * get：根据键得到值
 * remove：从map容器中移除一个键值对
 * isHave: 判断map中是否存在一个键值对，如果不存在则返回false，存在返回true
 */
(function( window, undefined ) {
    var c={};
    var map={
        put:function (k,v) {
            c[k]=v;
        },
        get:function (k) {
            return c[k];
        },
        remove:function (k) {
            return delete c[k];
        },
        removeAll:function () {
          c=new Object();
        },
        isHave:function (k) {
            if(undefined==c[k]){
                return false;
            }else{
                return true;
            }
        }
    }
    window.map=map;
})( window );
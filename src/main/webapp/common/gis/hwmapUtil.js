var hwmapUtil = {
    hwmap:undefined,
    mapWindow:undefined,
    init:function (mapWindow) {
        if (!this.mapWindow) {
            this.mapWindow = mapWindow;
        }
        this.addInitFinish(function (hwmap) {
            this.hwmap = hwmap;
        });
        return this;
    },
    addInitFinish:function (initFinishEvent) {
        if (this.hwmap) {
            initFinishEvent(this.hwmap);
        }
        if (!this.mapWindow.initFinishEvents){
            this.mapWindow.initFinishEvents = [];
        }
        this.mapWindow.initFinishEvents.push(initFinishEvent);

    }
};

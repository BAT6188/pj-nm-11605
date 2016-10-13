
/**
 * 增加百度layer
 * @param layerId
 */
mapPrototype.addBaiduLayer = function(layerId){
	if(layerId==="PanoramaCoverageLayer"){
		if(!this.PanoramaCoverageLayer){
			this.PanoramaCoverageLayer = new BMap.PanoramaCoverageLayer();
			this.mMap.addTileLayer(this.PanoramaCoverageLayer);
			this.PanoramaControl = new BMap.PanoramaControl();
			this.PanoramaControl.setOffset(new BMap.Size(20, 20));
			this.mMap.addControl(this.PanoramaControl);
		}
	}
}
/**
 * 删除百度layer
 * @param layerId
 */
mapPrototype.removeBaiduLayer = function(layerId){
	if(layerId==="PanoramaCoverageLayer" && this.PanoramaCoverageLayer){
		this.mMap.removeTileLayer(this.PanoramaCoverageLayer);
		this.PanoramaCoverageLayer = undefined;
		this.mMap.removeControl(this.PanoramaControl);
		this.PanoramaControl = undefined;
	}
}
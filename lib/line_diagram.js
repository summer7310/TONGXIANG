/*引入外部脚本*/
var main = document.createElement('script');
main.type = 'text/javascript';
main.src = 'main.js';

Global.line.method.init = function(initVar){
	Global.constant.currentMap = 1;
	OpenLayers.ProxyHost = "cgi/proxy.cgi?url=";
	var extent = new OpenLayers.Bounds(-20,-20,20,20);
	Global.line.draw.pointStyleMap = Global.style.lineDiagramStyle;
	Global.line.map = new OpenLayers.Map('map2');
	Global.line.map.div.oncontextmenu = function () { return false;};//屏蔽浏览器的右键菜单	

	var graphic = new OpenLayers.Layer.Image(
		'Grid',
		'img/linebg.png',
		Global.line.constant.fullExtent,
		new OpenLayers.Size(2000, 1500),
		{
			isBaseLayer: true,
			numZoomLevels: 3
		});

	Global.line.layers.unitLayer = new OpenLayers.Layer.Vector(
    	"设备层", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.line.save.saveStrategy],  	
			styleMap:Global.line.draw.pointStyleMap,
			filter: new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "belongs_cableline",
				value : 0
			}), 
			rendererOptions: {zIndexing: true},
    		protocol: new OpenLayers.Protocol.WFS({
        		url: "http://localhost:8081/geoserver/OpenGIS/wfs",//需要设置
        		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
        		featureType: "geo_connect_line",//图层名，需要设置
        		geometryName: "geometry_data"//几何信息字段名，需要设置
        		})
        	}
    );	
	
	if(Global.line.initVar){
		var eleFilter = new OpenLayers.Filter.Comparison({
			type : OpenLayers.Filter.Comparison.EQUAL_TO,
			property : "belongs_cableline",
			value: Global.line.initVar
		});
		Global.line.layers.unitLayer.filter = eleFilter;
	}
	Global.line.map.addLayers([graphic, Global.line.layers.unitLayer]);
	
	/*定义要素选择*/  
    Global.line.select.control = new OpenLayers.Control.SelectFeature(Global.line.layers.unitLayer,{
		onRightSelect:Global.method.onFeatureRightSelect,//添加的右键菜单
		toggle: true,
		box: true,
		boxKey: OpenLayers.Handler.MOD_SHIFT,
        multipleKey: "ctrlKey"
	});	

   	Global.line.draw.pointControl = new OpenLayers.Control.DrawFeature(
		Global.line.layers.unitLayer, OpenLayers.Handler.Point,
		{
			callbacks:{
				done: function(geometry) {
	        		var exist = false;
	    			for(var i=0;i<Global.line.layers.unitLayer.features.length;i++){
	    				if(Global.line.layers.unitLayer.features[i].attributes['static_id'] == Global.line.unit.staticId){
	    					exist = true;
	    		        	Plugins.dialog.show({
	    		        		'title': '错误信息',
	    		        		'text': '请勿重复添加'
	    		        	},{
	    		        		'submit' : function(e){
	    		        			Plugins.dialog.remove();
	    		        		}
	    		        	});
	    		        	break;
	    				}	    				
	    			}
	    			if(!exist){
						var feature = new OpenLayers.Feature.Vector(geometry);				       
						feature.renderIntent = 'default';
						var proceed = this.layer.events.triggerEvent(
					    	"sketchcomplete", {feature: feature}
					 	 );
						 if(proceed !== false) {
						 	feature.attributes['name'] = Global.line.unit.name;
						 	feature.attributes['object_type'] = Global.line.unit.typeID;
						 	feature.attributes['static_id'] = Global.line.unit.staticId;
						 	if(Global.line.unit.typeID == '1020101'){
							 	feature.attributes['z_index'] = Global.constant.unit-5;
						 	}else{
							 	feature.attributes['z_index'] = Global.constant.unit;
						 	}
						 	feature.attributes['belongs_cableline'] = Global.line.initVar;
					   	 	feature.state = OpenLayers.State.INSERT;
					 		this.layer.addFeatures([feature]);
							this.featureAdded(feature);
					 		this.events.triggerEvent("featureadded",{feature : feature});
						}
						 slide.del(Global.line.unit.name);//侧栏数据隐藏
	    			}
				},
	        	create: function(vertex, feature) {
					feature.style = Global.line.draw.styleType;
            		this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
				}
			}
		}
	);
   	Global.line.draw.lineControl = new OpenLayers.Control.DrawFeature(
		Global.line.layers.unitLayer, OpenLayers.Handler.Path,
		{
			callbacks:{
				done: function(geometry) {
					var feature = new OpenLayers.Feature.Vector(geometry);				       
					feature.renderIntent = 'default';
					var proceed = this.layer.events.triggerEvent(
				    	"sketchcomplete", {feature: feature}
				 	 );
					 if(proceed !== false) {
					 	feature.attributes['name'] = Global.line.unit.name;
					 	feature.attributes['object_type'] = Global.line.unit.typeID;
					 	feature.attributes['static_id'] = Global.line.unit.staticId;
					 	feature.attributes['z_index'] = Global.constant.wirecable_segment;
					 	feature.attributes['belongs_cableline'] = Global.line.initVar;
				   	 	feature.state = OpenLayers.State.INSERT;
				 		this.layer.addFeatures([feature]);
						this.featureAdded(feature);
				 		this.events.triggerEvent("featureadded",{feature : feature});
					}
					 slide.del(Global.line.unit.name);//侧栏数据隐藏
					 
				},
	        	create: function(vertex, feature) {
					feature.style = Global.line.draw.styleType;
            		this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
				}
			}
		}
	);
   	
   	Global.line.copy.dragControl = new OpenLayers.Control.DragFeature(Global.line.layers.unitLayer, {
		onStart: Global.line.method.startDrag,
		onDrag: Global.line.method.doDrag,
		onComplete: Global.line.method.endDrag,
	});
	Global.line.map.addControl(Global.line.copy.dragControl);

	var snap = new OpenLayers.Control.Snapping({layer: Global.line.layers.unitLayer});
	Global.line.map.addControl(snap);		
	Global.line.map.addControls([Global.line.select.control, Global.line.draw.pointControl, Global.line.draw.lineControl]);
	snap.activate();
    Global.line.select.control.activate();

    Global.line.controls = 
    {
    	pan: new OpenLayers.Control.Pan,
    	edit: new OpenLayers.Control.ModifyFeature(null, {
    		layers:[Global.line.layers.unitLayer],
    		mode: OpenLayers.Control.ModifyFeature.ROTATE |	OpenLayers.Control.ModifyFeature.RESIZE
    			| OpenLayers.Control.ModifyFeature.DRAG | OpenLayers.Control.ModifyFeature.DELETE
    		}
    	),
    	left: new OpenLayers.Control.DrawFeature(
        		Global.line.layers.unitLayer, OpenLayers.Handler.Point,{
    				callbacks:{
    					done: function(geometry) {
    						var points = [];
    						points[0] = geometry.clone();
    						points[1] = new OpenLayers.Geometry.Point(points[0].x-Global.constant.terminal_length, points[0].y);
    						points[2] = new OpenLayers.Geometry.Point(
    								points[1].x-Global.constant.terminal_length/2*Math.sqrt(3), points[1].y+Global.constant.terminal_length/2);
    						points[3] = new OpenLayers.Geometry.Point(points[2].x, points[2].y-Global.constant.terminal_length);
    						points[4] = points[1].clone();
    						var leftend = new OpenLayers.Geometry.LineString(points);
    						var feature = new OpenLayers.Feature.Vector(leftend);
    						feature.style = Global.style.form[11];
    						var proceed = this.layer.events.triggerEvent(
    					    	"sketchcomplete", {feature: feature}
    					    	);
    						if(proceed !== false) {
							 	feature.attributes['object_type'] = 1;
							 	feature.attributes['z_index'] = Global.constant.wirecable_segment;
							 	feature.attributes['belongs_cableline'] = Global.line.initVar;
    					   	 	feature.state = OpenLayers.State.INSERT;
    					 		this.layer.addFeatures([feature]);
    							this.featureAdded(feature);
    					 		this.events.triggerEvent("featureadded",{feature : feature});
        					}
        				}
        			}
        		}
		),
    	right: new OpenLayers.Control.DrawFeature(
        		Global.line.layers.unitLayer, OpenLayers.Handler.Point,{
    				callbacks:{
    					done: function(geometry) {
    						var points = [];
    						points[0] = geometry.clone();
    						points[1] = new OpenLayers.Geometry.Point(points[0].x+Global.constant.terminal_length, points[0].y);
    						points[2] = new OpenLayers.Geometry.Point(
    								points[1].x+Global.constant.terminal_length/2*Math.sqrt(3), points[1].y+Global.constant.terminal_length/2);
    						points[3] = new OpenLayers.Geometry.Point(points[2].x, points[2].y-Global.constant.terminal_length);
    						points[4] = points[1].clone();
    						var leftend = new OpenLayers.Geometry.LineString(points);
    						var feature = new OpenLayers.Feature.Vector(leftend);
    						feature.style = Global.style.form[11];
    						var proceed = this.layer.events.triggerEvent(
    					    	"sketchcomplete", {feature: feature}
    					    	);
    						if(proceed !== false) {
							 	feature.attributes['object_type'] = 1;
							 	feature.attributes['z_index'] = Global.constant.wirecable_segment;
							 	feature.attributes['belongs_cableline'] = Global.line.initVar;
    					   	 	feature.state = OpenLayers.State.INSERT;
    					 		this.layer.addFeatures([feature]);
    							this.featureAdded(feature);
    					 		this.events.triggerEvent("featureadded",{feature : feature});
        					}
        				}
        			}
        		}
		)
	};
		
	for(var key in Global.line.controls) {
		Global.line.map.addControl(Global.line.controls[key]);
	}

	Global.line.map.zoomToExtent(extent, true);
	
	Global.line.layers.unitLayer.events.on({
		"loadend": Global.line.method.cleanSlide
	});
}

/*加载单线图时，右侧栏隐藏已添加过的设备*/
Global.line.method.cleanSlide = function(){
	var response = setInterval(function(){
		if(Global.line.flag.slide){
			for(var i=0;i<Global.line.layers.unitLayer.features.length;i++){
				if(Global.line.layers.unitLayer.features[i].attributes['name']){
					slide.del(Global.line.layers.unitLayer.features[i].attributes['name']);
				}
			}				
			clearInterval(response);
		}
	},500);
}

/* Feature starting to move */
Global.line.method.startDrag = function (feature, pixel) {
	lastPixel = pixel;
}

/* Feature moving */
Global.line.method.doDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		Global.line.copy.features[l] = [];
		for (i in layer.selectedFeatures) {
			if(layer.selectedFeatures[i]){
				if(Global.line.flag.copy){
					Global.line.copy.features[l].push(layer.selectedFeatures[i].clone());
				}
				var res = this.map.getResolution();
				layer.selectedFeatures[i].geometry.move(
					res * (pixel.x - lastPixel.x), 
					res * (lastPixel.y - pixel.y)
				);				                                           
				layer.drawFeature(layer.selectedFeatures[i]);
				if(Global.line.flag.copy){
					layer.addFeatures(Global.line.copy.features[l]);
					for(var j=0;j<Global.line.copy.features[l].length;j++){
						Global.line.copy.features[l][j].state = OpenLayers.State.INSERT;
					}
					Global.line.copy.features[l] = [];
				}
			}
		}
	}
	lastPixel = pixel;
	if(Global.line.flag.copy){
		Global.line.flag.copy = false;
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L1']=true;
		Plugins.menu.update();
	}
	
}

/* Featrue stopped moving */
Global.line.method.endDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		for (i in layer.selectedFeatures) {
			layer.selectedFeatures[i].state = OpenLayers.State.UPDATE;
		}
	}
}

Global.line.method.drawUnit = function(name, type, sid){
	Global.line.unit.name = name;
	Global.line.unit.typeID = type;
	Global.line.unit.staticId = sid;
	switch(type){
		case "1010201" : Global.line.draw.styleType = Global.style.form[0];break;
		case "1010202" : Global.line.draw.styleType = Global.style.form[1];break;
		case "1010203" : Global.line.draw.styleType = Global.style.form[2];break;
		case "1010204" : Global.line.draw.styleType = Global.style.form[3];break;
		case "1010205" : Global.line.draw.styleType = Global.style.form[4];break;
		case "1010208" : Global.line.draw.styleType = Global.style.form[7];break;
		case "1020101" : Global.line.draw.styleType = Global.style.form[8];break;
		case "1020102" : Global.line.draw.styleType = Global.style.form[9];break;
		case "1010402"	: Global.line.draw.styleType = Global.style.form[11];break;
		case "1010403"	: Global.line.draw.styleType = Global.style.form[12];break;
	}
	if(type == "1010402" || type == "1010403"){
		Global.line.method.controlRelease();
		Global.line.draw.lineControl.activate();
	}else{		
		Global.line.method.controlRelease();
		Global.line.draw.pointControl.activate();
	}		
}

/*前台功能选择接口一*/
Global.line.method.toggleControl = function(element) {
	for(key in Global.line.controls) {
		if(element == key) {
			Global.line.controls[key].activate();
		} else {
			Global.line.controls[key].deactivate();
		}
	}
	if(element == "pan"){
		Global.line.select.control.activate();
	}else{
		Global.line.select.control.deactivate();
	}
}

Global.line.method.controlRelease = function(){
	if(Global.line.draw.pointControl.active){
		Global.line.draw.pointControl.deactivate();
	}
	if(Global.line.draw.lineControl.active){
		Global.line.draw.lineControl.deactivate();
	}
	for(key in Global.line.controls) {
		if(Global.line.controls[key].active && key != "pan"){
			Global.line.controls[key].deactivate();
			break;
		}
	}
	if(Global.line.copy.dragControl.active){
		Global.line.copy.dragControl.deactivate();	
	}
}

/*状态复位*/
Global.line.method.reset = function(){
	Global.line.method.controlRelease();
	Global.line.flag.copy = false;
}


Global.line.method.deleteFeature = function() {
	var selectedFeatures = 0;
	selectedFeatures += Global.line.select.control.layer.selectedFeatures.length;
	if(selectedFeatures>0){
    	Plugins.dialog.show({
    		'title': '警告',
    		'text': '确定删除要素？删除后数据将无法复原。'
    	},{
    		'submit' : function(e){
				console.log(Global.line.select.control.layer)
        		for(var j=0;j<Global.line.select.control.layer.selectedFeatures.length;j++){
                  	 if(Global.line.select.control.layer.selectedFeatures[j].fid == undefined) {
                    		Global.line.select.control.layer.destroyFeatures([Global.line.select.control.layer.selectedFeatures[j]]);
                      } else {
                        	 Global.line.select.control.layer.selectedFeatures[j].state = OpenLayers.State.DELETE;
                         	 Global.line.select.control.layer.events.triggerEvent("afterfeaturemodified", {feature: Global.line.select.control.layer.selectedFeatures[j]});
                         	 Global.line.select.control.layer.selectedFeatures[j].renderIntent = "select";
                         	 Global.line.select.control.layer.drawFeature(Global.line.select.control.layer.selectedFeatures[j]);
                      }
          		}
            	Plugins.dialog.remove();
            	Global.line.save.saveStrategy.save();
    		}
    	});
	}
}

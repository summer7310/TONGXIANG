/*引入外部脚本*/
var main = document.createElement('script');
main.type = 'text/javascript';
main.src = 'main.js';

Global.wire.method.init = function(initVar){
	Global.constant.currentMap = 2;
	OpenLayers.ProxyHost = "cgi/proxy.cgi?url=";
	var extent = new OpenLayers.Bounds(-20,-20,20,20);
	Global.wire.draw.pointStyleMap = Global.style.wireDiagramStyle;
	Global.wire.map = new OpenLayers.Map('map3');
	Global.wire.map.div.oncontextmenu = function () { return false;};// 屏蔽浏览器的右键菜单

	var graphic = new OpenLayers.Layer.Image(
		'Grid',
		'img/wirebg.png',
		Global.wire.constant.fullExtent,
		new OpenLayers.Size(2000, 1500),
		{
			isBaseLayer: true,
			numZoomLevels: 4
		});

	Global.wire.layers.unitLayer = new OpenLayers.Layer.Vector(
    	"设备层", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.wire.save.saveStrategy],  	
			styleMap:Global.wire.draw.pointStyleMap,
			filter: new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "belongs_container",
				value : 0
			}),
			// rendererOptions: {zIndexing: true},
    		protocol: new OpenLayers.Protocol.WFS({
        		url: "http://localhost:8081/geoserver/OpenGIS/wfs",// 需要设置
        		featureNS :  "OpenGIS.edu.zjut",// NameSapce命名空间,需要设置
        		featureType: "geo_connect_wire",// 图层名，需要设置
        		geometryName: "geometry_data"// 几何信息字段名，需要设置
    		})
    	}
    );	

	Global.wire.layers.gridLayer = new OpenLayers.Layer.Vector(
    	"栅格1",
   		{
    	styleMap: new OpenLayers.StyleMap({'default':{
        	pointRadius: 1,
        	strokeColor: "grey",
        	strokeOpacity: 0.6,
        	strokeWidth: 1.3,
        	strokeDashstyle: 'dash'     
		}}),
		} 		
	);    
	for(var i=-9;i<9;i++){
		var j=i*10;
		var lineFeature = new OpenLayers.Feature.Vector(	
                new OpenLayers.Geometry.Collection([             
                    new OpenLayers.Geometry.LineString([
                    	new OpenLayers.Geometry.Point(-90,j),
                        new OpenLayers.Geometry.Point(90,j),	                     
                    	]),
                    	new OpenLayers.Geometry.LineString([
                    	new OpenLayers.Geometry.Point(j,-90),
                        new OpenLayers.Geometry.Point(j,90),	                     
                    	])                   	
                	])           
               );
	    Global.wire.layers.gridLayer.addFeatures(lineFeature);
    }

	Global.wire.layers.dashLayer = new OpenLayers.Layer.Vector(
		"栅格2",
		{
		 	styleMap: new OpenLayers.StyleMap({'default':{
                strokeColor: "grey",
                strokeOpacity: 0.3,
                strokeWidth: 1.2,
                fillColor: "green",
                fillOpacity: 0.5,
                pointRadius: 6,
                strokeDashstyle: 'dash'
            }}),           
        }
    ); 
	for(var i=-45;i<45;i++){
		var j=i*2;
		var dashFeature = new OpenLayers.Feature.Vector(	
                new OpenLayers.Geometry.Collection([             
                    new OpenLayers.Geometry.LineString([
                    	new OpenLayers.Geometry.Point(-90,j),
                        new OpenLayers.Geometry.Point(90,j),	                     
                    	]),
                    	new OpenLayers.Geometry.LineString([
                    	new OpenLayers.Geometry.Point(j,-90),
                        new OpenLayers.Geometry.Point(j,90),	                     
                    	])                   	
                	])           
               );
	    Global.wire.layers.dashLayer.addFeatures(dashFeature);
    }

    Global.wire.layers.pointLayer = new OpenLayers.Layer.PointGrid({
        name: "Snap Grid",
        dx: 5, dy: 5, maxFeatures:1500,
        styleMap: new OpenLayers.StyleMap({
            pointRadius: 0.1,
            strokeColor: "grey",
            strokeWidth: 1,
            fillOpacity: 1,
            fillColor: "#ffffff",
            graphicName: "square"
            })
        });
    
	if(Global.wire.initVar){
		var eleFilter = new OpenLayers.Filter.Comparison({
			type : OpenLayers.Filter.Comparison.EQUAL_TO,
			property : "belongs_container",
			value: Global.wire.initVar
		});
		Global.wire.layers.unitLayer.filter = eleFilter;
	}
	Global.wire.map.addLayers([
        graphic,
        Global.wire.layers.dashLayer,
        Global.wire.layers.gridLayer,
        Global.wire.layers.unitLayer,
        Global.wire.layers.pointLayer
    ]);
    
	/* 定义要素选择 */  
    Global.wire.select.control = new OpenLayers.Control.SelectFeature(Global.wire.layers.unitLayer,{
			onRightSelect:Global.method.onFeatureRightSelect,// 添加的右键菜单
			toggle: true,
			box: true,
			boxKey: OpenLayers.Handler.MOD_SHIFT,
        	multipleKey: "ctrlKey"
	});	
  	
   	Global.wire.copy.dragControl = new OpenLayers.Control.DragFeature(Global.wire.layers.unitLayer,
   		{
			onStart: Global.wire.method.startDrag,
			onDrag: Global.wire.method.doDrag,
			onComplete: Global.wire.method.endDrag,
	});
	Global.wire.map.addControl(Global.wire.copy.dragControl);

    var snap = new OpenLayers.Control.Snapping({
        layer: Global.wire.layers.unitLayer,
        targets: [Global.wire.layers.pointLayer]
    });
	Global.wire.map.addControl(snap);	
	Global.wire.map.addControl(Global.wire.select.control);
	snap.activate();
    Global.wire.select.control.activate();

    Global.wire.controls = 
    {
    	pan: new OpenLayers.Control.Pan,
    	edit: new OpenLayers.Control.ModifyFeature(null, {
    		layers:[Global.wire.layers.unitLayer],
    		mode: OpenLayers.Control.ModifyFeature.ROTATE |	OpenLayers.Control.ModifyFeature.RESIZE
    			| OpenLayers.Control.ModifyFeature.DRAG | OpenLayers.Control.ModifyFeature.DELETE
    		}
    	),
    	gap: new OpenLayers.Control.DrawFeature(
    	     Global.wire.layers.unitLayer, OpenLayers.Handler.Path,
    	     {
    	     	callbacks:{
    	     		done:function(geometry){
    	     			var sx = geometry.components[0].x;
    	     			var ex = geometry.components[1].x;
    	     			var sy = geometry.components[0].y;
    	     			var ey = geometry.components[1].y;
    	     			var p1 = new OpenLayers.Geometry.Point(sx+(ex-sx)/3,sy+(ey-sy)/3);
    	     			var p2 = new OpenLayers.Geometry.Point(ex-(ex-sx)/3,ey-(ey-sy)/3);
    	     			var pm = geometry.components[0].clone();
    	     			pm.rotate(30,p2);
    	     			var points = new Array(
    	     					geometry.components[0],
    	     					p1,
    	     					pm,
    	     					p2,
    	     					geometry.components[1]
    	     					); 
						var line = [];
						line[0] = new OpenLayers.Geometry.LineString([points[0], points[1]]);
						line[1] = new OpenLayers.Geometry.LineString([points[2], points[3], points[4]]);
	     				feature = new OpenLayers.Feature.Vector(
	     						new OpenLayers.Geometry.MultiLineString(line));
	     				feature.renderIntent = 'default';
						var proceed = this.layer.events.triggerEvent(
					    	"sketchcomplete", {feature: feature}
					 	 );
    	     			if(proceed !== false){
    	     				feature.attributes['name'] = Global.wire.unit.name;
    				        feature.attributes['object_type'] = '1010601';
    				        feature.attributes['static_id'] = Global.wire.unit.staticId;
    				        feature.attributes['belongs_container'] = Global.wire.initVar;
    				        feature.state = OpenLayers.State.INSERT;
    	     				this.layer.addFeatures([feature]);
    	     				this.featureAdded(feature);
    	     				this.events.triggerEvent("featureadded",{feature : feature}); 
    	     			}
    	     			slide2.del(Global.wire.unit.name);// 侧栏数据隐藏
	     				Global.wire.draw.gapStart = null;
    	     		},
    	            create: function(vertex, feature) {
                		this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
    				}
    	     	}	
    	     }	           
		),
		line: new OpenLayers.Control.DrawFeature(
				Global.wire.layers.unitLayer, OpenLayers.Handler.Path,
				{
					callbacks:{
						done: function(geometry) {
							var feature = new OpenLayers.Feature.Vector(geometry);
							feature.renderIntent = 'default';
							var proceed = this.layer.events.triggerEvent(
						    	"sketchcomplete", {feature: feature}
						 	 );
							 if(proceed !== false) {
								feature.attributes['name'] = Global.wire.unit.name;
							 	feature.attributes['object_type'] = Global.wire.unit.typeID;
							 	feature.attributes['static_id'] = Global.wire.unit.staticId;
							 	feature.attributes['belongs_container'] = Global.wire.initVar;
						   	 	feature.state = OpenLayers.State.INSERT;
						 		this.layer.addFeatures([feature]);
								this.featureAdded(feature);
						 		this.events.triggerEvent("featureadded",{feature : feature});
							}
							 slide2.del(Global.wire.unit.name);// 侧栏数据隐藏
						},
			        	create: function(vertex, feature) {
							feature.style = Global.wire.draw.styleType;
		            		this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
						}
					}
				}
			),
    	polygon: new OpenLayers.Control.DrawFeature(
			Global.wire.layers.unitLayer, OpenLayers.Handler.Polygon,{
				callbacks:{
					done: function(geometry) {
						var feature = new OpenLayers.Feature.Vector(geometry);
						feature.style = Global.style.form[22];
						var proceed = this.layer.events.triggerEvent(
					    	"sketchcomplete", {feature: feature}
					 	 );
						 if(proceed !== false) {
						 	feature.attributes['object_type'] = 2;
						 	feature.attributes['belongs_container'] = Global.wire.initVar;
					   	 	feature.state = OpenLayers.State.INSERT;
					 		this.layer.addFeatures([feature]);
							this.featureAdded(feature);
					 		this.events.triggerEvent("featureadded",{feature : feature});
						}
					},
		        	create: function(vertex, feature) {
						feature.style = Global.style.form[22];
	            		this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
					}
				}
			}
		)
	};
		
	for(var key in Global.wire.controls) {
		Global.wire.map.addControl(Global.wire.controls[key]);
	} 

	Global.wire.map.zoomToExtent(extent, true);	
	
	Global.wire.layers.unitLayer.events.on({
		"loadend": Global.wire.method.cleanSlide
	});
}

/* 加载接线图时，右侧栏隐藏已添加过的设备 */
Global.wire.method.cleanSlide = function(){
	for(var i=0;i<Global.wire.layers.unitLayer.features.length;i++){
		if(Global.wire.layers.unitLayer.features[i].attributes['name']){
			slide2.del(Global.wire.layers.unitLayer.features[i].attributes['name']);
		}
	}
}

/* Feature starting to move */
Global.wire.method.startDrag = function (feature, pixel) {
	lastPixel = pixel;
}

/* Feature moving */
Global.wire.method.doDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		Global.wire.copy.features[l] = [];
		for (i in layer.selectedFeatures) {
			if(layer.selectedFeatures[i]){
				if(Global.wire.flag.copy){
					Global.wire.copy.features[l].push(layer.selectedFeatures[i].clone());
				}
				var res = this.map.getResolution();
				layer.selectedFeatures[i].geometry.move(
					res * (pixel.x - lastPixel.x), 
					res * (lastPixel.y - pixel.y)
				);				                                           
				layer.drawFeature(layer.selectedFeatures[i]);
				if(Global.wire.flag.copy){
					layer.addFeatures(Global.wire.copy.features[l]);
					for(var j=0;j<Global.wire.copy.features[l].length;j++){
						Global.wire.copy.features[l][j].state = OpenLayers.State.INSERT;
					}
					Global.wire.copy.features[l] = [];
				}
			}
		}
	}
	lastPixel = pixel;
	if(Global.wire.flag.copy){
		Global.wire.flag.copy = false;
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W1']=true;
		Plugins.menu.update();
	}
	
}

/* Featrue stopped moving */
Global.wire.method.endDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		for (i in layer.selectedFeatures) {
			layer.selectedFeatures[i].state = OpenLayers.State.UPDATE;
		}
	}
}

Global.wire.method.drawUnit = function(name, type, sid){
	Global.wire.unit.name = name;
	Global.wire.unit.typeID = type;
	Global.wire.unit.staticId = sid;
	switch(type){
	case "1" : 
		Global.wire.draw.styleType = Global.style.form[21];
		Global.wire.method.toggleControl('line');
		break;
	case "2" : 
		Global.wire.draw.styleType = Global.style.form[22];
		Global.wire.method.toggleControl('polygon');
		break;
	case "3" : 
		Global.wire.draw.styleType = Global.style.form[24];
		Global.wire.method.toggleControl('line');
		break;
	case "4" : 
		Global.wire.draw.styleType = Global.style.form[25];
		Global.wire.method.toggleControl('line');
		break;
	case "1010601" : 
		Global.wire.draw.styleType = Global.style.form[23];
		Global.wire.method.toggleControl('gap');
		break;
	case "1010402" : 
	case "1010403" : 
		Global.wire.draw.styleType = Global.style.form[20];
		Global.wire.method.toggleControl('line');
		break;
	}		
}

/* 前台功能选择接口一 */
Global.wire.method.toggleControl = function(element) {
	for(key in Global.wire.controls) {
		if(element == key) {
			Global.wire.controls[key].activate();
		} else {
			Global.wire.controls[key].deactivate();
		}
	}
	if(element == "pan"){
		Global.wire.select.control.activate();
	}else{
		Global.wire.select.control.deactivate();
	}
}

Global.wire.method.controlRelease = function(){
	for(key in Global.wire.controls) {
		if(Global.wire.controls[key].active && key != "pan"){
			Global.wire.controls[key].deactivate();
			break;
		}
	}
	if(Global.wire.copy.dragControl.active){
		Global.wire.copy.dragControl.deactivate();	
	}
}

/* 状态复位 */
Global.wire.method.reset = function(){
	Global.wire.method.controlRelease();
	Global.wire.flag.copy = false;
}


Global.wire.method.deleteFeature = function() {
	var selectedFeatures = 0;
	selectedFeatures += Global.wire.select.control.layer.selectedFeatures.length;
	if(selectedFeatures>0){
    	Plugins.dialog.show({
    		'title': '警告',
    		'text': '确定删除要素？删除后数据将无法复原。'
    	},{
    		'submit' : function(e){
				console.log(Global.wire.select.control.layer)
        		for(var j=0;j<Global.wire.select.control.layer.selectedFeatures.length;j++){
                  	 if(Global.wire.select.control.layer.selectedFeatures[j].fid == undefined) {
                    		Global.wire.select.control.layer.destroyFeatures([Global.wire.select.control.layer.selectedFeatures[j]]);
                      } else {
                        	 Global.wire.select.control.layer.selectedFeatures[j].state = OpenLayers.State.DELETE;
                         	 Global.wire.select.control.layer.events.triggerEvent("afterfeaturemodified", {feature: Global.wire.select.control.layer.selectedFeatures[j]});
                         	 Global.wire.select.control.layer.selectedFeatures[j].renderIntent = "select";
                         	 Global.wire.select.control.layer.drawFeature(Global.wire.select.control.layer.selectedFeatures[j]);
                      }
          		}
            	Plugins.dialog.remove();
            	Global.wire.save.saveStrategy.save();
    		}
    	});
	}
}
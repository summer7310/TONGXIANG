/*引入外部脚本*/
var main = document.createElement('script');
main.type = 'text/javascript';
main.src = 'main.js';	

/*地图初始化*/
Global.locus.method.init = function(){
	Global.constant.currentMap = 0;
	OpenLayers.ProxyHost = "cgi/proxy.cgi?url=";//代理设置
	var extent = new OpenLayers.Bounds(//初始化视图的边界设置,参照conf.cdi文件中的值
		120.50490375617102, 30.588471912865483,
		120.5859733795086,	30.685245907613851
	);
	var options = { 
			resolutions: [1.3248838880462998E-4, 4.568565131194137E-5, 2.2842825655970686E-5, 1.1421412827985343E-5, 2.2842825655970685E-6, 1.1421412827985342E-6, 5.710706413992671E-7],
			projection: new OpenLayers.Projection('EPSG:4326'),
			maxExtent: new OpenLayers.Bounds(-400.0,30.575736099832397,120.59245561913997,400.0),
			units: "degrees",
			numZoomLevels: 5,
			controls: []
	};
	var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
	renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
	Global.locus.map = new OpenLayers.Map('map', options);
	Global.locus.map.div.oncontextmenu = function () { return false;};//屏蔽浏览器的右键菜单 
	var drawArr = ['变电站','开关站','变压器','环网柜','分支箱',
	                '分支接头','盘余','中间接头','工井','杆塔','虚拟工井'];
	Global.locus.draw.number = drawArr.length;
	var visibleArr = ['变电站','开关站','变压器','环网柜','分支箱',
	                '分支接头','盘余','中间接头','工井','杆塔',
	                '虚拟工井','电缆','导线','虚拟管沟','排管',
	                '桥架','沟道','直埋','隧道','顶管'];
	Global.locus.constant.visible = visibleArr.length;
	/*地图要素的选择显示*/	
	for(var i=0;i<Global.locus.constant.visible;i++){
		Global.locus.draw.scale[i]=Global.style.buildingStyle_nolabel.styles["default"].rules[i].maxScaleDenominator;	
	}	
    var theHTML = '';
    for(var i=0;i<Global.locus.constant.visible;i++){
    	theHTML += '<li><input id="fVis' + i + '" type="checkbox" checked onclick="Global.locus.method.changeFeatureVisibility(' + i + ',this.checked)"><label>' + visibleArr[i] + '</label></li>';
    }        
	theHTML = '<ul class="layout_ul">' + theHTML + '</ul>';
    document.getElementById("layout_list").innerHTML = theHTML;
    Global.locus.layers.baseLayer = new OpenLayers.Layer.WMS(
            "桐乡地图", "http://"+Global.option.ip+":8081/geoserver/gwc/service/wms",
            {
            	layers: 'TONGXIANG',
                format: 'image/png'
        	},{
        		tileSize: new OpenLayers.Size(256,256),
                tileOrigin : new OpenLayers.LonLat(-400.0,400.0)
            }
    );        

    Global.locus.layers.electricalLayer = new OpenLayers.Layer.Vector(
    	"电气设备", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[0]],
    		projection: new OpenLayers.Projection("EPSG:4326"),
			styleMap: Global.style.buildingStyle_nolabel,
			rendererOptions: {zIndexing: true},
    		protocol: new OpenLayers.Protocol.WFS({
    		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
    		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
    		featureType: "geo_electrical",//图层名，需要设置
    		geometryName: "geometry_data"//几何信息字段名，需要设置
    		})
    	}
    );
    
    Global.locus.layers.buildingLayer = new OpenLayers.Layer.Vector(
        	"土建设备", 
        	{
        		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[1]],
        		projection: new OpenLayers.Projection("EPSG:4326"),
    			styleMap: Global.style.buildingStyle_nolabel,
    			rendererOptions: {zIndexing: true},
        		protocol: new OpenLayers.Protocol.WFS({
        		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
        		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
        		featureType: "geo_civil_engineering",//图层名，需要设置
        		geometryName: "geometry_data"//几何信息字段名，需要设置
        		})
        	}
        );
    
    Global.locus.layers.elevationLayer.surveyLayer = new OpenLayers.Layer.Vector(
    	"土建测绘", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[2]],
    		projection: new OpenLayers.Projection("EPSG:4326"),
    		styleMap: Global.style.surveyStyle,
			filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "well_id",
				value: 0
			}),   		    		 		
    		protocol: new OpenLayers.Protocol.WFS({
    		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
    		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
    		featureType: "geo_point_survery",//图层名，需要设置
    		geometryName: "geometry_data"//几何信息字段名，需要设置
    		})
    	}
    );
    
    Global.locus.layers.elevationLayer.profileLayer = new OpenLayers.Layer.Vector(
    	"剖面", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[3]],
    		projection: new OpenLayers.Projection("EPSG:4326"),     		  		
    		styleMap: Global.style.profileStyle,
			filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "well_id",
				value: 0
			}),     		                                         		
    		protocol: new OpenLayers.Protocol.WFS({
        		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
        		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
        		featureType: "geo_pipe_profile",//图层名，需要设置
        		geometryName: "geometry_data"//几何信息字段名，需要设置
    		})
    	}
    );   
    
    Global.locus.layers.elevationLayer.pipe_propertyLayer = new OpenLayers.Layer.Vector(
    	"剖面属性", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[4]],
    		projection: new OpenLayers.Projection("EPSG:4326"), 
    		styleMap: Global.style.pipe_propertyStyle, 
			filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "well_id",
				value: 0
			}), 			                   		
    		protocol: new OpenLayers.Protocol.WFS({
    		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
    		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
    		featureType: "geo_pipe_hole",//图层名，需要设置
    		geometryName: "geometry_data"//几何信息字段名，需要设置
    		})
    	}
    );
    
    Global.locus.layers.elevationLayer.cableLayer = new OpenLayers.Layer.Vector(
    	"剖面电缆", 
    	{
    		strategies: [new OpenLayers.Strategy.BBOX(), Global.locus.save.saveStrategy[5]],
    		projection: new OpenLayers.Projection("EPSG:4326"),
    		styleMap: Global.style.cableStyle,
			filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "well_id",
				value: 0
			}),     		                		
    		protocol: new OpenLayers.Protocol.WFS({
    		url: "http://"+Global.option.ip+":8081/geoserver/OpenGIS/wfs",//需要设置
    		featureNS :  "OpenGIS.edu.zjut",//NameSapce命名空间,需要设置
    		featureType: "geo_cable",//图层名，需要设置
    		geometryName: "geometry_data"//几何信息字段名，需要设置
    		})
		}
	);
    
    Global.locus.layers.tempLayer = new OpenLayers.Layer.Vector("tempLayer", {
		styleMap: Global.style.tempStyle,
		renderers: renderer,
		displayInLayerSwitcher: false
	});  

	Global.locus.map.addLayers([
		Global.locus.layers.baseLayer, 		
		Global.locus.layers.elevationLayer.surveyLayer, 
		Global.locus.layers.elevationLayer.profileLayer, 
		Global.locus.layers.elevationLayer.pipe_propertyLayer, 
		Global.locus.layers.elevationLayer.cableLayer, 
		Global.locus.layers.tempLayer, 
		Global.locus.layers.buildingLayer,
		Global.locus.layers.electricalLayer
	]);
	
	/*电气设备添加按钮*/  
	for(var i=0;i<Global.constant.electricalNum;i++){
    	Global.locus.draw.controls[i] = new OpenLayers.Control.DrawFeature(
	        Global.locus.layers.electricalLayer, OpenLayers.Handler.Point,
	        {
	            callbacks:{
					done: function(geometry) {
				        var feature = new OpenLayers.Feature.Vector(geometry);	
				        feature.attributes['object_type'] = Global.locus.draw.unitType;
				        feature.attributes['z_index'] = Global.constant.unit;//设置电气层不同要素的叠放等级
				        feature.attributes['x_offset'] = 0;//设置默认注记偏移值
				        feature.attributes['y_offset'] = -15;
				        feature.renderIntent = 'default';
				        var proceed = this.layer.events.triggerEvent(
					            "sketchcomplete", {feature: feature}
					        );
				        if(proceed !== false) {
				            feature.state = OpenLayers.State.INSERT;
				            this.layer.addFeatures([feature]);
				            this.featureAdded(feature);
				            this.events.triggerEvent("featureadded",{feature : feature});
				        }
						Plugins.dialog.show({
							title	: '属性', 
							url		: Global.option.URL+'/getProperty.action',
							obj		: feature, 
							param	: {
								type	: feature.attributes['object_type']
							}
						}, {
							'success' : function(){
					        	var projectTo = new OpenLayers.Projection("EPSG:4326"); //WGS 1984 projection
					            var projectSource = Global.locus.map.getProjectionObject(); //The map projection
					            var featureClone = feature.clone();
						        featureClone.geometry.transform(projectSource, projectTo);
						        Plugins.dialog.set('#lon', featureClone.geometry.x);
						        Plugins.dialog.set('#lat', featureClone.geometry.y);
							},
							'submit' : function(e){
								if($('#feature_name').val()==""){							
									Plugins.dialog.warm(e, '名称不能为空，请填写后重新提交' ,{});	
								}else{
									Base.request.insert_pro(e, 0);
								}
							},
							'cancel' : function(e){
								e['obj'].destroy();
							}
						});
					},          
	            	create: function(vertex, feature) {
						feature.style = Global.locus.draw.styleType;
            			this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
					}
				}
			}
	    );  	     
	}
	
	/*土建设备添加按钮*/
	for(var i=Global.constant.electricalNum;i<Global.locus.draw.number;i++){
    	Global.locus.draw.controls[i] = new OpenLayers.Control.DrawFeature(
	        Global.locus.layers.buildingLayer, OpenLayers.Handler.Point,
	        {
	            callbacks:{
					done: function(geometry) {
				        var feature = new OpenLayers.Feature.Vector(geometry);	
				        feature.renderIntent = 'default';
				        feature.attributes['object_type'] = Global.locus.draw.unitType;
				        feature.attributes['z_index'] = Global.constant.unit;
				        feature.attributes['x_offset'] = 0;
				        feature.attributes['y_offset'] = 15;
				        var proceed = this.layer.events.triggerEvent(
					            "sketchcomplete", {feature: feature}
					        );
				        if(proceed !== false) {
				            feature.state = OpenLayers.State.INSERT;
				            this.layer.addFeatures([feature]);
				            this.featureAdded(feature);
				            this.events.triggerEvent("featureadded",{feature : feature});
				        }
						Plugins.dialog.show({
							title	: '属性', 
							url		: Global.option.URL+'/getProperty.action',
							obj		: feature, 
							param	: {
								type	: feature.attributes['object_type']
							}
						}, {
							'success' : function(){
					        	var projectTo = new OpenLayers.Projection("EPSG:4326"); //WGS 1984 projection
					            var projectSource = Global.locus.map.getProjectionObject(); //The map projection
					            var featureClone = feature.clone();
						        featureClone.geometry.transform(projectSource, projectTo);
						        Plugins.dialog.set('#lon', featureClone.geometry.x);
						        Plugins.dialog.set('#lat', featureClone.geometry.y);
							},
							'submit' : function(e){
								if($('#feature_name').val()==""){							
									Plugins.dialog.warm(e, '名称不能为空，请填写后重新提交' ,{});	
								}else{
									Base.request.insert_pro(e, 1);
								}
							},
							'cancel' : function(e){
								e['obj'].destroy();
							}
						});
					},          
	            	create: function(vertex, feature) {
						feature.style = Global.locus.draw.styleType;
            			this.layer.events.triggerEvent("sketchstarted", {vertex:vertex,feature:feature})
					}
				}
			}
	    );  	     
	}
	

	
	/*定义要素选择按钮*/  
    Global.locus.select.control = new OpenLayers.Control.SelectFeature(
    	[
 			Global.locus.layers.elevationLayer.surveyLayer,
 			Global.locus.layers.electricalLayer,
    		Global.locus.layers.buildingLayer,
    		Global.locus.layers.elevationLayer.profileLayer,
    		Global.locus.layers.elevationLayer.pipe_propertyLayer,
    		Global.locus.layers.elevationLayer.cableLayer
    	],
    	{
			onRightSelect:Global.method.onFeatureRightSelect,//添加的右键菜单
			toggle: true,
			box: true,//框选
			boxKey: OpenLayers.Handler.MOD_SHIFT,//框选启动键
            multipleKey: "ctrlKey"//多要素
		}
	);

   /*定义要素拖动按钮*/ 
   Global.locus.copy.dragControl = new OpenLayers.Control.DragFeature(
		   [
		    Global.locus.layers.electricalLayer,                                                              	 
    		Global.locus.layers.buildingLayer,
    		Global.locus.layers.elevationLayer.surveyLayer 		
        	], {
				onStart: Global.locus.method.startDrag,
				onDrag: Global.locus.method.doDrag,
				onComplete: Global.locus.method.endDrag,
			}
   );
   Global.locus.map.addControl(Global.locus.copy.dragControl);
   
   /*定义右下角坐标显示按钮*/
   Global.locus.mousePositionCtl.control = new OpenLayers.Control.MousePosition({
       prefix: '<a target="_blank" ' +
           'href="http://spatialreference.org/ref/epsg/4326/">' +
           'EPSG:4326</a> coordinates: ',
       separator: ' | ',
       numDigits: 10,
       emptyString: 'Mouse is not over map.'
       }
   );
   Global.locus.map.addControl( Global.locus.mousePositionCtl.control);
   Global.locus.mousePositionCtl.control.activate();

   /*定义键盘侦听*/
   var keyboard = new OpenLayers.Control();
   var keyHandler = new OpenLayers.Handler.Keyboard(keyboard, {
   	keydown: function(evt) {
       	var handled = true;
           switch(evt.keyCode) {
           case 16://shift
           	Global.locus.flag.shift = true;
           	break;
           case 27://esc
           	switch(Global.constant.currentMap){
           	case 0:
        		Plugins.menu.refresh();
        		Plugins.FLAG.MAP['M1']=true;
        		Plugins.menu.update();
               	Global.locus.method.reset();
               	Global.locus.method.toggleControl("pan");
               	break;
           	case 1:
        		Plugins.menu.refresh();
        		Plugins.FLAG.LINE['L1']=true;
        		Plugins.menu.update();
               	Global.line.method.reset();
               	Global.line.method.toggleControl("pan");
               	break;
           	case 2:
        		Plugins.menu.refresh();
        		Plugins.FLAG.WIRE['W1']=true;
        		Plugins.menu.update();
               	Global.wire.method.reset();
               	Global.wire.method.toggleControl("pan");
               	break;
           	}
           	break;
           case 46://delete
           	switch(Global.constant.currentMap){//区分不同选项卡
           	case 0:
           		Global.locus.method.deleteFeature();
               	break;
        	case 1:
        		Global.line.method.deleteFeature();
        		break;
        	case 2:
        		Global.wire.method.deleteFeature();
        		break;
               	/*
           	case 1:
           	case 2:
	            	Plugins.dialog.show({
	            		'title': '错误信息',
	            		'text': '请从轨迹图或者树目录中进行删除！'
	            	},{
	            		'submit' : function(e){
	            			Plugins.dialog.remove();
	            		}
	            	});          		
           		break;
           		*/
           	}
           	break;
           default:
               handled = false;
           }
           if (handled) {
               OpenLayers.Event.stop(evt);
           }
       },
   	keyup: function(evt) {
       	var handled = true;
           switch(evt.keyCode) {
           case 16:
           	Global.locus.flag.shift = false;
           	break;
           default:
               handled = false;
           }
           if (handled) {
               OpenLayers.Event.stop(evt);
           }
       }
   });
   keyHandler.activate();
   Global.locus.map.addControl(keyboard);
   
   /*定义捕捉(吸附)按钮*/
   var snap = new OpenLayers.Control.Snapping({layer: Global.locus.layers.electricalLayer,
	   targets: [Global.locus.layers.buildingLayer]});
   Global.locus.map.addControl(snap);     
   snap.activate(); 
   
   Global.locus.map.addControl(new OpenLayers.Control.PanZoomBar({
	   position: new OpenLayers.Pixel(2, 15)
   }));
   var navigation = new OpenLayers.Control.Navigation();
   navigation.zoomBoxKeyMask = OpenLayers.Handler.MOD_ALT;
   Global.locus.map.addControl(navigation);
   Global.locus.map.addControl(new OpenLayers.Control.ScaleLine());
   Global.locus.map.addControl(new OpenLayers.Control.LayerSwitcher());
   Global.locus.map.addControl(new OpenLayers.Control.OverviewMap());
   Global.locus.map.addControl(Global.locus.select.control);
   Global.locus.select.control.activate();
   
   /*框选、点选事件侦听器*/
   Global.locus.select.control.events.on({
	   "boxselectionend": Global.locus.method.box_selected
   });
   Global.locus.layers.electricalLayer.events.on({
		"featureselected": Global.locus.method.electrical_select,
		"featureunselected": Global.locus.method.electrical_unselect
   });
   Global.locus.layers.buildingLayer.events.on({
		"featureselected": Global.locus.method.building_select,
		"featureunselected": Global.locus.method.building_unselect
   });	
	Global.locus.layers.elevationLayer.profileLayer.events.on({
		"featureselected": Global.locus.method.profile_select,
		"featureunselected": Global.locus.method.profile_unselect
	});
	Global.locus.layers.elevationLayer.pipe_propertyLayer.events.on({
		"featureselected": Global.locus.method.pipe_property_select,
		"featureunselected": Global.locus.method.pipe_property_unselect
	});  
	Global.locus.layers.elevationLayer.cableLayer.events.on({
		"featureselected": Global.locus.method.cable_select,
		"featureunselected": Global.locus.method.cable_unselect
	});  	    
	
    Global.locus.controls = {
    	pan: new OpenLayers.Control.Pan,//漫游按钮
    	edit: new OpenLayers.Control.ModifyFeature(null, {//编辑按钮
    		layers:[
				Global.locus.layers.electricalLayer, 
    			Global.locus.layers.buildingLayer, 
    			Global.locus.layers.elevationLayer.surveyLayer
    		],
    		mode: OpenLayers.Control.ModifyFeature.ROTATE |	OpenLayers.Control.ModifyFeature.RESIZE//编辑模式设置：可旋转、改变大小、移动、删除
    			| OpenLayers.Control.ModifyFeature.DRAG | OpenLayers.Control.ModifyFeature.DELETE,
    		onModification: Global.locus.method.modifiHandler,//编辑事件侦听(未完成)
    		onModificationEnd: Global.locus.method.modifiEndHandler
    		}  		
    	),
    	cable: new OpenLayers.Control.DrawFeature(//电缆穿管按钮
    			Global.locus.layers.elevationLayer.profileLayer, OpenLayers.Handler.Point,{
    			callbacks:{
    				done: function(geometry){
    					Global.locus.draw.cable.geometry = new OpenLayers.Geometry.Point(geometry.x,geometry.y);
    					for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
    						if(Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['static_id']){
    							if(Global.locus.layers.elevationLayer.profileLayer.features[i].geometry.components[0].containsPoint(geometry)){
        							var Ppfeature, feature = Global.locus.layers.elevationLayer.profileLayer.features[i];
        							for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
        								if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] ==
        									feature.attributes['pp_segment_id']){
        									Ppfeature = Global.locus.layers.buildingLayer.features[i];
        									break;
        								}
        							}
        							if(Ppfeature){
        							    OpenLayers.Request.GET({
        							        url: "getPropertyJson.action?type="+Ppfeature.attributes['object_type']
        							        	+"&staticId="+Ppfeature.attributes['static_id'],
        							        success: ppSegmentProperty_success,
        							        failure: (function(e){
        							        	Plugins.dialog.show({
        							        		'title': '错误信息',
        							        		'text': e.responseText
        							        	},{
        							        		'submit' : function(e){
        							        			Plugins.dialog.remove();
        							        		}
        							        	});
        							        	}
        							        )
        							    });
        							}else{
        				            	Plugins.dialog.show({
        				            		'title': '错误信息',
        				            		'text': '请检查剖面所属管沟是否存在或者被隐藏!'
        				            	},{
        				            		'submit' : function(e){
        				            			Plugins.dialog.remove();
        				            		}
        				            	});
        							}
        						    function ppSegmentProperty_success(req){
        						    	var format = new OpenLayers.Format.JSON();
        						    	var response = format.read(req.responseXML || req.responseText);
        						    	var x_line = new OpenLayers.Geometry.LineString(
        						    			[feature.geometry.components[0].components[0].components[0],
        						    			 feature.geometry.components[0].components[0].components[1]]);
        						    	var y_line = new OpenLayers.Geometry.LineString(
        						    			[feature.geometry.components[0].components[0].components[3],
        						    			 feature.geometry.components[0].components[0].components[4]]);
        						    	var x_length = Global.locus.draw.cable.geometry.distanceTo(y_line);
        						    	var y_length = Global.locus.draw.cable.geometry.distanceTo(x_line);
        						    	var high = feature.geometry.components[0].components[0].components[1].distanceTo(
        						    			feature.geometry.components[0].components[0].components[2]);
        						    	var featureCopy;//镜像剖面
        							    for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
        							    	if(Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['static_id'] ==
        							    		feature.attributes['static_id']){
        							    		var reference = feature.geometry.components[0].components[0].components[0];
        							    		var geometry = Global.locus.layers.elevationLayer.profileLayer.features[i].geometry.components[0].components[0].components[0];
        							    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
        							    			featureCopy = Global.locus.layers.elevationLayer.profileLayer.features[i];
        							    		}
        							    	}
        							    }
        							    if(featureCopy){
        							    	var start = feature.geometry.components[0].components[0].components[0];
        							    	var end = featureCopy.geometry.components[0].components[0].components[0];
        							    	var direction = 
        							    		Math.atan((high-y_length)/(x_length)) + 
        							    		Math.atan((end.y-start.y)/(end.x-start.x));
        							    	var compare = start;
        							    	var mirrorCompare = end;
        							    	var changedFlag = false;
        							    	var checkedPass = true;
        							        if(response['startBuilding'] == feature.attributes['well_id']){
        							        }else{
        							        	if(response['endBuilding'] == feature.attributes['well_id']){
        							        		compare = end;
        							        		mirrorCompare = start;
        							    	    	changedFlag = true;
        							        	}else{
        	        				            	Plugins.dialog.show({
        	        				            		'title': '错误信息',
        	        				            		'text': '剖面所属管沟未经过剖面所属工井，请检查数据正确性！'
        	        				            	},{
        	        				            		'submit' : function(e){
        	        				            			Plugins.dialog.remove();
        	        				            		}
        	        				            	});
        							        		checkedPass = false;
        							        	}
        							        }
        							        if(checkedPass){
        							    		var length = Math.sqrt((x_length)*(x_length)+
        							    				(high-y_length)*(high-y_length));
        							    		var center = new OpenLayers.Geometry.Point();
        							    		var mirrorCenter = new OpenLayers.Geometry.Point();
        							    		var wellId, mirrorWellId;
        							    		if(changedFlag){
        							    			center.x = compare.x - length*Math.cos(direction);
        							    			center.y = compare.y - length*Math.sin(direction);
        							    			mirrorCenter.x = Global.locus.draw.cable.geometry.x;
        							    			mirrorCenter.y = Global.locus.draw.cable.geometry.y;
        							    			wellId = response['endBuilding'];
        							    			mirrorWellId = response['startBuilding'];
        							    		}else{
        							    			center.x = Global.locus.draw.cable.geometry.x;
        							    			center.y = Global.locus.draw.cable.geometry.y;
        							        		mirrorCenter.x = mirrorCompare.x - length*Math.cos(direction);
        							        		mirrorCenter.y = mirrorCompare.y - length*Math.sin(direction);
        							        		wellId = response['startBuilding'];
        							        		mirrorWellId = response['endBuilding'];
        							    		}
        							    		Plugins.dialog.show({
        			    							title: '选择电缆', 
        			    							url: Global.option.URL+'/getProOfWireCables.action',
        			    							width: 500,
        			    							feature: feature,
        			    							center: center,
        			    							centerCopy: mirrorCenter,
        			    							wellId: wellId,
        			    							mirrorWellId: mirrorWellId,
        			    							param	: {
        			    								PPSId	: feature.attributes['pp_segment_id']
        			    							}
        			    						}, {
        			    							'submit' : function(e){
        			    								var selectedCable = $('#cable').val();
        			    								var style,cableRadius;
        		    									var cableDiameter = $('#cableDiameter').val();
        		    									cableRadius = cableDiameter/2;
        		    									var voltage = $('input[name="'+selectedCable+'"]').val();
        		    									switch(voltage){
        		    									case "0" :
        		    										style = {externalGraphic:"img/holes/communicate.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "1" : 
        		    										style = {externalGraphic:"img/holes/hole04.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "2" : 
        		    										style = {externalGraphic:"img/holes/hole10.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "3" : 
        		    										style = {externalGraphic:"img/holes/hole35.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "4" : 
        		    										style = {externalGraphic:"img/holes/hole110.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "5" : 
        		    										style = {externalGraphic:"img/holes/hole220.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "6" : 
        		    										style = {externalGraphic:"img/holes/hole330.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "7" : 
        		    										style = {externalGraphic:"img/holes/hole500.svg",pointRadius: cableRadius};
        		    										break;
        		    									case "8" : 
        		    										style = {externalGraphic:"img/holes/hole750.svg",pointRadius: cableRadius};
        		    										break;
        		    									default:
        		    										style = null;								
        		    									}
        		    									var attributes = {
        		    											'cable_id': selectedCable,
        		    											'voltage_grade': parseInt(voltage),
        		    											'cable_diameter': cableDiameter,
        		    											'pp_segment_id': e['feature'].attributes['pp_segment_id']
        		    									}
        		    									if(style){
        		    										var cp = new OpenLayers.Feature.Vector(e['center'], attributes);
        		    										cp.renderIntent = 'default';
        		    										cp.attributes['well_id'] = e['wellId'];
        		    										var cpCopy = new OpenLayers.Feature.Vector(e['centerCopy'], attributes);
        		    										cpCopy.renderIntent = 'default';
        		    										cpCopy.attributes['well_id'] = e['mirrorWellId'];
        		    										cp.state = OpenLayers.State.INSERT;
        		    										cpCopy.state = OpenLayers.State.INSERT;
        		    										Global.locus.layers.elevationLayer.cableLayer.addFeatures([cp,cpCopy]);
        		    							    		Global.locus.constant.saveEventsCount = 1;
        		    							    		Global.locus.save.saveStrategy[5].save([cp,cpCopy]);
        		    					    				var response = setInterval(function(){
        		    					    					if(Global.locus.save.state['剖面电缆'] == 'success'){
        		    					    	    				$.post(Global.option.URL + 'updatePro.action?SId=' + e['feature'].attributes['static_id']
        		    					    	    					+ '&wcSeg=' + selectedCable);
        		    												Global.locus.flag.matchCableFlag = false;
        		    									    		Plugins.menu.refresh();
        		    									    		Plugins.FLAG.MAP['M1']=true;
        		    									    		Plugins.menu.update();
        		    												Plugins.dialog.remove();
        		    					    						clearInterval(response);
        		    					    						Global.locus.method.reset();
        		    					    					}
        		    					    					if(Global.locus.save.state['剖面电缆'] == 'fail'){
        		    					    						clearInterval(response);
        		    					    						Global.locus.method.reset();
        		    					    					}
        		    					    				},500);
        		    									}
        			    							}
        			    					    });	
        							        }	
        							    }else{
	        				            	Plugins.dialog.show({
	        				            		'title': '错误信息',
	        				            		'text': '请确认管孔所属剖面是否存在镜像剖面或者被隐藏！'
	        				            	},{
	        				            		'submit' : function(e){
	        				            			Plugins.dialog.remove();
	        				            		}
	        				            	});
        							    }  
        						    }
        							break;
        						}	
    						}    						
    					}
    				}
    			}
    		}),
		polygon: new OpenLayers.Control.DrawFeature(//测绘层工井俯视图多边形外框
			Global.locus.layers.elevationLayer.surveyLayer, OpenLayers.Handler.Polygon,{
				callbacks:{
					done: function(geometry) {
						var feature = new OpenLayers.Feature.Vector(geometry);				       
						feature.renderIntent = 'default';
						var proceed = this.layer.events.triggerEvent(
					    	"sketchcomplete", {feature: feature}
					 	 );
						if(proceed !== false) {
							feature.attributes['well_id'] = Global.locus.draw.surveyWellId;
							feature.attributes['object_type'] = '1030110';
					   	 	feature.state = OpenLayers.State.INSERT;
					 		this.layer.addFeatures([feature]);
							this.featureAdded(feature);
					 		this.events.triggerEvent("featureadded",{feature : feature});
						}
//						Global.locus.constant.saveEventsCount = 1;
//			    		Global.locus.save.saveStrategy[2].save(feature);
					}
				}
			}
		)	
	};
    
	for(var key in Global.locus.controls) {
		Global.locus.map.addControl(Global.locus.controls[key]);
	}
	for(var i=0;i<Global.locus.draw.number;i++){
		Global.locus.map.addControl(Global.locus.draw.controls[i]);
	}

    Global.locus.map.zoomToExtent(extent, true);//缩放至设定的边界
}

/* 删除功能函数 */
Global.locus.method.deleteFeature = function() {
	var selectedFeatures = 0;
	for(var i=0;i<Global.locus.select.control.layers.length;i++){
		selectedFeatures += Global.locus.select.control.layers[i].selectedFeatures.length;
	}
	if(selectedFeatures>0){
    	Plugins.dialog.show({
    		'title': '警告',
    		'text': '确定删除要素？删除后数据将无法复原。'
    	},{
    		'submit' : function(e){
    			function deleteSelected(i){
            		for(var j=0;j<Global.locus.select.control.layers[i].selectedFeatures.length;j++){
                      	 if(Global.locus.select.control.layers[i].selectedFeatures[j].fid == undefined) {
                        		Global.locus.select.control.layers[i].destroyFeatures([Global.locus.select.control.layers[i].selectedFeatures[j]]);
                          } else {
                            	 Global.locus.select.control.layers[i].selectedFeatures[j].state = OpenLayers.State.DELETE;
                             	 Global.locus.select.control.layers[i].events.triggerEvent("afterfeaturemodified", {feature: Global.locus.select.control.layers[i].selectedFeatures[j]});
                             	 Global.locus.select.control.layers[i].selectedFeatures[j].renderIntent = "select";
                             	 Global.locus.select.control.layers[i].drawFeature(Global.locus.select.control.layers[i].selectedFeatures[j]);
                          }
              		}
    			}
            	for(var i=0;i<Global.locus.select.control.layers.length;i++){
            		switch(Global.locus.select.control.layers[i].name){
            		case '剖面' :
            			var originLength = Global.locus.select.control.layers[i].selectedFeatures.length;
            			if(originLength>0){
        					var ppIds = [];
        					var index = 0;     			
                			for(var j=0;j<originLength;j++){
                				if(Global.locus.select.control.layers[i].selectedFeatures[j].attributes['static_id']){
                					ppIds[index] = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['pp_segment_id'];
                					index++;
                				}            				
                			}                			
                			Global.constant.profileIndex = i;
                		    OpenLayers.Request.POST({
                		        url: "deleteHole.action?type=profile&holesId="+ppIds.toString(),
                		        success: profileStatus_success,
                		        failure: (function(e){
                		        	Plugins.dialog.show({
                		        		'title': '错误信息',
                		        		'text': e.responseText
                		        	},{
                		        		'submit' : function(e){
                		        			Plugins.dialog.remove();
                		        		}
                		        	});
                		        	}
                		        )
                		    });
                		    function profileStatus_success(req){
                		    	if(req.responseText == 'pole'){
                   		        	Plugins.dialog.show({
                		        		'title': '错误信息',
                		        		'text': '剖面内部包含管孔、支架或电缆，请先删除内部元素！'
                		        	},{
                		        		'submit' : function(e){
                		        			Plugins.dialog.remove();
                		        		}
                		        	});
                		    	}else{
                		    		var i = Global.constant.profileIndex;
                        			var originLength = Global.locus.select.control.layers[i].selectedFeatures.length;
                        			for(var j=0;j<originLength;j++){
                        				var ppid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['pp_segment_id'];
                        				for(var k=0;k<Global.locus.layers.elevationLayer.profileLayer.features.length;k++){
                        					if(Global.locus.layers.elevationLayer.profileLayer.features[k].attributes['pp_segment_id'] == ppid){
                        						if(Global.locus.layers.elevationLayer.profileLayer.features[k].renderIntent == 'default'){
                        							Global.locus.select.control.layers[i].selectedFeatures.push(Global.locus.layers.elevationLayer.profileLayer.features[k]);
                        							Global.locus.layers.elevationLayer.profileLayer.features[k].renderIntent = 'select';
                        						}
                        					}
                        				}
                        			}
                        			deleteSelected(i);
                        			Global.locus.save.saveStrategy[i].save();
                		    	}
                		    }	
            			}
            			break;
            		case '剖面电缆':
            			var originLength = Global.locus.select.control.layers[i].selectedFeatures.length;
            			if(originLength>0){
                			var holeIds = [];           			
                			for(var j=0;j<originLength;j++){
                				holeIds[j] = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['hole_id'];
                				var sid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['cable_id'];
                				var hid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['hole_id'];
                				var ppid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['pp_segment_id'];
                				for(var k=0;k<Global.locus.layers.elevationLayer.cableLayer.features.length;k++){
                					if(Global.locus.layers.elevationLayer.cableLayer.features[k].attributes['cable_id'] == sid &&
                							Global.locus.layers.elevationLayer.cableLayer.features[k].attributes['hole_id'] == hid &&
                							Global.locus.layers.elevationLayer.cableLayer.features[k].attributes['pp_segment_id'] == ppid){
                						if(Global.locus.layers.elevationLayer.cableLayer.features[k].renderIntent == 'default'){
                							Global.locus.select.control.layers[i].selectedFeatures.push(Global.locus.layers.elevationLayer.cableLayer.features[k]);
                							Global.locus.layers.elevationLayer.cableLayer.features[k].renderIntent = 'select';
                							break;
                						}
                					}
                				}
                			}
        					$.post(Global.option.URL +'/delete.action',{
        						sids 	: holeIds.toString(),
        						type	: 'cable'
        					});
                			deleteSelected(i);	
            			}
            			break;
            		case '剖面属性':
            			var originLength = Global.locus.select.control.layers[i].selectedFeatures.length;
            			if(originLength>0){
        					var holeIds = [], bracketIds = [];
        					var hIndex = 0, bIndex = 0;
        					var url;
                			for(var j=0;j<originLength;j++){
                				if(Global.locus.select.control.layers[i].selectedFeatures[j].attributes['object_type'] == '1010502'){
                					holeIds[hIndex] = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['static_id'];
                					hIndex++;
                				}
                				if(Global.locus.select.control.layers[i].selectedFeatures[j].attributes['object_type'] == '1010503'){
                					bracketIds[bIndex] = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['static_id'];
                					bIndex++;
                				}
                			}
                			if(hIndex != 0){
                				if(bIndex != 0){
                					url = "deleteHole.action?type=hole&holesId="+holeIds.toString()+"&bracketsId="+bracketIds.toString();
                				}else{
                					url = "deleteHole.action?type=hole&holesId="+holeIds.toString();
                				}               				
                			}else{
                				if(bIndex != 0){
                					url = "deleteHole.action?type=hole&bracketsId="+bracketIds.toString();
                				}else{
                					url = "deleteHole.action?type=hole&holesId="+holeIds.toString()+"&bracketsId="+bracketIds.toString();
                				}
                			}
                			Global.constant.holeIndex = i;
                		    OpenLayers.Request.POST({
                		        url: url,
                		        success: holeStatus_success,
                		        failure: (function(e){
                		        	Plugins.dialog.show({
                		        		'title': '错误信息',
                		        		'text': e.responseText
                		        	},{
                		        		'submit' : function(e){
                		        			Plugins.dialog.remove();
                		        		}
                		        	});
                		        	}
                		        )
                		    });
                		    function holeStatus_success(req){
                		    	if(req.responseText == 'pole'){
                   		        	Plugins.dialog.show({
                		        		'title': '错误信息',
                		        		'text': '不能删除穿管管孔，请先删除所穿电缆！'
                		        	},{
                		        		'submit' : function(e){
                		        			Plugins.dialog.remove();
                		        		}
                		        	});
                		    	}else{
                		    		var i = Global.constant.holeIndex;
                		    		var originLength = Global.locus.select.control.layers[i].selectedFeatures.length;
                        			for(var j=0;j<originLength;j++){
                        				var sid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['static_id'];
                        				var ppid = Global.locus.select.control.layers[i].selectedFeatures[j].attributes['pp_segment_id'];
                        				for(var k=0;k<Global.locus.layers.elevationLayer.pipe_propertyLayer.features.length;k++){
                        					if(Global.locus.layers.elevationLayer.pipe_propertyLayer.features[k].attributes['static_id'] == sid &&
                        							Global.locus.layers.elevationLayer.pipe_propertyLayer.features[k].attributes['pp_segment_id'] == ppid){
                        						if(Global.locus.layers.elevationLayer.pipe_propertyLayer.features[k].renderIntent == 'default'){
                        							Global.locus.select.control.layers[i].selectedFeatures.push(Global.locus.layers.elevationLayer.pipe_propertyLayer.features[k]);
                        							Global.locus.layers.elevationLayer.pipe_propertyLayer.features[k].renderIntent = 'select';
                        							break;
                        						}
                        					}
                        				}
                        			}
                        			deleteSelected(i);
                        			Global.locus.save.saveStrategy[i].save();
                		    	}
                		    }	
            			}
            			break;
            		default:
            			if(Global.locus.select.control.layers[i].selectedFeatures.length>0){
            				deleteSelected(i);	
            			}         			
            		}
            	}
            	Plugins.dialog.remove();
        		for (var i = 0; i < Global.locus.constant.saveNum; i++) {
        			Global.locus.save.saveStrategy[i].save();
        		}
    		}
    	});	
	}
}

/* Feature starting to move */
Global.locus.method.startDrag = function (feature, pixel) {
	lastPixel = pixel;
}

/* Feature moving */
Global.locus.method.doDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		Global.locus.copy.features[l] = [];
		for (i in layer.selectedFeatures) {
			if(layer.selectedFeatures[i]){
				if(Global.locus.flag.copy){
					Global.locus.copy.features[l].push(layer.selectedFeatures[i].clone());
				}
				var res = this.map.getResolution();
				layer.selectedFeatures[i].geometry.move(
					res * (pixel.x - lastPixel.x), 
					res * (lastPixel.y - pixel.y)
				);	
				layer.drawFeature(layer.selectedFeatures[i]);
				if(Global.locus.flag.copy){
					layer.addFeatures(Global.locus.copy.features[l]);
					for(var j=0;j<Global.locus.copy.features[l].length;j++){
						Global.locus.copy.features[l][j].state = OpenLayers.State.INSERT;
					}
					Global.locus.copy.features[l] = [];
				}
			}
		}
	}
	lastPixel = pixel;
	if(Global.locus.flag.copy){
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M1']=true;
		Plugins.menu.update();
		Global.locus.flag.copy = false;
	}
	
}

/* Featrue stopped moving */
Global.locus.method.endDrag = function (feature, pixel) {
	var layers = this.layers || [this.layer];
	var layer;
	for(var l=0; l<layers.length; ++l) {
		layer = layers[l];
		for (i in layer.selectedFeatures) {
			layer.selectedFeatures[i].state = OpenLayers.State.UPDATE;
		}
	}
}

/* 框选事件捕捉 */
Global.locus.method.box_selected = function(e){
	var sum = [];
	var index = 0;
	for(var i=0;i<e.layers.length;i++){
		for(var j=0;j<e.layers[i].selectedFeatures.length;j++){
			sum[index] = e.layers[i].selectedFeatures[j];
			index++;
		}
	}
	Plugins.slide_form.show({//弹框
		'title'		: '所选对象',
		'buttons'	: 'no',
		'content'	: '<table class="featurelist"></table>'
	});
	Plugins.slide_form.add_list(sum);	
}

Global.locus.method.electrical_select = function(e){
	if(!Global.locus.flag.shift){
		/*画管沟功能-虚拟土建*/	
		if(Global.locus.flag.addPipe){
			Global.locus.method.addPipe(e.feature);		
		}	
	}
}

Global.locus.method.electrical_unselect = function(e){
	
}

Global.locus.method.building_select = function(e){
	if(!Global.locus.flag.shift){
		/*添加剖面*/
		if(Global.locus.flag.addProfilePpSegment){
			Global.locus.method.createProfile(e.feature);
		}
		if(Global.locus.flag.addProfileWell){
			Global.locus.method.addProfile(e.feature);
		}
		
		/*添加测绘层*/
		if(Global.locus.flag.addSurveyFlag && 
				(e.feature.attributes['object_type'] == '1020101' || e.feature.attributes['object_type'] == '1020103')){
			Global.locus.draw.surveyWellId = e.feature.attributes['static_id'];
			//Global.locus.method.toggleControl("polygon");
			Plugins.dialog.show({
				url 	:	Global.option.URL + '/editProfile.action',
				param	:	{
				points	: 3
				}
			},{
				submit	: function(){
					var n = 0;
					var pointsx = [];
					var pointsy = [];
					var x=0,y=0, flag=true;
					$('.pointx').each(function(){
						if($(this).val() == ''){
							flag = false;
						}
						pointsx[x] = $(this).val();
						x++;
					});
					
					$('.pointy').each(function(){
						if($(this).val() == ''){
							flag = false;
						}
						pointsy[y] = $(this).val();
						y++;
					});
					
					if(flag){
						//alert('第一个点的经纬度为' + pointsx[0] + ':' + pointsy[0]);
					} else {
						alert('数据不能为空');
					}
					Plugins.dialog.lock('unlocked'); 
					
					//var res = map.getResolution();
					var point = [];
					for(var i=0; i<x; i++){
						point[i] = new OpenLayers.Geometry.Point(pointsx[i], pointsy[i]); 								
					}
					var ln = new OpenLayers.Geometry.Polygon(new OpenLayers.Geometry.LinearRing(point)); 
					var feature = new OpenLayers.Feature.Vector(ln);
					feature.attributes['well_id'] = Global.locus.draw.surveyWellId;
					feature.attributes['object_type'] = 1030110;
					feature.state = OpenLayers.State.INSERT;
					Global.locus.layers.elevationLayer.surveyLayer.addFeatures([feature]);
					Global.locus.constant.saveEventsCount = 1;
					Global.locus.save.saveStrategy[2].save();
	    			Plugins.dialog.remove();
				}
			});
		}
		
		/*画管沟功能-真实土建*/	
		if(Global.locus.flag.addPipe){			
			Global.locus.method.addPipe(e.feature);		
		}
		
		/*电缆入沟功能*/ 
		function judge(originStart, originEnd, newAddFeature){
			var newAdd = newAddFeature.geometry.components;
			var newPointS = newAdd[0];
			var newPointE = newAdd[newAdd.length-1];
			if(((originEnd.x == newPointS.x) && (originEnd.y == newPointS.y))&&
					((originStart.x != newPointE.x) || (originStart.y != newPointE.y))){
				Plugins.slide_form.add_data({
					'text'	: newAddFeature.attributes['name'],
					'name' 	: 'topipe['+Global.constant.index+']',//数组名wires不可修改
					'value'	: newAddFeature.attributes['static_id'],
					'type' 	: 'int'
				});
				Global.constant.index++;
				for(var i=1;i<newAdd.length;i++){
					Global.locus.draw.cablePoints.push(newAdd[i]);
				}
				return true;
			}else{
				if(((originEnd.x == newPointE.x) && (originEnd.y == newPointE.y)) &&
						((originStart.x != newPointS.x) || (originStart.y != newPointS.y))){
					Plugins.slide_form.add_data({
						'text'	: newAddFeature.attributes['name'],
						'name' 	: 'topipe['+Global.constant.index+']',//数组名wires不可修改
						'value'	: newAddFeature.attributes['static_id'],
						'type' 	: 'int'
					});
					Global.constant.index++;
					for(var i=newAdd.length-2;i>=0;i--){
						Global.locus.draw.cablePoints.push(newAdd[i]);
					}
					return true;
				}else{
					return false;
				}
			}
		}
		if(Global.locus.flag.cableToPipe){
			var type = e.feature.attributes['object_type'];
			if(type == '1020203' || type == '1020211' || type == '1020212' || type == '1020213' ||
					type == '1020214' || type == '1020215' || type == '1020216'){
				if(Global.locus.draw.cablePoints.length != 0){
					if(!judge(Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-2],Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-1], e.feature)){
						if(Global.locus.flag.cableToPipeStart){
							var arr = [];
							for(var i=0;i<Global.locus.draw.cablePoints.length;i++){
								arr[i] = Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-1-i].clone();
							}
							for(var i=0;i<Global.locus.draw.cablePoints.length;i++){
								Global.locus.draw.cablePoints[i] = arr[i];
							}
							if(judge(Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-2],Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-1], e.feature)){
								Global.locus.flag.cableToPipeStart = false;
							}else{
				            	Plugins.dialog.show({
				            		'title': '错误信息',
				            		'text': '所选管沟段不连续或重复。'
				            	},{
				            		'submit' : function(e){
				            			Plugins.dialog.remove();
				            		}
				            	});
							}
						}else{
			            	Plugins.dialog.show({
			            		'title': '错误信息',
			            		'text': '所选管沟段不连续或重复。'
			            	},{
			            		'submit' : function(e){
			            			Plugins.dialog.remove();
			            		}
			            	});
						}
					}
				}else{
					var start,end,sflag,eflag;
					if(Global.locus.toPipe.startType != "1020102"){
						sflag = true;
					}else{
						sflag = false;
					}
					if(Global.locus.toPipe.endType != "1020102"){
						eflag = true;
					}else{
						eflag = false;
					}
					if(sflag || eflag){
						for(var i=0;i<Global.locus.layers.electricalLayer.features.length;i++){
							if(sflag && !start && Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == Global.locus.toPipe.start){
								start = Global.locus.layers.electricalLayer.features[i].geometry;
							}else{
								if(eflag && !end && Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == Global.locus.toPipe.end){
									end = Global.locus.layers.electricalLayer.features[i].geometry;
								}
							}
						}
					}
					if(!sflag || !eflag){
						for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
							if(!sflag && !start && Global.locus.layers.buildingLayer.features[i].attributes['static_id'] == Global.locus.toPipe.start){
								start = Global.locus.layers.buildingLayer.features[i].geometry;
							}else{
								if(!eflag && !end && Global.locus.layers.buildingLayer.features[i].attributes['static_id'] == Global.locus.toPipe.end){
									end = Global.locus.layers.buildingLayer.features[i].geometry;
								}
							}
						}
					}
					var pipeStart = e.feature.geometry.components[0];
					var pipeEnd = e.feature.geometry.components[e.feature.geometry.components.length-1];
					if(pipeStart.equals(start) || pipeStart.equals(end) || pipeEnd.equals(start) || pipeEnd.equals(end)){
						var temp = e.feature.geometry.clone();
						Global.locus.draw.cablePoints = temp.components;
						Plugins.slide_form.add_data({
							'text'	: e.feature.attributes['name'],
							'name' 	: 'topipe['+Global.constant.index+']',//数组名wires不可修改
							'value'	: e.feature.attributes['static_id'],
							'type' 	: 'int'
						});
						Global.constant.index++;
						Global.locus.flag.cableToPipeStart = true;
					}else{
			        	Plugins.dialog.show({
			        		'title': '错误信息',
			        		'text': '所选管沟段位置有误，请重新选择'
			        	},{
			        		'submit' : function(e){
			        			Plugins.dialog.remove();
			        		}
			        	});
					}
				}
			}else{
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': '请选择管沟段'
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
			}
		}	
	}
} 

Global.locus.method.building_unselect = function(e){  
}

Global.locus.method.survey_select = function(e){  
}

Global.locus.method.survey_unselect = function(e){  	
}

Global.locus.method.profile_select = function(e){
	if(!Global.locus.flag.shift){
		   Global.locus.copy.source = e.feature.geometry.getBounds().getCenterLonLat();
		    if(e.feature.attributes['object_type'] == '1010501'){
		    	var display = true;
		    	
		    	/*添加单个管孔*/
		        if(Global.locus.flag.addSingleHoleFlag){
		        	display = false;
		    		Plugins.dialog.show({
		    			title: '管孔',
		    			feature: e.feature,
		    			url: Global.option.URL+'/template/property/pipehole.jsp'
		    		},{
		    			'submit' : function(e){
		    				if($('#hole_x').val() == "" || $('#hole_y').val() == "" || $('#diameter').val() == ""){
		    					Plugins.dialog.warm({
									'title'	: '错误信息'
								},"横坐标、埋深、直径不能为空，请填写后重新提交",{});
		    				}else{
		    					Global.locus.method.drawPipeHole(e['feature']);
		    				}
		    			}
		    		});
		        }
		        
		        /*添加规则管孔*/
		    	if(Global.locus.flag.addRegularHoleFlag){
		    		display = false;
		    		Plugins.dialog.show({
		    			title: '规则管孔',
		    			feature: e.feature,
		    			url: Global.option.URL+'/template/property/regularholes.jsp'
		    		},{
		    			'submit' : function(e){
		    				Global.locus.method.drawRegularPipeHole(e['feature']);
		    			}
		    		});
		    	}
		    	
		    	/*添加支架*/
		    	if(Global.locus.flag.addBracketFlag){
		    		display = false;
		    		Plugins.dialog.show({
		    			title: '支架',
		    			feature: e.feature,
		    			url		: Global.option.URL+'/getProperty.action',
		    			param	: {
		    				type	: 1010503
		    			}
		    		},{
		    			'submit' : function(e){
		    				Global.locus.method.drawBrackets(e['feature']);
		    			}
		    		});
		    	}  	
		    	
		        /*穿管信息*/
		    	if(Global.locus.flag.labelFlag){
		    		display = false;
		    		Plugins.slide_form.show({
		    			'title'		: '穿管信息',
		    			'url'		: Global.option.URL + '/getWireOfProfile.action',
		    			'buttons'	: 'off',
		    			'param'		:{
		    				'SId'	: e.feature.attributes['static_id']
		    			}
		    		},{
		    			'success'	: function(){
		    				Global.locus.flag.slide = true;
		    			} 
		    		});
		    	}    	
		    	
				/*显示长宽*/
		    	if(display){
		    		if(e.feature.geometry.components.length == 1){
		        	    OpenLayers.Request.GET({
		        	    	url: "getPropertyJson.action?type="+e.feature.attributes['object_type']
		        	    		+"&staticId="+e.feature.attributes['static_id'],
		        	        success: profileProperty_success,
					        failure: (function(e){
					        	Plugins.dialog.show({
					        		'title': '错误信息',
					        		'text': e.responseText
					        	},{
					        		'submit' : function(e){
					        			Plugins.dialog.remove();
					        		}
					        	});
					        	}
					        )
		        	    });
		        		function profileProperty_success(req){
		        			var format = new OpenLayers.Format.JSON();
		        		    var response = format.read(req.responseXML || req.responseText);
		        		    Global.locus.method.displaySize(e.feature, response['profileWidth'], response['profileHigh']);
		        		}	
		    		}
		    	}
		    }	
	}
}

/*添加、更新单个管孔功能函数*/
Global.locus.method.drawPipeHole = function(feature,holeFeature){
	var Ppfeature;
	for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
		if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] ==
			feature.attributes['pp_segment_id']){
			Ppfeature = Global.locus.layers.buildingLayer.features[i];
			break;
		}
	}
	if(Ppfeature){
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+Ppfeature.attributes['object_type']
	        	+"&staticId="+Ppfeature.attributes['static_id'],
	        success: ppSegmentProperty_success,
	        failure: (function(e){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': e.responseText
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        	}
	        )
	    });
	}else{
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请检查剖面所属管沟是否存在或者被隐藏!'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}
    function ppSegmentProperty_success(req){
    	var format = new OpenLayers.Format.JSON();
    	var response = format.read(req.responseXML || req.responseText);	
    	var x_length = parseFloat($('#hole_x').val())*Global.constant.scaleMM;
    	var y_length = parseFloat($('#hole_y').val())*Global.constant.scaleMM;
    	var radius = parseFloat($('#diameter').val()/2)*Global.constant.scaleMM;
    	var high = feature.geometry.components[0].components[0].components[1].distanceTo(
    			feature.geometry.components[0].components[0].components[2]);
    	var featureCopy;//镜像剖面
	    for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
	    	if(Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['static_id'] ==
	    		feature.attributes['static_id']){
	    		var reference = feature.geometry.components[0].components[0].components[0];
	    		var geometry = Global.locus.layers.elevationLayer.profileLayer.features[i].geometry.components[0].components[0].components[0];
	    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
	    			featureCopy = Global.locus.layers.elevationLayer.profileLayer.features[i];
	    		}
	    	}
	    }
	    if(featureCopy){
	    	var start = feature.geometry.components[0].components[0].components[0];
	    	var end = featureCopy.geometry.components[0].components[0].components[0];
	    	var direction = 
	    		Math.atan((high-y_length-radius)/(x_length+radius)) + 
	    		Math.atan((end.y-start.y)/(end.x-start.x));
	    	var compare = start;
	    	var mirrorCompare = end;
	    	var changedFlag = false;
	    	var checkedPass = true;
	        if(response['startBuilding'] == feature.attributes['well_id']){
	        }else{
	        	if(response['endBuilding'] == feature.attributes['well_id']){
	        		compare = end;
	        		mirrorCompare = start;
	    	    	changedFlag = true;
	        	}else{
	            	Plugins.dialog.show({
	            		'title': '错误信息',
	            		'text': '剖面所属管沟未经过剖面所属工井，请检查数据正确性！'
	            	},{
	            		'submit' : function(e){
	            			Plugins.dialog.remove();
	            		}
	            	});
	        		checkedPass = false;
	        	}
	        }
	        if(checkedPass){
	    		var length = Math.sqrt((x_length+radius)*(x_length+radius)+
	    				(high-y_length-radius)*(high-y_length-radius));
	    		var center = new OpenLayers.Geometry.Point();
	    		var mirrorCenter = new OpenLayers.Geometry.Point();
	    		if(changedFlag){
	    			center.x = compare.x - length*Math.cos(direction);
	    			center.y = compare.y - length*Math.sin(direction);
	    			mirrorCenter.x = mirrorCompare.x + length*Math.cos(direction);
	    			mirrorCenter.y = mirrorCompare.y + length*Math.sin(direction);
	    		}else{
	    			center.x = compare.x + length*Math.cos(direction);
	    			center.y = compare.y + length*Math.sin(direction);
	        		mirrorCenter.x = mirrorCompare.x - length*Math.cos(direction);
	        		mirrorCenter.y = mirrorCompare.y - length*Math.sin(direction);
	    		}
	    		if(holeFeature){//判断是更新管孔属性还是添加管孔
	    			holeFeature.move(new OpenLayers.LonLat(center.x,center.y));
	    		    var holeFeatureCopy;
	    		    for(var i=0;i<Global.locus.layers.elevationLayer.pipe_propertyLayer.features.length;i++){
	    		    	if(Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i].attributes['static_id'] ==
	    		    		holeFeature.attributes['static_id']){
	    		    		var reference = holeFeature.geometry.components[0].components[0];
	    		    		var geometry = Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i].geometry.components[0].components[0];
	    		    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
	    		    			holeFeatureCopy = Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i];
	    		    		}
	    		    	}
	    		    }
	    		    holeFeatureCopy.move(new OpenLayers.LonLat(mirrorCenter.x,mirrorCenter.y));
		    		Global.locus.constant.saveEventsCount = 1;
		    		Global.locus.save.saveStrategy[4].save([holeFeature, holeFeatureCopy]);
	    		}else{
	    			var circle = [];
	    			circle[0] = new OpenLayers.Feature.Vector(
	    					OpenLayers.Geometry.Polygon.createRegularPolygon(center,radius,30));
	    			circle[1] = new OpenLayers.Feature.Vector(
	    					OpenLayers.Geometry.Polygon.createRegularPolygon(mirrorCenter,radius,30));
	    			var staticId = Plugins.get_staticid();
	    			if(changedFlag){
	    				circle[0].attributes['well_id'] = response['endBuilding'];
	    				circle[1].attributes['well_id'] = response['startBuilding'];
	    			}else{
	    				circle[0].attributes['well_id'] = response['startBuilding'];
	    				circle[1].attributes['well_id'] = response['endBuilding'];
	    			}
	    			for(var i=0;i<2;i++){
	    	    		circle[i].attributes['static_id'] = staticId;
	    	    		circle[i].attributes['object_type'] = '1010502';
	    	    		circle[i].attributes['pp_segment_id'] = response['staticId'];	
	    	    		circle[i].state = OpenLayers.State.INSERT;
	    			}
	    			Global.locus.layers.elevationLayer.pipe_propertyLayer.addFeatures(circle);
	    			Plugins.dialog.add('holes[0].staticId', staticId, 'int');
	    			Plugins.dialog.add('holes[0].wellId', response['startBuilding'], 'int');
	    			Plugins.dialog.add('holes[0].ppSegmentId', response['staticId'], 'int');
	    			Plugins.dialog.add('holes[0].profileId', feature.attributes['static_id'], 'int');
	    			Plugins.dialog.add('holes[0].mirrorWellId', response['endBuilding'], 'int');
	        		Plugins.menu.refresh();
	        		Plugins.FLAG.MAP['M1']=true;
	        		Plugins.menu.update();
	    			Global.locus.flag.addSingleHoleFlag = false;
		    		Global.locus.constant.saveEventsCount = 1;
		    		Global.locus.save.saveStrategy[4].save(circle);
	    		}
	    		var response = setInterval(function(){
	    			if(Global.locus.save.state['剖面属性'] == 'success'){
	    				$.post(Global.option.URL + 'saveProperties.action', Base.request.get_param(),function(res){
	    				});
	    				Plugins.dialog.remove();				
	    				clearInterval(response);
	    			}
	    			if(Global.locus.save.state['剖面属性'] == 'fail'){
	    				clearInterval(response);
	    			}
	    		},500);	
	        }	
	    }else{
        	Plugins.dialog.show({
        		'title': '错误信息',
        		'text': '请确认管孔所属剖面是否存在镜像剖面或者被隐藏！'
        	},{
        		'submit' : function(e){
        			Plugins.dialog.remove();
        		}
        	});
	    }
    }
}

/*添加规则管孔功能函数*/
Global.locus.method.drawRegularPipeHole = function(feature){
	var Ppfeature;
	for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
		if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] ==
			feature.attributes['pp_segment_id']){
			Ppfeature = Global.locus.layers.buildingLayer.features[i];
			break;
		}
	}
	if(Ppfeature){
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+Ppfeature.attributes['object_type']
	        	+"&staticId="+Ppfeature.attributes['static_id'],
	        success: ppSegmentProperty_success,
	        failure: (function(e){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': e.responseText
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        	}
	        )
	    });
	}else{
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请检查剖面所属管沟是否存在或者被隐藏!'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}
    function ppSegmentProperty_success(req){
    	var format = new OpenLayers.Format.JSON();
    	var response = format.read(req.responseXML || req.responseText);	
		var obj = [];
		obj[0] = new Object(), obj[1] = new Object();
		var hx = parseFloat($('#pholex').val());
		var hy = parseFloat($('#pholey').val());
		var hx_start = parseFloat($('#pholex_start').val())*Global.constant.scaleMM;
		var hy_start = parseFloat($('#pholey_start').val())*Global.constant.scaleMM;
		var hx_span = parseFloat($('#pholex_span').val())*Global.constant.scaleMM;
		var hy_span = parseFloat($('#pholey_span').val())*Global.constant.scaleMM;
		var hr = parseFloat($('#pholed').val())*Global.constant.scaleMM/2;
		var code = $('#code').val();
    	var featureCopy;//镜像剖面
	    for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
	    	if(Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['static_id'] ==
	    		feature.attributes['static_id']){
	    		var reference = feature.geometry.components[0].components[0].components[0];
	    		var geometry = Global.locus.layers.elevationLayer.profileLayer.features[i].geometry.components[0].components[0].components[0];
	    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
	    			featureCopy = Global.locus.layers.elevationLayer.profileLayer.features[i];
	    		}
	    	}
	    }
	    if(featureCopy){
			obj[0].vertex = Ppfeature.geometry.components[0].clone();
			obj[1].vertex = Ppfeature.geometry.components[Ppfeature.geometry.components.length-1].clone();
	    	var changedFlag = false;
	    	var checkedPass = true;
			if(obj[0].vertex.x > obj[1].vertex.x || 
					(obj[0].vertex.x == obj[1].vertex.x && obj[0].vertex.y > obj[1].vertex.y)){
				var temp = obj[0].vertex.clone();
				obj[0].vertex = obj[1].vertex.clone();
				obj[1].vertex = temp.clone();
				changedFlag = true;
			}
	        if(response['startBuilding'] != feature.attributes['well_id'] &&
	        		response['endBuilding'] != feature.attributes['well_id']){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': '剖面所属管沟未经过剖面所属工井，请检查数据正确性！'
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
        		checkedPass = false;
	        }
	        var Rdirection = Math.atan((obj[1].vertex.y-obj[0].vertex.y)/(obj[1].vertex.x-obj[0].vertex.x));
	        if(checkedPass){
	        	obj[0].points = [], obj[1].points = [];
	        	var profileDistance = obj[1].vertex.distanceTo(obj[0].vertex);
				var distance = profileDistance/4;
				var Pwidth = feature.geometry.components[0].components[0].components[0].distanceTo(
		    			feature.geometry.components[0].components[0].components[1]);
				var Phigh = feature.geometry.components[0].components[0].components[1].distanceTo(
		    			feature.geometry.components[0].components[0].components[2]);
				obj[0].points[0] = new OpenLayers.Geometry.Point(
						obj[0].vertex.x+distance*Math.cos(Rdirection),
						obj[0].vertex.y+distance*Math.sin(Rdirection));
				obj[1].points[0] = new OpenLayers.Geometry.Point(
						obj[1].vertex.x-distance*Math.cos(Rdirection),
						obj[1].vertex.y-distance*Math.sin(Rdirection));
				var length;
				for(var i=0;i<3;i++){
					length = (i+1)%2*Pwidth+i%2*Phigh;
					obj[0].dx = length * Math.cos(Rdirection+Math.PI/2*i);
					obj[0].dy = length * Math.sin(Rdirection+Math.PI/2*i);
					obj[1].dx = length * Math.cos(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
					obj[1].dy = length * Math.sin(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
					obj[0].points[i+1] = new OpenLayers.Geometry.Point(obj[0].points[i].x+obj[0].dx,obj[0].points[i].y+obj[0].dy);
					obj[1].points[i+1] = new OpenLayers.Geometry.Point(obj[1].points[i].x+obj[1].dx,obj[1].points[i].y+obj[1].dy);
				}
				obj[0].points[4] = obj[0].points[0].clone();
				obj[1].points[4] = obj[1].points[0].clone();
				
				var staticId = Plugins.get_staticid();				 
				obj[0].sp = obj[0].points[3];
				obj[1].sp = obj[1].points[3];
				obj[0].hole = [], obj[1].hole = [];
				var tempx, tempy, temp, Hdirection;
				var index = 0, encode, codeIndex, numIndex;
				codeIndex = Global.locus.constant.encode.search(code.charAt(0));
				numIndex = parseInt(code.slice(1,code.length));
				obj[0].center = new OpenLayers.Geometry.Point();
				obj[1].center = new OpenLayers.Geometry.Point();
				for(var i=0;i<hx;i++){
					tempy = hy_start+i*hy_span+(2*i+1)*hr;
					obj[0].array = [], obj[1].array = [];
					for(var j=0;j<hy;j++){
						tempx = hx_start+j*hx_span+(2*j+1)*hr;
						temp = Math.sqrt(tempx*tempx+tempy*tempy);
						encode = changedFlag ? Global.locus.constant.encode.charAt(codeIndex+hx-i+1)+(numIndex+hy-j) //非旋转hx-i+1换'i'
								: Global.locus.constant.encode.charAt(codeIndex+i)+(numIndex+j);
						Hdirection = Math.atan(tempx/tempy);
						obj[0].center.x = obj[0].sp.x + temp*Math.sin(Hdirection + Rdirection);
						obj[0].center.y = obj[0].sp.y - temp*Math.cos(Hdirection + Rdirection);
						obj[1].center.x = obj[1].sp.x - temp*Math.sin(Hdirection + Rdirection);//非旋转括号内换'-'
						obj[1].center.y = obj[1].sp.y + temp*Math.cos(Hdirection + Rdirection);//非旋转两个'+'都换'-'
						for(var k=0;k<2;k++){
							obj[k].array[j] = OpenLayers.Geometry.Polygon.createRegularPolygon(obj[k].center,hr,30);
							obj[k].hole[index] = new OpenLayers.Feature.Vector(obj[k].array[j]);
							obj[k].hole[index].attributes['pp_segment_id'] = feature.attributes['pp_segment_id'];
							obj[k].hole[index].attributes['object_type'] = '1010502';
							obj[k].hole[index].attributes['static_id'] = staticId + index + 1;
							obj[k].hole[index].state = OpenLayers.State.INSERT;
						}
						obj[0].hole[index].attributes['well_id'] = 
							changedFlag ? response['endBuilding'] : response['startBuilding'];
						obj[1].hole[index].attributes['well_id'] = 
							changedFlag ? response['startBuilding'] : response['endBuilding'];			
						Plugins.dialog.add('holes[' + index + '].objectType', '1010502', 'int');
						Plugins.dialog.add('holes[' + index + '].wellId', response['startBuilding'], 'int');
						Plugins.dialog.add('holes[' + index + '].mirrorWellId', response['endBuilding'], 'int');
						Plugins.dialog.add('holes[' + index + '].profileId', feature.attributes['static_id'], 'int');
						Plugins.dialog.add('holes[' + index + '].pipeCode', encode);
						Plugins.dialog.add('holes[' + index + '].coordinateX', tempx/Global.constant.scaleMM, 'int');
						Plugins.dialog.add('holes[' + index + '].deep', tempy/Global.constant.scaleMM, 'int');
						Plugins.dialog.add('holes[' + index + '].pipeDiameter', 2*hr/Global.constant.scaleMM, 'int');
						Plugins.dialog.add('holes[' + index + '].ppSegmentId', feature.attributes['pp_segment_id'], 'int');
						Plugins.dialog.add('holes[' + index + '].staticId', staticId + index + 1, 'int');	
						Global.locus.layers.elevationLayer.pipe_propertyLayer.addFeatures([obj[0].hole[index],obj[1].hole[index]]);								
						index++;
					}
				}
				var saveArr = [], k=0;
				for(i=0;i<index;i++){
					saveArr[k] = obj[0].hole[i];
					saveArr[k+1] = obj[1].hole[i];
					k+=2;
				}
				Global.locus.flag.addRegularHoleFlag = false;
				Plugins.menu.refresh();
				Plugins.FLAG.MAP['M1']=true;
				Plugins.menu.update();
				Global.locus.constant.saveEventsCount = 1;
				Global.locus.save.saveStrategy[4].save(saveArr);
	    		var response = setInterval(function(){
	    			if(Global.locus.save.state['剖面属性'] == 'success'){
	    				$.post(Global.option.URL + 'saveProperties.action', Base.request.get_param(),function(res){
	    				});
	    				Plugins.dialog.remove();				
	    				clearInterval(response);
	    			}
	    			if(Global.locus.save.state['剖面属性'] == 'fail'){
	    				//Plugins.dialog.remove();
	    				clearInterval(response);
	    			}
	    		},500);
	        }	
	    }else{
        	Plugins.dialog.show({
        		'title': '错误信息',
        		'text': '请确认管孔所属剖面是否存在镜像剖面或者被隐藏！'
        	},{
        		'submit' : function(e){
        			Plugins.dialog.remove();
        		}
        	});
	    }
    }
}

/*添加支架功能函数*/
Global.locus.method.drawBrackets = function(feature){
	var Ppfeature;
	for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
		if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] ==
			feature.attributes['pp_segment_id']){
			Ppfeature = Global.locus.layers.buildingLayer.features[i];
			break;
		}
	}
	if(Ppfeature){
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+Ppfeature.attributes['object_type']
	        	+"&staticId="+Ppfeature.attributes['static_id'],
	        success: ppSegmentProperty_success,
	        failure: (function(e){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': e.responseText
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        	}
	        )
	    });
	}else{
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请检查剖面所属管沟是否存在或者被隐藏!'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}
    function ppSegmentProperty_success(req){
    	var format = new OpenLayers.Format.JSON();
    	var response = format.read(req.responseXML || req.responseText);	
		var obj = [];
		obj[0] = new Object(), obj[1] = new Object();
		var bLine = parseInt($('#bracket_numbers').val())/2;
		var bSpan = parseFloat($('#bracket_interval').val())*Global.constant.scaleMM;
		var model = $('#bracket_model').val().toLowerCase().split('x');
		var bLength = parseFloat(model[0])*Global.constant.scaleMM;
		var bThick = parseFloat(model[2])*Global.constant.scaleMM;
    	var featureCopy;//镜像剖面
	    for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
	    	if(Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['static_id'] ==
	    		feature.attributes['static_id']){
	    		var reference = feature.geometry.components[0].components[0].components[0];
	    		var geometry = Global.locus.layers.elevationLayer.profileLayer.features[i].geometry.components[0].components[0].components[0];
	    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
	    			featureCopy = Global.locus.layers.elevationLayer.profileLayer.features[i];
	    		}
	    	}
	    }
	    if(featureCopy){
			obj[0].vertex = Ppfeature.geometry.components[0].clone();
			obj[1].vertex = Ppfeature.geometry.components[Ppfeature.geometry.components.length-1].clone();
	    	var changedFlag = false;
	    	var checkedPass = true;
			if(obj[0].vertex.x > obj[1].vertex.x || 
					(obj[0].vertex.x == obj[1].vertex.x && obj[0].vertex.y > obj[1].vertex.y)){
				var temp = obj[0].vertex.clone();
				obj[0].vertex = obj[1].vertex.clone();
				obj[1].vertex = temp.clone();
				changedFlag = true;
			}
	        if(response['startBuilding'] != feature.attributes['well_id'] &&
	        		response['endBuilding'] != feature.attributes['well_id']){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': '剖面所属管沟未经过剖面所属工井，请检查数据正确性！'
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
        		checkedPass = false;
	        }
	        var Rdirection = Math.atan((obj[1].vertex.y-obj[0].vertex.y)/(obj[1].vertex.x-obj[0].vertex.x));
	        if(checkedPass){
	        	obj[0].points = [], obj[1].points = [];
	        	var profileDistance = obj[1].vertex.distanceTo(obj[0].vertex);
				var distance = profileDistance/4;
				var Pwidth = feature.geometry.components[0].components[0].components[0].distanceTo(
		    			feature.geometry.components[0].components[0].components[1]);
				var Phigh = feature.geometry.components[0].components[0].components[1].distanceTo(
		    			feature.geometry.components[0].components[0].components[2]);
				obj[0].points[0] = new OpenLayers.Geometry.Point(
						obj[0].vertex.x+distance*Math.cos(Rdirection),
						obj[0].vertex.y+distance*Math.sin(Rdirection));
				obj[1].points[0] = new OpenLayers.Geometry.Point(
						obj[1].vertex.x-distance*Math.cos(Rdirection),
						obj[1].vertex.y-distance*Math.sin(Rdirection));
				var length;
				for(var i=0;i<3;i++){
					length = (i+1)%2*Pwidth+i%2*Phigh;
					obj[0].dx = length * Math.cos(Rdirection+Math.PI/2*i);
					obj[0].dy = length * Math.sin(Rdirection+Math.PI/2*i);
					obj[1].dx = length * Math.cos(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
					obj[1].dy = length * Math.sin(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
					obj[0].points[i+1] = new OpenLayers.Geometry.Point(obj[0].points[i].x+obj[0].dx,obj[0].points[i].y+obj[0].dy);
					obj[1].points[i+1] = new OpenLayers.Geometry.Point(obj[1].points[i].x+obj[1].dx,obj[1].points[i].y+obj[1].dy);
				}
				obj[0].points[4] = obj[0].points[0].clone();
				obj[1].points[4] = obj[1].points[0].clone();
				
				var staticId = Plugins.get_staticid();	
				obj[0].sp = obj[0].points[3];
				obj[1].sp = obj[1].points[3];
				var tempx, tempy, temp, Hdirection, increase;
				obj[0].left_points = [],obj[1].left_points = [],obj[0].right_points = [],obj[1].right_points = [];
				obj[0].rectangle = [],obj[1].rectangle = [];
				var index = 0, encode;
				for(var i=0;i<bLine;i++){
					obj[0].left_points[0] = new OpenLayers.Geometry.Point(
							obj[0].points[3].x+((i+1)*bSpan+i*bThick)*Math.sin(Rdirection),
							obj[0].points[3].y-((i+1)*bSpan+i*bThick)*Math.cos(Rdirection));
					obj[1].left_points[0] = new OpenLayers.Geometry.Point(
							obj[1].points[3].x-((i+1)*bSpan+i*bThick)*Math.sin(Rdirection),
							obj[1].points[3].y+((i+1)*bSpan+i*bThick)*Math.cos(Rdirection));
					obj[0].right_points[0] = new OpenLayers.Geometry.Point(
							obj[0].points[2].x+((i+1)*bSpan+i*bThick)*Math.sin(Rdirection)+bLength*Math.cos(Rdirection-Math.PI),
							obj[0].points[2].y-((i+1)*bSpan+i*bThick)*Math.cos(Rdirection)+bLength*Math.sin(Rdirection-Math.PI));
					obj[1].right_points[0] = new OpenLayers.Geometry.Point(
							obj[1].points[2].x-((i+1)*bSpan+i*bThick)*Math.sin(Rdirection)+bLength*Math.cos(Rdirection),
							obj[1].points[2].y+((i+1)*bSpan+i*bThick)*Math.cos(Rdirection)+bLength*Math.sin(Rdirection));
					for(var j=0;j<3;j++){
						increase = (j+1)%2*bLength+j%2*bThick;
						obj[0].dx = increase * Math.cos(Rdirection-Math.PI/2*j);
						obj[0].dy = increase * Math.sin(Rdirection-Math.PI/2*j);
						obj[1].dx = increase * Math.cos(Rdirection-Math.PI/2*j-Math.PI);
						obj[1].dy = increase * Math.sin(Rdirection-Math.PI/2*j-Math.PI);
						obj[0].left_points[j+1] = new OpenLayers.Geometry.Point(obj[0].left_points[j].x+obj[0].dx,obj[0].left_points[j].y+obj[0].dy);
						obj[1].left_points[j+1] = new OpenLayers.Geometry.Point(obj[1].left_points[j].x+obj[1].dx,obj[1].left_points[j].y+obj[1].dy);
						obj[0].right_points[j+1] = new OpenLayers.Geometry.Point(obj[0].right_points[j].x+obj[0].dx,obj[0].right_points[j].y+obj[0].dy);
						obj[1].right_points[j+1] = new OpenLayers.Geometry.Point(obj[1].right_points[j].x+obj[1].dx,obj[1].right_points[j].y+obj[1].dy);
					}
					obj[0].left_points[4] = obj[0].left_points[0].clone();
					obj[1].left_points[4] = obj[1].left_points[0].clone();
					obj[0].right_points[4] = obj[0].right_points[0].clone();
					obj[1].right_points[4] = obj[1].right_points[0].clone();
					obj[0].rectangle[2*i] = new OpenLayers.Geometry.Polygon(
							new OpenLayers.Geometry.LinearRing(obj[0].left_points));
					obj[1].rectangle[2*i] = new OpenLayers.Geometry.Polygon(
							new OpenLayers.Geometry.LinearRing(obj[1].left_points));
					obj[0].rectangle[2*i+1] = new OpenLayers.Geometry.Polygon(
							new OpenLayers.Geometry.LinearRing(obj[0].right_points));
					obj[1].rectangle[2*i+1] = new OpenLayers.Geometry.Polygon(
							new OpenLayers.Geometry.LinearRing(obj[1].right_points));
				}
				obj[0].polygons = new OpenLayers.Feature.Vector(
						new OpenLayers.Geometry.MultiPolygon(obj[0].rectangle));
				obj[1].polygons = new OpenLayers.Feature.Vector(
						new OpenLayers.Geometry.MultiPolygon(obj[1].rectangle));
				for(var i=0;i<2;i++){
					obj[i].polygons.attributes['pp_segment_id'] = feature.attributes['pp_segment_id'];
					obj[i].polygons.attributes['object_type'] = '1010503';
					obj[i].polygons.attributes['static_id'] = staticId;
					obj[i].polygons.state = OpenLayers.State.INSERT;
				}
				obj[0].polygons.attributes['well_id'] = 
					changedFlag ? response['endBuilding'] : response['startBuilding'];
				obj[1].polygons.attributes['well_id'] = 
					changedFlag ? response['startBuilding'] : response['endBuilding'];
				Plugins.dialog.set('#staticId', staticId);
				Plugins.dialog.add('bracket.wellId', response['startBuilding'], 'int');
				Plugins.dialog.add('bracket.mirrorWellId', response['endBuilding'], 'int');
				Plugins.dialog.add('bracket.belongsPpSegment', feature.attributes['pp_segment_id'], 'int');					
				Global.locus.layers.elevationLayer.pipe_propertyLayer.addFeatures([obj[0].polygons,obj[1].polygons]);
				Global.locus.flag.addBracketFlag = false;
				Plugins.menu.refresh();
				Plugins.FLAG.MAP['M1']=true;
				Plugins.menu.update();
				Global.locus.constant.saveEventsCount = 1;
				Global.locus.save.saveStrategy[4].save([obj[0].polygons,obj[1].polygons]);
	    		var response = setInterval(function(){
	    			if(Global.locus.save.state['剖面属性'] == 'success'){
	    				$.post(Global.option.URL + 'saveProperty.action', Base.request.get_param(),function(res){
	    				});
	    				Plugins.dialog.remove();				
	    				clearInterval(response);
	    			}
	    			if(Global.locus.save.state['剖面属性'] == 'fail'){
	    				//Plugins.dialog.remove();
	    				clearInterval(response);
	    			}
	    		},500);
	        }	
	    }else{
        	Plugins.dialog.show({
        		'title': '错误信息',
        		'text': '请确认管孔所属剖面是否存在镜像剖面或者被隐藏！'
        	},{
        		'submit' : function(e){
        			Plugins.dialog.remove();
        		}
        	});
	    }
    }
}

Global.locus.method.profile_unselect = function(e){  
	var arraylength = Global.locus.label.point.length;
	for(i=0;i<arraylength;i++){
		Global.locus.label.point[i].destroy();	
	}
}

Global.locus.method.pipe_property_select = function(e){
	Global.locus.copy.source = e.feature.geometry.getBounds().getCenterLonLat();
	if(Global.locus.flag.matchCableFlag){
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+e.feature.attributes['object_type']
	        	+"&staticId="+e.feature.attributes['static_id'],
	        success: holeProperty_success,
	        failure: (function(e){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': e.responseText
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        	}
	        )
	    });
	    var featureCopy;
	    for(var i=0;i<Global.locus.layers.elevationLayer.pipe_propertyLayer.features.length;i++){
	    	if(Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i].attributes['static_id'] ==
	    		e.feature.attributes['static_id']){
	    		var reference = e.feature.geometry.components[0].components[0];
	    		var geometry = Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i].geometry.components[0].components[0];
	    		if((geometry.x != reference.x) && (geometry.y != reference.y)){
	    			featureCopy = Global.locus.layers.elevationLayer.pipe_propertyLayer.features[i];
	    		}
	    	}
	    }
	    function holeProperty_success(req){
	    	var format = new OpenLayers.Format.JSON();
	        var response = format.read(req.responseXML || req.responseText);
	        if(response['wirecableSegmentId']){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': "该管孔已被穿管"
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        }else{
	        	var holeCenter = new OpenLayers.Geometry.Point();
	        	var holeCopyCenter = new OpenLayers.Geometry.Point();
	        	var vertex = e.feature.geometry.components[0].components;
	        	var vertexCopy = featureCopy.geometry.components[0].components;
	        	holeCenter.x = (vertex[0].x+vertex[15].x)/2;
	        	holeCenter.y = (vertex[0].y+vertex[15].y)/2;
	        	holeCopyCenter.x = (vertexCopy[0].x+vertexCopy[15].x)/2;
	        	holeCopyCenter.y = (vertexCopy[0].y+vertexCopy[15].y)/2;      	
		        Plugins.dialog.show({
					title: '选择电缆', 
					url: Global.option.URL+'/getProOfWireCables.action',
					width: 500,
					feature: e.feature,
					featureCopy: featureCopy,
					center: holeCenter,
					centerCopy: holeCopyCenter,
					param	: {
						PPSId	: e.feature.attributes['pp_segment_id']
					}
				}, {
					'submit' : function(e){
					    OpenLayers.Request.GET({
					        url: "getPropertiesJson.action?pipeSId="+e['feature'].attributes['static_id'],
					        success: holeInProfile_success,
					        failure: (function(e){
								Plugins.dialog.warm({
									'title'	: '错误信息'
								},e.responseText,{});
					        	}
					        )
					    });
					    function holeInProfile_success(req){
					    	var format = new OpenLayers.Format.JSON();
					        var response = format.read(req.responseXML || req.responseText);
					        var selectedCable = $('#cable').val();
					        var flag = false;
					        for(var i=0;i<response.length;i++){
					        	if(selectedCable == response[i]['wirecableSegmentId']){
					        		Plugins.dialog.warm(e, '该电缆已穿在'+response[i]['pipeCode']+'号管孔，确定继续穿管？',{
										'yes' 	: createCableIcon
					        		});
					        		flag = true;
					        		break;
					        	}
					        }
					        if(!flag){
					        	createCableIcon();
					        }
					        function createCableIcon(){
								var style,cableRadius;
								var cableDiameter = $('#cableDiameter').val();
								cableRadius = cableDiameter/2;
								var voltage = $('input[name="'+selectedCable+'"]').val();
								switch(voltage){
								case "0" :
									style = {externalGraphic:"img/holes/communicate.svg",pointRadius: cableRadius};
									break;
								case "1" : 
									style = {externalGraphic:"img/holes/hole04.svg",pointRadius: cableRadius};
									break;
								case "2" : 
									style = {externalGraphic:"img/holes/hole10.svg",pointRadius: cableRadius};
									break;
								case "3" : 
									style = {externalGraphic:"img/holes/hole35.svg",pointRadius: cableRadius};
									break;
								case "4" : 
									style = {externalGraphic:"img/holes/hole110.svg",pointRadius: cableRadius};
									break;
								case "5" : 
									style = {externalGraphic:"img/holes/hole220.svg",pointRadius: cableRadius};
									break;
								case "6" : 
									style = {externalGraphic:"img/holes/hole330.svg",pointRadius: cableRadius};
									break;
								case "7" : 
									style = {externalGraphic:"img/holes/hole500.svg",pointRadius: cableRadius};
									break;
								case "8" : 
									style = {externalGraphic:"img/holes/hole750.svg",pointRadius: cableRadius};
									break;
								default:
									style = null;								
								}
								var attributes = {
										'cable_id': selectedCable,
										'voltage_grade': parseInt(voltage),
										'cable_diameter': cableDiameter,
										'pp_segment_id': e['feature'].attributes['pp_segment_id']
								}
								if(style){
									var cp = new OpenLayers.Feature.Vector(e['center'], attributes);
									cp.renderIntent = 'default';
									cp.attributes['well_id'] = e['feature'].attributes['well_id'];
									cp.attributes['hole_id'] = e['feature'].attributes['static_id'];
									var cpCopy = new OpenLayers.Feature.Vector(e['centerCopy'], attributes);
									cpCopy.renderIntent = 'default';
									cpCopy.attributes['well_id'] = e['featureCopy'].attributes['well_id'];
									cpCopy.attributes['hole_id'] = e['featureCopy'].attributes['static_id'];
									cp.state = OpenLayers.State.INSERT;
									cpCopy.state = OpenLayers.State.INSERT;
									Global.locus.layers.elevationLayer.cableLayer.addFeatures([cp,cpCopy]);
						    		Global.locus.constant.saveEventsCount = 1;
						    		Global.locus.save.saveStrategy[5].save([cp,cpCopy]);
				    				var response = setInterval(function(){
				    					if(Global.locus.save.state['剖面电缆'] == 'success'){
				    	    				$.post(Global.option.URL + 'updatePro.action?SId=' + e['feature'].attributes['static_id']
				    	    					+ '&wcSeg=' + selectedCable);
											Global.locus.flag.matchCableFlag = false;
								    		Plugins.menu.refresh();
								    		Plugins.FLAG.MAP['M1']=true;
								    		Plugins.menu.update();
											Plugins.dialog.remove();
				    						clearInterval(response);
				    					}
				    					if(Global.locus.save.state['剖面电缆'] == 'fail'){
				    						clearInterval(response);
				    					}
				    				},500);
								}else{
									Plugins.dialog.warm({
										'title'	: '错误信息'
									},'所穿电缆电压等级有误，请检查数据',{});
								}
					        }
					    }
					}
			    });
	        }
	    }
    }
}	

Global.locus.method.pipe_property_unselect = function(e){  	
}

Global.locus.method.cable_select = function(e){
	if(Global.locus.flag.slide){
		Plugins.slide_form.highlight(e.feature.attributes['cable_id']);
	}
}

Global.locus.method.cable_unselect = function(e){  
	if(Global.locus.flag.slide){
		Plugins.slide_form.unhighlight();
	}	
}


/*添加剖面函数*/
Global.locus.method.addProfile = function(feature){
	if(feature.attributes['object_type'] != '1020101' && feature.attributes['object_type'] != '1020103'){
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请选择工井'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}else{
		if(!Global.locus.flag.addProfilePpSegment){
			Global.locus.profile.unit = feature.attributes['static_id'];
		}
		Global.locus.flag.addProfilePpSegment = true;
		Global.locus.flag.addProfileWell = false;
	}
}

Global.locus.method.createProfile = function(feature){
	var type = feature.attributes['object_type'];
	if(type=='1020211' || type=='1020212' || type=='1020213' ||
			type=='1020214' || type=='1020215' || type=='1020216'){
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+feature.attributes['object_type']
	        	+"&staticId="+feature.attributes['static_id'],
	        success: ppSegmentProperty_success,
	        failure: (function(e){
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': e.responseText
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        	}
	        )
	    });
	    function ppSegmentProperty_success(req){
	    	var format = new OpenLayers.Format.JSON();
	        var response = format.read(req.responseXML || req.responseText);
	        if(response['startBuilding'] && response['endBuilding']){
		        if(response['startBuilding'] == Global.locus.profile.unit || 
		        		response['endBuilding'] == Global.locus.profile.unit){
		        	Global.locus.flag.addProfilePpSegment = false;
		    		Plugins.dialog.show({
		    			title: '添加剖面图', 
		    			feature: feature,
		    			obj: response,
		    			url: Global.option.URL+'/template/property/cross.jsp'
		    		},{
		    			'submit' : function(e){
		    				var obj = [];
		    				obj[0] = new Object(), obj[1] = new Object();
		    				var changedFlag = false;
		    				var Pwidth = parseFloat($('#plengthx').val())*Global.constant.scaleMM;
		    				var Phigh = parseFloat($('#plengthy').val())*Global.constant.scaleMM;
		    				obj[0].vertex = e['feature'].geometry.components[0].clone();
		    				obj[1].vertex = e['feature'].geometry.components[e['feature'].geometry.components.length-1].clone();
		    				if(obj[0].vertex.x > obj[1].vertex.x || 
		    						(obj[0].vertex.x == obj[1].vertex.x && obj[0].vertex.y > obj[1].vertex.y)){
		    					var temp = obj[0].vertex.clone();
		    					obj[0].vertex = obj[1].vertex.clone();
		    					obj[1].vertex = temp.clone();
		    					changedFlag = true;
		    				}
		    				var Rdirection = Math.atan((obj[1].vertex.y-obj[0].vertex.y)/(obj[1].vertex.x-obj[0].vertex.x));
		    				obj[0].points = [], obj[1].points = [];
		    	        	var profileDistance = obj[1].vertex.distanceTo(obj[0].vertex);
		    				var distance = profileDistance/4;
		    				obj[0].points[0] = new OpenLayers.Geometry.Point(
		    						obj[0].vertex.x+distance*Math.cos(Rdirection),
		    						obj[0].vertex.y+distance*Math.sin(Rdirection));
		    				obj[1].points[0] = new OpenLayers.Geometry.Point(
		    						obj[1].vertex.x-distance*Math.cos(Rdirection),
		    						obj[1].vertex.y-distance*Math.sin(Rdirection));
		    				var length;
		    				for(var i=0;i<3;i++){
		    					length = (i+1)%2*Pwidth+i%2*Phigh;
		    					obj[0].dx = length * Math.cos(Rdirection+Math.PI/2*i);
		    					obj[0].dy = length * Math.sin(Rdirection+Math.PI/2*i);
		    					obj[1].dx = length * Math.cos(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
		    					obj[1].dy = length * Math.sin(Rdirection+Math.PI/2*i-Math.PI);//非旋转换'+'
		    					obj[0].points[i+1] = new OpenLayers.Geometry.Point(obj[0].points[i].x+obj[0].dx,obj[0].points[i].y+obj[0].dy);
		    					obj[1].points[i+1] = new OpenLayers.Geometry.Point(obj[1].points[i].x+obj[1].dx,obj[1].points[i].y+obj[1].dy);
		    				}
		    				obj[0].points[4] = obj[0].points[0].clone();
		    				obj[1].points[4] = obj[1].points[0].clone();
		    				
		    				obj[0].polygons = [], obj[1].polygons = [];
		    				obj[0].polygons[0] = new OpenLayers.Geometry.Polygon(
		    						new OpenLayers.Geometry.LinearRing(obj[0].points));
		    				obj[1].polygons[0] = new OpenLayers.Geometry.Polygon(
		    						new OpenLayers.Geometry.LinearRing(obj[1].points));		    				
		    				var bottomSpan = Pwidth/4;
		    				var bottomLong = 1.4*bottomSpan;
		    				var bottomLines = [], bottomPoint = [], bottomEndPoint = [];
		    				var dx = bottomLong * Math.cos(Rdirection+Math.PI/4);
		    				var dy = bottomLong * Math.sin(Rdirection+Math.PI/4);
		    				var bottomNum = parseInt(Pwidth/bottomSpan)+1;
		    				var spanDx, spanDy;
		    				for(var i=0;i<bottomNum;i++){
		    					spanDx = bottomSpan*i*Math.cos(Rdirection+Math.PI);
		    					spanDy = bottomSpan*i*Math.sin(Rdirection+Math.PI);
		    					bottomPoint[i] = new OpenLayers.Geometry.Point(
		    							obj[0].points[3].x-spanDx,obj[0].points[3].y-spanDy);
		    					bottomEndPoint[i] = new OpenLayers.Geometry.Point(
		    							bottomPoint[i].x+dx,bottomPoint[i].y+dy);
		    					bottomLines[i] = new OpenLayers.Geometry.LineString(
		    							[bottomPoint[i],bottomEndPoint[i]]); 
		    				}
		    				obj[0].bottomLine = new OpenLayers.Feature.Vector(
		    						new OpenLayers.Geometry.MultiLineString(bottomLines));		    				
		    				dx = bottomLong * Math.cos(Rdirection-Math.PI/4);
		    				dy = bottomLong * Math.sin(Rdirection-Math.PI/4);
		    				for(var i=0;i<bottomNum;i++){
		    					spanDx = bottomSpan*i*Math.cos(Rdirection);
		    					spanDy = bottomSpan*i*Math.sin(Rdirection);
		    					bottomPoint[i] = new OpenLayers.Geometry.Point(
		    							obj[1].points[3].x-spanDx,obj[1].points[3].y-spanDy);
		    					bottomEndPoint[i] = new OpenLayers.Geometry.Point(
		    							bottomPoint[i].x+dx,bottomPoint[i].y+dy);
		    					bottomLines[i] = new OpenLayers.Geometry.LineString(
		    							[bottomPoint[i],bottomEndPoint[i]]); 
		    				}
		    				obj[1].bottomLine = new OpenLayers.Feature.Vector(
		    						new OpenLayers.Geometry.MultiLineString(bottomLines));
		    				
		    				/*判断是矩形、拱形还是圆形剖面*/
		    				switch(Global.locus.profile.shape){
		    				case 'rectangle':
		    					break;
		    				case 'arch':		    					
			    				var rW= Pwidth/2; // horizontal radius
			    				var rH= Phigh; // vertical radius
			    				var rotation = Rdirection; //Rotation
			    				var xCent, yCent, xEnd, yEnd, xCopyCent, yCopyCent, xCopyEnd, yCopyEnd;
			    				xCent = obj[0].vertex.x+(rW+distance)*Math.cos(Rdirection);
			    				yCent = obj[0].vertex.y+(rW+distance)*Math.sin(Rdirection);
			    				xEnd = obj[0].vertex.x+distance*Math.cos(Rdirection);
			    				yEnd = obj[0].vertex.y+distance*Math.sin(Rdirection);
			    				xCopyCent = obj[1].vertex.x-(rW+distance)*Math.cos(Rdirection);
			    				yCopyCent = obj[1].vertex.y-(rW+distance)*Math.sin(Rdirection);
			    				xCopyEnd = obj[1].vertex.x-distance*Math.cos(Rdirection);
			    				yCopyEnd = obj[1].vertex.y-distance*Math.sin(Rdirection);
			    				var list = [], listCopy = [];
			    				list[0] = new OpenLayers.Geometry.Point(xCent,yCent);
			    				listCopy[0] = new OpenLayers.Geometry.Point(xCopyCent,yCopyCent);
			    				for (var angle = 0; angle <Math.PI; angle += 0.1 ) {//angle控制精度，越小，点个数越多
			    					x = xCent + rW * Math.cos(angle) * Math.cos(rotation) - rH * Math.sin(angle) * Math.sin(rotation);
			    					y = yCent + rW * Math.cos(angle) * Math.sin(rotation) + rH * Math.sin(angle) * Math.cos(rotation);
			    				    list.push(new OpenLayers.Geometry.Point(x, y));
			    				};
			    				for (var angle = Math.PI; angle < 2*Math.PI; angle += 0.1 ) {//angle控制精度，越小，点个数越多
			    					x = xCopyCent + rW * Math.cos(angle) * Math.cos(rotation) - rH * Math.sin(angle) * Math.sin(rotation);
			    					y = yCopyCent + rW * Math.cos(angle) * Math.sin(rotation) + rH * Math.sin(angle) * Math.cos(rotation);
			    				    listCopy.push(new OpenLayers.Geometry.Point(x, y));
			    				};
			    				list.push(new OpenLayers.Geometry.Point(xEnd, yEnd));
			    				listCopy.push(new OpenLayers.Geometry.Point(xCopyEnd, yCopyEnd));
			    				obj[0].polygons[1] = new OpenLayers.Geometry.Polygon(new OpenLayers.Geometry.LinearRing(list));
			    				obj[1].polygons[1] = new OpenLayers.Geometry.Polygon(new OpenLayers.Geometry.LinearRing(listCopy));
			    				break;
		    				case 'round':
		    					var center, centerCopy;
			    				center = new OpenLayers.Geometry.Point(
			    						obj[0].vertex.x+(Pwidth/2+distance)*Math.cos(Rdirection)+Phigh/2*Math.cos(Rdirection+Math.PI/2),
			    						obj[0].vertex.y+(Pwidth/2+distance)*Math.sin(Rdirection)+Phigh/2*Math.sin(Rdirection+Math.PI/2));
			    				centerCopy = new OpenLayers.Geometry.Point(
			    						obj[1].vertex.x-(Pwidth/2+distance)*Math.cos(Rdirection)+Phigh/2*Math.cos(Rdirection-Math.PI/2),
			    						obj[1].vertex.y-(Pwidth/2+distance)*Math.sin(Rdirection)+Phigh/2*Math.sin(Rdirection-Math.PI/2));
			    				obj[0].polygons[1] = OpenLayers.Geometry.Polygon.createRegularPolygon(center,Pwidth/2,30);
			    				obj[1].polygons[1] = OpenLayers.Geometry.Polygon.createRegularPolygon(centerCopy,Pwidth/2,30);
		    					break;
		    				}
		    				obj[0].profile = new OpenLayers.Geometry.MultiPolygon(obj[0].polygons);
		    				obj[1].profile = new OpenLayers.Geometry.MultiPolygon(obj[1].polygons);
		    				obj[0].Pfeature = new OpenLayers.Feature.Vector(obj[0].profile);
		    				obj[1].Pfeature = new OpenLayers.Feature.Vector(obj[1].profile);
		    				var staticId = Plugins.get_staticid();	
		    				for(var i=0;i<2;i++){
		    					obj[i].Pfeature.attributes['pp_segment_id'] = e['feature'].attributes['static_id'];
		    					obj[i].Pfeature.attributes['object_type'] = '1010501';
		    					obj[i].bottomLine.attributes['object_type'] = '1000501';
		    					obj[i].Pfeature.attributes['static_id'] = staticId;
		    					obj[i].Pfeature.state = OpenLayers.State.INSERT;
		    					obj[i].bottomLine.state = OpenLayers.State.INSERT;
		    				}
		    				if(changedFlag){
		    					obj[1].Pfeature.attributes['well_id'] = e['obj']['startBuilding']; 
			    				obj[1].bottomLine.attributes['well_id'] = e['obj']['startBuilding'];
			    				obj[1].bottomLine.attributes['pp_segment_id'] = e['feature'].attributes['static_id']; 
			    				obj[0].Pfeature.attributes['well_id'] = e['obj']['endBuilding'];
			    				obj[0].bottomLine.attributes['well_id'] = e['obj']['endBuilding'];
			    				obj[0].bottomLine.attributes['pp_segment_id'] = e['feature'].attributes['static_id'];
		    				}else{
			    				obj[0].Pfeature.attributes['well_id'] = e['obj']['startBuilding']; 
			    				obj[0].bottomLine.attributes['well_id'] = e['obj']['startBuilding'];
			    				obj[0].bottomLine.attributes['pp_segment_id'] = e['feature'].attributes['static_id']; 
			    				obj[1].Pfeature.attributes['well_id'] = e['obj']['endBuilding'];
			    				obj[1].bottomLine.attributes['well_id'] = e['obj']['endBuilding'];
			    				obj[1].bottomLine.attributes['pp_segment_id'] = e['feature'].attributes['static_id'];
		    				}
		    				Plugins.dialog.add('profile[0].ppSegmentId', e['feature'].attributes['static_id'], 'int');
				        	Plugins.dialog.add('profile[0].wellId', e['obj']['startBuilding'], 'int');
				        	Plugins.dialog.add('profile[0].mirrorWellId', e['obj']['endBuilding'], 'int');
		    				Plugins.dialog.add('profile[0].objectType', '1010501', 'int');
		    				Plugins.dialog.add('profile[0].staticId', staticId, 'int');
		    				Global.locus.layers.elevationLayer.profileLayer.addFeatures([obj[0].Pfeature,obj[1].Pfeature,obj[0].bottomLine,obj[1].bottomLine]);
    						Plugins.menu.refresh();
    						Plugins.FLAG.MAP['M1']=true;
    						Plugins.menu.update();
		    				Global.locus.constant.saveEventsCount = 1;
		    				Global.locus.save.saveStrategy[3].save([obj[0].Pfeature,obj[1].Pfeature,obj[0].bottomLine,obj[1].bottomLine]);

		    				var response = setInterval(function(){
		    					if(Global.locus.save.state['剖面'] == 'success'){
		    						$.post(Global.option.URL + 'saveProperties.action', Base.request.get_param(),function(res){
		    						});		    						
		    						Plugins.dialog.remove();
		    						clearInterval(response);
		    					}
		    					if(Global.locus.save.state['剖面'] == 'fail'){
		    						clearInterval(response);
		    					}
		    				},500);
		    			}
		    		});
		        }else{
		        	Plugins.dialog.show({
		        		'title': '错误信息',
		        		'text': '所选管沟段未经过所选工井，请确认后重新选择管沟段'
		        	},{
		        		'submit' : function(e){
		        			Plugins.dialog.remove();
		        		}
		        	});
		        }
	        }else{
	        	Plugins.dialog.show({
	        		'title': '错误信息',
	        		'text': '所选管沟段属性有误，请检查后重新选择管沟段'
	        	},{
	        		'submit' : function(e){
	        			Plugins.dialog.remove();
	        		}
	        	});
	        }			
	    }
	}else{
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请选择电缆管沟'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}
}

/*画管沟函数*/	
Global.locus.method.addPipe = function(feature){
	function chooseType(start,startType,end,endType){
		Plugins.dialog.show({
			title : '请选择管沟类型',
			url : Global.option.URL + '/template/property/cableline_type.jsp',
			width : 420
		}, {
			'submit' : function(e) {
				var type = Plugins.dialog.getval('#linetype');
				Plugins.dialog.remove();
				pipePropertyDialog(type,start,startType,end,endType);				
			}
		});
	}
	function pipePropertyDialog(pipeType,start,startType,end,endType){
		
		Plugins.dialog.show({
			title	: '属性', 
			url		: Global.option.URL+'/getProperty.action',
			param	: {
				type	: pipeType
			}
		}, 
		{
			'success' : function(){
				Plugins.dialog.lock('unlocked');
				var fakeNum = 0;
				if(startType != '1020103'){
					Plugins.dialog.set('#start', start.attributes['static_id']);
					Plugins.dialog.set('#startName', start.attributes['name']);
					Plugins.dialog.set('#startTypeName', '工井');
				}else{
					Plugins.dialog.set('#startName', $('#feature_name').val()+'0');
					Plugins.dialog.set('#startTypeName', '虚拟工井');
					fakeNum++;
				}						
				Plugins.dialog.set('#start_type', startType);
				if(endType != '1020103'){
					Plugins.dialog.set('#end', end.attributes['static_id']);
					Plugins.dialog.set('#endName', end.attributes['name']);
					Plugins.dialog.set('#endTypeName', '工井');
				}else{
					Plugins.dialog.set('#endName', $('#feature_name').val()+fakeNum);
					Plugins.dialog.set('#endTypeName', '虚拟工井');
				}					
				Plugins.dialog.set('#end_type', endType);
			},
			'submit' : function(e){
				if($('#feature_name').val()==""){							
					Plugins.dialog.warm(e, '名称不能为空，请填写后重新提交' ,{});	
				}else{				
					var staticId = Plugins.get_staticid();
					var points = [],style;
					points[0] = new OpenLayers.Geometry.Point(start.geometry.x, start.geometry.y);
					points[1] = new OpenLayers.Geometry.Point(end.geometry.x, end.geometry.y);
					if(pipeType == '1020203'){
						style = Global.style.form[19];
					}else{
						style = Global.style.form[13];
					}
					var pipeGeometry;
					if(Global.locus.draw.middleUnit){
						var middle = new OpenLayers.Geometry.Point(Global.locus.draw.middleUnit.geometry.x,
								Global.locus.draw.middleUnit.geometry.y);
						pipeGeometry = new OpenLayers.Geometry.LineString(
								[points[0],middle,points[1]]
						);	
					}else{
						pipeGeometry = new OpenLayers.Geometry.LineString(points);
					}
					var pipe = new OpenLayers.Feature.Vector(pipeGeometry);
					pipe.renderIntent = 'default';
					pipe.attributes['static_id'] = staticId;
					pipe.attributes['name'] = $('#feature_name').val()
					pipe.attributes['object_type'] = pipeType;
					pipe.attributes['z_index'] = Global.constant.pp_segment;
					pipe.state = OpenLayers.State.INSERT;
					Global.locus.layers.buildingLayer.addFeatures([pipe]);
					var pipeName;
					switch(pipeType){
					case '1020203' :
						pipeName = 'virtualpipe';
						break;
					case '1020211' :
						pipeName = 'rackpipe';
						break;
					case '1020212' :
						pipeName = 'bridge';
						break;
					case '1020213' :
						pipeName = 'channel';
						break;
					case '1020214' :
						pipeName = 'buried';
						break;
					case '1020215' :
						pipeName = 'tunnel';
						break;
					case '1020216' :
						pipeName = 'jacking';
						break;
					}
					var fakeWell = [], fakeStaticId = [];
					if(startType == '1020103'){
						fakeWell[0] = creatInventedUnit(start);
						fakeStaticId[0] = staticId + 1;
						Plugins.dialog.set('#start', fakeStaticId[0]);
					}
					if(endType == '1020103'){
						if(fakeWell[0]){
							fakeWell[1] = creatInventedUnit(end);
							fakeStaticId[1] = staticId + 2;
							Plugins.dialog.set('#end', fakeStaticId[1]);
						}else{
							fakeWell[0] = creatInventedUnit(end);
							fakeStaticId[0] = staticId + 1;
							Plugins.dialog.set('#end', fakeStaticId[0]);
						}						
					}
					Plugins.dialog.set('#staticId', staticId);
					var featureClone = [];
					for(var i=0;i<fakeWell.length;i++){
			        	var projectTo = new OpenLayers.Projection("EPSG:4326"); //WGS 1984 projection
			            var projectSource = Global.locus.map.getProjectionObject(); //The map projection
			            featureClone[i] = fakeWell[i].clone();
				        featureClone[i].geometry.transform(projectSource, projectTo);
				        fakeWell[i].attributes['name'] = $('#feature_name').val()+i;
				        fakeWell[i].attributes['static_id'] = fakeStaticId[i];
						Plugins.dialog.add('wells['+i+'].staticId', fakeStaticId[i], 'int');
						Plugins.dialog.add('wells['+i+'].name', $('#feature_name').val()+i);
						Plugins.dialog.add('wells['+i+'].objectType', '1020103', 'int');
						Plugins.dialog.add('wells['+i+'].longitude', featureClone[i].geometry.x);
						Plugins.dialog.add('wells['+i+'].latitude', featureClone[i].geometry.y);
						Plugins.dialog.add('wells['+i+'].isVirtualWell', 0, 'int');
					}	 
					var saveArr = [];
					for(i=0;i<fakeWell.length;i++){
						saveArr[i] = fakeWell[i];
					}
					saveArr.push(pipe);
					Global.locus.constant.saveEventsCount = 1;
					Global.locus.save.saveStrategy[1].save(saveArr);
    				var response = setInterval(function(){
    					if(Global.locus.save.state['土建设备'] == 'success'){
    						$.post(Global.option.URL + 'saveProperties.action', Base.request.get_param(),function(res){
    						});
    						Plugins.dialog.remove();
    						Global.locus.flag.addPipe = false;
    						Plugins.menu.refresh();
    						Plugins.FLAG.MAP['M1']=true;
    						Plugins.menu.update();
    						Global.locus.draw.middleUnit = null;
    						clearInterval(response);
    					}
    					if(Global.locus.save.state['土建设备'] == 'fail'){
    						clearInterval(response);
    					}
    				},500);	
				}
			}
		});
	}
	function creatInventedUnit(feature){
		var inventedUnit = feature.clone();
		inventedUnit.style = Global.style.form[10];	
		inventedUnit.attributes['object_type'] = '1020103';
		inventedUnit.attributes['z_index'] = Global.constant.unit;
		inventedUnit.state = OpenLayers.State.INSERT;
		Global.locus.layers.buildingLayer.addFeatures([inventedUnit]);
		return inventedUnit;
	}
	if(!Global.locus.flag.finishAddPipe){
		Global.locus.draw.startUnit = feature;
		Global.locus.flag.finishAddPipe = true;
	}else{
		if(feature.attributes['object_type']=='1020103'){
			Global.locus.draw.middleUnit = feature;
		}else{
			var startType = Global.locus.draw.startUnit.attributes['object_type'];
			var endType = feature.attributes['object_type'];	
			switch(startType){
			case '1020101' :
				if(endType != '1020101'){
					chooseType(Global.locus.draw.startUnit, startType, feature, '1020103');
				}else{
					chooseType(Global.locus.draw.startUnit, startType, feature, endType);
				}			
				break;
			case '1020102' :
				if(endType == '1020102'){
					pipePropertyDialog('1020203',Global.locus.draw.startUnit, '1020103', feature, '1020103');//虚拟管沟段
				}else{
					if(endType == '1020101'){
//						wellPropertyDialog(creatInventedUnit(Global.locus.draw.startUnit));
						chooseType(Global.locus.draw.startUnit, '1020103', feature, endType);
					}else{
				    	Plugins.dialog.show({
				    		'title': '错误信息',
				    		'text': '起点终点选择有误'
				    	},{
				    		'submit' : function(e){
				    			Plugins.dialog.remove();
				    		}
				    	});
					}
				}
				break;
			default :
				if(endType == '1020101'){
					chooseType(Global.locus.draw.startUnit, '1020103', feature, endType);
				}else{
					if(endType != '1020102'){
						chooseType(Global.locus.draw.startUnit, '1020103', feature, '1020103');
					}else{
				    	Plugins.dialog.show({
				    		'title': '错误信息',
				    		'text': '起点终点选择有误'
				    	},{
				    		'submit' : function(e){
				    			Plugins.dialog.remove();
				    		}
				    	});
					}
				}
			}	
			Global.locus.flag.finishAddPipe = false;
		}	
	}
}

/*显示隐藏要素功能函数*/
Global.locus.method.changeFeatureVisibility = function(){
	for(var i=0;i<Global.locus.constant.visible;i++){//根据要选择显示的要素某字段的各个值给数组赋值，需要设置
		var flag = document.getElementById("fVis"+i).checked;
		if(!flag){
			Global.locus.layers.buildingLayer.styleMap.styles["default"].rules[i].setMaxScaleDenominator(1);
			Global.locus.layers.electricalLayer.styleMap.styles["default"].rules[i].setMaxScaleDenominator(1);
		}else{
			Global.locus.layers.buildingLayer.styleMap.styles["default"].rules[i].setMaxScaleDenominator(Global.locus.draw.scale[i]);
			Global.locus.layers.electricalLayer.styleMap.styles["default"].rules[i].setMaxScaleDenominator(Global.locus.draw.scale[i]);	
		}
	}
	Global.locus.layers.buildingLayer.redraw();
	Global.locus.layers.electricalLayer.redraw();
}
	
/*模糊查询功能函数*/
Global.locus.method.vague_search = function(text, layerIndex){
	Global.constant.searchIndex = layerIndex;
	var layer;
	if(layerIndex == 0){
		layer = 'electrical';
	}else{
		layer = 'civil_engineering';
	}
	var filter = new OpenLayers.Filter.Comparison({//比较操作符  
		type: OpenLayers.Filter.Comparison.LIKE,
		property: "name",//查询的字段，需要根据图层设置  
		value: "*"+text+"*"  
	})  		
	var filter_1_0 = new OpenLayers.Format.Filter.v1_0_0();   
	var tempXML = new OpenLayers.Format.XML();           
	var xmlPara = tempXML.write(filter_1_0.write(filter));    		  
	var dataXML = Global.locus.constant.originXML 
			+	'<wfs:Query typeName="OpenGIS:geo_'+layer+'">' + "/n"//查询的图层，需要设置
			+	'<wfs:PropertyName>OpenGIS:name</wfs:PropertyName>' + '/n'//查询的属性字段，需要设置
			+	'<wfs:PropertyName>OpenGIS:object_type</wfs:PropertyName>' + '/n'//查询的属性字段，需要设置
			+	'<wfs:PropertyName>OpenGIS:static_id</wfs:PropertyName>' + '/n'//查询的属性字段，需要设置
			+	'<wfs:PropertyName>OpenGIS:geometry_data</wfs:PropertyName>' + '/n'//查询的几何字段，需要设置
			+	xmlPara
			+	'</wfs:Query>' + "/n"
			+	'</wfs:GetFeature>';
	var request = OpenLayers.Request.POST({  
		url : "http://"+Global.option.ip+":8081/geoserver/wfs",  
		data : dataXML,                     
		callback : Global.locus.method.vague_search_handler  
	});
}

/*模糊查询功能的回调函数*/	
Global.locus.method.vague_search_handler = function(req){
	var gml =  new OpenLayers.Format.GML();
	var features = gml.read(req.responseText);
	if(features.length == 0){
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '未检测到匹配设备'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
	}else{	
		var theHTML = '';
	    for(var i=0;i<features.length;i++){
	    	theHTML += '<li><span onclick="Global.locus.method.exact_search(\''
	    			+ features[i].attributes['static_id']
	    			+ '\','+features[i].attributes['object_type']+')" title='
	    			+ features[i].attributes['static_id']
	    			+ '>' 
	    			+ features[i].attributes['name']
	    			+ '</span> <div class="tcon" ><span onclick="Plugins.dialog.show('
	    			+ '{title: \'属性\', url: Global.option.URL+\'\/getProperty.action\',param:{type:'
	    			+ features[i].attributes['object_type']
	    			+ ',isbn:'
	    			+ features[i].attributes['static_id']
	    			+ '}},{\'submit\':function(e){Base.request.update_pro(e, '
	    			+ Global.constant.searchIndex
	    			+ ', \'unit\');}})">属性</span></div></li>';
		}
	    document.getElementById("search_result").innerHTML = theHTML;
	    Plugins.init_search_result();
	    
		for(var i=0;i<features.length;i++){
			if(features[i].attributes['name']==document.getElementById("st").value){
				Global.locus.method.trans(features[i]);
				i=features.length;
			}
		}
	}	  	  
}
	
/*查询功能的定位函数*/
Global.locus.method.trans = function(obj){
	var geometry = obj.geometry;
	if(!geometry.x){
		var xmin,xmax,ymin,ymax;
		xmin = obj.geometry.components[0].x;
		xmax = xmin;
		ymin = obj.geometry.components[0].y;
		ymax = ymin;
		for(var i=1;i<obj.geometry.components.length;i++){
			if(obj.geometry.components[i].x<xmin){
				xmin = obj.geometry.components[i].x;
			}else{
				if(obj.geometry.components[i].x>xmax){
					xmax = obj.geometry.components[i].x;
				}
			}
			if(obj.geometry.components[i].y<ymin){
				ymin = obj.geometry.components[i].y;
			}else{
				if(obj.geometry.components[i].y>ymax){
					ymax = obj.geometry.components[i].y;
				}
			}
		}
		geometry = new OpenLayers.Geometry.Point((xmin+xmax)/2,(ymin+ymax)/2);
	}
	var foundPosition = new OpenLayers.LonLat(geometry.x, geometry.y).transform(
		new OpenLayers.Projection("EPSG:4326"),
		Global.locus.map.getProjectionObject()
	);
	Global.locus.map.setCenter(foundPosition, Global.constant.zoomLevel);
	if(Global.constant.searchIndex == 0){
		for(var i=0;i<Global.locus.layers.electricalLayer.features.length;i++){
			if(Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == obj.attributes['static_id']){
				Global.locus.select.control.unselectAll();
				Global.locus.select.control.select(Global.locus.layers.electricalLayer.features[i]);
			}
		}
	}else{
		for(var i=0;i<	Global.locus.layers.buildingLayer.features.length;i++){
			if(	Global.locus.layers.buildingLayer.features[i].attributes['static_id'] == obj.attributes['static_id']){
				Global.locus.select.control.unselectAll();
				Global.locus.select.control.select(Global.locus.layers.buildingLayer.features[i]);
			}
		}
	}
	Plugins.init_search_result();
}

/*精确查询功能函数*/
Global.locus.method.exact_search = function(id, type){
	var findFlag = false;
	if (type == 1010201 || type == 1010202 || type == 1010203 ||
			type == 1010204 || type == 1010205 || type == 1010206 ||
			type == 1010402 || type == 1010403 || type == 1010601 ||
			type == 1010602 || type == 1010207 || type == 1010208){
		for(var i=0;i<Global.locus.layers.electricalLayer.features.length;i++){
			if(Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == id &&
					Global.locus.layers.electricalLayer.features[i].attributes['object_type'] == type){
				Global.constant.searchIndex = 0;
				Global.locus.method.trans(Global.locus.layers.electricalLayer.features[i]);
				findFlag = true;
				break;
			}			
		}
		if(!findFlag){
	    	Plugins.dialog.show({
	    		'title': '错误信息',
	    		'text': '未检测到设备'
	    	},{
	    		'submit' : function(e){
	    			Plugins.dialog.remove();
	    		}
	    	});
		}
	}else{
		for(var i=0;i<	Global.locus.layers.buildingLayer.features.length;i++){
			if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] == id &&
					Global.locus.layers.buildingLayer.features[i].attributes['object_type'] == type){
				Global.constant.searchIndex = 1;
				Global.locus.method.trans(Global.locus.layers.buildingLayer.features[i]);
				findFlag = true;
				break;
			}			
		}
		if(!findFlag){
	    	Plugins.dialog.show({
	    		'title': '错误信息',
	    		'text': '未检测到设备'
	    	},{
	    		'submit' : function(e){
	    			Plugins.dialog.remove();
	    		}
	    	});
		}
	}
}

/*按坐标查询功能*/
Global.locus.method.cr_search = function(text){
    var values = text.split(",");
    if (values.length !== 2) {
    	Plugins.dialog.show({
    		'title': '错误信息',
    		'text': '请按照x,y的形式输入'
    	},{
    		'submit' : function(e){
    			Plugins.dialog.remove();
    		}
    	});
        values = null;
    } else {
        values[0] = parseFloat(values[0]);
        values[1] = parseFloat(values[1]);
        if (isNaN(values[0]) || isNaN(values[1])) {
        	Plugins.dialog.show({
        		'title': '错误信息',
        		'text': '输入的x,y必须是数字'
        	},{
        		'submit' : function(e){
        			Plugins.dialog.remove();
        		}
        	});
            values = null;
        } else {
		var foundPosition = new OpenLayers.LonLat(values[0], values[1]).transform(
			new OpenLayers.Projection("EPSG:4326"),
			Global.locus.map.getProjectionObject()
		);
		Global.locus.map.setCenter(foundPosition, Global.constant.zoomLevel);        
        } 
    }    
}

/*释放触发按钮*/
Global.locus.method.controlRelease = function(){
	for(i=0;i<Global.locus.draw.number;i++){
		if(Global.locus.draw.controls[i].active){
			Global.locus.draw.controls[i].deactivate();
			break;
		}	
	}
	for(key in Global.locus.controls) {
		if(Global.locus.controls[key].active && key != "pan"){
			Global.locus.controls[key].deactivate();
			break;
		}
	}
	if(Global.locus.copy.dragControl.active){
		Global.locus.copy.dragControl.deactivate();	
	}	
}

/*标识、按钮状态复位*/
Global.locus.method.reset = function(){
	Global.locus.method.controlRelease();
	Global.locus.flag.cableToPipe = false;
	Global.locus.flag.cableToPipeStart = false;
	Global.locus.flag.addProfileWell = false;
	Global.locus.flag.addProfilePpSegment = false;
	Global.locus.flag.addSingleHoleFlag = false;
	Global.locus.flag.addRegularHoleFlag = false;
	Global.locus.flag.addBracketFlag = false;
	Global.locus.flag.matchCableFlag = false;
	Global.locus.flag.addSurveyFlag = false;
	Global.locus.flag.labelFlag = false;
	Global.locus.flag.finishList = false;
	Global.locus.flag.elevation = false;
	Global.locus.flag.addPipe = false;
	Global.locus.flag.finishAddPipe = false;
	Global.locus.flag.judgeCross = true;
	Global.locus.flag.copy = false;
}

/*显示长宽信息函数*/
Global.locus.method.displaySize = function(feature, width, high){
	var arraylength = feature.geometry.getVertices().length; 

    var preX,preY,nextX,nextY,heading = [],angle = [],position = [];
	for (i=0;i<arraylength;i++){		
		if (i<arraylength-1){
			preX = feature.geometry.getVertices()[i].x;
			preY = feature.geometry.getVertices()[i].y;            	
			nextX = feature.geometry.getVertices()[i+1].x;
			nextY = feature.geometry.getVertices()[i+1].y;
		}else{
			preX = feature.geometry.getVertices()[i].x;
			preY = feature.geometry.getVertices()[i].y;            	
			nextX = feature.geometry.getVertices()[0].x;
			nextY = feature.geometry.getVertices()[0].y;			
		}	
		/*--calculate angle --*/ 	
		heading[i] = Math.atan2(nextY - preY, nextX - preX),
		angle[i] = heading[i] * 180 / Math.PI;
		position[i] = "ct";
		if ( angle[i]>90 && angle[i]<270){
			angle[i] += 180;
			position[i] = "cb";
		}      	
		var point = new OpenLayers.Geometry.Point((preX + nextX)/2, (preY + nextY)/2);
		Global.locus.label.point[i] = new OpenLayers.Feature.Vector(point);		    	                     
		Global.locus.label.point[i].attributes = {
				length: Math.round(((i+1)%2)*width*10)/10//四舍五入保留一位小数
					+Math.round((i%2)*high*10)/10,
					favColor: 'red',
					align: position[i],
					angle: angle[i],
					Select:true
		};
		Global.locus.layers.tempLayer.addFeatures([Global.locus.label.point[i]]);
	}		
}


/*剖面注释绘制函数*/
/*
Global.locus.method.drawList = function(geometry, direction){
	var origin = new OpenLayers.Geometry.Point();
	switch(direction){
		case "rb" : {	
			Global.locus.controls["list"].insertDirectionLength(-180, 160);
			Global.locus.controls["list"].insertDirectionLength(90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(0, 160);
			Global.locus.controls["list"].insertDirectionLength(-90, Global.locus.label.count*20);			
			origin.x = geometry.x-160;
			origin.y = geometry.y+Global.locus.label.count*20;
			}break;
		case "rt" : {
			Global.locus.controls["list"].insertDirectionLength(-90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(-180, 160);
			Global.locus.controls["list"].insertDirectionLength(90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(0, 160);
			origin.x = geometry.x-160;
			origin.y = geometry.y;
			}break;		
		case "lt" : {		
			Global.locus.controls["list"].insertDirectionLength(0, 160);		
			Global.locus.controls["list"].insertDirectionLength(-90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(-180, 160);
			Global.locus.controls["list"].insertDirectionLength(90, Global.locus.label.count*20);
			origin.x = geometry.x;
			origin.y = geometry.y;			
			}break;
		case "lb" : {		
			Global.locus.controls["list"].insertDirectionLength(90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(0, 160);
			Global.locus.controls["list"].insertDirectionLength(-90, Global.locus.label.count*20);
			Global.locus.controls["list"].insertDirectionLength(-180, 160);			
			origin.x = geometry.x;
			origin.y = geometry.y+Global.locus.label.count*20;
			}break;				
	}		
	var list_line = [];
	var list_label = [];
	for(var i=0;i<Global.locus.label.count-1;i++){
		var start_point = new OpenLayers.Geometry.Point(origin.x, origin.y - (i+1)*20);
		var end_point = new OpenLayers.Geometry.Point(origin.x+160, origin.y - (i+1)*20);
		var line = new OpenLayers.Geometry.LineString([start_point, end_point]);
		list_line[i] = new OpenLayers.Feature.Vector(line);
		list_line[i].attributes['well_id'] = Global.locus.profile.unit;
		list_line[i].attributes['pp_segment_id'] = Global.locus.profile.pp_segment;
		list_line[i].state = OpenLayers.State.INSERT;
		Global.locus.layers.elevationLayer.listLayer.addFeatures(list_line[i]);
	}
	var string = '';
	for(var i=0;i<Global.locus.response.length;i++){
		if(Global.locus.response[i]['wirecableSegmentId']){
			string = string + Global.locus.response[i]['wirecableSegmentId'] + ',';
		}
	}
    OpenLayers.Request.GET({
        url: "getProJsonOfCables.action?SIds="+string,
        success: cableProperty_success,
        failure: (function(e){
        	Plugins.dialog.show({
        		'title': '错误信息',
        		'text': e.responseText
        	},{
        		'submit' : function(e){
        			Plugins.dialog.remove();
        		}
        	});
        	}
        )
    });
    function cableProperty_success(req){
		var format = new OpenLayers.Format.JSON();
	    var response = format.read(req.responseXML || req.responseText);
    	for(var i=0,j=0;i<Global.locus.label.count;i++){													
    		var label_point = new OpenLayers.Geometry.Point(origin.x+80, origin.y - (i+1)*20+10);
    		list_label[i] = new OpenLayers.Feature.Vector(label_point);
    		if(Global.locus.response[i]['wirecableSegmentId']){
    			list_label[i].attributes['cable_name'] = response[j]['name'];
    			j++;
    		}
    		list_label[i].attributes['pipe_hole_id'] = Global.locus.response[i]['pipeCode'] ;
    		list_label[i].attributes['color'] = "black" ;
    		list_label[i].attributes['is_available'] = Global.locus.response[i]['isAvailable'] ;
    		list_label[i].attributes['well_id'] = Global.locus.profile.unit;
    		list_label[i].attributes['pp_segment_id'] = Global.locus.profile.pp_segment;
    		list_label[i].state = OpenLayers.State.INSERT;
    		Global.locus.layers.elevationLayer.labelLayer.addFeatures(list_label[i]);
    	}
    	Global.locus.flag.labelFlag = false;
    } 			
}
*/

/*地图注记显示隐藏*/
Global.locus.method.lable_switch = function(){
	if(!Global.locus.flag.labelDisplayFlag){
		Global.locus.layers.buildingLayer.styleMap = Global.style.buildingStyle_label;
		Global.locus.layers.electricalLayer.styleMap = Global.style.buildingStyle_label;
		Global.locus.layers.buildingLayer.redraw();
		Global.locus.layers.electricalLayer.redraw();
	    Global.locus.flag.labelDisplayFlag = true;
	}else{	
		Global.locus.layers.buildingLayer.styleMap = Global.style.buildingStyle_nolabel;
		Global.locus.layers.electricalLayer.styleMap = Global.style.buildingStyle_nolabel;
		Global.locus.layers.buildingLayer.redraw();	
		Global.locus.layers.electricalLayer.redraw();
	    Global.locus.flag.labelDisplayFlag = false;
	}
}

/*打开立视图*/
Global.locus.method.openElevation = function(e) {
	var eleFilter = new OpenLayers.Filter.Comparison({
		type: OpenLayers.Filter.Comparison.EQUAL_TO,
		property: "well_id",
		value: e.feature.attributes['static_id']
	});
	for(var key in Global.locus.layers.elevationLayer){
		Global.locus.layers.elevationLayer[key].filter = eleFilter;
		Global.locus.layers.elevationLayer[key].refresh({force: true});
	}
}

/*关闭立视图*/
Global.locus.method.closeElevation = function() {
	var eleFilter = new OpenLayers.Filter.Comparison({
		type: OpenLayers.Filter.Comparison.EQUAL_TO,
		property: "well_id",
		value: 0
	});
	for(var key in Global.locus.layers.elevationLayer){
		Global.locus.layers.elevationLayer[key].filter = eleFilter;
		Global.locus.layers.elevationLayer[key].refresh({force: true});
	}	 	
}
// 跳转到接线图
Global.locus.method.goto_connect_line = function () {
        Plugins.func_tabs(".midtabs", function(obj) {
            Plugins.change_content(obj);
        }, $('#tab_wire'));

        $.post(Global.option.URL + 'getSideBarJson.action', {
            //'id'    : treeNode.id,
            'level' : 0
        }, function(data) {
            slide.set_data(data);
        }, 'json');

        slide.slide_in();
    }
/*前台功能选择接口一*/
Global.locus.method.toggleControl = function(element) {
	for(key in Global.locus.controls) {
		if(element == key) {
			Global.locus.controls[key].activate();
		} else {
			Global.locus.controls[key].deactivate();
		}
	}
	if(element == "pan"){
		Global.locus.select.control.activate();
	}else{
		Global.locus.select.control.deactivate();
	}
}

/*前台功能选择接口二：绘制功能*/
Global.locus.method.toggleDrawControl = function(index){
	for(var i=0;i<Global.locus.draw.number;i++){
		if(i == index){
			Global.locus.draw.styleType=Global.style.form[i];
			Global.locus.draw.unitType=Global.unit[i];
			Global.locus.draw.controls[i].activate();
		} else{
			Global.locus.draw.controls[i].deactivate();
		}
	}
}


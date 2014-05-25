<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String id = request.getParameter("id");
	%>
	<base href="<%=basePath%>">
	<link href="style/profile.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body onload='init(<%=id%>)'> 
	<div id="map"></div>
	<script>
	function init(id){
		var extent = new OpenLayers.Bounds(//初始化视图的边界设置,参照conf.cdi文件中的值
				120.50490375617102, 30.588471912865483,
				120.5859733795086,	30.685245907613851
			);
		var map = new OpenLayers.Map('map');
		map.div.oncontextmenu = function () { return false;};//屏蔽浏览器的右键菜单 
		var baseLayer = new OpenLayers.Layer.Image(
				'Grid',
				'img/blank.png',
				extent,
				new OpenLayers.Size(2000, 1500),
				{
					isBaseLayer: true,
					numZoomLevels: 8
				});
		var layer = new OpenLayers.Layer.Vector();
		map.addLayers([baseLayer,layer]);
		   var mousePositionCtl = new OpenLayers.Control.MousePosition({
		       prefix: '<a target="_blank" ' +
		           'href="http://spatialreference.org/ref/epsg/4326/">' +
		           'EPSG:4326</a> coordinates: ',
		       separator: ' | ',
		       numDigits: 2,
		       emptyString: 'Mouse is not over map.'
		       }
		   );
		   map.addControl(mousePositionCtl);
	    OpenLayers.Request.GET({
	    	url: "getProfileCableHoleByWell.action?WellId="+id,
	        success: profileProperty_success
	    });
		function profileProperty_success(req){
			var format = new OpenLayers.Format.JSON();
		    var response = format.read(req.responseXML || req.responseText);
		    var wellStyle;
		    if(response['well'].countSquare && parseInt(response['well'].countSquare)!=0){
		    	wellStyle = {		    			
			    		externalGraphic : "img/icons/square.svg",
			    		pointRadius : 8
		    	}
		    }else{
		    	wellStyle = {	
			    		externalGraphic : "img/icons/well.svg",
			    		pointRadius : 8
			    };
		    } 
		    var style;
		    var features = [];		    
		    var index = 0;
		    var segment = [], feat = [], hole = [], cable = [];
		    var feat_survery, intersects12;
		    console.log(response)		   
			for(key in response){
				if(key != 'well'){
					for(var i=0;i<response[key].length;i++,index++){												
						switch(key){						
						case 'ccable':
							var cableRadius = 6;
							switch(response[key][i].voltagegrade){
							case 0:
								style = {externalGraphic:"img/holes/communicate.svg",pointRadius: cableRadius};
								break;
							case 1:
								style = {externalGraphic:"img/holes/hole04.svg",pointRadius: cableRadius};
								break;
							case 2: 
								style = {externalGraphic:"img/holes/hole10.svg",pointRadius: cableRadius};
								break;
							case 3: 
								style = {externalGraphic:"img/holes/hole35.svg",pointRadius: cableRadius};
								break;
							case 4: 
								style = {externalGraphic:"img/holes/hole110.svg",pointRadius: cableRadius};
								break;
							case 5: 
								style = {externalGraphic:"img/holes/hole220.svg",pointRadius: cableRadius};
								break;
							case 6: 
								style = {externalGraphic:"img/holes/hole330.svg",pointRadius: cableRadius};
								break;
							case 7: 
								style = {externalGraphic:"img/holes/hole500.svg",pointRadius: cableRadius};
								break;
							case 8: 
								style = {externalGraphic:"img/holes/hole750.svg",pointRadius: cableRadius};
								break;
							}
							break;						
						case 'segment':
							switch(response[key][i].objecttype){
							case 1020211:
								style = {
									strokeWidth : 10,
									strokeColor : '#3399CC'
								};// 排管
								break;
							case 1020212:
								style = {
									strokeWidth : 10,
									strokeColor : '#00FFFF'
								};// 桥架
								break;
							case 1020213:
								style = {
									strokeWidth : 10,
									strokeColor : '#996600'
								};// 沟道
								break;
							case 1020214:
								style = {
									strokeWidth : 10,
									strokeColor : '#00FF00'
								};// 直埋
								break;
							case 1020215:
								style = {
									strokeWidth : 10,
									strokeColor : '#666666'
								};// 隧道
								break;
							case 1020216:
								style = {
									strokeWidth : 10,
									strokeColor : '#003399'
								};// 顶管
								break;
							case 1020203:
								style = {
									strokeOpacity: 0.5,
									strokeWidth : 10,
									strokeColor : '#3399CC'
								};// 虚拟管沟
								break;
							}
							break;
						case 'pointsurvery':
							style = {
								strokeColor : "black",
								strokeOpacity : 0,
								strokeWidth : 1,
								fillOpacity : 0,
								fillColor : "#4FD5D6",
								pointRadius : 6,
								cursor : "inherit"
							};//工井测绘
							break;
						case 'profile':
							style = {
								strokeColor : "black",
						    	strokeOpacity : 0,
						    	strokeWidth : 1,
						    	fillOpacity : 0,
						    	fillColor : "#FFCC00",
						    	pointRadius : 6,
						    	cursor : "inherit"
						   	};	
							break;
						default:
							style = {
								strokeColor : "none",
								strokeOpacity : 1,
								strokeWidth : 1,
								fillOpacity : 0.9,
								fillColor : "none",
								pointRadius : 6,
								cursor : "inherit"
							};
						}						
						features[index] = new OpenLayers.Feature.Vector(
								new OpenLayers.Geometry.fromWKT(response[key][i].geometry),null,style
						);
					}
				}			
			}	
			/*
			 * 剖面弹出框设备样式
			 */		    
		    style_profile = {
					strokeColor : "black",
		    		strokeOpacity : 1,
		    		strokeWidth : 1,
		    		fillOpacity : 0.5,
		    		fillColor : "#FFCC00",
		    		pointRadius : 6,
		    		cursor : "inherit"
		   		};	
		    style_pointsurvery = {
					strokeColor : "black",				
					strokeOpacity : 1,
					strokeWidth : 1,
					fillOpacity : 0.5,
					fillColor : "#4FD5D6",
					pointRadius : 6,
					cursor : "inherit"
				};
		    style_segment = {
					strokeColor : "red",				
					strokeOpacity : 1,
					strokeWidth : 4,
					strokeDashstyle : 'dash',
					cursor : "inherit"
				};
		    style_hole = {
					strokeColor : "black",				
					strokeOpacity : 1,
					strokeWidth : 1,
					fillColor : "white",
					cursor : "inherit"
				};
		    features[features.length] = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.fromWKT(response['well'].geometry),null,wellStyle);
			layer.addFeatures(features);
			if(response['profile'].length > 0 ){
				for(var i=0;i<response['profile'].length;i++){
					feat[i] = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.fromWKT(response['profile'][i].geometry),null,style_profile);				
				}				
				layer.addFeatures(feat);
			}
			if(response['hole'].length > 0){
        		for(var ho=0;ho<response['hole'].length;ho++){
    				hole[ho] = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.fromWKT(response['hole'][ho].geometry),null,style_hole);				
    			}
        		layer.addFeatures(hole);
			}
			if(response['cable'].length > 0){
    			var cableRadius = 6;
    			for(var ca=0;ca<response['cable'].length;ca++){            				
    				switch(response['cable'][ca].voltagegrade){            				
					case 0:
						style_cable = {externalGraphic:"img/holes/communicate.svg",pointRadius: cableRadius};
						break;
					case 1:
						style_cable = {externalGraphic:"img/holes/hole04.svg",pointRadius: cableRadius};
						break;
					case 2: 
						style_cable = {externalGraphic:"img/holes/hole10.svg",pointRadius: cableRadius};
						break;
					case 3: 
						style_cable = {externalGraphic:"img/holes/hole35.svg",pointRadius: cableRadius};
						break;
					case 4: 
						style_cable = {externalGraphic:"img/holes/hole110.svg",pointRadius: cableRadius};
						break;
					case 5: 
						style_cable = {externalGraphic:"img/holes/hole220.svg",pointRadius: cableRadius};
						break;
					case 6: 
						style_cable = {externalGraphic:"img/holes/hole330.svg",pointRadius: cableRadius};
						break;
					case 7: 
						style_cable = {externalGraphic:"img/holes/hole500.svg",pointRadius: cableRadius};
						break;
					case 8: 
						style_cable = {externalGraphic:"img/holes/hole750.svg",pointRadius: cableRadius};
						break;
					}
					cable[ca] = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.fromWKT(response['cable'][ca].geometry),null,style_cable);				
				}
    			layer.addFeatures(cable);
			}
			if(response['segment'].length > 0 ){
				for(var se=0;se<response['segment'].length;se++){      					
        			segment[se] = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.fromWKT(response['segment'][se].geometry),null,style_segment);	
        		}
				layer.addFeatures(segment);
			}
			if(response['pointsurvery'].length > 0 ){
				feat_survery = new OpenLayers.Feature.Vector( new OpenLayers.Geometry.fromWKT(response['pointsurvery'][0].geometry),null,style_pointsurvery);
				layer.addFeatures(feat_survery);
				if(response['profile'].length>0){
					var res = map.getResolution();
		            for(var i=0;i<response['profile'].length;i++){
		            	var feat_be = response['profile'][i].belongsSegment;				            	
		            	var k =i%2;
		            	var f = 0;
						if(k==0){
							f = i;
						}else if(k==1){
							f = i-1;
						}	
						var a = feat[f].geometry.getVertices()[1];
		    			var b = feat[f].geometry.getVertices()[0];
		    		    var pixel = getViewPortPxFromLonLat(a , res);
		    	        var lastPixel = getViewPortPxFromLonLat(b , res);
		            	intersects12 = feat[i].geometry.intersects(feat_survery.geometry);
		            	var j =i+1;
						/*
						 * 判断剖面和测绘是否相交
						 */
						while(intersects12) { 
							if(j<response['profile'].length){
								feat[i].geometry.move(res * (pixel.x - lastPixel.x), 
													res * (lastPixel.y - pixel.y));
								feat[j].geometry.move(res * (pixel.x - lastPixel.x), 
													res * (lastPixel.y - pixel.y)); 
								for(var ho=0;ho<response['hole'].length;ho++){
									var hole_be = response['hole'][ho].belongsSegment;
									if(feat_be == hole_be){
									hole[ho].geometry.move(res * (pixel.x - lastPixel.x), 
														res * (lastPixel.y - pixel.y));            			
									layer.drawFeature(hole); 
									}
								}
								for(var ca=0;ca<response['cable'].length;ca++){
									var cable_be = response['cable'][ca].belongsSegment;
									if(feat_be == cable_be){
									cable[ca].geometry.move(res * (pixel.x - lastPixel.x), 
														res * (lastPixel.y - pixel.y));            			
									layer.drawFeature(cable); 
									}
								}
								layer.drawFeature(feat);
								layer.drawFeature(hole);
								layer.drawFeature(cable);
							}
							intersects12 = feat[i].geometry.intersects(feat_survery.geometry);
						}
					}  
					/*
					 * 函数：经纬度转换为像素点
					 */           
		            function getViewPortPxFromLonLat(lonlat, resolution) {
		                var px = null; 
		                if (lonlat != null) {               
		                    px = new OpenLayers.Pixel(
		                        (1/resolution * (lonlat.x - extent.left)),
		                        (1/resolution * (extent.top - lonlat.y))
		                    );    
		                }
		                return px;
		            }
				}
			}
		    map.zoomToExtent(extent, true);
		    var center = new OpenLayers.LonLat(features[features.length-1].geometry.x, features[features.length-1].geometry.y).transform(
					new OpenLayers.Projection("EPSG:4326"),
					map.getProjectionObject());
		    map.setCenter(center,5);
		    console.log(map)
		}
	}
	
	</script>
	<script src="lib/OpenLayers.js"></script>
</body>
</html>

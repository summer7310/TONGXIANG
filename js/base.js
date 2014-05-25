/*
 * 前台业务处理模块
 * 空间标识		:Base
 * Base.handle	:前台事件业务转发
 * Base.request	:后台交互业务处理
 * 依赖文件：前台页面效果模块&地图模块脚本
 */
var main = document.createElement('script');
line_diagram = document.createElement('script');
wire_diagram = document.createElement('script');
main.type = 'text/javascript';
main.src = 'main.js';
line_diagram.type = 'text/javascript';
line_diagram.src = 'line_diagram.js';
wire_diagram.type = 'text/javascript';
wire_diagram.src = 'wire_diagram.js';

var Base = {};
Base.handle = {};
Base.request = {};
/*
 * 主菜单事件转发
 */
Base.handle.main_menu = function(obj) {// obj:the object clicked
	var cmd = obj.attr('rel');
	switch (cmd) {
	case "m1c1_cmd1":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M1']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.method.toggleControl("pan");
		break;
	case "m1c1_cmd2":
		Global.locus.map.zoomIn();
		break;
	case "m1c1_cmd3":
		Global.locus.map.zoomOut();
		break;
	case "m1c1_cmd4":
		Global.locus.map.zoomToExtent(Global.locus.constant.fullExtent);
		break;
	case "m1c2_cmd1":
		for (var i = 0; i < Global.locus.constant.saveNum; i++) {
			Global.locus.save.saveStrategy[i].save();
		}
		break;
	case "m1c2_cmd2":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M6']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.method.toggleControl("edit");
		break;
	case "m1c2_cmd3":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M7']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.copy.dragControl.activate();
		break;
	case "m1c2_cmd4":
		var selectedFeatures = 0;
		for(var i=0;i<Global.locus.select.control.layers.length;i++){
			selectedFeatures += Global.locus.select.control.layers[i].selectedFeatures.length;
		}
		if(selectedFeatures>0){
			Plugins.menu.refresh();
			Plugins.FLAG.MAP['M8']=true;
			Plugins.menu.update();
			if (Global.locus.copy.dragControl.active) {
				Global.locus.flag.copy = true;
			} else {
				Global.locus.copy.dragControl.activate();
				Global.locus.flag.copy = true;
			}
		}else{
			Plugins.dialog.show({
				'text'	: '未选中数据',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		break;
	case "m1c2_cmd5":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M9']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.flag.addPipe = true;
		break;
	case "m1c3_cmd1":
		if(Plugins.FLAG.MAP['M10']){
			Plugins.FLAG.MAP['M10'] = false;
		}else{
			Plugins.FLAG.MAP['M10'] = true;
		}
		Plugins.menu.update();
		Global.locus.method.lable_switch();
		break;
	case "m1c3_cmd2":
		if(Plugins.FLAG.MAP['M11']){
			var filter = new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "well_id",
				value: 0
			});
			Global.locus.layers.elevationLayer.profileLayer.filter = filter;
			Global.locus.layers.elevationLayer.pipe_propertyLayer.filter = filter;
			Global.locus.layers.elevationLayer.cableLayer.filter = filter;
			Global.locus.layers.elevationLayer.surveyLayer.filter = filter;
			Global.locus.layers.elevationLayer.profileLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.pipe_propertyLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.cableLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.surveyLayer.refresh({force: true});
			Plugins.FLAG.MAP['M11'] = false;
		}else{
			Global.locus.layers.elevationLayer.profileLayer.filter = null;
			Global.locus.layers.elevationLayer.pipe_propertyLayer.filter = null;
			Global.locus.layers.elevationLayer.cableLayer.filter = null;
			Global.locus.layers.elevationLayer.surveyLayer.filter = null;
			Global.locus.layers.elevationLayer.profileLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.pipe_propertyLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.cableLayer.refresh({force: true});
			Global.locus.layers.elevationLayer.surveyLayer.refresh({force: true});
			Plugins.FLAG.MAP['M11'] = true;
		}
		Plugins.menu.update();
		break;
	case "m1c3_cmd3":
		Global.locus.layers.buildingLayer.filter = null;
		Global.locus.layers.electricalLayer.filter = null;
		Global.locus.layers.buildingLayer.refresh({force: true});
		Global.locus.layers.electricalLayer.refresh({force: true});
		break;
	case "m1c3_cmd4_on":
		if(Global.locus.flag.slide){
			Plugins.slide_form.slide_out();
		}else{
			//alert("不存在打开的侧边栏")
			Plugins.dialog.show({
				'text'	: '不存在打开的侧边栏',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		break;
	case "m1c3_cmd4_off":
		Plugins.slide_form.slide_in();
		break;
	case "m1c4_cmd1":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M13']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.flag.addProfileWell = true;
		Global.locus.profile.shape = 'rectangle';
		Global.locus.method.toggleControl("pan");
		break;
	case "m1c4_cmd2":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M14']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.flag.addProfileWell = true;
		Global.locus.profile.shape = 'arch';
		Global.locus.method.toggleControl("pan");
		break;
	case "m1c4_cmd3":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M15']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.flag.addProfileWell = true;
		Global.locus.profile.shape = 'round';
		Global.locus.method.toggleControl("pan");
		break;
	case "m1c4_cmd4":
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
    		Plugins.menu.refresh();
    		Plugins.FLAG.MAP['M16']=true;
    		Plugins.menu.update();
    		Global.locus.method.reset();
    		Global.locus.flag.addSingleHoleFlag = true;
    	}
		break;
	case "m1c4_cmd5":
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
			Plugins.menu.refresh();
			Plugins.FLAG.MAP['M17']=true;
			Plugins.menu.update();
    		Global.locus.method.reset();
    		Global.locus.flag.addRegularHoleFlag = true;
    	}
		break;
	case "m1c4_cmd6":
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
			Plugins.menu.refresh();
			Plugins.FLAG.MAP['M18']=true;
			Plugins.menu.update();
    		Global.locus.method.reset();
    		Global.locus.flag.addBracketFlag = true;
    	}
		break;
	case "m1c4_cmd7":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M19']=true;
		Plugins.menu.update();
		Global.locus.method.reset();
		Global.locus.flag.addSurveyFlag = true;
		break;
	case "m1c4_cmd8":
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
    		Plugins.menu.refresh();
    		Plugins.FLAG.MAP['M20']=true;
    		Plugins.menu.update();
    		Global.locus.method.reset();
    		Global.locus.flag.matchCableFlag = true;
    		Global.locus.method.toggleControl("pan");
    	}
		break;
	case "m1c4_cmd9":
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
    		Plugins.menu.refresh();
    		Plugins.FLAG.MAP['M21']=true;
    		Plugins.menu.update();
    		Global.locus.method.reset();
    		Global.locus.method.toggleControl("cable");
    	}
		break;
	case "m1c4_cmd10":
		Plugins.menu.refresh();
		Plugins.FLAG.MAP['M22']=true;
		Plugins.menu.update();
		Global.locus.flag.labelFlag = true;
		/*
		Global.locus.method.toggleControl("pan");
		var judge = setInterval(function() {
			if (Global.locus.flag.finishList) {
				Global.locus.controls["list"].finishSketch();
				Global.locus.flag.finishList = false;
				Global.locus.controls["list"].deactivate();
				clearInterval(judge);
			}
		}, 500);
		*/
		break;
	case "m2c1_cmd1":
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L1']=true;
		Plugins.menu.update();
		Global.line.method.reset();
		Global.line.method.toggleControl("pan");
		break;
	case "m2c1_cmd2":
		Global.line.map.zoomIn();
		break;
	case "m2c1_cmd3":
		Global.line.map.zoomOut();
		break;
	case "m2c1_cmd4":
		Global.line.map.zoomToExtent(Global.line.constant.fullExtent);
		break;
	case "m2c2_cmd1":
		Global.line.save.saveStrategy.save();
		break;
	case "m2c2_cmd2":
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L6']=true;
		Plugins.menu.update();
		Global.line.method.reset();
		Global.line.method.toggleControl("edit");
		break;
	case "m2c2_cmd3_on":
		slide.slide_out();
		break;
	case "m2c2_cmd3_off":
		slide.slide_in();
		break;
	case "m2c2_cmd4":
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L7']=true;
		Plugins.menu.update();
		Global.line.method.reset();
		Global.line.copy.dragControl.activate();
		break;
	case "m2c2_cmd5":
		var selectedFeatures = 0;
		for(var i=0;i<Global.line.select.control.layers.length;i++){
			selectedFeatures += Global.line.select.control.layers[i].selectedFeatures.length;
		}
		if(selectedFeatures>0){
			Plugins.menu.refresh();
			Plugins.FLAG.LINE['L8']=true;
			Plugins.menu.update();
			if (Global.line.copy.dragControl.active) {
				Global.line.flag.copy = true;
			} else {
				Global.line.copy.dragControl.activate();
				Global.line.flag.copy = true;
			}
		}else{
			Plugins.dialog.show({
				'text'	: '未选中数据',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		break;
	case "m2c2_cmd6":
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L9']=true;
		Plugins.menu.update();
		Global.line.method.reset();
		Global.line.method.toggleControl('left');
		break;
	case "m2c2_cmd7":
		Plugins.menu.refresh();
		Plugins.FLAG.LINE['L10']=true;
		Plugins.menu.update();
		Global.line.method.reset();
		Global.line.method.toggleControl('right');
		break;
	case "m3c1_cmd1":
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W1']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.toggleControl("pan");
		break;
	case "m3c1_cmd2":
		Global.wire.map.zoomIn();
		break;
	case "m3c1_cmd3":
		Global.wire.map.zoomOut();
		break;
	case "m3c1_cmd4":
		Global.wire.map.zoomToExtent(Global.wire.constant.fullExtent);
		break;
	case "m3c2_cmd1":
		Global.wire.save.saveStrategy.save();
		break;
	case "m3c2_cmd2":
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W6']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.toggleControl("edit");
		break;
	case "m3c2_cmd3_on":
		slide2.slide_out();
		break;
	case "m3c2_cmd3_off":
		slide2.slide_in();
		break;
	case "m3c2_cmd4":
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W7']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.copy.dragControl.activate();
		break;
	case "m3c2_cmd5":
		var selectedFeatures = 0;
		for(var i=0;i<Global.wire.select.control.layers.length;i++){
			selectedFeatures += Global.wire.select.control.layers[i].selectedFeatures.length;
		}
		if(selectedFeatures>0){
			Plugins.menu.refresh();
			Plugins.FLAG.WIRE['W8']=true;
			Plugins.menu.update();
			if (Global.wire.copy.dragControl.active) {
				Global.wire.flag.copy = true;
			} else {
				Global.wire.copy.dragControl.activate();
				Global.wire.flag.copy = true;
			}
		}else{
			Plugins.dialog.show({
				'text'	: '未选中数据',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		break;
	case "m3c2_cmd6" : 
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W9']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.drawUnit(null, '1');
		break;	
	case "m3c2_cmd7" :
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W10']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.drawUnit(null, '2');
		break;  
	case "m3c2_cmd8" :
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W11']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.drawUnit(null, '3');
		break;  
	case "m3c2_cmd9" :
		Plugins.menu.refresh();
		Plugins.FLAG.WIRE['W12']=true;
		Plugins.menu.update();
		Global.wire.method.reset();
		Global.wire.method.drawUnit(null, '4');
		break;  	
	default:
		//alert('点击了按钮，它的rel属性是' + cmd);
		Plugins.set_status('点击了按钮，它的rel属性是' + cmd);
	}
}

/*
 * 下拉菜单事件转发
 */
Base.handle.dropmenu = function(obj) {
	var cmd = obj.attr('rel');
	switch (cmd) {
	case "f0":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(0);
		break;
	case "f1":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(1);
		break;
	case "f2":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(2);
		break;
	case "f3" :
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(3);
		break;
	case "f4" :
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(4);
		break;
	case "f5" :
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(5);
		break;
	case "f6":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(6);
		break;
	case "f7" :
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(7);
		break;
	case "f8":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(8);
		break;
	case "f9":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(9);
		break;
	case "f10":
		Global.locus.method.reset();
		Global.locus.method.toggleDrawControl(10);
		break;
	default:
		Plugins.set_status('Test warm!', 'warm');
	}
}

/*
 * 右侧滑出菜单事件处理
 */
Base.handle.slide_menu = function(obj, title) {
	var cmd  = obj.attr('class');
	var name = obj.parent().prev().text();
	var type = obj.parents('tr').attr('id');
	var sid  = obj.parents('tr').find('td:first').attr('id');
	if (cmd == "add") {
		switch(title){
		case '.list_menu' :
			var exist = false;
			for(var i=0;i<Global.line.layers.unitLayer.features.length;i++){
				if(Global.line.layers.unitLayer.features[i].attributes['static_id'] == sid){
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
				Global.line.method.reset();
				Global.line.method.drawUnit(name, type, sid);	
			}
			break;
		case '.list_menu2' :
			Global.wire.method.reset();
			Global.wire.method.drawUnit(name, type, sid);
			break;
		}		
	} else {
		if (cmd = "locate") {
			Plugins.func_tabs(".midtabs", function(obj) {
				Plugins.change_content(obj);
			}, $('#tab_normal'));			
			Global.locus.method.exact_search(sid, type);
		}
	}
}

/*
 * 搜索事件转发
 */
Base.handle.search = function(){
	
	$('#gosearch').click(function(){
		var type = $('#search-type').val();
		var key = $('#st').val();
		switch (type) {
		case 'electrical':
			Global.locus.method.vague_search(key, 0);
			break;
		case 'building':
			Global.locus.method.vague_search(key, 1);
			break;	
		case 'locate':
			Global.locus.method.cr_search(key);
			break;		
		}
	});
}

/*
 * 树目录右键菜单事件处理
 */
Base.handle.tree_menu = function(e) {
	var zTree = $.fn.zTree.getZTreeObj("treelist");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	// alert()
	switch (e['obj']) {
		case 'show_single_line':
			show_single_line();
			break;
		case 'show_single_station':
			show_single_station();
			break;
		case 'single_line':
			goto_single_line();
			break;
		case 'connect_line':
			goto_connect_line();
			break;	
		case 'new_line':
			add_feature(1010301);
			break;
		case 'rename':
			rename();
			break;
		case 'delete':
			if(treeNode.check_Child_State == -1){
				delete_feature(zTree, treeNode);
			}else{
				//alert('非空，请先删除子设备');
				Plugins.dialog.show({
					'text'	: '非空，请先删除子设备',
					'title'	: '提示'
				}, {
					'submit'	: function(){
						Plugins.dialog.remove();
					}
				});
			}
			break;
		case 'pro':
			editpro();
			break;
		case 'locate':
			Global.locus.method.exact_search(treeNode.id.split('-')[1], treeNode.id.split('-')[0]);
			break;
		case 'pic' :
			Plugins.slide_attachment.show(e);
			break;
		case 'add_station':
			add_feature(1010201);
			break;
		case 'add_switchStation':
			add_feature(1010202);
			break;
		case 'add_branchBox':
			add_feature(1010205);
			break;
		case 'add_ringmainuint':
			add_feature(1010204);
			break;
		case 'add_transformer':
			add_feature(1010203);
			break;
		case 'add_inter':
			add_feature(1010601);
			break;
		case 'add_cableseg':
			add_feature(1010402);
			break;
		case 'add_wireseg':
			add_feature(1010403);
			break;
		case 'add_road':
			add_feature(1020301);
			break;
		case 'add_pipeline':
			add_feature(1020200);
			break;
		case 'topipe':
			topipe(treeNode.id.split('-')[1],treeNode.id.split('-')[0]);
		
	}
	

	
	//单线路显示
	function show_single_line() {
		var ids = treeNode.id.split('-');
		var lineId = ids[1];
	    OpenLayers.Request.GET({
	        url: "getProJsonConBuildofCableline.action?CablelineId="+lineId,
	        success: singleLineUnit_success,
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
	    function singleLineUnit_success(req){
			var format = new OpenLayers.Format.JSON();
		    var response = format.read(req.responseXML || req.responseText);
		    var electrical_filters = [], civilEngineering_filters = [];
		    for(var i=0;i<response.electrical.length;i++){
		    	electrical_filters[i] =
		    		new OpenLayers.Filter.FeatureId({
		  		  	  fids: [response.electrical[i].value]
		  		    });
			};
			Global.locus.layers.electricalLayer.filter = 
				new OpenLayers.Filter.Logical({
				    type: OpenLayers.Filter.Logical.OR,
				    filters: electrical_filters
				});
			Global.locus.layers.electricalLayer.refresh({force: true});
		    for(var i=0;i<response.civilEngineering.length;i++){
		    	civilEngineering_filters[i] =
		    		new OpenLayers.Filter.FeatureId({
		  		  	  fids: [response.civilEngineering[i].value]
		  		    });
			};
			Global.locus.layers.buildingLayer.filter = 
				new OpenLayers.Filter.Logical({
				    type: OpenLayers.Filter.Logical.OR,
				    filters: civilEngineering_filters
				});
			Global.locus.layers.buildingLayer.refresh({force: true});
			var wktFormat = new OpenLayers.Format.WKT();
			var features = [],arrX = [],arrY = [],index = 0;
			for(var i=0;i<response.electrical.length;i++){
				features[i] = wktFormat.read(response.electrical[i].geometry);
				if(!features[i].geometry.components){
					arrX[index] = features[i].geometry.x;
					arrY[index] = features[i].geometry.y;
					index++;
				}
			}
			var left = arrX[0];
			var right = arrX[0];
			var bottom = arrY[0];
			var top = arrY[0];
			for(var i=1;i<arrX.length;i++){
				if(arrX[i]<left){
					left = arrX[i];
				}
				if(arrX[i]>right){
					right = arrX[i];
				}
				if(arrY[i]<bottom){
					bottom = arrY[i];
				}
				if(arrY[i]>top){
					top = arrY[i];
				}
			}
			var center = new OpenLayers.LonLat((left+right)/2, (bottom+top)/2).transform(
					new OpenLayers.Projection("EPSG:4326"),
					Global.locus.map.getProjectionObject()
			);
			Global.locus.map.setCenter(center,Global.constant.zoomLevel);
	    }
	}
	
	//单变电站显示
	function show_single_station() {
		var ids = treeNode.id.split('-');
		var stationId = ids[1];
	    OpenLayers.Request.GET({
	        url: "getProJsonOfWireCableByTransStation.action?staticId="+stationId,
	        success: singleStationUnit_success,
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
	    function singleStationUnit_success(req){
			var format = new OpenLayers.Format.JSON();
		    var response = format.read(req.responseXML || req.responseText);
		    var filters = [];
		    for(var i=0;i<response.length;i++){
		    	filters[i] =
		    		new OpenLayers.Filter.FeatureId({
		  		  	  fids: [response[i].value]
		  		    });
			};
			Global.locus.layers.electricalLayer.filter = 
				new OpenLayers.Filter.Logical({
				    type: OpenLayers.Filter.Logical.OR,
				    filters: filters
				});
			Global.locus.layers.electricalLayer.refresh({force: true});
			Global.locus.layers.buildingLayer.filter = 
				new OpenLayers.Filter.FeatureId({
		  		  	  fids: [-1]
		  		    });
			Global.locus.layers.buildingLayer.refresh({force: true});
			var wktFormat = new OpenLayers.Format.WKT();
			var features = [],arrX = [],arrY = [],index = 0, VertexNum = 0;
			for(var i=0;i<response.length;i++){
				features[i] = wktFormat.read(response[i].geometry);
				for(var j=0;j<features[i].geometry.components.length;j++){
					arrX[index] = features[i].geometry.components[j].x;
					arrY[index] = features[i].geometry.components[j].y;
					index++;
				}
			}
			var left = arrX[0];
			var right = arrX[0];
			var bottom = arrY[0];
			var top = arrY[0];
			for(var i=1;i<arrX.length;i++){
				if(arrX[i]<left){
					left = arrX[i];
				}
				if(arrX[i]>right){
					right = arrX[i];
				}
				if(arrY[i]<bottom){
					bottom = arrY[i];
				}
				if(arrY[i]>top){
					top = arrY[i];
				}
			}
			var center = new OpenLayers.LonLat((left+right)/2, (bottom+top)/2).transform(
					new OpenLayers.Projection("EPSG:4326"),
					Global.locus.map.getProjectionObject()
			);
			Global.locus.map.setCenter(center,Global.constant.zoomLevel);
	    }
	}
	
	// 跳转到单线图
	function goto_single_line() {
		var ids = treeNode.id.split('-');
		Global.line.initVar = parseInt(ids[1]);
		if(Global.line.layers.unitLayer){
			var eleFilter = new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "belongs_cableline",
				value: Global.line.initVar
			});
			Global.line.layers.unitLayer.filter = eleFilter;
			Global.line.layers.unitLayer.refresh({force: true});
		}
		Plugins.FLAG.hasData.line = true;
		Plugins.func_tabs(".midtabs", function(obj) {
			Plugins.change_content(obj);
		}, $('#tab_line'));

		$.post(Global.option.URL + 'getSideBarJson.action', {
			'SId'	: treeNode.id.split('-')[1]
		}, function(data) {
			slide.set_data(data);
			Global.line.flag.slide = true;
		}, 'json');
		
		slide.slide_in();
		Plugins.slide_form.remove();
	}
	// 跳转到接线图
	function goto_connect_line() {
		var ids = treeNode.id.split('-');
		Global.wire.initVar = parseInt(ids[1]);
		if(Global.wire.layers.unitLayer){
			var eleFilter = new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "belongs_container",
				value: Global.wire.initVar
			});
			Global.wire.layers.unitLayer.filter = eleFilter;
			Global.wire.layers.unitLayer.refresh({force: true});
		}
		Plugins.FLAG.hasData.connect = true;
		Plugins.func_tabs(".midtabs", function(obj) {
			Plugins.change_content(obj);
		}, $('#tab_wire'));

		$.post(Global.option.URL + 'getSideBarJJson.action', {
			'SId'	: treeNode.id.split('-')[1]
		}, function(data) {
			//alert(data);
			slide2.set_data(data);
		}, 'json');
		
		slide2.slide_in();
		Plugins.slide_form.remove();
	}
	
	//入沟
	function topipe(sid,type){
		Global.constant.index = 0;
	    OpenLayers.Request.GET({
	        url: "getPropertyJson.action?type="+type
	        	+"&staticId="+sid,
	        success: cableSegmentProperty_success,
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
	    function cableSegmentProperty_success(req){
	    	var format = new OpenLayers.Format.JSON();
	        var response = format.read(req.responseXML || req.responseText);
	        Global.locus.toPipe.start = response['startContainer'];
	        Global.locus.toPipe.startType =response['startContainerType'];
	        Global.locus.toPipe.end = response['endContainer'];
	        Global.locus.toPipe.endType = response['endContainerType'];
			Global.locus.flag.cableToPipe = true;
			Global.locus.flag.cableToPipeStart = true;
			Global.locus.draw.cablePoints = [];
			Plugins.slide_form.show({
				'title'		: treeNode.name + '入沟',
				
			},{
				'submit'	: function(){
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
					var pipeEnd = Global.locus.draw.cablePoints[Global.locus.draw.cablePoints.length-1];
					if(pipeEnd.equals(start) || pipeEnd.equals(end)){
						var createflag = true;
						for(var i=0;i<Global.locus.layers.electricalLayer.features.length;i++){
							if(Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == sid){
								Global.locus.layers.electricalLayer.features[i].geometry.components = [];
								Global.locus.layers.electricalLayer.redraw();
								Global.locus.layers.electricalLayer.features[i].geometry = 
									new OpenLayers.Geometry.LineString(Global.locus.draw.cablePoints);
								Global.locus.layers.electricalLayer.features[i].state = OpenLayers.State.UPDATE;
								Global.locus.layers.electricalLayer.redraw();
								createflag = false;
								break;
							}
						}
						if(createflag){
							var attr = {
									'static_id':	treeNode.id.split('-')[1],
									'name':		treeNode.name,
									'object_type':	treeNode.id.split('-')[0],
									'z_index':	Global.constant.wirecable_segment
								}
							var cable = new OpenLayers.Feature.Vector(
										new OpenLayers.Geometry.LineString(Global.locus.draw.cablePoints), attr
							);
							cable.renderIntent = 'default';
							cable.state = OpenLayers.State.INSERT;
							Global.locus.layers.electricalLayer.addFeatures([cable]);
						}
						Global.locus.constant.saveEventsCount = 1;
						Global.locus.save.saveStrategy[0].save();
						$.post(Global.option.URL + 'topipe.action', Base.request.get_param('.slideform'), function(res){
							Plugins.slide_form.remove();
							Global.constant.index = 0;
							Global.locus.draw.cablePoints = null;
							Global.locus.flag.cableToPipe = false;
						});
					}else{
		            	Plugins.dialog.show({
		            		'title': '错误信息',
		            		'text': '管沟未完全入沟，请继续选择管沟段。'
		            	},{
		            		'submit' : function(e){
		            			Plugins.dialog.remove();
		            		}
		            	});
					}
				}
			});
			//Plugins.slide_form.add_list("tests");
			//入沟目标静态ID
			/*
			Plugins.slide_form.add_data({
				'text'	: '测试管沟',//显示内容
				'name' 	: 'topipe[0]',//数组名wires不可修改
				'value'	: 1383539660870734,
				'type' 	: 'int'
			});
			Plugins.slide_form.add_data({
				'text'	: '测试管沟2',//显示内容
				'name' 	: 'topipe[1]',//数组名wires不可修改
				'value'	: 1383539660870739,
				'type' 	: 'int'
			});
			*/
			//入沟对象静态ID
			Plugins.slide_form.add_val({
				'name'	: 'wire',
				'value'	: treeNode.id.split('-')[1],
				'type'	: 'int'
			});
			/*
			Plugins.dialog.show({
				'title'		: '标准段入沟',
				'url'		: Global.option.URL + '/template/property/topipe.jsp'
			},{
				'submit'	: function(){
					$.post(Global.option.URL + 'saveProperty.action', Base.request.get_param(),
							function(res) {
								alert(res);
								Plugins.dialog.remove();
						});
				},
				'success'	: function(){
					Plugins.dialog.set('input[name="topipeSegmentHaveWirecable"]', treeNode.name);
					Plugins.dialog.set('input[name="topipe.segmentHaveWirecable"]', treeNode.id.split('-')[1]);
				}
			});*/
	    }
	}
	
	// 添加一个设备
	function add_feature(type) {
		var staId = Plugins.get_staticid();
		var title,isParent,createGeo;
		switch(type){
		case 1010201:{
			title 	= '变电站属性';
			isParent= true;
			createGeo = true;
			break;
		}
		case 1010202:{
			title 	= '开关站属性';
			isParent= true;
			createGeo = true;
			break;
		}
		case 1010205:{
			title 	= '分支箱属性';
			isParent= true;
			createGeo = true;
			break;
		}
		case 1010204:{
			title 	= '环网柜属性';
			isParent= true;
			createGeo = true;
			break;
		}
		case 1010203:{
			title 	= '变压器属性';
			isParent= true;
			createGeo = true;
			break;
		}
		case 1010301:{
			title 	= '线路属性';
			isParent= true;
			break;
		}
		case 1010601:{
			title	= '间隔属性';
			isParent= false;
			break;
		}
		case 1010403:{
			title	= '导线段属性';
			isParent= false;
			break;
		}
		case 1010402:{
			title	= '电缆段属性';
			isParent= false;
			break;
		}
		case 1020301:{
			title	= '道路属性';
			isParent= true;
			break;
		}
		case 1020200:{
			title	= '管沟线属性';
			isParent= true;
			break;
		}
		}
		
		Plugins.dialog.show({
			title : title,
			url : Global.option.URL + '/getProperty.action',
			param : {
				type : type
			}
		}, {
			'submit' : function(e) {
				if ($('#feature_name').val() == "") {
					Plugins.dialog.warm({
						'title'	: '提 醒'
					},'名称不能为空，请填写后提交',{});
				} else {
					if(createGeo){
						if($('#lon').val() == "" || $('#lat').val() == ""){
							Plugins.dialog.warm({
								'title'	: '提 醒'
							},'经纬度不能为空，请填写后提交',{});
						}else{
							var projectSource = new OpenLayers.Projection("EPSG:4326");
							var projectTo = Global.locus.map.getProjectionObject();
							var unit = new OpenLayers.Geometry.Point($('#lon').val(), $('#lat').val());
							unit.transform(projectSource, projectTo);
							var feature = new OpenLayers.Feature.Vector(unit);
							feature.attributes['static_id'] = staId;
							feature.attributes['name'] = $('#feature_name').val();
					        feature.attributes['object_type'] = type;
					        feature.attributes['z_index'] = Global.constant.unit;
					        feature.renderIntent = 'select';
					        feature.state = OpenLayers.State.INSERT;
							Global.locus.layers.electricalLayer.addFeatures([feature]);
							Plugins.dialog.set('#staticId', staId);
							Global.locus.constant.saveEventsCount = 1;
							Global.locus.save.saveStrategy[0].save();
							var response = setInterval(function(){
		    					if(Global.locus.save.state['电气设备'] == 'success'){
									Base.request.insert_pro_tree({
										'treeNode' 	: treeNode,
										'type' 		: type,
										'staId' 	: staId,
										'name' 		: Plugins.dialog.getval('#feature_name'),
										'isParent' 	: isParent
									});
									Global.locus.method.cr_search($('#lon').val()+','+$('#lat').val())
		    						clearInterval(response);
		    					}
		    					if(Global.locus.save.state['电气设备'] == 'fail'){
		    						clearInterval(response);
		    					}
		    				},500);

						}
					}else{
						Plugins.dialog.set('#staticId', staId);
						Base.request.insert_pro_tree({
							'treeNode' 	: treeNode,
							'type' 		: type,
							'staId' 	: staId,
							'name' 		: Plugins.dialog.getval('#feature_name'),
							'isParent' 	: isParent
						});	
					}
				}
			},
			'success' : function() {
				if(type == 1010201){

				}
				if(type == 1010202){
//					Plugins.dialog.set('input[name="switchSta.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
					Plugins.dialog.set('input[name="sw"]', treeNode.getParentNode().name);
				}
				if(type == 1010205){
//					Plugins.dialog.set('input[name="branchBox.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
					Plugins.dialog.set('input[name="bra"]', treeNode.getParentNode().name);
				}
				if(type == 1010204){
//					Plugins.dialog.set('input[name="ringmainunit.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
					Plugins.dialog.set('input[name="rin"]', treeNode.getParentNode().name);
				}
				if(type == 1010203){
//					Plugins.dialog.set('input[name="trans.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
					Plugins.dialog.set('input[name="tra"]', treeNode.getParentNode().name);
				}
				if(type == 1010301){
					Plugins.dialog.set('input[name="cableline.startTransStation"]', treeNode.id.split('-')[1]);
					Plugins.dialog.set('input[name="cablelineStartTransStation"]', treeNode.name);
				}
				if(type == 1010601){
					Plugins.dialog.set('input[name="intermission.containerType"]', treeNode.id.split('-')[0]);
					Plugins.dialog.set('input[name="intermissionContainerType"]', treeNode.getParentNode().name);
					Plugins.dialog.set('input[name="intermission.containerId"]', treeNode.id.split('-')[1]);
					Plugins.dialog.set('input[name="intermissionContainerId"]', treeNode.name);
				}
				if(type == 1010402){
					Plugins.dialog.set('input[name="cableB"]', treeNode.getParentNode().name);
					Plugins.dialog.set('input[name="cableSeg.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
				}
				if(type == 1010403){
					Plugins.dialog.set('input[name="wireB"]', treeNode.getParentNode().name);
					Plugins.dialog.set('input[name="wireSeg.belongsCableline"]', treeNode.getParentNode().id.split('-')[1]);
				}
				if(type == 1020200){
					Plugins.dialog.set('input[name="roadname"]', treeNode.name);
					Plugins.dialog.set('input[name="pipeline.belongsRoad"]', treeNode.id.split('-')[1]);
				}
			}
		});
	}
	
	//删除设备
	function delete_feature(zTree, treeNode) {
		var type = parseFloat(treeNode.id.split('-')[0]);
		var sid = parseFloat(treeNode.id.split('-')[1]);
		switch(type){
		case 1010201:
		case 1010202:
		case 1010203:
		case 1010204:
		case 1010205:
		case 1010206:
		case 1010207:
		case 1010208:
		case 1010402:
		case 1010403:
			Plugins.dialog.show({
				'text'	: '确定删除？删除后数据将无法恢复。',
				'title'	: '警告',
				'sid': sid
			}, {
				'submit'	: function(v){
					for(var i=0;i<Global.locus.layers.electricalLayer.features.length;i++){
						if(Global.locus.layers.electricalLayer.features[i].attributes['static_id'] == v['sid']){
							Global.locus.layers.electricalLayer.features[i].state = OpenLayers.State.DELETE;
							Global.locus.layers.electricalLayer.drawFeature(Global.locus.layers.electricalLayer.features[i]);
							Global.locus.constant.saveEventsCount = 1;
							Global.locus.save.saveStrategy[0].save();
							break;
						}
					}
					Plugins.dialog.remove();
					zTree.removeNode(treeNode);
				}						
			});
			break;
		case 1020101:
		case 1020102:
		case 1020103:
		case 1020203:
		case 1020211:
		case 1020212:
		case 1020213:
		case 1020214:
		case 1020215:
		case 1020216:
			Plugins.dialog.show({
				'text'	: '确定删除？删除后数据将无法恢复。',
				'title'	: '警告',
				'sid': sid
			}, {
				'submit'	: function(v){
					for(var i=0;i<Global.locus.layers.buildingLayer.features.length;i++){
						if(Global.locus.layers.buildingLayer.features[i].attributes['static_id'] == v['sid']){
							Global.locus.layers.buildingLayer.features[i].state = OpenLayers.State.DELETE;
							Global.locus.layers.buildingLayer.drawFeature(Global.locus.layers.buildingLayer.features[i]);
							Global.locus.constant.saveEventsCount = 1;
							Global.locus.save.saveStrategy[1].save();
							break;
						}
					}
					Plugins.dialog.remove();
					zTree.removeNode(treeNode);
				}						
			});
			break;
		case 1010301:
		case 1010601:
		case 1010602:
		case 1020200:
		case 1020301:
			Plugins.dialog.show({
				'text'	: '确定删除？删除后数据将无法恢复。',
				'title'	: '警告',
				'sid': sid
			}, {
				'submit'	: function(v){
					$.post(Global.option.URL +'/delete.action',{
						sid 	: v['sid'],
						type	: 'property'
					},function(data){
						if(data != 'success'){
							Plugins.dialog.remove();
							Plugins.dialog.show({
								'text'	: '删除发生错误，请检查网络后重试',
								'title'	: '警告'
							}, {
								'submit'	: function(){
									Plugins.dialog.remove();
									zTree.removeNode(treeNode);
								}
							})
						} else {
							Plugins.dialog.remove();
							zTree.removeNode(treeNode);
						}						
					});
				}
			})
			break;
		}
	}
	
	// 编辑属性
	function editpro() {
		// var otype = e['node'].id.split("-")[0];
		Plugins.dialog.show({
			title : '属性',
			url : Global.option.URL + '/getProperty.action',
			treeNode : e['node'],
			type : 1010301,
			param : {
				type : e['node'].id.split("-")[0],
				isbn : e['node'].id.split("-")[1]
			}
		}, {
			'submit' : function(e) {
				if ($('#feature_name').val() == "") {
					Plugins.dialog.warm({
						'title'	: '错误信息'
					},"名称不能为空，请填写后重新提交",{});
				} else {
					// var staId = Plugins.get_staticid();
					// Plugins.dialog.set('#staticId', staId);
					// Base.request.insert_pro_tree();
					// Plugins.dialog.remove();
					Base.request.update_pro_tree(e);
				}
			},
			'success' : function() {

			}
		});
	}
	// 重命名
	function rename() {
		zTree.editName(e['node']);
	}
	// 删除节点
	function remove() {
		zTree.removeNode(e['node'], function() {
		});
	}
	
}

/*
 * 树目录的保存数据事件,参数传入保存后所需添加节点的属性
 */
Base.request.insert_pro_tree = function(e) {
	var zTree = $.fn.zTree.getZTreeObj("treelist");
	$.post(Global.option.URL + 'saveProperty.action', Base.request.get_param(),
			function(res) {
//				alert(res);
				if (e['treeNode']) {
					//alert("add pipeline")
					e['treeNode'] = zTree.addNodes(treeNode, {
						id 			: (e['type'] + '-' + e['staId']),
						isParent 	: e['isParent'],
						name 		: e['name'],
						'iconSkin' 	: 'tree_' + e['type']
					});
				} else {/*
					e['treeNode'] = zTree.addNodes(null, {
						id 			: (e['type'] + '-' + e['staId']),
						isParent 	: e['isParent'],
						name 		: e['name'],
						'iconSkin'	: 'tree_' + e['type']
					});*/
				}
				Plugins.dialog.remove();
			});
}

/*
 * 树目录的更新数据事件,参数传入保存后所需更新节点的属性
 */
Base.request.update_pro_tree = function(e) {
	var zTree = $.fn.zTree.getZTreeObj("treelist");
	$.post(Global.option.URL + 'saveProperty.action', Base.request.get_param(),
			function(res) {
				// alert(e['name']);
				e['treeNode'].name = Plugins.dialog.getval('#feature_name');// e['name'];
				zTree.updateNode(e['treeNode']);

				Plugins.dialog.remove();
			});
}

/*
 * 要素右键菜单事件处理
 */
Base.request.handle_feature_menu = function(e) {
	
	switch (e['obj']) {
	case 'electrical_property':
		Base.request.get_pro(e, 0, 'unit');
		break;
	case 'property':
		Base.request.get_pro(e, 1, 'unit');
		break;
	case 'profile_property':
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
    		Base.request.get_pro(e, null, 'profile');
    	}
		break;
	case 'hole_property':
    	if(Global.locus.layers.elevationLayer.profileLayer.filter){
    		//alert('要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内')
    		Plugins.dialog.show({
				'text'	: '要进行剖面相关编辑，请先打开全剖面，并确认目标剖面与镜像剖面均显示在视窗内',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    	}else{
    		Base.request.get_pro(e, null, 'hole');
    	}
		break;
	case 'bracket_property':
		Base.request.get_pro(e, null, 'bracket');
		break;
	case 'popli':
		var id = e.feature.attributes['static_id'];
		window.open ("profile.jsp?id="+id, "剖面图", "height=500, width=900, top=100, left=100,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		break;
	case 'openli':
		Global.locus.method.openElevation(e);
		break;
	case 'closeli':
		Global.locus.method.closeElevation();
		break;
	case '3dwell' :
		//window.open ("well3D.action", "3D剖面图", "height=500, width=900, top=100, left=100,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		window.open ("3D/well/3dwell.jsp", "3D工井剖面图", "height=500, width=900, top=100, left=100,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		break;
	case '3dpipe' :
		//window.open ("well3D.action", "3D剖面图", "height=500, width=900, top=100, left=100,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		window.open ("3D/pipe/3dpipe.jsp", "3D管道剖面图", "height=500, width=900, top=100, left=100,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
		break;
	case 'open_connect_line':
		Global.locus.method.goto_connect_line();
		break;
	case 'delete':
    	switch(Global.constant.currentMap){
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
    		//alert('请从轨迹图或者树目录中进行删除！');
    		Plugins.dialog.show({
				'text'	: '请从轨迹图或者树目录中进行删除！',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
    		break;
    		*/
    	}
		break;
	case 'pic' :
		Plugins.slide_attachment.show(e);
		break;
	case 'label':
		Plugins.set_label_pos.show({
			'feature'	: e['feature']
		},{
			'preview'	: function(e){
				e.feature.attributes['x_offset'] = Plugins.set_label_pos.get_data().xoffset;
				e.feature.attributes['y_offset'] = Plugins.set_label_pos.get_data().yoffset;
				e.feature.layer.redraw();
				},
			'save'		: function(e){
				e.feature.state = OpenLayers.State.UPDATE;			
				Global.locus.constant.saveEventsCount = 1;
				e.feature.layer.strategies[1].save();}
		});
		Plugins.set_label_pos.set_data(e.feature.attributes);
		break;
	case 'survery_modify':
		editSurvery();
		break;
	}
	
	//编辑测绘图
	function editSurvery(){
		var points = e.feature.geometry.getVertices();
		var counter = points.length;
		Plugins.dialog.show({
			url 	:	Global.option.URL + '/editProfile.action',
			feature	: 	e['feature'],
			
			param	:	{
				points	: counter
			}
		},{
			success	:function(e){
				var points = e.feature.geometry.getVertices();
				var counter = points.length;
				var n = 0;
				$('.locate').each(function(){
					$(this).find('.pointx').val(points[n].x);
					$(this).find('.pointy').val(points[n].y);
					n++;
				});
			},
			submit	: function(e){
				
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
					var point = [];
					for(var i=0; i<x; i++){
						point[i] = new OpenLayers.Geometry.Point(pointsx[i], pointsy[i]); 								
					}
					
					
					var ln = new OpenLayers.Geometry.Polygon(new OpenLayers.Geometry.LinearRing(point)); 
					var feature = new OpenLayers.Feature.Vector(ln);
					feature.attributes['well_id'] = e.feature.attributes['well_id'];
					feature.attributes['object_type'] = 1030110;
					feature.state = OpenLayers.State.INSERT;
					Global.locus.layers.elevationLayer.surveyLayer.addFeatures([feature]);
					//e.feature.geometry.removeComponents();
					
					//e.feature.geometry.addComponents(ln);
					
					//e.feature.geometry.calculateBounds();
					e.feature.state = OpenLayers.State.DELETE;
					e.feature.renderIntent = "select";
					Global.locus.layers.elevationLayer.surveyLayer.drawFeature(e.feature);
					
					Global.locus.constant.saveEventsCount = 1;
					Global.locus.save.saveStrategy[2].save();
					
				} else {
					alert('数据不能为空');
				}
				Plugins.dialog.lock('unlocked'); 
				
				
				 
    			Plugins.dialog.remove();
			}
		});
	}
}

Base.request.get_pro = function(e, index, type) {
	var params;

	if (typeof (e['feature'].attributes['static_id']) == 'undefined') {

		params = {
			type : e['feature'].attributes['object_type'],
			name : e['feature'].attributes['name']
		}
	} else {
		params = {
			type : e['feature'].attributes['object_type'],
			isbn : e['feature'].attributes['static_id']
		}
	}
	
	if(type == 'profile'){
		params.wellSid = e['feature'].attributes['well_id'];
	}

	switch(type){
	case 'unit':
		Plugins.dialog.show({
			title : '属性',
			url : Global.option.URL + '/getProperty.action',
			obj : e['feature'],
			param : params
		}, {
			'success' : function(){
				Plugins.dialog.set('#xoffset', e['feature'].attributes['x_offset']);
				Plugins.dialog.set('#yoffset', e['feature'].attributes['y_offset']);
			},
			'submit' : function(v) {
				if ($('#feature_name').val() == "") {
					Plugins.dialog.warm(e, '名称不能为空，请填写后重新提交', {});
				} else {
					Base.request.update_pro(v, index, 'unit');
				}
			}
		});
		break;
	case 'profile':
		Plugins.dialog.show({
			title : '属性',
			url : Global.option.URL + '/getProperty.action',
			obj : e['feature'],
			param : params
		}, {
			'submit' : function(v) {
				Base.request.update_pro(v, null, 'profile');
			}
		});
		break;
	case 'hole':
		Plugins.dialog.show({
			title : '属性',
			url : Global.option.URL + '/getProperty.action',
			obj : e['feature'],
			param : params
		}, {
			'submit' : function(v) {
				Base.request.update_pro(v, null, 'hole');
			}
		});
		break;
	case 'bracket':
		Plugins.dialog.show({
			title : '属性',
			url : Global.option.URL + '/getProperty.action',
			obj : e['feature'],
			param : params
		}, {
			'submit' : function(v) {
				Plugins.dialog.warm({
					'title'	: '错误信息'
				},"建设中",{});
			}
		});
		break;
	}

}

/*
 * 添加属性业务处理
 */
Base.request.insert_pro = function(e, index) {//index为需要执行保存的图层number
	var projectSource = new OpenLayers.Projection("EPSG:4326");
	var projectTo = Global.locus.map.getProjectionObject();
	var exactLocation = new OpenLayers.LonLat($('#lon').val(), $('#lat').val());
	exactLocation.transform(projectSource, projectTo);
	e['obj'].move(exactLocation);
	/* 生成静态ID */
	var staId = Plugins.get_staticid();
	Plugins.dialog.set('#staticId', staId);
	// Plugins.dialog.set('#objectType',
	// Global.locus.draw.unitType);//先在表单里相应字段加上id
	e['obj'].attributes['static_id'] = staId;
	e['obj'].attributes['name'] = $('#feature_name').val();
	// e['obj'].attributes['belongs_cableline'] = $('#belongsCableline').val();
	Global.locus.constant.saveEventsCount = 1;
	Global.locus.flag.form = true;
	Global.locus.save.saveStrategy[index].save();
}

/*
 * 修改属性业务处理
 */
Base.request.update_pro = function(e, index, type) {
	switch(type){
	case 'unit':	
		if($('#lon').val() && $('#lon').val()!=0){
			var projectSource = new OpenLayers.Projection("EPSG:4326");
			var projectTo = Global.locus.map.getProjectionObject();	
			var exactLocation = new OpenLayers.LonLat($('#lon').val(), $('#lat').val());
			exactLocation.transform(projectSource, projectTo);
			e['obj'].move(exactLocation);
		}
		e['obj'].attributes['name'] = $('#feature_name').val();
		e['obj'].state = OpenLayers.State.UPDATE;
		if(index == 1){
			$.post(Global.option.URL + 'saveProperty.action', Base.request.get_param(),function(res){
			});
			Plugins.dialog.remove();
		}else{
			Global.locus.constant.saveEventsCount = 1;
			Global.locus.flag.form = true;
			Global.locus.save.saveStrategy[index].save();
		}
		break;
	case 'profile':
    	Plugins.dialog.warm({
    		'title': '错误信息'
    	},'建设中...限制编辑', {
    		 
    	});
		break;
	case 'hole':
    	var profileFeature;
	    for(var i=0;i<Global.locus.layers.elevationLayer.profileLayer.features.length;i++){
	    	if((Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['well_id'] ==
	    		e['obj'].attributes['well_id']) &&
	    		Global.locus.layers.elevationLayer.profileLayer.features[i].attributes['pp_segment_id']==
	    			e['obj'].attributes['pp_segment_id']){
    			profileFeature = Global.locus.layers.elevationLayer.profileLayer.features[i];
	    	}
	    }
	    if(profileFeature){
			Global.locus.method.drawPipeHole(profileFeature,e['obj']);
	    }else{
	    	//alert('请确认管孔是否存在所属剖面或者被隐藏！')
	    	Plugins.dialog.show({
				'text'	: '请确认管孔是否存在所属剖面或者被隐藏！',
				'title'	: '提示'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			});
	    }
		break;
	}
}

// 遍历表单
Base.request.get_param = function(obj) {
	obj = obj?obj:'.property';
	var res = {};
	var inp = $(obj).find('input');
	var sel = $(obj).find('select');

	inp.each(function() {
		var s = $(this).attr("class");
		var data;

		if (typeof (s) == "undefined") {
			data = $(this).val();
			return true;
		}
		// 忽略不用提交的内容
		if (s.indexOf('ignore') >= 0) {
			return true;
		}
		
		//忽略空值
		if($(this).val() == ""){
			return true;
		}
		
		// 类型转换
		if (s.indexOf('int') >= 0) {
			data = parseInt($(this).val());
		} else {
			data = $(this).val();
		}
		res[$(this).attr('name')] = data;
		
	});

	sel.each(function() {
		res[$(this).attr('name')] = $(this).val();
	});
	console.log(res)
	return res;
}

/*
 * 删除处理
 * type:photo删除图片，property删除属性
 */
Base.request.deleteObj = function(id, type){
	if(type == "photo"){
		$.post(Global.option.URL +'/delete.action',{
			aid 	: id,
			type	: 'photo'
		},function(data){
			//$('.list_attachment').find('.list_box').html(data);
			if(data == 'error'){
				Plugins.dialog.show({
					'text'	: '删除发生错误，请检查网络后重试',
					'title'	: '警告'
				}, {
					'submit'	: function(){
						Plugins.dialog.remove();
					}
				})
			}
		});
	}
}

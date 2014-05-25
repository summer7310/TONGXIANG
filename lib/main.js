/*引入外部脚本*/
var plugins = document.createElement('script');
plugins.type = 'text/javascript';
plugins.src = 'plugins.js';
var base = document.createElement('script');
base.type = 'text/javascript';
base.src = 'base.js';

/* 全局变量声明 */
var Global = Global || {};
Global.namespace = function(str) {// 命名空间函数，避免成员变量命名冲突
	var parts = str.split("."), parent = Global;
	for (i = (parts[0] == "Global") ? 1 : 0; i < parts.length; i++) {
		parent[parts[i]] = parent[parts[i]] || {};
		parent = parent[parts[i]];
	}
	return parent;
}

Global.namespace("method");
Global.namespace("option");
Global.namespace("style");
Global.namespace("color");
Global.namespace("lineWidth");
Global.namespace("opacity");
Global.namespace("unit");
Global.namespace("constant");
Global.namespace("locus.method");
Global.namespace("locus.map");
Global.namespace("locus.layers");
Global.namespace("locus.layers.elevationLayer");
Global.namespace("locus.controls");
Global.namespace("locus.draw");
Global.namespace("locus.draw.cable");
Global.namespace("locus.select");
Global.namespace("locus.copy");
Global.namespace("locus.copy.features");
Global.namespace("locus.profile");
Global.namespace("locus.label");
Global.namespace("locus.flag");
Global.namespace("locus.screen");
Global.namespace("locus.save");
Global.namespace("locus.constant");
Global.namespace("locus.mousePositionCtl");
Global.namespace("locus.toPipe");

Global.namespace("line.initVar");
Global.namespace("line.method");
Global.namespace("line.map");
Global.namespace("line.layers");
Global.namespace("line.controls");
Global.namespace("line.draw");
Global.namespace("line.select");
Global.namespace("line.unit");
Global.namespace("line.save");
Global.namespace("line.constant");
Global.namespace("line.copy");
Global.namespace("line.flag");

Global.namespace("wire.initVar");
Global.namespace("wire.method");
Global.namespace("wire.map");
Global.namespace("wire.layers");
Global.namespace("wire.controls");
Global.namespace("wire.draw");
Global.namespace("wire.select");
Global.namespace("wire.unit");
Global.namespace("wire.save");
Global.namespace("wire.constant");
Global.namespace("wire.copy");
Global.namespace("wire.flag");

Global.option.ip = ip;
Global.option.URL = url;
//Global.option.ip = "localhost";
//Global.option.URL = "http://"+Global.option.ip+":8080/TONGXIANG/";
Global.style.form = [];
Global.color.cable = '#FF0000';
Global.color.cableSelect = '#33FF66';
Global.color.wire = '#FF0000';
Global.color.wireSelect = '#33FF66';
Global.color.rackpipe = '#3399CC';
Global.color.bridge = '#00FFFF';
Global.color.channel = '#996600';
Global.color.buried = '#00FF00';
Global.color.tunnel = '#666666';
Global.color.jacking = '#003399';
Global.color.pipeSelect = '#FF9900';
Global.lineWidth.cable = 4.0;
Global.lineWidth.pipe = 10.0;
Global.opacity.pipe = 0.8;
Global.unit = [];
Global.constant.electricalNum = 8;
Global.constant.zoomLevel = 4;
Global.constant.profileIndex = 0;
Global.constant.holeIndex = 0;
Global.constant.index = 0;
Global.constant.pp_segment = 10;
Global.constant.wirecable_segment = 20;
Global.constant.unit = 30;
Global.constant.scaleM = 0.00001;
Global.constant.scaleMM = 0.000001;
Global.constant.searchIndex = 0;
//Global.constant.cableEnlarge = 10;
Global.constant.terminal_length = 2;
Global.locus.attributes = [];
Global.locus.controls = [];
Global.locus.draw.cablePoints = [];
Global.locus.draw.controls = [];
Global.locus.draw.scale = [];
Global.locus.save.state = [];
Global.locus.label.point = [];
Global.locus.copy.features = [];
Global.locus.flag.slide = false;
Global.locus.flag.cableToPipe = false;
Global.locus.flag.cableToPipeStart = false;
Global.locus.flag.addProfileWell = false;
Global.locus.flag.addProfilePpSegment = false;
Global.locus.flag.addSingleHoleFlag = false;
Global.locus.flag.addRegularHoleFlag = false;
Global.locus.flag.addBracketFlag = false;
Global.locus.flag.matchCableFlag = false;
Global.locus.flag.labelFlag = false;
Global.locus.flag.labelDisplayFlag = false;
Global.locus.flag.addSurveyFlag = false;
Global.locus.flag.finishList = false;
Global.locus.flag.elevation = false;
Global.locus.flag.addPipe = false;
Global.locus.flag.finishAddPipe = false;
Global.locus.flag.judgeCross = true;
Global.locus.flag.form = false;
Global.locus.flag.copy = false;
Global.locus.flag.shift = false;
Global.locus.constant.saveNum = 6;
Global.locus.constant.saveEventsCount = 6;
Global.locus.constant.encode = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
Global.locus.constant.fullExtent = new OpenLayers.Bounds(// 鹰眼视图的边界设置
120.50490375617102, 30.588471912865483, 120.5859733795086, 30.685245907613851);
Global.locus.constant.originXML = new OpenLayers.Format.XML();
Global.locus.constant.originXML = '<wfs:GetFeature service="WFS" version="1.0.0" outputFormat="GML2"'
		+ '  xmlns:topp="http://www.openplans.org/topp"'
		+ '  xmlns:wfs="http://www.opengis.net/wfs"'
		+ '  xmlns:ogc="http://www.opengis.net/ogc"'
		+ '  xmlns:gml="http://www.opengis.net/gml"'
		+ '  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"'
		+ '  xsi:schemaLocation="http://www.opengis.net/wfs'
		+ '  http://schemas.opengis.net/wfs/1.1.0/WFS-locus.xsd">';

Global.line.controls = [];
Global.line.copy.features = [];
Global.line.flag.copy = false;
Global.line.flag.slide = false;
Global.line.constant.fullExtent = new OpenLayers.Bounds(-90, -90, 90, 90);

Global.wire.controls = [];
Global.wire.copy.features = [];
Global.wire.flag.copy = false;
Global.wire.constant.fullExtent = new OpenLayers.Bounds(-90, -90, 90, 90);

Global.style.form[0] = {
	externalGraphic : "img/icons/transformer_substation.svg",
	pointRadius : 8
};
Global.unit[0] = "1010201";// 变电站
Global.style.form[1] = {
	externalGraphic : "img/icons/switch_station.svg",
	pointRadius : 8
};
Global.unit[1] = "1010202";// 开关站
Global.style.form[2] = {
	externalGraphic : "img/icons/transformer.svg",
	pointRadius : 8
};
Global.unit[2] = "1010203";// 变压器
Global.style.form[3] = {
	externalGraphic : "img/icons/ringmainunit.svg",
	pointRadius : 8
};
Global.unit[3] = "1010204";// 环网柜
Global.style.form[4] = {
	externalGraphic : "img/icons/branch_box.svg",
	pointRadius : 8
};
Global.unit[4] = "1010205";// 分支箱
Global.style.form[5] = {
	externalGraphic : "img/icons/branch.svg",
	pointRadius : 8
};
Global.unit[5] = "1010206";// 分支接头(图标需要修改)
Global.style.form[6] = {
	externalGraphic : "img/icons/margin.svg",
	pointRadius : 8
};
Global.unit[6] = "1010207";// 盘余
Global.style.form[7] = {
	externalGraphic : "img/icons/transition_joint.svg",
	pointRadius : 8
};
Global.unit[7] = "1010208";// 中间接头
Global.style.form[8] = {
	externalGraphic : "img/icons/well.svg",
	pointRadius : 8
};
Global.unit[8] = "1020101";// 工井
Global.style.form[9] = {
	externalGraphic : "img/icons/tower.svg",
	pointRadius : 8
};
Global.unit[9] = "1020102";// 杆塔
Global.style.form[10] = {
	externalGraphic : "img/icons/fakewell.svg",
	pointRadius : 8
};
Global.unit[10] = "1020103";// 虚拟工井
Global.style.form[11] = {
	strokeWidth : Global.lineWidth.cable,
	strokeColor : Global.color.cable,
	strokeDashstyle : 'dash'
};// 电缆
Global.style.form[12] = {
	strokeWidth : Global.lineWidth.cable,
	strokeColor : Global.color.wire,
	strokeDashstyle : 'dot'
};// 导线
Global.style.form[13] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.rackpipe
};// 排管
Global.style.form[14] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.bridge
};// 桥架
Global.style.form[15] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.channel
};// 沟道
Global.style.form[16] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.buried
};// 直埋
Global.style.form[17] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.tunnel
};// 隧道
Global.style.form[18] = {
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.jacking
};// 顶管
Global.style.form[19] = {
	strokeOpacity: 0.5,
	strokeWidth : Global.lineWidth.pipe,
	strokeColor : Global.color.rackpipe
};// 虚拟管沟
Global.unit[13] = "1010601";// 间隔
Global.style.form[20] = {
	strokeWidth : 1.0,
	strokeColor : 'red'
};// 联络线
Global.style.form[21] = {
	strokeWidth : 4.0,
	strokeColor : 'red'	
};// 母线
Global.style.form[22] = {
	strokeWidth : 2.0,
	strokeColor : 'black',
	fill : false
};// 边框
Global.style.form[23] = {
	strokeWidth : 2.0,
	strokeColor : 'red'
};// 间隔
Global.style.form[24] = {
	strokeWidth : 2.0,
	strokeColor : 'red'
};// 分支进线
Global.style.form[25] = {
	strokeWidth : 2.0,
	strokeColor : 'blue'
};// 分支出线
/* 轨迹图保存按钮事件处理 */
Global.locus.save.saveStrategy = [];
for (var i = 0; i < Global.locus.constant.saveNum; i++) {
	Global.locus.save.saveStrategy[i] = new OpenLayers.Strategy.Save();
	Global.locus.save.saveStrategy[i].events
			.on({
				'success' : function(event) {
					
					if (Global.locus.flag.form) {
						$.post(Global.option.URL + 'saveProperty.action',
								Base.request.get_param(), function(res) {
							if(res != "success") {
								Plugins.dialog.warm({
									'title'	: '错误信息'
								},"属性同步异常，请检查网络后重试",{});
							} else {
								Plugins.dialog.remove();
							}
								});
						if(event.object.layer.name == '电气设备'){
							Global.locus.layers.electricalLayer.redraw();
						}
						if(event.object.layer.name == '土建设备'){
							Global.locus.layers.buildingLayer.redraw();
						}							
						Global.locus.flag.form = false;
					}
					Global.locus.save.state[event.object.layer.name] = 'success';
					if (Global.locus.save.saveMessage == "Error! Changes not saved") {
					} else
						Global.locus.save.saveMessage = "Changes saved";
					Global.locus.constant.saveEventsCount--;
					if (Global.locus.constant.saveEventsCount == 0) {
						Plugins.set_status(Global.locus.save.saveMessage);
						Global.locus.constant.saveEventsCount = Global.locus.constant.saveNum;
					}
				},
				'fail' : function(event) {
					Global.locus.save.state[event.object.layer.name] = 'fail';
					Global.locus.save.saveMessage = "Error! Changes not saved";
					Global.locus.constant.saveEventsCount--;
					if (Global.locus.constant.saveEventsCount == 0) {
						Plugins.dialog.warm({
							'title'	: '错误信息'
						},Global.locus.save.saveMessage + " 请重试",{});
						Global.locus.save.saveMessage = null;
						Global.locus.constant.saveEventsCount = Global.locus.constant.saveNum;
					}
				},
				scope : this
			});
}

/* 单线图保存按钮事件处理 */
Global.line.save.saveStrategy = new OpenLayers.Strategy.Save();
Global.line.save.saveStrategy.events.on({
	'success' : function(event) {
		Plugins.set_status('save success');
	},
	'fail' : function(event) {
		//alert("error!");
		Plugins.dialog.show({
			'text'	: "发生错误，请重试",
			'title'	: '错误'
		}, {
			'submit'	: function(){
				Plugins.dialog.remove();
			}
		});
	},
	scope : this
});
/* 接线图保存按钮事件处理 */
Global.wire.save.saveStrategy = new OpenLayers.Strategy.Save();
Global.wire.save.saveStrategy.events.on({
	'success' : function(event) {
		Plugins.set_status('save success');
	},
	'fail' : function(event) {
		//alert("error!");
		Plugins.dialog.show({
			'text'	: "发生错误，请重试",
			'title'	: '错误'
		}, {
			'submit'	: function(){
				Plugins.dialog.remove();
			}
		});
	},
	scope : this
});
/* 公用函数声明 */

/* 右键菜单 */
Global.method.onFeatureRightSelect = function(feature) {
	var tmp = {
		'1010201' : {// 变电站
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'			
		},
		'1010202' : {// 开关站
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010203' : {// 变压器
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010204' : {// 环网柜
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010205' : {// 分支箱
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010207' : {// 盘余
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010208' : {// 中间接头
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010206' : {// 分支接头
			'pic'					: '照片和附件',
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010402' : {// 电缆段
			'delete'				: '删除',
			'label'					: '标注偏移设置',
			'electrical_property' 	: '属性'
		},
		'1010403' : {// 导线段
			'delete'				: '删除',
			'electrical_property' 	: '属性'
		},
		'1010501' : {// 剖面
			'delete'		 	: '删除',
			'profile_property'	: '属性'
		},
		'1010502' : {// 管孔
			'delete'		: '删除',
			'hole_property' : '属性'
		},
		'1010503' : {// 支架
			'delete'			: '删除',
			'bracket_property'	: '属性'
		},
		'1010601' : {// 间隔
			'electrical_property' : '属性'
		},
		'1020101' : {// 工井
			'popli'		: '弹出立视图',
			'openli' 	: '打开立视图',
			'closeli' 	: '关闭立视图',
			'3dwell'	: '打开工井3D图',
			'pic'		: '照片和附件',
			'delete'	: '删除',
			'label'		: '标注偏移设置',
			'property'	: '属性'
		},
		'1020102' : {// 杆塔
			'pic'		: '照片和附件',
			'delete'	: '删除',
			'label'		: '标注偏移设置',
			'property'	: '属性'
		},
		'1020103' : {// 虚拟工井
			'delete'	: '删除',
			'label'		: '标注偏移设置',
			'property'	: '属性'
		},
		'1020203' : {// 虚拟管沟
			'delete'	: '删除',
			'property'	: '属性'
		},
		'1020211' : {// 排管
			'delete'	: '删除',
			'property'	: '属性',
			'3dpipe'	: '打开3D图'
		},
		'1020212' : {// 桥架
			'delete'	: '删除',
			'property'	: '属性'
		},
		'1020213' : {// 沟道
			'delete'	: '删除',
			'property'	: '属性'
		},
		'1020214' : {//直埋
			'delete'	: '删除',
			'property'	: '属性'
		},
		'1020215' : {//隧道
			'delete'	: '删除',
			'property'	: '属性',
			'3dpipe'	: '打开3D图'
		},
		'1020216' : {//顶管
			'delete'	: '删除',
			'property'	: '属性'
		},
		'1030110' : {//测绘层
			'delete'		: '删除',
			'survery_modify':	'坐标修正'
		}
	
	}
	
	Plugins.rmenu({
		x : screenX,
		y : screenY
	}, {
		feature : feature,
		data : tmp[feature.attributes['object_type']]

	}, function(e) {
		Base.request.handle_feature_menu(e);
	});
	return false;
}

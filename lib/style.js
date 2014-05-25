/*引入外部脚本*/
var main = document.createElement('script');
main.type = 'text/javascript';
main.src = 'main.js';

var scaleLevel = [];
scaleLevel[0] = 15000;
scaleLevel[1] = 10000;

Global.style.buildingStyle_label = new OpenLayers.StyleMap({
	"default" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15,
		label : "${name}",
		fontSize: "${getSize}",
//		labelSelect: true,
		labelXOffset : "${x_offset}",
		labelYOffset : "${y_offset}"
	}, {
		context: {
	        getSize: function(feature) {
	        	var type = feature.attributes['object_type'];
	        	var zoom = Global.locus.map.getZoom();
	        	switch(type){
	        	case '1010201' :
	        	case '1010202' :
	        	case '1010203' :
	        	case '1010204' :
	        	case '1010205' :
	        	case '1010206' :
	        	case '1010207' :
	        	case '1010208' :
	        	case '1020101' :
	        	case '1020102' :
	        	case '1020103' :
	        		if(zoom>3){
	        			return '5px';
	        		}else{
	        			return '0px';
	        		}
	        	break;
	        	default:
	        		if(zoom>2){
	        			return '5px';
	        		}else{
	        			return '0px';
	        		}
	        	}
	        }
	    },
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				externalGraphic : "img/icons/well.svg",
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cable,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				externalGraphic : "img/icons/well.svg",
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wire,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟管沟",
			symbolizer : {
				strokeOpacity: 0.3,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.rackpipe
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020203
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "排管",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.rackpipe
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020211
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "桥架",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.bridge
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020212
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "沟道",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.channel
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020213
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "直埋",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.buried
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020214
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "隧道",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.tunnel
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020215
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "顶管",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.jacking
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020216
			}),
			maxScaleDenominator : scaleLevel[0]
		}) ]
	}),
	"select" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15,
		label : "${name}",
		labelXOffset : "${x_offset}",
		labelYOffset : "${y_offset}"
	}, {
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cableSelect,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wireSelect,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟管沟",
			symbolizer : {
				strokeOpacity: 0.3,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020203
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "排管",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020211
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "桥架",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020212
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "沟道",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020213
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "直埋",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020214
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "隧道",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020215
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "顶管",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020216
			}),
			maxScaleDenominator : scaleLevel[0]
		}) ]
	})
});

Global.style.buildingStyle_nolabel = new OpenLayers.StyleMap({
	"default" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15
	}, {
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				externalGraphic : "img/icons/well.svg",
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cable,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				externalGraphic : "img/icons/well.svg",
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wire,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟管沟",
			symbolizer : {
				strokeOpacity: 0.3,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.rackpipe
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020203
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "排管",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.rackpipe
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020211
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "桥架",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.bridge
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020212
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "沟道",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.channel
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020213
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "直埋",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.buried
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020214
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "隧道",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.tunnel
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020215
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "顶管",
			symbolizer : {
				strokeOpacity: Global.opacity.pipe,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.jacking
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020216
			}),
			maxScaleDenominator : scaleLevel[0]
		}) ]
	}),
	"select" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15
	}, {
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cableSelect,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				externalGraphic : "img/icons/well.svg",
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wireSelect,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			}),
			maxScaleDenominator : scaleLevel[1]
		}), new OpenLayers.Rule({
			name : "虚拟管沟",
			symbolizer : {
				strokeOpacity: 0.3,
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020203
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "排管",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020211
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "桥架",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020212
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "沟道",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020213
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "直埋",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020214
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "隧道",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020215
			}),
			maxScaleDenominator : scaleLevel[0]
		}), new OpenLayers.Rule({
			name : "顶管",
			symbolizer : {
				strokeWidth : Global.lineWidth.pipe,
				strokeColor : Global.color.pipeSelect
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020216
			}),
			maxScaleDenominator : scaleLevel[0]
		}) ]
	})
});

Global.style.surveyStyle = new OpenLayers.StyleMap({
	'default' : {
		strokeColor : "black",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillOpacity : 0.5,
		fillColor : "#4FD5D6",
		pointRadius : 6,
		cursor : "inherit"
	},
	'select' : {
		strokeColor : "red",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillColor : "#4FD5D6",
		fillOpacity : 0.6,
		pointRadius : 6,
		cursor : "inherit"
	}
});

Global.style.profileStyle = new OpenLayers.StyleMap({
	'default' : {
		strokeColor : "black",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillOpacity : 0.5,
		fillColor : "#FFCC00",
		pointRadius : 6,
		cursor : "inherit"
	},
	'select' : {
		strokeColor : "red",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillColor : "#FFCC00",
		fillOpacity : 0.6,
		pointRadius : 6,
		cursor : "inherit"
	}
});

Global.style.pipe_propertyStyle = new OpenLayers.StyleMap({
	'default' : {
		strokeColor : "black",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillOpacity : 0.9,
		fillColor : "#f2f2f2",
		pointRadius : 6,
		cursor : "inherit"
	},
	'select' : {
		strokeColor : "red",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillColor : "#f2f2f2",
		fillOpacity : 1,
		pointRadius : 6,
		cursor : "inherit"
	}
});

Global.style.cableStyle = new OpenLayers.StyleMap({
	"default" : new OpenLayers.Style({
		graphicWidth : 15,
		pointRadius : "${cable_diameter}"
	}, {
		rules : [ new OpenLayers.Rule({
			name : "通信电缆",
			symbolizer : {
				externalGraphic : "img/holes/communicate.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 0
			})
		}), new OpenLayers.Rule({
			name : "400V",
			symbolizer : {
				externalGraphic : "img/holes/hole04.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 1
			})
		}), new OpenLayers.Rule({
			name : "10KV",
			symbolizer : {
				externalGraphic : "img/holes/hole10.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 2
			})
		}), new OpenLayers.Rule({
			name : "35KV",
			symbolizer : {
				externalGraphic : "img/holes/hole35.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 3
			})
		}), new OpenLayers.Rule({
			name : "110KV",
			symbolizer : {
				externalGraphic : "img/holes/hole110.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 4
			})
		}), new OpenLayers.Rule({
			name : "220KV",
			symbolizer : {
				externalGraphic : "img/holes/hole220.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 5
			})
		}), new OpenLayers.Rule({
			name : "330KV",
			symbolizer : {
				externalGraphic : "img/holes/hole330.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 6
			})
		}), new OpenLayers.Rule({
			name : "500KV",
			symbolizer : {
				externalGraphic : "img/holes/hole500.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 7
			})
		}), new OpenLayers.Rule({
			name : "750KV",
			symbolizer : {
				externalGraphic : "img/holes/hole750.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 8
			})
		}) ]
	}),
	"select" : new OpenLayers.Style({
		graphicWidth : 15,
		pointRadius : "${cable_diameter}"
	}, {
		rules : [ new OpenLayers.Rule({
			name : "通信电缆",
			symbolizer : {
				externalGraphic : "img/holes/communicate_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 0
			})
		}), new OpenLayers.Rule({
			name : "400V",
			symbolizer : {
				externalGraphic : "img/holes/hole04_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 1
			})
		}), new OpenLayers.Rule({
			name : "10KV",
			symbolizer : {
				externalGraphic : "img/holes/hole10_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 2
			})
		}), new OpenLayers.Rule({
			name : "35KV",
			symbolizer : {
				externalGraphic : "img/holes/hole35_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 3
			})
		}), new OpenLayers.Rule({
			name : "110KV",
			symbolizer : {
				externalGraphic : "img/holes/hole110_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 4
			})
		}), new OpenLayers.Rule({
			name : "220KV",
			symbolizer : {
				externalGraphic : "img/holes/hole220_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 5
			})
		}), new OpenLayers.Rule({
			name : "330KV",
			symbolizer : {
				externalGraphic : "img/holes/hole330_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 6
			})
		}), new OpenLayers.Rule({
			name : "500KV",
			symbolizer : {
				externalGraphic : "img/holes/hole500_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 7
			})
		}), new OpenLayers.Rule({
			name : "750KV",
			symbolizer : {
				externalGraphic : "img/holes/hole750_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "voltage_grade",
				value : 8
			})
		}) ]
	})
});

Global.style.tempStyle = new OpenLayers.StyleMap({
	'default' : {
		rotation : 60,
		pointerEvents : "unvisiblePainted",
		label : " ${length}",
		labelSelect : "${Select}",
		fontColor : "${favColor}",
		fontSize : "12px",
		fontFamily : "Courier New, monospace",
		fontWeight : "bold",
		labelAlign : "${align}",
		labelOutlineColor : "none",
		angle : "${angle}",
		labelOutlineWidth : 1
	}
});

Global.style.listStyle = new OpenLayers.StyleMap({
	'default' : {
		strokeColor : "black",
		strokeOpacity : 1,
		strokeWidth : 1,
		fillOpacity : 0.5,
		fillColor : "#E7DF9C"
	}
});

Global.style.labelStyle = new OpenLayers.StyleMap({
	'default' : {
		strokeOpacity : 0,
		fillOpacity : 0,
		pointRadius : 6,
		label : "${pipe_hole_id}" + " " + "${cable_name}" + " "
				+ "${is_available}",
		fontColor : "${color}",
		fontSize : "12px",
		fontFamily : "Courier New, monospace",
		fontWeight : "bold",
		labelAlign : "cm",
		labelOutlineColor : "white",
		labelOutlineWidth : 1
	}
});

Global.style.lineDiagramStyle = new OpenLayers.StyleMap({
	"default" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15,
		label : "${getLabel}",
		labelYOffset : -15
	}, {
		context: {
	        getLabel: function(feature) {
	            if(feature.attributes['name']) {
	                return feature.attributes['name'];
	            }else{
	            	return '';
	            }
	        }
	    },
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			})
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			})
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			})
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			})
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			})
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			})
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			})
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			})
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			})
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			})
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cable,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			})
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wire,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			})
		}), new OpenLayers.Rule({
			name : "终端头",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cable,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1
			})
		}) ]
	}),
	"select" : new OpenLayers.Style({
		graphicZIndex : "${z_index}",
		graphicWidth : 15,
		label : "${getLabel}",
		labelYOffset : -15
	}, {
		context: {
	        getLabel: function(feature) {
	            if(feature.attributes['name']) {
	                return feature.attributes['name'];
	            }else{
	            	return '';
	            }
	        }
	    },		
		rules : [ new OpenLayers.Rule({
			name : "变电站",
			symbolizer : {
				externalGraphic : "img/icons/transformer_substation_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010201
			})
		}), new OpenLayers.Rule({
			name : "开关站",
			symbolizer : {
				externalGraphic : "img/icons/switch_station_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010202
			})
		}), new OpenLayers.Rule({
			name : "变压器",
			symbolizer : {
				externalGraphic : "img/icons/transformer_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010203
			})
		}), new OpenLayers.Rule({
			name : "环网柜",
			symbolizer : {
				externalGraphic : "img/icons/ringmainunit_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010204
			})
		}), new OpenLayers.Rule({
			name : "分支箱",
			symbolizer : {
				externalGraphic : "img/icons/branch_box_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010205
			})
		}), new OpenLayers.Rule({
			name : "分支接头",
			symbolizer : {
				externalGraphic : "img/icons/branch_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010206
			})
		}), new OpenLayers.Rule({
			name : "盘余",
			symbolizer : {
				externalGraphic : "img/icons/margin_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010207
			})
		}), new OpenLayers.Rule({
			name : "中间接头",
			symbolizer : {
				externalGraphic : "img/icons/transition_joint_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010208
			})
		}), new OpenLayers.Rule({
			name : "工井",
			symbolizer : {
				externalGraphic : "img/icons/well_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020101
			})
		}), new OpenLayers.Rule({
			name : "杆塔",
			symbolizer : {
				externalGraphic : "img/icons/tower_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020102
			})
		}), new OpenLayers.Rule({
			name : "虚拟工井",
			symbolizer : {
				externalGraphic : "img/icons/fakewell_select.svg"
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1020103
			})
		}), new OpenLayers.Rule({
			name : "电缆",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cableSelect,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010402
			})
		}), new OpenLayers.Rule({
			name : "导线",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.wireSelect,
				strokeDashstyle : 'dot'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010403
			})
		}), new OpenLayers.Rule({
			name : "终端头",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : Global.color.cableSelect,
				strokeDashstyle : 'dash'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1
			})
		}) ]
	})
});

Global.style.wireDiagramStyle = new OpenLayers.StyleMap({
	"default" : new OpenLayers.Style({
		label : "${getLabel}",
		labelYOffset : -15
	}, {
		context: {
	        getLabel: function(feature) {
	            if(feature.attributes['name']) {
	                return feature.attributes['name'];
	            }else{
	            	return '';
	            }
	        }
	    },
		rules : [ new OpenLayers.Rule({
			name : "母线",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : 'red'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1//自定义类型
			})
		}), new OpenLayers.Rule({
			name : "边框",
			symbolizer : {
				strokeColor : "black",
				strokeWidth : Global.lineWidth.cable/2,
				fill		: false
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 2//自定义类型
			})
		}), new OpenLayers.Rule({
			name : "联络线",
			symbolizer : {
				strokeWidth : 30.0,
				strokeColor : 'red',
				strokeOpacity : 0,	
			},
			filter : new OpenLayers.Filter.Logical({
				type : OpenLayers.Filter.Logical.OR,
				filters : [ new OpenLayers.Filter.Comparison({
					type : OpenLayers.Filter.Comparison.EQUAL_TO,
					property : "object_type",
					value : 1010402
				}), new OpenLayers.Filter.Comparison({
					type : OpenLayers.Filter.Comparison.EQUAL_TO,
					property : "object_type",
					value : 1010403
				}), ]
			})
		}), new OpenLayers.Rule({
			name : "间隔",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'red',
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010601
			})
		}),new OpenLayers.Rule({
			name : "分支进线",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'red'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 3//自定义类型
			})
		}),new OpenLayers.Rule({
			name : "分支出线",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'blue'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 4//自定义类型
			})
		})]
	}),
	"select" : new OpenLayers.Style({
		label : "${getLabel}",
		labelYOffset : -15
	}, {
		context: {
	        getLabel: function(feature) {
	            if(feature.attributes['name']) {
	                return feature.attributes['name'];
	            }else{
	            	return '';
	            }
	        }
	    },
		rules : [ new OpenLayers.Rule({
			name : "母线",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable,
				strokeColor : 'blue'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1//自定义类型
			})
		}), new OpenLayers.Rule({
			name : "边框",
			symbolizer : {
				strokeWidth : Global.lineWidth.cable/2,
				strokeColor : 'red',
				fill		: false
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 2//自定义类型
			})
		}), new OpenLayers.Rule({
			name : "联络线",
			symbolizer : {
				strokeWidth : 20.0,
				strokeColor : 'yellow',
				strokeOpacity : 0,
			},
			filter : new OpenLayers.Filter.Logical({
				type : OpenLayers.Filter.Logical.OR,
				filters : [ new OpenLayers.Filter.Comparison({
					type : OpenLayers.Filter.Comparison.EQUAL_TO,
					property : "object_type",
					value : 1010402
				}), new OpenLayers.Filter.Comparison({
					type : OpenLayers.Filter.Comparison.EQUAL_TO,
					property : "object_type",
					value : 1010403
				}), ]
			})
		}), new OpenLayers.Rule({
			name : "间隔",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'yellow',
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 1010601
			})
		}),new OpenLayers.Rule({
			name : "分支进线",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'green'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 3//自定义类型
			})
		}),new OpenLayers.Rule({
			name : "分支出线",
			symbolizer : {
				strokeWidth : 2.0,
				strokeColor : 'yellow'
			},
			filter : new OpenLayers.Filter.Comparison({
				type : OpenLayers.Filter.Comparison.EQUAL_TO,
				property : "object_type",
				value : 4//自定义类型
			})
		})]
	})
});
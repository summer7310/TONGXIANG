/*
 * 前台页面效果处理模块
 */
var Plugins = {};
var slide;//单线图的侧边栏
var slide2;//接线图侧边栏

/*
 * 菜单是否选中状态标志
 */
Plugins.FLAG = {}
Plugins.FLAG.MAP = {//地图菜单
	M1 : true,		//平移
	M2 : false,		//放大
	M3 : false,		//缩小
	M4 : false,		//鹰眼
	M5 : false,		//保存
	M6 : false,		//编辑
	M7 : false,  	//拖动
	M8 : false,		//复制
	M9 : false,		//添加管沟
	M10: false,		//标注
	M11: false,		//全剖面
	M12: false,		//全设备
	M13: false,		//矩形剖面
	M14: false,		//拱形剖面
	M15: false, 	//圆形剖面
	M16: false,		//管孔
	M17: false,		//规则管孔
	M18: false,		//支架
	M19: false,		//多边形
	M20: false,		//穿管
	M21: false,		//放置电缆
	M22: false		//剖面注释
};
Plugins.FLAG.LINE = {//单线图菜单
	L1 : true,		//移动
	L2 : false,		//放大
	L3 : false,		//缩小
	L4 : false,		//鹰眼
	L5 : false,		//保存
	L6 : false,		//编辑
	L7 : false,		//拖动
	L8 : false,		//复制
	L9 : false,		//左边端头
	L10: false		//右边端头
};
Plugins.FLAG.WIRE = {//接线图菜单
	W1 : true,		//移动
	W2 : false,		//放大
	W3 : false,		//缩小
	W4 : false,		//鹰眼
	W5 : false,		//保存
	W6 : false,		//编辑
	W7 : false,		//拖动
	W8 : false,		//复制
	W9 : false,		//母线
	W10: false,		//间隔框	
	W11: false,		//分支进线
	W12: false		//分支出线
};
                               
//单线图，接线图是否加载数据标志
Plugins.FLAG.hasData = {
	line : false,
	connect: false
}

$(document).ready(function(){
	Plugins.init_tabs(".ltabs", function(){});
	Plugins.init_tabs(".midtabs", function(obj){
		Plugins.change_content(obj);
	});
	Plugins.init_menu('.menu');
	//Plugins.menu.refresh();
	Plugins.menu.update();
	Plugins.init_dropmenu('#add_feather_list');	
	Plugins.init_dropmenu('#layout_list2');
	Plugins.switch_button('.dou');
	Plugins.switch_button('.dou2');
	Plugins.switch_button('.dou3');
	Plugins.body_hide_all();
	Plugins.init_height();
	Plugins.pop_help('.hoverpop');
	Plugins.init_button();
	Plugins.init_search_result();
	Plugins.init_tree();
	$(document).bind("contextmenu", function(e) { 
		return false; 
	}); 
	Base.handle.search();
	//Plugins.get_staticid();
});

Plugins.init_tabs = function(obj, func){
	$(obj).children("li").click(function(){
		var t = $(this);
		Plugins.func_tabs(obj, func, t);
	});
}

Plugins.func_tabs = function(obj, func, t){
	$(obj).children("li").removeClass("current");
	t.addClass("current");
	var id = t.attr("rel");
	
	$(obj + "_div").children("div").css({"display":"none"});
	$(obj + "_" + id).fadeIn("fast");
	func(t);
}

Plugins.init_height = function(){
	var h = $(window).height() - 76;
	var h2 = h + 34;
	var obj = ['#map', '#map2', '#map3','#setting', '.ltabs_div', '.list_menu', '.list_menu2', '.list_attachment', '.list_box', '.list_box2', '.list_box3','.attachment'];
	for(var key in obj){
		if(obj[key]=='#setting'){
			$(obj[key]).css({'height' : h2 + 'px'});
		} else if(obj[key]=='.list_box'){
			h3 = h-34;
			$(obj[key]).css({'height' : h3 + 'px'});
		} else if(obj[key]=='.attachment'){
			h4 = h-62;
			$(obj[key]).css({'height' : h4 + 'px'});
		} else if(obj[key]=='.list_box3'){
			h5 = h-70
			$(obj[key]).css({'max-height' : h5 + 'px'});
		} else {
			$(obj[key]).css({'height' : h + 'px'});
		}
	}
}

/*
 * 菜单事件处理
 */
Plugins.init_menu = function(obj){
	$(obj).children('.cli').click(function(){
		//$(this).parent().parent().find('li').removeClass('sele');
		//$(this).addClass('sele');
	
		Base.handle.main_menu($(this));
		//return false;
	});
	
	$(obj).find('.hov').hover(function(){
		$(this).children('div').css({'display' : 'block'});
	}, function(){
		$(this).children('div').css({'display' : 'none'});
	});
}

Plugins.menu = {};
//更新菜单的选中状态
Plugins.menu.update = function(){
	var map_menu = $('.midtabs_1').find('.cli');
	var line_menu = $('.midtabs_2').find('.cli');
	var wire_menu = $('.midtabs_3').find('.cli');
	
	switch(Global.constant.currentMap){
	case 0:
		map_menu.each(function(){
			var id = $(this).attr('id');
			if(Plugins.FLAG.MAP[id]){
				$(this).addClass('sele');
			} else {
				$(this).removeClass('sele');
			}
		});
		break;
	case 1:
		line_menu.each(function(){
			var id = $(this).attr('id');
			if(Plugins.FLAG.LINE[id]){
				$(this).addClass('sele');
			} else {
				$(this).removeClass('sele');
			}
		});
		break;
	case 2:
		wire_menu.each(function(){
			var id = $(this).attr('id');
			if(Plugins.FLAG.WIRE[id]){
				$(this).addClass('sele');
			} else {
				$(this).removeClass('sele');
			}
		});
		break;
	}
}
//撤销所有菜单的选中状态
Plugins.menu.refresh = function(){
	switch(Global.constant.currentMap){
	case 0:
		for(key in Plugins.FLAG.MAP){		
			if(key != 'M10' && key != 'M11'){
				Plugins.FLAG.MAP[key] = false;
			}		
		}
		break;
	case 1:
		for(key in Plugins.FLAG.LINE){
			if(key != 'M10'){
				Plugins.FLAG.LINE[key] = false;
			}
		}
		break;
	case 2:
		for(key in Plugins.FLAG.WIRE){
			if(key != 'M10'){
				Plugins.FLAG.WIRE[key] = false;
			}
		}
		break;
	}
	Plugins.menu.update();
}

Plugins.body_hide_all = function(){
	$('body').click(function(){
		Plugins.hide_all_drop();
	});
}

Plugins.hide_all_drop = function(){
	$('.rmenu').css({'display' : 'none'});
}

Plugins.init_dropmenu = function(obj){
	$(obj).find('li').click(function(){
		$('.menu').find('li').removeClass('sele');
		Base.handle.dropmenu($(this));
		return false;
	});
	
	$('.drop_box').click(function(){
	
	});
}

/*
 * 开关事件按钮
 */
Plugins.switch_button = function(obj){
	tg($(obj + '_h'), 'none');
	
	$(obj + '_h').click(function(){
		tg($(obj + '_h'), 'none');
		tg($(obj + '_v'), 'block');
	});
	$(obj + '_v').click(function(){
		tg($(obj + '_h'), 'block');
		tg($(obj + '_v'), 'none');
	});
	
	function tg(o, t){
		o.css({display : t});
	}
}
 

//status///////////////////////////
//type{normal, warm}
//mod{keep, flash}
Plugins.set_status = function(info, style, mode){
	mode = arguments[2]?arguments[2]:'flash';
	style = arguments[1]?arguments[1]:'normal';
	
	$('#status').fadeIn('fast').text(info).removeClass().addClass('status_' + style);
		
	if(mode == 'flash'){
		var go = setTimeout(function(){
			$('#status').fadeOut('fast');
		},8000);	
	} 
}

/*
 * 弹出框
 */
Plugins.dialog = {};
Plugins.dialog.show = function(v, callback){
//	Plugins.slide_form.remove();
	v['width'] = v['width']?v['width']:842;
	//v['url'] =  v['url']?v['url']:"null";
	//v['text'] = v['text']?v['text']:"message";
	v['title'] = v['title']?v['title']:'弹出框';
	v['param'] = v['param']?v['param']:{};
	callback['submit'] = callback['submit']?callback['submit']:function(e){};
	callback['cancel'] = callback['cancel']?callback['cancel']:function(e){};
	callback['success'] = callback['success']?callback['success']:function(e){};
	callback['fail'] = callback['fail']?callback['fail']:function(e){};
	
	if(typeof(v['text']) != 'undefined'){
		v['width'] = 300;
	}
	
	var tmp_title 	= '<div id="dialog_title"><h3>' + v['title'] + '</h3><div class="close" id="close_dialog"></div></div>';
	var tmp_dock 	= '<div class="dialog_dock"><div class="dock_button button_normal button cancel">取 消</div><div class="button button_normal dock_button" id="dialog_yes">确 定</div></div>';
	var tmp 		= '<div id="hide_layout"><div id="dialog" style="width:' + v['width'] + 'px;" tabindex="0">' + tmp_title + '<div id="td"><div class="loading"><img src="' + Global.option.URL + '/img/loading.gif" /><span>加载中</span></div></div>' + tmp_dock + '</div></div>';
	$('body').append(tmp);
	
	if(typeof(v['url']) != "undefined"){
		$.post(v['url'], v['param'],
			function(html){
				
				$('#td').html(html);
				callback['success'](v);
				fix_dialog();				
				
		}, 'html');
	} else {
		$('#td').html('<div style="padding:10px 10px 0 0;text-align:left;">' + v['text'] + '</div>');
	}
	

	fix_dialog();
	$('#dialog').focus();
	
	$('#dialog').find('.close').click(function(){
		Plugins.dialog.remove();
	});
	
	$('#dialog').find('.cancel').click(function(){
		callback.cancel(v);
		Plugins.dialog.remove();
	});
	
	$('#dialog').keydown(function(e){
		if(e.ctrlKey && e.keyCode == '13'){
			if(Plugins.dialog.lock('lock')){
				callback.submit(v);
				Plugins.dialog.lock('locked')
			}
		}
	});
	
	$('#dialog_yes').click(function(){
		if(Plugins.dialog.lock('lock')){
			
			Plugins.dialog.lock('locked');
			callback.submit(v);	
		}
	});
	
	move_dialog();
	
	$('.button').hover(function(){
		$(this).removeClass('button_normal');
		$(this).addClass('button_hover');
	}, function(){
		$(this).removeClass('button_hover');
		$(this).addClass('button_normal');
	});
	
	function fix_dialog(){
		if($('#dialog').height() > $(window).height()-40){
			$('#td').css({height : $(window).height()-200});
		}
		var h = $(document).height();
		var top = $(window).height()/2-$('#dialog').height()/2;
		var left = $(window).width()/2-$('#dialog').width()/2;
		$('#dialog').css({'margin-left' : left, 'margin-top' : top});
	}

	//move dialog
	function move_dialog(){
		var _move=false;
		var _x,_y;
	    $("#dialog_title").click(function(){
	        }).mousedown(function(e){
	        _move=true;
	        _x=e.pageX-parseInt($("#dialog").css("margin-left"));
	        _y=e.pageY-parseInt($("#dialog").css("margin-top"));
	    });
	    $(document).mousemove(function(e){
	        if(_move){
	            var x=e.pageX-_x;
	            var y=e.pageY-_y;
	            $("#dialog").css({'margin-top':y,'margin-left':x});
	        }
	    }).mouseup(function(){
	    _move=false;
	  });
	}
}

Plugins.dialog.fix = function(){
	if($('#dialog').height() > $(window).height()-40){
		$('#td').css({height : $(window).height()-200});
	}
	var h = $(document).height();
	var top = $(window).height()/2-$('#dialog').height()/2;
	var left = $(window).width()/2-$('#dialog').width()/2;
	$('#dialog').css({'margin-left' : left, 'margin-top' : top});
}

/*
 * 弹出框的子对话框，仅在调用父对话框时可以调用
 */
Plugins.dialog.warm = function(v, text, callback){
	if($('#hide_layout').html().length < 1){
		return;
	}
	
	v['title'] = v['title']?v['title']:'提 示';
	callback['yes'] = callback['yes']?callback['yes']:function(e){};
	callback['no'] = callback['no']?callback['no']:function(e){};
	
	Plugins.dialog.hidden();
	
	var tmp_title 	= '<div id="warm-title"><h3>' + v['title'] + '</h3><div class="close" id="close-warm"></div></div>';
	var tmp_dock 	= '<div class="warm-dock"><div class="warm-button button_normal button cancel">取 消</div><div class="button button_normal warm-button" id="warm-yes">确 定</div></div>';
	var tmp 		= '<div id="dialog-warm" tabindex="0">' + tmp_title + '<div id="warm-td"><div class="loading"><img src="' + Global.option.URL + '/img/loading.gif" /><span>加载中</span></div></div>' + tmp_dock + '</div>';
	$('#hide_layout').append(tmp);	
	$('#warm-td').html('<div style="padding:10px 10px 0 0;text-align:left;">' + text + '</div>');
		
	fix_dialog();
	$('#dialog-warm').focus();
	
	$('#dialog-warm').find('.close').click(function(){
		$('#dialog-warm').remove();
		Plugins.dialog.display();
	});
	
	$('#dialog-warm').find('.cancel').click(function(){
		callback.no(v);
		remove_dialog();
		Plugins.dialog.display();
		Plugins.dialog.lock('unlocked');
	});
	
	$('#dialog-warm').keydown(function(e){
		if(e.ctrlKey && e.keyCode == '13'){
			if(Plugins.dialog.lock('lock')){
				callback.yes(v);
				remove_dialog();
				Plugins.dialog.display();
				Plugins.dialog.lock('unlocked');	
			}
		}
	});
	
	$('#warm-yes').click(function(){
		callback.yes(v);		
		remove_dialog();
		Plugins.dialog.display();
		Plugins.dialog.lock('unlocked');		
		//$('#hide_layout').remove();
	});
	
	$('.button').hover(function(){
		$(this).removeClass('button_normal');
		$(this).addClass('button_hover');
	}, function(){
		$(this).removeClass('button_hover');
		$(this).addClass('button_normal');
	});
	
	function fix_dialog(){
		if($('#dialog-warm').height() > $(window).height()-40){
			$('#warm-td').css({height : $(window).height()-200});
		}
		var h = $(document).height();
		var top = $(window).height()/2-$('#dialog-warm').height()/2;
		var left = $(window).width()/2-$('#dialog-warm').width()/2;
		$('#dialog-warm').css({'margin-left' : left, 'margin-top' : top});
	}

	function remove_dialog(){
		$('#dialog-warm').remove();
	}
}

Plugins.dialog.lock =  function(mode){
	if(mode == 'locked'){
		$('#dialog_yes').text('处理中');
		return false;
	} else if(mode == 'unlocked') {
		$('#dialog_yes').text('确 定')
		return true;
	} else {
		if($('#dialog_yes').text() == '确 定'){
			return true;
		} else {
			return false;
		}
	}
}

Plugins.dialog.remove =  function(){
	$('#hide_layout').remove();
}

Plugins.dialog.hidden =  function(){
	$('#dialog').css({'display': 'none'});
}

Plugins.dialog.display =  function(){
	$('#dialog').css({'display': 'inline'});
	$('#dialog').focus();
}

Plugins.dialog.add = function(name, value, set){
	set = set?set:'str';
	$('.property').append('<input type="hidden" name=\"' + name + '\" value= \"' + value + '\" class="' + set + '" />');
}

Plugins.dialog.enable = function(name, sta){
	$('.property').find(name).attr();
}

Plugins.dialog.set = function(obj, value){
	$('.property').find(obj).val(value);
}

Plugins.dialog.getval = function(obj){
	return $('.property').find(obj).val();
}
/*
 * 菜单提示
 */
Plugins.pop_help = function(obj){
	$('#content_menu').find('li').find('.tip').each(function(){
		$(this).addClass('s'+$(this).text().length);
	});
	
	$('#content_menu').find('.cli').hover(function(){
		$(this).find('.tip').css({display: 'block'});
	}, function(){
		$(this).find('.tip').css({display: 'none'});
	});
}

/*
 * 按钮变色
 */
Plugins.init_button = function(){
	$('.gb').hover(function(){
		$(this).removeClass('grey_bg');
		$(this).addClass('grey_bg_hover');
	}, function(){
		$(this).removeClass('grey_bg_hover');
		$(this).addClass('grey_bg');
	});
	
	$('.button').hover(function(){
		$(this).removeClass('button_normal');
		$(this).addClass('button_hover');
	}, function(){
		$(this).removeClass('button_hover');
		$(this).addClass('button_normal');
	});
}

/*
 * 搜索结果
 */
Plugins.init_search_result = function(){
	$('.tul1').find('li').hover(function(){
		$(this).find('.tcon').css({'display' :'block'});
	}, function(){
		$(this).find('.tcon').css({'display' :'none'});
	});
}

/*
 * 右键菜单1
 */
Plugins.rmenu = function(pos, setting, func){
    var h_tmp_c = 0;
    $('.rmenu').remove();
    
    setting['data'] = setting['data']?setting['data']:[{}];
	var tmp ="";
	for(key in setting['data']){
		tmp += '<li class="' + key + '">' + setting['data'][key] + '</li>'; 
	}
	
	$('.drop_menu').remove();
	$('body').append('<div class="rmenu box_shadow"><ul>' + tmp + '</ul></div>');
	
	$('.rmenu').find('li').click(function(){
		func({
			'feature'	: setting['feature'],
			'obj' 		: $(this).attr('class')
		});
	});
	
	var ww = $(window).width();
    var wh = $(window).height();

    var mw = $('.rmenu').width();
    var mh = $('.rmenu').height();
    
    var x = 0;
    var y = 0;
    
    if(pos['x'] > ww-mw-10){
    	x = pos['x'] - mw;
    } else {
    	x = pos['x'];
    }
    
    if(pos['y'] > wh-mh-10){
    	y = pos['y'] - mh;
    } else {
    	y = pos['y'];
    }
  
    $('.rmenu').css({
    	left	: x,
    	top		: y
    });
	
	$('body').click(function(){
		$('.rmenu').remove();
	});
}

/*
 * 右键下拉菜单_樹目錄
 */
Plugins.drop_menu = function(event, setting, func){
	setting['data'] = setting['data']?setting['data']:[{}];
	var tmp ="";
	for(key in setting['data']){
		tmp += '<li class="' + key + '">' + setting['data'][key] + '</li>'; 
	}
	
	$('.drop_menu').remove();
	$('body').append('<div class="drop_menu box_shadow"><ul>' + tmp + '</ul></div>');
	$('.drop_menu').css({'left': event.clientX, 'top': event.clientY});
	
	$('.drop_menu').find('li').click(function(){
		func({
			'father': setting['father'],
			'node' 	: setting['node'],
			'obj' 	: $(this).attr('class')
		});
	});
	
	$('body').click(function(){
		$('.drop_menu').remove();
	});
}

/*
 * 切换主界面
 * obj:产生点击事件的对象
 */
Plugins.change_content = function(obj){
	
	var id = obj.attr('rel');
	if(id == 1){
		Global.constant.currentMap = 0;
		change({
			'#content_menu'	: 'block',
			'#map' 			: 'block',
			'#map2' 		: 'none',
			'#map3' 		: 'none',
			'#setting' 		: 'none'
		});
		slide.slide_out();
		slide2.slide_out();	
	}
	
	if(id == 2){
		Global.constant.currentMap = 1;
		change({
			'#content_menu'	: 'block',
			'#map' 			: 'none',
			'#map2' 		: 'block',
			'#map3' 		: 'none',
			'#setting' 		: 'none'
		});
		if($('#map2').html().length < 100){
			
			$.post(Global.option.URL +'/line.html', {
				
			}, function(html){
				$('#map2').html(html);
			});		
		}
		if(!Plugins.FLAG.hasData.line){
			Plugins.dialog.show({
				title 	: '提醒',
				text 	: '未加载数据，请从树目录中选择相应线路'
			}, {
				submit	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		
		if(slide.hasData){
			slide.slide_in();
		}  	
		
		slide2.slide_out();	
		Plugins.slide_form.remove();
	}

	if(id == 3){
		Global.constant.currentMap = 2;
		change({
			'#content_menu'	: 'block',
			'#map' 			: 'none',
			'#map2' 		: 'none',
			'#map3' 		: 'block',
			'#setting' 		: 'none'
		});
		if($('#map3').html().length < 100){
			
			$.post(Global.option.URL +'/wire.html', {
				
			}, function(html){
				$('#map3').html(html);
			});
		} 
		
		if(!Plugins.FLAG.hasData.connect){
			Plugins.dialog.show({
				title 	: '提醒',
				text 	: '未加载数据，请从树目录中选择相应设备'
			}, {
				submit	: function(){
					Plugins.dialog.remove();
				}
			});
		}
		
		if(slide2.hasData){
			slide2.slide_in();
		}  
		
		slide.slide_out();
		Plugins.slide_form.remove();
	}

	if(id == 4){
		change({
			'#content_menu'	: 'none',
			'#map' 			: 'none',
			'#map2' 		: 'none',
			'#map3' 		: 'none',
			'#setting' 		: 'block'
		});
		
		$('#setting_frame').attr('src', Global.option.URL+'getSystemPage.action?pageType=uploadsXML');
		
		slide.slide_out();	
		slide2.slide_out();	
		Plugins.slide_form.remove();
	}
	
	function change(aim){
		for(key in aim){
			$(key).css({display: aim[key]});
		}
	}
}

/*
 * 树目录
 */
Plugins.init_tree = function(){
	slide 	= new Plugins.slide_menu('.list_menu');
	slide2 	= new Plugins.slide_menu('.list_menu2', '设备信息');
	
	var setting = {
		async: {
			enable: true,
			url:"initTree.action",
			//url:"tree/data_test.html",
			autoParam:["id", "name", "level"]
			//otherParam:{"otherParam":"zTreeAsyncTest"}//,
			//dataFilter: filter
		},
		callback: {
			onRightClick: onRightClick,
			onMouseUp	: onMouseUp,
			onCollapse	: collapse
			//beforeAsync: BeforeAsync
		},
		view: {
			//addDiyDom: addDiyDom
		}
	};
	
	/*
	 * 
	 */
	function collapse(event, treeId, treeNode){
		//alert("f");
		//if (treeNode && treeNode.length>0) {
			zTree.reAsyncChildNodes(treeNode, "refresh", true);
			//alert("f");
		//}
	}
	
/*
//	function filter(treeId, parentNode, childNodes) {
//		if (!childNodes) return null;
//		for (var i=0, l=childNodes.length; i<l; i++) {
//			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
//		}
//		return childNodes;
//	}
	*/
	function onRightClick(event, treeId, treeNode) {
		zTree.selectNode(treeNode);
		var type = treeNode.id.split("-")[0];	
		var tmp = {
				'1030101':{//城区
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_station' 	: '增加变电站'
				}},
				'1010201':{//变电站
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'show_single_station': '单变电站显示',
						'new_line' 		: '增加新线路',						
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1010301':{//线路
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'single_line' 	: '打开单线图',
						'show_single_line': '单线路显示',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10104010':{//标准段
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_cableseg'	: '增加电缆段',
						'add_wireseg'	: '增加导线段'
				}},
				'1010402':{//电缆段
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'topipe'		: '电缆段入沟',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1010403':{//导线段
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'topipe'		: '导线段入沟',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10102020':{//开关站组织目录
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_switchStation' : '增加开关站'
				}},
				'1010202':{//开关站
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'connect_line' 	: '打开接线图',
						'add_inter' 	: '增加间隔',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10102050':{//分支箱组织目录
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_branchBox' : '增加分支箱'
				}},
				'1010205':{//分支箱
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'connect_line' 	: '打开接线图',
						'add_inter' 	: '增加间隔',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10102040':{//环网柜组织目录
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_ringmainuint' : '增加环网柜'
				}},
				'1010204':{//环网柜
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'connect_line' 	: '打开接线图',
						'add_inter' 	: '增加间隔',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10102030':{//变压器组织目录
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_transformer' 	: '增加变压器'
				}},
				'1010203':{//变压器
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'connect_line' 	: '打开接线图',
						'add_inter'		: '增加间隔',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1010601':{//间隔
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1010602':{//虚拟间隔
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'10200000':{//土建
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_road'		: '增加道路',
						'pro'			: '属性'
				}},
				'1020301':{//道路
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'add_pipeline'	: '增加管沟线',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020101':{//工井
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020102':{//杆塔
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'pic'			: '照片和附件',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020103':{//虚拟工井
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020200':{//管沟线
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020203':{//虚拟管沟
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020211':{//排管
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020212':{//桥架
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020213':{//沟道
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020214':{//直埋
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020215':{//隧道
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}},
				'1020216':{//顶管
					'father' : treeId,
					'node' : treeNode,
					'data' : {
						'locate'		: '地图定位',
						'delete'		: '删除',
						'pro'			: '属性'
				}}		
		};
			
		function click_func(e){
			Base.handle.tree_menu(e);
		}
		
		if(typeof(tmp[type]) != "undefined"){
			Plugins.drop_menu(event, tmp[type], click_func);	
		};
		
	}

	function onMouseUp(event, treeId, treeNode) {}
	$.fn.zTree.init($("#treelist"), setting);
	zTree = $.fn.zTree.getZTreeObj("treelist");
}

/*
 * 右侧画出列表
 * 从远程获取JSON数据
 */
Plugins.slide_menu = function(obj, title){
	this.obj = $(obj);
	this.title = title?title:'线缆所经设备';
	this.obj.find('.list_title').text(this.title);
	this.hasData = false;
	 

	this.set_data = function(data){
		var tmp ="";
		var flag = 0;
		var flag2 = 0;
		var catetitle = {
				'1010201'	: '变电站',
				'1010202'	: '开关站',
				'1010205'	: '分支箱',
				'1010204'	: '环网柜',
				'1010203'	: '变压器',
				'1010208'	: '中间接头',
				'1020102'	: '塔杆',
				'1010601'	: '间隔',
				'1010402'	: '电缆段',
				'1010403'	: '导线段',
				'1020101'	: '工井'
		}
		
		for(key in data){
			var ifconnect = true;
			if(data[key].wc=='' || typeof(data[key].wc) == 'undefined'){
				ifconnect = false;
			}
			
			//alert(data[key].type + data[key].wc)
			if(flag != data[key].type && !ifconnect){
				 
				tmp += '<tr><th width="160px">' + catetitle[data[key].type] + '</th><th width="70px">操作</th></tr>';
			}
			if(flag2 != data[key].wc && ifconnect){
				var title_name;
				if(data[key].wc == 'start'){
					title_name = '线缆段(出线)';
				}
				else if(data[key].wc == 'end'){
					title_name = '线缆段(进线)';
				} else {
					title_name = '';
				}
				
				tmp += '<tr><th width="160px">' + title_name + '</th><th width="70px">操作</th></tr>';
			}
			
			flag = data[key].type;
			flag2 = data[key].wc?data[key].wc:'';
		
			tmp += '<tr id="' + data[key].type + '"><td class="ds" id="'+ data[key].sid +'">' + data[key].name + '</td><td><span class="add">添加</span> <span class="locate">定位</span></td></tr>';
		}
		this.obj.find('.list_box').html('<table border="0" width="100%">' + tmp + '</table>');
		
		this.obj.find('span').click(function(){
			$(this).parents('table').find('tr').removeClass('selected');
			$(this).parents('tr').addClass('selected');
			Base.handle.slide_menu($(this), obj);
		});
		this.hasData = true;
	};
	
	this.del = function(name){
		this.obj.find('tr').each(function(){
			if($(this).find('.ds').text() == name){
				//$(this).fadeOut();
				$(this).removeClass('selected').find('td:first').addClass('done');
			}
		});
	}
	
	this.add = function(name){
		//alert("f");
		this.obj.find('tr').each(function(){
			if($(this).find('.ds').text() == name){
				$(this).fadeIn();
			}
		});
		this.hasData = true;
	}
	
	 
	this.slide_in = function(){
		$('.slider').css({'right' : -250});
		this.obj.animate({'right' : 0});
		 
	};
	
	this.slide_out = function(){
		//$('.slider').css({'display' : 'none'});
		this.obj.animate({'right' : -250});
		 
	};
}

/*
 * 设备图片及附件右侧菜单
 */
Plugins.slide_attachment = {}
Plugins.slide_attachment.show = function(v){
	$('.list_attachment').remove();
	slide.slide_out();
	slide2.slide_out();
	$('.sf').remove();
	var tmp = '<div class="list_attachment slider box_shadow_left" style="right:-180px;width:180px;">'
			+ '<div class="list_title">照片及附件<span id="closeatt"><img src="img/cancel.png" /></span></div>'
			+ '<div class="list_box2">数据载入中....</div></div>';
	$('#main').append(tmp);
	Plugins.init_height();
	var sid;
	if(v['feature']){
		sid = v['feature'].attributes['static_id'];
	}else{
		sid = v['node'].id.split("-")[1];
	}
	$('.list_attachment').animate({'right': 0}, 500, function(){
		$.post(Global.option.URL +'/getAttachment.action',{
			sid 	: sid,
			type	: 'photo'
		},function(data){
 
			$('.list_attachment').find('.list_box2').html(data);
			Plugins.init_height();
		});
	})
}

Plugins.slide_attachment.init = function(){
	$('.list_attachment').find('#closeatt').click(function(){
		Plugins.slide_attachment.remove();
	});
	
	$('.list_attachment').find('li').hover(function(){
		$(this).find('span').css({'display' : 'block'});
	}, function(){
		$(this).find('span').css({'display' : 'none'});
	})
	
	$('.list_attachment').find('li span').click(function(){
		Plugins.dialog.show({
			'text'	: '确定删除文件？删除后数据将无法恢复。',
			'title'	: '警告',
			'autoId': $(this).attr('id'),
			'type'	: $(this).attr('class'),
			'aim'  	: $(this)
		}, {
			'submit'	: function(v){
				$.post(Global.option.URL +'/delete.action',{
					aid 	: v['autoId'],
					type	: v['type']
				},function(data){
					//$('.list_attachment').find('.list_box').html(data);
					if(data != 'success'){
						Plugins.dialog.remove();
						Plugins.dialog.show({
							'text'	: '删除发生错误，请检查网络后重试',
							'title'	: '警告'
						}, {
							'submit'	: function(){
								Plugins.dialog.remove();
							}
						})
					} else {
						Plugins.dialog.remove();
						//v['aim'].parents('li').remove();
						Plugins.slide_attachment.refresh(photoSId, 'photo');
					}
					
				});
			}
		})
	});
}

Plugins.slide_attachment.refresh = function(sid, type){
	$.post(Global.option.URL +'/getAttachment.action',{
		sid 	: sid,
		type	: type
	},function(data){
		$('.list_attachment').find('.list_box2').html(data);
		Plugins.init_height();
	});
}

Plugins.slide_attachment.remove = function(){
	$('.list_attachment').animate({'right' : -180}, 500, function(){
		$(this).remove();
	})
}

/*
 * 右侧滑出表单
 * 用于入沟和多选要素选择
 */
Plugins.slide_form = {}
Plugins.slide_form.show = function(v, func){
	$('.list_attachment').remove();
	slide.slide_out();
	slide2.slide_out();
	$('.sf').remove();

	v = v?v:{};
	func = func?func:{};
	v['title'] 		= v['title']?v['title']:"入沟选择";
	v['notice'] 	= v['notice']?v['notice']:"数据初始化";
	v['url']		= v['url']?v['url']:"";
	v['content']	= v['content']?v['content']:"";
	v['buttons']	= v['buttons']?v['buttons']:'on';
	v['param']		= v['param']?v['param']:{};
	func['submit'] 	= func['submit']?func['submit']:function(){};
	func['cancel'] 	= func['cancel']?func['cancel']:function(){};
	func['success'] = func['success']?func['success']:function(){};
	
	
	var dock;
	if(v['buttons'] == 'on'){
		dock 	= '<div class="dock"><div class="button button_normal" id="yes">确 定</div>'
				+ '<div class="button_normal button" id="no">取 消</div></div>';	
	} else {
		dock 	= '';
	}
	
	if(v['url'] == "" && v['content'] == ""){
		v['content'] = '<table border="0" width="100%"><tr><th width="80%">所选管沟</th><th width="20%"> 操作</th></tr></table>';
	}
	
	var tmp		= '<div class="sf slideform slider box_shadow_left">'
				+ '<div class="list_title">' + v['title'] + '<span id="closeatt"><img src="img/cancel.png" /></span></div>'
				+ '<div class="list_box3">' + v['content']
				+ '</div><div class="data"></div>' + dock + '</div>';

	$('#main').append(tmp);
	
	$('.slideform #closeatt').click(function(){
		
		Plugins.slide_form.remove();
	});
	
	if(v['url'] != ""){
		$.post(v['url'], v['param'], function(data){
			$('.slideform').find('.list_box3').html(data);
			func.success();
		})
	}
	
	$('.slideform').find('#yes').click(function(){
		func['submit'](v);
	});
	$('.slideform').find('#no').click(function(){
		func['cancel'](v);
		Plugins.slide_form.remove();
	});
	Plugins.slide_form.slide_in();
	Plugins.init_height();
}

Plugins.slide_form.slide_in = function(){
	$('.slideform').animate({'right' : 0});
}

Plugins.slide_form.slide_out = function(){
	$('.slideform').animate({'right' : -250});
} 

Plugins.slide_form.remove = function(){
	$('.slideform').remove();
}

//仅入沟时向列表添加管沟数据时调用
Plugins.slide_form.add_data = function(pram){
	pram.name = pram.name?pram.name:"";
	pram.value = pram.value?pram.value:"";
	pram.type = pram.type?pram.type:"str";
	pram.text = pram.text?pram.text:"";
	$('.slideform').find('table').append('<tr><td>' + pram.text + '<input type="hidden" name=\"' + pram.name + '\" value= \"' + pram.value + '\" class="' + pram.type + '" /></td><td><span class="del">取消</span></td></tr>');
	$('.slideform').find('table').find('.del').click(function(){
		$(this).parents('tr').remove();
	});
}

Plugins.slide_form.highlight = function(id){
	$('.slideform').find('tr').removeClass('selected');
	if(typeof(id) == 'undefined'){return ;}
	Plugins.slide_form.select(id, function(e){
		e.addClass('selected');
	})
}
Plugins.slide_form.unhighlight = function(){
	$('.slideform').find('tr').removeClass('selected');
	
}
Plugins.slide_form.select = function(id, func){
	$('.slideform').find('tr').each(function(){
		//$(this).removeClass('selected');
		if($(this).find('td:first').attr('id') ==  id){
			func($(this));
		}
	})
}

/*
 * 改变要素名称标签的位置窗口
 */
Plugins.set_label_pos = {};
Plugins.set_label_pos.show = function(v, func){
	$('.list_attachment').remove();
	slide.slide_out();
	slide2.slide_out();
	$('.sf').remove();
	
	func['save'] 	= func['save']?func['save']:function(){};
	func['cancel'] 	= func['cancel']?func['cancel']:function(){};
	func['preview'] = func['preview']?func['preview']:function(){};
	
	var dock = '<div class="dock"><div class="button button_normal" id="preview">预 览</div>'
		+ '<div class="button_normal button" id="save">保 存</div></div>';	
	
	var content = '<table class="label_pos"><tr><td width="30%">X偏移量</td><td><input type="text" name="xoffset" ></td></tr>'
			+ '<tr><td>Y偏移量</td><td><input type="text" name="yoffset" ></td></tr></table>';
	
	var tmp	= '<div class="sf slide_label slider box_shadow_left">'
		+ '<div class="list_title">设置名称标签位置'
		+ '<span id="closeatt"><img src="img/cancel.png" /></span></div>'
		+ '<div class="list_box3">' + content
		+ '</div><div class="data"></div>' + dock + '</div>';
	
	$('#main').append(tmp);
	
	$('.slide_label #closeatt').click(function(){
		Plugins.set_label_pos.remove();
	});
	
	$('.slide_label').find('#preview').click(function(){
		if(check()){
			func['preview'](v);
		} else {
			Plugins.dialog.show({
				'text'	: '请输入整数',
				'title'	: '警告'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			})
		}
	});
	
	$('.slide_label').find('#save').click(function(){
		if(check()){
			func['save'](v);
		} else {
			Plugins.dialog.show({
				'text'	: '请输入整数',
				'title'	: '警告'
			}, {
				'submit'	: function(){
					Plugins.dialog.remove();
				}
			})
		}
	});
	
	function check(){
		 var tester = /^-?[0-9]\d*$/;
		if(tester.test($('.slide_label input[name="xoffset"]').val()) &&
				tester.test($('.slide_label input[name="yoffset"]').val())){
			return true;
		} else {
			return false;
		}
	}
	
	Plugins.set_label_pos.slide_in();
}

Plugins.set_label_pos.slide_in = function(){
	$('.slide_label').animate({'right' : 0});
}

Plugins.set_label_pos.slide_out = function(){
	$('.slide_label').animate({'right' : -250});
} 

Plugins.set_label_pos.remove = function(){
	$('.slide_label').remove();
}

Plugins.set_label_pos.set_data = function(data){
	data = data?data:{'xoffset':10, 'yoffset':10};
	$('.slide_label input[name="xoffset"]').val(data.x_offset);
	$('.slide_label input[name="yoffset"]').val(data.y_offset);
}

Plugins.set_label_pos.get_data = function(){
	var data = {};
	data.xoffset = $('.slide_label input[name="xoffset"]').val();
	data.yoffset = $('.slide_label input[name="yoffset"]').val();
	return data;
}

//仅多选地图设备添加列表时调用,传入参数为地图对象数组
Plugins.slide_form.add_list = function(list){
	for(var id in list){
		$('.slideform table').append('<tr class=' + list[id].attributes['object_type'] + '><td class=' + list[id].attributes['static_id'] + '>' + list[id].attributes['name'] + '</td></tr>');
	}
	
	$('.slideform table tr').hover(function(){
		$(this).css({'background' : '#f2f2f2'});
	}, function(){
		$(this).css({'background' : '#fff'});
	});
	
	var selectFeature, zIndex, layerIndex;
	$('.slideform table tr').click(function(){
		$('.slideform table tr').each(function(){
			$(this).removeClass('selected');
		});
		if($(this).attr('class') != 'selected'){
			$(this).addClass('selected');
		} else {
			$(this).removeClass('selected');
		}	
		
		if(selectFeature){
			selectFeature.layer.map.setLayerIndex(selectFeature.layer, layerIndex);
			selectFeature.attributes['z_index'] = zIndex;			
		}
		for(var i in list){
			if(list[i].attributes['static_id'] != $(this).find('td').attr('class')){
				Global.locus.select.control.unselect(list[i]);
			}else{				
				selectFeature = list[i];
				zIndex = list[i].attributes['z_index'];
				layerIndex = list[i].layer.map.getLayerIndex(list[i].layer);
				list[i].layer.map.setLayerIndex(list[i].layer, 99);
				list[i].attributes['z_index'] = 99;
				Global.locus.select.control.select(list[i]);
			}
		}
		selectFeature.layer.events.triggerEvent("featureunselected", {feature: selectFeature});
	});
	
	$('.slideform #closeatt').click(function(){
		if(selectFeature){
			selectFeature.layer.map.setLayerIndex(selectFeature.layer, layerIndex);
			selectFeature.attributes['z_index'] = zIndex;			
		}
	});
}

Plugins.slide_form.add_val = function(pram){
	pram.type = pram.type?pram.type:'str';
	$('.slideform').find('.data').append('<input type="hidden" name=\"' + pram.name + '\" value= \"' + pram.value + '\" class="' + pram.type + '" />');
}

/*
 * 表单验证
 */
Plugins.check = {};
Plugins.check.str = function(str){
	
}

Plugins.check.number = function(){
	$('.float').keyup(function(){
		$(this).val($(this).val().replace(/[^0-9.]/gi,""));
	});
	
	$('.int').keyup(function(){
		$(this).val($(this).val().replace(/[^0-9]/gi,""));
	});

}

Plugins.check.form = function(){
	Plugins.check.number();
	$('.date').calendar();
}

/*
 * 生成静态ID
 */
Plugins.get_staticid = function(){
	var timestamp = new Date().getTime();
	return timestamp*1000 + Math.round(Math.random()*1000);
}

/*
 * 提交数据缓存
 */
Plugins.temp = {}
Plugins.temp.init = function(){
	$('body').append('<div id="data-temp" style="display:none;"></div>');
}

Plugins.temp.add = function(data){
	$('#data-temp').append(data);
}
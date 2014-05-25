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
	%>
	<base href="<%=basePath%>">
	<link href="theme/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<link href="style/base.css" rel="stylesheet" type="text/css"/>
	<link href="style/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css"/>
	<link href="style/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
	<link href="style/lhgcalendar/lhgcalendar.css" rel="stylesheet" type="text/css"/>		
	<link href="style/jquery-foxibox-0.2.css" rel="stylesheet" type="text/css"/>
	<link href="style/uploadify.css" rel="stylesheet" type="text/css"/>

<script>
var url = "<%=basePath%>";
var ip = url.split('//')[1].split(':')[0];
</script>
</head>
<body onload="Global.locus.method.init()">
<script>
//window.open('page.html'); 
</script>
<div id="header">
	<div id="logo" class="fl">
			<img src="img/logo.png" />
	</div>
	<ul class="Ttabs midtabs fl">
		<li class="current" id="tab_normal" rel="1">地图管理</li>
		<li rel="2" id="tab_line">单线图管理</li>
		<li rel="3" id="tab_wire">接线图管理</li>
		<li rel="4">其他功能</li>
	</ul>
	<ul class="righttabs fr">
		<li><a href="login.action?mode=loginout">注销登录</a></li>	
		<li>欢迎您！
			<s:property value="#session.userInfo.userName"/>
		</li>  
		
	</ul>
</div>
<!--#END HEADER========================================== -->
<div id="left_menu">
	<ul class="ltabs">
		<li rel="1" class="current" >树目录</li>
		<li rel="2">图 例</li>
		<li rel="3">搜 索</li>

	</ul>	
	<div class="ltabs_div">
		<div class="ltabs_1" style="padding:10px;">
			<ul id="treelist" class="ztree"></ul>
		</div>
		<div class="ltabs_2" style="display:none;">
			<ul class="icons_list" style="padding:20px 28px;">
				<li><span class="building"></span>土 建</li>
				<li><span class="city"></span>城 市</li>
				<li><span class="h220"></span>220KV</li>
				<li><span class="h220"></span>220KV</li>
				<li><span class="h750"></span>750KV</li>
				<li><span class="h500"></span>500KV</li>
				<li><span class="h330"></span>330KV</li>
				<li><span class="h04"></span>0.4KV</li>
				<li><span class="h10"></span>10KV</li>
				<li><span class="h35"></span>35KV</li>
				<li><span class="h110"></span>110KV</li>
				<li><span class="sub"></span>变电站</li>
				<li><span class="well"></span>圆形井盖</li>
				<li><span class="vwell"></span>虚拟井盖</li>
				<li><span class="wellfx"></span>方形井盖</li>
				<li><span class="towerjg"></span>脚钢塔</li>
				<li><span class="towergg"></span>钢管塔</li>
				<li><span class="towerx"></span>钢管杆</li>
				<li><span class="towerq"></span>未定塔杆</li>
				<li><span class="towersn"></span>水泥杆</li>
				<li><span class="tower0"></span>木 杆</li>
				<li><span class="kg"></span>开关站</li>
				<li><span class="df"></span>分支箱</li>
				<li><span class="hw"></span>环网柜</li>
				<li><span class="trans"></span>变压器</li>
				<li><span class="margin"></span>盘 余</li>
				<li><span class="transition"></span>中间接头</li>	
				<li><span class="line"></span>线 路</li>
				<li><span class="bzd"></span>标准段</li>
				<li><span class="wireseg"></span>电缆段</li>
				<li><span class="cableseg"></span>导线段</li>	
				<li><span class="pipeline"></span>管沟线</li>
				<li><span class="road"></span>道 路</li>
				<li><span class="rickpipe"></span>排 管</li>
				<li><span class="bridges"></span>桥 架</li>
				<li><span class="channel"></span>沟 道</li>
				<li><span class="buried"></span>直 埋</li>
				<li><span class="tunnel"></span>隧 道</li>
				<li><span class="jacking"></span>顶 管</li>
				<li><span class="v"></span>虚拟管沟</li>
			</ul>
		</div>	
		<div id="search" class="ltabs_3" style="display:none;padding-top:10px;" >
			<div class="search_form"  style="margin-bottom:15px;">
				<select id="search-type" class="type">
					<option value="electrical">电气</option>
					<option value="building">土建</option>
					<option value="locate">坐标</option>
				</select>
				<input type="text" id="st" class="text">
				<input type="button" id="gosearch" value="查询" class="sbutton grey_bg gb">
				<!-- 处理Base.handle.search() -->
			</div>
				
			<div class="clear"></div>
			<ul id="search_result" class="tul1">			
			</ul>
		</div>	
	</div>	
</div>
<!--#END LEFT MENU========================================== -->
<div id="main">
	<div id="content_menu" class="midtabs_div">
		<div class="midtabs_1">
			<ul class="menu">
				<li rel="m1c1_cmd1" class="cli" id="M1"><div class="but b1"></div><span class="tip">平移</span></li>
				<li rel="m1c1_cmd2" class="cli" id="M2"><div class="but b2"></div><span class="tip">放大</span></li>
				<li rel="m1c1_cmd3" class="cli" id="M3"><div class="but b3"></div><span class="tip">缩小</span></li>
				<li rel="m1c1_cmd4" class="cli" id="M4"><div class="but b5"></div><span class="tip">鹰眼</span></li>
			</ul>
			<ul class="menu line">				
				<li rel="m1c2_cmd1" class="cli" id="M5"><div class="but b6"></div><span class="tip">保存</span></li>	
				<li rel="m1c2_cmd2" class="cli" id="M6"><div class="but b10"></div><span class="tip">编辑</span></li>
				<li rel="m1c2_cmd3" class="cli" id="M7"><div class="but move"></div><span class="tip">拖动</span></li>
				<li rel="m1c2_cmd4" class="cli" id="M8"><div class="but copy"></div><span class="tip">复制</span></li>				
				<li rel="m1c2_cmd5" class="cli" id="M9"><div class="but b19"></div><span class="tip">添加管沟</span></li>			
				<li rel="add" class="hov b8"> 
					<div class="drop_box" style="width:302px;display:none;">
						<div id="add_feather_list">							
							<ul class="icons_list" style="padding:5px;">
								<li rel="f0"><span class="sub"></span>变电站</li>
								<li rel="f1"><span class="kg"></span>开关站</li>
								<li rel="f2"><span class="trans"></span>变压器</li>
								<li rel="f3"><span class="hw"></span>环网柜</li>
								<li rel="f4"><span class="df"></span>分支箱</li>
								<li rel="f5"><span class="transition"></span>分支接头</li>
								<li rel="f6"><span class="margin"></span>盘 余</li>
								<li rel="f7"><span class="transition"></span>中间接头</li>
								<li rel="f8"><span class="well"></span>工 井</li>				
								<li rel="f9"><span class="towersn"></span>塔 杆</li>
								<li rel="f10"><span class="vwell"></span>虚拟工井</li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
			<ul class="menu line">					
				<li rel="layouts" class="hov b9"> 
					<div class="drop_box" style="width:300px;display:none;">
						<div id="layout_list">
	
						</div>
					</div>
				</li>
				<li rel="m1c3_cmd1" class="cli" id="M10"><div class="but b18"></div><span class="tip">标注</span></li>
				<li rel="m1c3_cmd2" class="cli" id="M11"><div class="but allprofile"></div><span class="tip">全剖面</span></li>
				<li rel="m1c3_cmd3" class="cli instru" id="M12"><div class="but instru"></div><span class="tip">全设备</span></li>
				<li rel="m1c3_cmd4_on" class="cli  dou_v" ><div class="but hide"></div><span class="tip">隐藏右栏</span></li>
				<li rel="m1c3_cmd4_off" class="cli dou_h" ><div class="but show"></div><span class="tip">显示右栏</span></li>
			</ul>
			<ul class="menu line">	
				<li rel="m1c4_cmd1" class="cli" id="M13"><div class="but square-profile"></div><span class="tip">矩形剖面</span></li>
				<li rel="m1c4_cmd2" class="cli" id="M14"><div class="but half-profile"></div><span class="tip">拱形剖面</span></li>
				<li rel="m1c4_cmd3" class="cli" id="M15"><div class="but circle-profile"></div><span class="tip">圆形剖面</span></li>
				<li rel="m1c4_cmd4" class="cli" id="M16"><div class="but b13"></div><span class="tip">管孔</span></li>
				<li rel="m1c4_cmd5" class="cli" id="M17"><div class="but hole"></div><span class="tip">规则管孔</span></li>
				<li rel="m1c4_cmd6" class="cli" id="M18"><div class="but foundation"></div><span class="tip">支架</span></li>
				<li rel="m1c4_cmd7" class="cli" id="M19"><div class="but b14"></div><span class="tip">多边形</span></li>
				<li rel="m1c4_cmd8" class="cli" id="M20"><div class="but b16"></div><span class="tip">穿管</span></li>
				<li rel="m1c4_cmd9" class="cli" id="M21"><div class="but b16"></div><span class="tip">放置电缆</span></li>
				<li rel="m1c4_cmd10" class="cli" id="M22"><div class="but b17"></div><span class="tip">剖面注释</span></li>
			</ul>			
 <!--单线图-->
		</div>
		<div class="midtabs_2" style="display:none;">
			<ul class="menu">
				<li rel="m2c1_cmd1" class="cli" id="L1"><div class="but b1"></div><span class="tip">平移</span></li>
				<li rel="m2c1_cmd2" class="cli" id="L2"><div class="but b2"></div><span class="tip">放大</span></li>
				<li rel="m2c1_cmd3" class="cli" id="L3"><div class="but b3"></div><span class="tip">缩小</span></li>
				<li rel="m2c1_cmd4" class="cli" id="L4"><div class="but b5"></div><span class="tip">鹰眼</span></li>				
			</ul>
			<ul class="menu line">					
				<li rel="m2c2_cmd1" class="cli" id="L5"><div class="but b6"></div><span class="tip">保存</span></li>
				<li rel="m2c2_cmd2" class="cli" id="L6"><div class="but b10"></div><span class="tip">编辑</span></li>
				<li rel="m2c2_cmd3_on" class="cli dou2_v"><div class="but hide"></div><span class="tip">隐藏右栏</span></li>
				<li rel="m2c2_cmd3_off" class="cli dou2_h"><div class="but show"></div><span class="tip">显示右栏</span></li>	
				<li rel="m2c2_cmd4" class="cli" id="L7"><div class="but move"></div><span class="tip">拖动</span></li>
				<li rel="m2c2_cmd5" class="cli" id="L8"><div class="but copy"></div><span class="tip">复制</span></li>
				<li rel="m2c2_cmd6" class="cli" id="L9"><div class="but l"></div><span class="tip">左终端头</span></li>
				<li rel="m2c2_cmd7" class="cli" id="L10"><div class="but r"></div><span class="tip">右终端头</span></li>															
			</ul>	
		</div>
<!-- 接线图-->
	    <div class="midtabs_3" style="display:none;">
			<ul class="menu">
				<li rel="m3c1_cmd1" class="cli" id="W1"><div class="but b1"></div><span class="tip">平移</span></li>
				<li rel="m3c1_cmd2" class="cli" id="W2"><div class="but b2"></div><span class="tip">放大</span></li>
				<li rel="m3c1_cmd3" class="cli" id="W3"><div class="but b3"></div><span class="tip">缩小</span></li>
				<li rel="m3c1_cmd4" class="cli" id="W4"><div class="but b5"></div><span class="tip">鹰眼</span></li>				
			</ul>
			<ul class="menu line">					
				<li rel="m3c2_cmd1" class="cli" id="W5"><div class="but b6"></div><span class="tip">保存</span></li>
				<li rel="m3c2_cmd2" class="cli" id="W6"><div class="but b10"></div><span class="tip">编辑</span></li>
				<li rel="m3c2_cmd3_on" class="cli dou3_v"><div class="but hide"></div><span class="tip">隐藏右栏</span></li>
				<li rel="m3c2_cmd3_off" class="cli dou3_h"><div class="but show"></div><span class="tip">显示右栏</span></li>		
				<li rel="m3c2_cmd4" class="cli" id="W7"><div class="but move"></div><span class="tip">拖动</span></li>
				<li rel="m3c2_cmd5" class="cli" id="W8"><div class="but copy"></div><span class="tip">复制</span></li>
				<li rel="m3c2_cmd6" class="cli" id="W9"><div class="but motherline"></div><span class="tip">母线</span></li>
				<li rel="m3c2_cmd7" class="cli" id="W10"><div class="but b12"></div><span class="tip">间隔框</span></li>		
				<li rel="m3c2_cmd8" class="cli" id="W11"><div class="but in"></div><span class="tip">分支进线</span></li>
				<li rel="m3c2_cmd9" class="cli" id="W12"><div class="but out"></div><span class="tip">分支出线</span></li>																			
			</ul>	
		</div>
		
		<div class="midtabs_4" style="display:none;">
			<ul class="menu">
				
			</ul>
		</div>
	</div><!--#content_menu-->
	
	<div id="diva" style="display:none;"></div>
	<div id="map">

	</div>
	<div id="map2" style="display:none;">
		单线图管理加载中....
	</div>

	<div id="map3" style="display:none;">
		接线图管理加载中....
	</div>
	<div class="list_menu2 slider box_shadow_left">
		<div class="list_title"> </div>
		<div class="list_box">
			载入数据中....
		</div>
	</div>
	<div class="list_menu slider box_shadow_left">
		<div class="list_title"> </div>
		<div class="list_box">
			载入数据中....
		</div>
	</div>	
	<div id="setting" style="display:none;">
		
		<iframe id="setting_frame" src="" width="100%" height="100%" frameborder="0">
				
		</iframe>
	</div>
	<div id="status" class="status_normal" style="display:none">
	</div>
</div><!--#main-->
	<script src="lib/OpenLayers.js"></script>
	<script src="lib/deprecated.js"></script>	
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="js/lhgcalendar.min.js"></script>
	<script src="js/jquery.ztree.all-3.5.min.js"></script>
	<script src="lib/main.js"></script>
	<script src="js/base.js"></script>
	<script src="js/plugins.js"></script>	
	<script src="lib/locus.js"></script>
	<script src="lib/line_diagram.js"></script><!--debug需要，暂时引入-->
	<script src="lib/style.js"></script>
	<script src="lib/wire_diagram.js"></script>
	<script src="lib/OpenLayers/Control/ModifyFeature-tools.js"></script>
	<!-- 实时匹配 -->
	<script src="js/jquery.ui.core.min.js"></script>
	<script src="js/jquery.ui.widget.min.js"></script>
	<script src="js/jquery.ui.position.min.js"></script>
	<script src="js/jquery.ui.menu.min.js"></script>
	<script src="js/jquery.ui.autocomplete.min.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="js/jquery.mousewheel.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/jquery-foxibox-0.2.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/jquery.uploadify.min.js"></script>
</body>
</html>
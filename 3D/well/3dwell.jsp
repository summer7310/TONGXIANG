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
	 
	<link href="3D/style/style.css" rel="stylesheet" type="text/css" media="screen"/>
	<script src="js/jquery-1.10.1.min.js"></script>
	<script src="3D/build/three.min.js"></script>
	<script src="3D/build/gentilis_regular.typeface.js"></script>
	<script src="3D/well/js/wires.js"></script>
	<script src="3D/well/js/profile.js"></script>
</head>
	<body > 
	<script src="js/controls/OrbitControls.js"></script>	
	<script>
	var meshes;
	var container,camera,controls,scene,renderer;
		init();
		animate();
		onWindowResize();
	</script>	
	<div id="list">
		<div id="title">
			电缆线列表
		</div>
		<ul>
			<li class="wire1">测试电缆一	
			</li>
			<li class="wire2">测试电缆二 
			</li>
			<li class="wire3">测试电缆三
			</li>
			<li class="">经度:120.55913227
						纬度：30.632563315
			</li>
		</ul>
	</div>
	<script>
	L3D.menu.highLight(meshes);
	</script>
	</body>
</html>

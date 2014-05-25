<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传XML文件</title>
<link href="style/system.css" rel="stylesheet" type="text/css"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>">

<meta http-equiv="refresh" content="1;url=uploadsExcel.action">

<style>
#import-box{border:1px #ccc solid;padding:20px;margin:20px;float:left;background:#f2f2f2;}
#import-bar{border:1px #ccc solid;padding:2px;width:500px;height:10px;border-radius:6px;}
#import-barin{height:100%;width:0;background:#3B5998;border-radius:4px;}
#import-info{padding:14px 0 0 0;}
</style>
</head>
<body>
<div class="clear"></div>
<div class="system_box">
	<div id="import-box">
		<div id="import-bar">
			<div id="import-barin" style="width:30px;"></div>
		</div>
		<div id="import-info">
			已完成<s:property value="importService.importDao.countFinish"/>/<s:property value="importService.importDao.countAll"/>,0条错误信息
			
			<ul>
				<li>cuowu 11</li>
				<li>cuowu 11</li>
				<li>cuowu 11</li>
			</ul>
		</div>
	</div>	
</div>
</body>
</html>
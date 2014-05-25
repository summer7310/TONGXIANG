<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入文件成功</title>
<link href="style/system.css" rel="stylesheet" type="text/css"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<style>
.wrong-info{
	color:red;
	line-height:22px;
	list-style-type:decimal;
}
</style>
<base href="<%=basePath%>">
</head>
<body>
<s:include value="commonMenu.jsp"></s:include>
<div class="clear"></div>
<div class="system_box">
	<div class="success-box">
	文件导入完成，文件总共有<s:property value="%{importService.countAll}"/>条记录，
	成功导入<s:property value="%{importService.countFinish}"/>条。
	<a href="getSystemPage.action?pageType=uploadsXML">返回导入页面</a>
	</div>
	错误信息：
	<ul class="wrong-info">
		<s:iterator value="importService.errInfo" id="info">
			<li><s:property value="info"/></li>
		</s:iterator>
	</ul>
	
</div>
</body>
</html>
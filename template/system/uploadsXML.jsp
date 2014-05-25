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
</head>
<body>
<s:include value="commonMenu.jsp"></s:include>
<div class="clear"></div>
<div class="system_box">
	<s:form action="uploadsExcel" cssClass="system-table" method="post" enctype="multipart/form-data">

		<tr>
		<s:file label="导入文件" name="upFile" cssClass="i290"></s:file>
		<s:token />
		<tr>
		    <td class="tdLabel">文件类型</td>
		    <td>
		    	<select name="type" class="i290  select1">
					<option value="survey">测绘层</option>
					<option value="well">电缆井</option>
					<option value="joint">电缆接头</option>
					<option value="wire">电缆段</option>
					<option value="remainder">电缆盘余</option>
					<option value="ele">电气设备</option>
				</select>
		    </td>
		</tr>
		<s:submit value="上传导入" cssClass="system-button"></s:submit>
	</s:form>
</div>
</body>
</html>
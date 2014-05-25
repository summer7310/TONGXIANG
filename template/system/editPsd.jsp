<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
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
<!-- 
<s:form action="addUser" cssClass="system-table" method="post" enctype="multipart/form-data">
	<table class="system-table">
		<tr><th colspan="2">修改密码</th></tr>
		<input type="hidden" name="mode" class="str" value="editPsd"/>
		<tr>
			<td width="80px">旧密码</td>
			<td width="340px">			
				<s:password name="oldPsd" cssClass="psd i290" reqiured="true"  value=""/>
			</td>
		</tr>
		<tr>
			<td width="80px">新密码</td>
			<td width="340px">			
				<s:password name="newPsd" cssClass="psd i290"  reqiured="true"  value=""/>
			</td>
			
		</tr>
		
		<tr>
			<td width="80px"> </td>
			<td width="340px">			
				<input type="submit" class="system-button" value="提 交"/>
			</td>
		</tr>
	</table>
</s:form>
-->
<s:form action="addUser" cssClass="system-table" method="post" enctype="multipart/form-data">
		<input type="hidden" name="mode" class="str" value="editPsd"/>
		<s:password name="oldPsd" label="旧密码" cssClass="psd i290" />
		<s:password name="newPsd" label="新密码" cssClass="psd i290"  />
		<s:submit value="提 交" cssClass="system-button"/>
</s:form>
</div>
</body>
</html>
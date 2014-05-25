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
<s:form action="addUser" theme="simple" method="post" enctype="multipart/form-data">
	<table class="system-table">
		<tr><th colspan="2">账户基本信息(必填)</th></tr>
		<tr>
			<td width="80px">登录名*</td>
			<td width="340px">			
				<s:textfield theme="simple" name="user.userName" cssClass="str i290" reqiured="true"  value=""/>
			</td>
		</tr>
		<tr>
			<td width="80px">用户密码*</td>
			<td width="340px">			
				<s:password theme="simple" name="user.userPassword" cssClass="psd i290"  value=""/>
			</td>
			
		</tr>
		
		<tr>
			<td width="80px">用户组别*</td>
			<td width="340px">			<select class="select1" name="user.userLevel">
			<option value ="0">超级管理员</option>
			<option value ="1">管理员</option>
			<option value ="2" selected>普通用户</option>
			</select></td>
			
		</tr>
		<tr><th colspan="2">用户个人信息(选填)</th></tr>
		<tr>
			<td width="80px">真实姓名</td>
			<td width="340px">			
				<s:textfield theme="simple" name="user.userRealName" cssClass="str i290" value=""/>
			</td>
			
		</tr>
		<tr>
			<td width="80px">手机号</td>
			<td width="340px">			
				<s:textfield theme="simple" name="user.userMobile" cssClass="str i290"  value=""/>
			</td>
			
		</tr>
		<tr>
			<td width="80px">邮 箱</td>
			<td width="340px">			
				<s:textfield theme="simple" name="user.userEmail" cssClass="str i290" value=""/>
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
</div>
</body>
</html>
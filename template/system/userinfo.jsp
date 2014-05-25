<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
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
<div class="property">
	<table class="system-table">
		<tr><th colspan="2">账户基本信息</th></tr>
		
		<s:if test="user.autoId != null">
		<input type="hidden" name="mode" class="str" value="update" />
		<input type="hidden" name="user.userPassword" class="str" value="<s:property value="%{user.userPassword}"/>" />
		<input type="hidden" name="user.userAddDate" class="str" value="<s:property value="%{user.userAddDate}"/>" />
		<tr>
			<td width="75px">系统ID</td>
			<td width="340px">
			<input type="text" name="user.autoId" class="readonly i290" readonly="readonly" value="<s:property value="%{user.autoId}"/>"/>
			</td>
			<td width="75px">添加日期</td>
			<td width="300px">			
			<input type="text" name="user.userAddDate" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{user.userAddDate}"/>"/>
			</td>
		</tr>
		</s:if>
		<tr>
			<td>用户名</td>
			<td>
			<s:textfield theme="simple" name="user.userName" cssClass="str i290" value="%{user.userName}"/>
			</td>
			<td>用户组</td>
			<td>			<select name="user.userLevel">
			<option value ="0" <s:if test="user.userLevel==0">selected</s:if>>超级管理员</option>
			<option value ="1" <s:if test="user.userLevel==1">selected</s:if>>管理员</option>
			<option value ="2" <s:if test="user.userLevel==1">selected</s:if>>普通用户</option>
			</select></td>
		</tr>
		
		<tr><th colspan="2">账户个人信息（选填）</th></tr>
		<tr>
			<td width="75px">真实姓名</td>
			<td width="340px">
			<s:textfield theme="simple" name="user.userRealName" cssClass="str i290" value="%{user.userRealName}"/>
			</td>
			<td width="75px">手机号</td>
			<td width="300px">			
			<s:textfield theme="simple" name="user.userMobile" cssClass="str i290" value="%{user.userMobile}"/>
			</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>
			<s:textfield theme="simple" name="user.userEmail" cssClass="str i290" value="%{user.userEmail}"/>
			</td>
			<td> </td>
			<td>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
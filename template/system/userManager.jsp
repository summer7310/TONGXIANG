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
<script type="text/javascript">
var URL = '<%=basePath%>';
</script>
</head>
<body>
<s:include value="commonMenu.jsp"></s:include>
<div class="clear"></div>
<div class="system_box" style="padding-top:25px;">
	<table cellSpacing=0 cellPadding=0 class="users-table table" style="border:1px #ccc solid;border-left:0px">
		<tr>
			<th width="100px">用户名</th>
			<th width="100px">组 别</th>
			<th width="100px">真实姓名</th>
			<th width="140px">手 机</th>
			<th width="140px">邮 箱</th>
			<th width="140px">添加时间</th>
			<th width="140px">操 作</th>	
		</tr>
		<s:iterator value="users" var="user">
		<tr id="<s:property value="%{autoId}"/>">
			<td><s:property value="%{userName}"/></td>
			<td>
			<s:if test="userLevel==0">超级管理员</s:if>
			<s:if test="userLevel==1">管理员</s:if>
			<s:if test="userLevel==2">普通用户</s:if>
			</td>
			<td><s:property value="%{userRealName}"/></td>
			<td><s:property value="%{userMobile}"/></td>
			<td><s:property value="%{userEmail}"/></td>
			<td><s:property value="%{userAddDate}"/></td>
			<td><span class="control edit">修改</span>    <s:if test="userLevel!=0 && #session.userInfo.userLevel==0">|  <span class="control delete">删除</span></s:if></td>
		</tr>
		</s:iterator>		
	</table>
</div>
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/system.js"></script>
</body>
</html>
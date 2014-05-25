<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

<div id="system_tabs">
	<ul>
		
		<s:if test="#session.userInfo.userLevel==0 || #session.userInfo.userLevel==1">
		<li <s:if test="pageType == 'userManager'">class="current"</s:if> >
			<a href="getSystemPage.action?pageType=userManager">用户管理</a>
		</li>
		<li <s:if test="pageType == 'addUser'">class="current"</s:if> >
			<a href="getSystemPage.action?pageType=addUser">添加用户</a>
		</li>
		</s:if>
		<li <s:if test="pageType == 'editPsd'">class="current"</s:if> >
			<a href="getSystemPage.action?pageType=editPsd">修改密码</a>
		</li>
	 
		<li <s:if test="pageType == 'uploadsXML'">class="current"</s:if> >
			<a href="getSystemPage.action?pageType=uploadsXML">导入Excel</a>
		</li>
		 
	</ul>
</div>
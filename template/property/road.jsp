<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>道路</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">道路的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="road.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="road.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="road.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">
			<select name="road.objectType">
			<option value="1020301">道 路</option>
			</select>
			</td>
		</tr>
		<tr>
			
			<td>RFID</td>
			<td>			<select name="road.rfid">
			<option value ="0" <s:if test="result.rfid==0">selected</s:if>> </option>
			</select></td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
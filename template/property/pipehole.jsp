<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管孔</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">管孔的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="holes[0].autoId" cssClass="int" value="%{result.autoId}"/></s:if>
		<tr>
			<td width="80px">管孔编号</td>
			<td width="340px">
			<s:textfield theme="simple" name="holes[0].pipeCode" cssClass="str i290" value="%{result.pipeCode}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			<select name="holes[0].objectType">
			<option value="1010502" <s:if test="result.ObjectType==0">selected</s:if>>管孔</option>
			</select></td>
		</tr>
		<tr>
			<td width="80px">横向间距(mm)</td>
			<td width="340px">			
			<s:textfield theme="simple" name="holes[0].coordinateX" id="hole_x" cssClass="int i290" value="%{result.coordinateX}"/>
			</td>
			<td width="80px">埋深(mm)</td>
			<td width="300px">
			<s:textfield theme="simple" name="holes[0].deep" id="hole_y" cssClass="int i290" value="%{result.deep}"/>
			</td>
		</tr>
		<tr>
			<td width="80px">管孔直径(mm)</td>
			<td width="340px">			
			<s:textfield theme="simple" name="holes[0].pipeDiameter" id="diameter" cssClass="int i290" value="%{result.pipeDiameter}"/>
			</td>
			<td width="80px">材料</td>
			<td width="300px">
			<s:textfield theme="simple" name="holes[0].material" cssClass="str i290" value="%{result.material}"/>
			</td>			
		</tr>
		<tr>
			<td width="80px">有效性</td>
			<td width="340px">
			<select name="holes[0].isAvailable">
				<option value =true <s:if test="result.isAvailable==0">selected</s:if>>是</option>
				<option value =false <s:if test="result.isAvailable==1">selected</s:if>>否</option>
			</select>
			</td>
			<td  style="display:none;" width="80px">电缆段ID</td>
			<td  style="display:none;" width="300px">			
			<s:textfield theme="simple" name="holes[0].wirecableSegmentId" cssClass="int i290" value="%{result.wirecableSegmentId}"/>
			</td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>间隔</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">间隔的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="intermission.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="intermission.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
			
		<tr>
			<td width="80px">名称</td>
			<td width="300px"><s:textfield theme="simple" name="intermission.name" id="feature_name" cssClass="str i290" value="%{result.name}"/></td>		
			<td width="80px">对象类型</td>
			<td width="340px">			
			<select name="intermission.objectType">
				<option value="1010601">间 隔</option>
			</select>
			</td>
		</tr>
		<tr>	
			<td>设备类型</td>
			<td>
			<input name="intermission.containerType" type="hidden" id="containerType" class="int" value="<s:property value="%{containerType}" />">
			<input name="intermissionContainerType" type="text" id="containerTypeName" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.containerType==1010202">开关站</s:if><s:if test="result.containerType==1010203">变压器</s:if><s:if test="result.containerType==1010204">环网柜</s:if><s:if test="result.containerType==1010205">分支箱</s:if>">			
			</td>
			<td>设备ID</td>
			<td>
			<input name="intermission.containerId" type="hidden" id="container" class="int" value="<s:property value="%{result.containerId}" />">
			<input name="intermissionContainerId" type="text" id="containerName" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{containerId}" />" >			
			</td>
		</tr>
		<tr>
			<td>单元编号</td>
			<td><s:textfield theme="simple" name="intermission.unitCode" cssClass="str i290" value="%{result.unitCode}"/></td>
			<td>单元类型</td>
			<td>			<select name="intermission.unitType">
			<option value ="0" <s:if test="result.unitType==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>所属设备</td>
			<td>	
			<input name="intermission.belongsContainer" type="text" readonly="readonly" class="readonly int i290" >
			</td>
			<td>电压等级</td>
			<td>			<select name="intermission.voltagsGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
		</tr>
		<tr>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="intermission.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
			<td>调度单位</td>
			<td><s:textfield theme="simple" name="intermission.schedulingUnit" cssClass="str i290" value="%{result.schedulingUnit}"/></td>
		</tr>
		<tr>
		
			<td style="display:none;">所属一次接线图</td>
			<td style="display:none;">			<select name="intermission.belongsWiringDiagram">
			<option value ="0" <s:if test="result.belongsWiringDiagram==0">selected</s:if>> </option>
			</select></td>  
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配电线路</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">配电线路的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="cableline.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="cableline.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="cableline.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">
			<select name="cableline.objectType">
				<option value="1010301">配电线路</option>
			</select>
			</td>
		</tr>
		
		<tr>
			<td>运行编号</td>
			<td><s:textfield theme="simple" name="cableline.runCode" cssClass="str i290" value="%{result.runCode}"/></td>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="cableline.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
		</tr>
		<tr>
			<td>电压等级</td>
			<td>			<select name="cableline.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
			<td>开关编号</td>
			<td><s:textfield theme="simple" name="cableline.switchCode" cssClass="str i290" value="%{result.switchCode}"/></td>
		</tr>
		<tr>
			<td>起点电站</td>
			<td>
			<input name="cableline.startTransStation" type="hidden" id="startTrans" class="int" value="<s:property value="%{result.startTransStation}" />">
			<input name="cablelineStartTransStation" type="text" id="StartTransName" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{startTransStation}" />" >
			</td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="cableline.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>线路性质</td>
			<td>			<select name="cableline.cablelineProperties">
			<option value="0" <s:if test="result.cablelineProperties==0">selected</s:if>> </option>
			<option value="1" <s:if test="result.cablelineProperties==1">selected</s:if>>国家电网公司</option>
			<option value="2" <s:if test="result.cablelineProperties==2">selected</s:if>>区域电网公司</option>
			<option value="3" <s:if test="result.cablelineProperties==3">selected</s:if>>省公司</option>
			<option value="4" <s:if test="result.cablelineProperties==4">selected</s:if>>县公司</option>
			<option value="5" <s:if test="result.cablelineProperties==5">selected</s:if>>用户</option>
			</select></td>
			<td>线路类别</td>
			<td>			<select name="cableline.cablelineType">
			<option value ="0" <s:if test="result.cablelineType==0">selected</s:if>>架空线路</option>
			<option value ="1" <s:if test="result.cablelineType==1">selected</s:if>>混合线路</option>
			<option value ="2" <s:if test="result.cablelineType==2">selected</s:if>>电缆线路</option>
			</select></td>
		</tr>
		<tr>
			<td>线路总长度(m)</td>
			<td>			
			<s:textfield theme="simple" name="cableline.cablelineLength" cssClass="int i290" value="%{result.cablelineLength}"/>
			</td>
			<td>架空线路长度(m)</td>
			<td>			
			<s:textfield theme="simple" name="cableline.aerialcableLength" cssClass="int i290" value="%{result.aerialcableLength}"/>
			</td>
		</tr>
		<tr>
			<td>电缆线路长度(m)</td>
			<td>			
			<s:textfield theme="simple" name="cableline.undergroundcableLength" cssClass="int i290" value="%{result.undergroundcableLength}"/>
			</td>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="cableline.builder" cssClass="str i290" value="%{result.builder}"/></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="cableline.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="cableline.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><s:textfield theme="simple" name="cableline.remark" cssClass="str i290" value="%{result.remark}"/></td>
			<td>运行状态</td>
			<td>			<select name="cableline.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
		</tr>
	<!-- 
			<td>竣工资料附件ID</td>
			<td>			<select name="cableline.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td> -->
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
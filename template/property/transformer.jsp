<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变压器</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">变压器的基本属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="trans.autoId" value="%{result.autoId}" cssClass="int" /></s:if>
			<s:hidden theme="simple" name="trans.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名称</td>
			<td width="340px">
			<s:textfield theme="simple" name="trans.name" cssClass="str i290" id="feature_name" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="trans.objectType">
				<option value ="1010203">变压器</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>经 度</td>
			<td><s:textfield theme="simple" name="trans.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/></td>
			<td>所在位置</td>
			<td>
			<s:textfield theme="simple" name="pps" id="birds2" cssClass="ignore i290" value="%{building}"/>
			<s:hidden theme="simple" name="trans.locationId" cssClass="int" value="%{result.locationId}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchBuildingHost.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="trans.locationId"]').val(ui.item.id);
						$('input[name="trans.locationType"]').val(ui.item.type);
						switch(ui.item.type){
						case 1020101:
							$('input[name="locationType"]').val("工井");
							break;
						case 1020102:
							$('input[name="locationType"]').val("塔杆");
							break;
						case 1020203:
							$('input[name="locationType"]').val("虚拟管沟");
							break;
						case 1020211:
							$('input[name="locationType"]').val("排管");
							break;
						case 1020212:
							$('input[name="locationType"]').val("桥架");
							break;
						case 1020213:
							$('input[name="locationType"]').val("沟道");
							break;
						case 1020214:
							$('input[name="locationType"]').val("直埋");
							break;
						case 1020215:
							$('input[name="locationType"]').val("隧道");
							break;
						case 1020216:
							$('input[name="locationType"]').val("顶管");
							break;
						default:
							$('input[name="locationType"]').val("超出范围的类型号");
						break;
						}
					}
				});
			});
			</script>
			</td>
		</tr>
		<tr>
			<td>维 度</td>
			<td><s:textfield theme="simple" name="trans.latitude" cssClass="float i290" id="lat" value="%{result.latitude}"/></td>
			<td>所在类型</td>
			<td>
			<input type="text" name="locationType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.locationType==1020101">工 井</s:if><s:if test="result.locationType==1020102">塔 杆</s:if><s:if test="result.locationType==1020203">虚拟管沟</s:if><s:if test="result.locationType==1020211">排 管</s:if><s:if test="result.locationType==1020212">桥 架</s:if><s:if test="result.locationType==1020213">沟 道</s:if><s:if test="result.locationType==1020214">直 埋</s:if><s:if test="result.locationType==1020215">隧 道</s:if><s:if test="result.locationType==1020216">顶 管</s:if>"/>
			<s:hidden theme="simple" name="trans.locationType" cssClass="int i290" value="%{result.locationType}"/>
			</td>			
		</tr>
		<tr>
			<td style="display:none;">所属配电线路</td>
			<td style="display:none;">			
			<s:textfield theme="simple" name="tra" id="birds" cssClass="ignore i290" value="%{clName}"/>
			<s:hidden theme="simple" name="trans.belongsCableline" cssClass="" value="%{result.belongsCableline}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="trans.belongsCableline"]').val(ui.item.id);
					}
				});
			});
			</script>
			</td>		
			<td>运行状态</td>
			<td>			<select name="trans.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
			<td>电压等级</td>
			<td>
			<select name="trans.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>高 程(m)</td>
			<td><s:textfield theme="simple" name="trans.elevation" cssClass="float i290" value="%{result.elevation}"/></td>		
			
		</tr>
		<tr><th colspan="2">变压器的其他属性</th></tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="trans.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="trans.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>站址</td>
			<td><s:textfield theme="simple" name="trans.stationAddress" cssClass="str i290" value="%{result.stationAddress}"/></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="trans.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="trans.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>运行编号</td>
			<td><s:textfield theme="simple" name="trans.runCode" cssClass="str i290" value="%{result.runCode}"/></td>
		</tr>
		
		<tr style="display:none;">
			<td>一次接线图ID</td>
			<td>			<select name="trans.wiringDiagramId">
			<option value ="0" <s:if test="result.wiringDiagramId==0">selected</s:if>> </option>
			
			</select></td>
			<td>竣工资料附件ID</td>
			<td>			<select name="trans.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			
			</select></td>
		</tr>
		
		<tr>
			<td>接地电阻</td>
			<td><s:textfield theme="simple" name="trans.groundResistance" cssClass="float i290" value="%{result.groundResistance}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="trans.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>变压器类型</td>
			<td>			<select name="trans.transformerType">
			<option value ="0" <s:if test="result.transformerType==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.transformerType==1">selected</s:if>>单相变压器</option>
			<option value ="2" <s:if test="result.transformerType==2">selected</s:if>>三相变压器</option>
			<option value ="3" <s:if test="result.transformerType==3">selected</s:if>>多相变压器</option>
			</select></td>
			<td>箱变类型</td>
			<td>			<select name="trans.boxType">
			<option value ="0" <s:if test="result.boxType==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.boxType==1">selected</s:if>>箱变</option>
			<option value ="2" <s:if test="result.boxType==2">selected</s:if>>柱上变</option>
			</select></td>
		</tr>
		<tr>
			<td>是否具有环网</td>
			<td>			<select name="trans.hasRing">
			<option value =true <s:if test="result.hasRing==0">selected</s:if>>是</option>
			<option value =false <s:if test="result.hasRing==1">selected</s:if>>否</option>
			</select></td>
			<td>绝缘等级</td>
			<td>	<s:textfield theme="simple" name="trans.insulationClass" cssClass="str i290" value="%{result.insulationClass}"/>		</td>
		</tr>
		<tr>
			<td>使用性质</td>
			<td>			<select name="trans.natureOfUse">
			<option value ="0" <s:if test="result.natureOfUse==0">selected</s:if>>公用变</option>
			<option value ="1" <s:if test="result.natureOfUse==1">selected</s:if>>专用变</option>
			<option value ="2" <s:if test="result.natureOfUse==2">selected</s:if>>路灯变</option>
			</select></td>
			<td>变压器型号</td>
			<td><s:textfield theme="simple" name="trans.transformerModel" cssClass="str i290" value="%{result.transformerModel}"/></td>
		</tr>
		<tr>
			<td>生产厂家</td>
			<td><s:textfield theme="simple" name="trans.manufacturer" cssClass="str i290" value="%{result.manufacture}"/></td>
			<td>出厂编号</td>
			<td><s:textfield theme="simple" name="trans.manufactureCode" cssClass="str i290" value="%{result.manufactureCode}"/></td>
		</tr>
		<tr>
			<td>出厂日期</td>
			<td><s:textfield theme="simple" name="trans.manufactureDate" cssClass="date i290" value="%{result.manufactureDate}"/></td>
			<td>相数</td>
			<td>			<select name="trans.phaseCount">
			<option value ="0" <s:if test="result.phaseCount==0">selected</s:if>>单相</option>
			<option value ="1" <s:if test="result.phaseCount==1">selected</s:if>>三相</option>
			</select></td>
		</tr>
		<tr>
			<td>绝缘介质</td>
			<td>			<select name="trans.dielectric">
			<option value ="0" <s:if test="result.dielectric==0">selected</s:if>>干 式</option>
			<option value ="1" <s:if test="result.dielectric==1">selected</s:if>>油浸式</option>
			</select></td>
			<td>额定容量</td>
			<td><s:textfield theme="simple" name="trans.ratedCapacity" cssClass="float i290" value="%{result.ratedCapacity}"/></td>
		</tr>
		<tr>
			<td>阻抗电压</td>
			<td><s:textfield theme="simple" name="trans.impedanceVoltage" cssClass="float i290" value="%{result.impedanceVoltage}"/></td>
			<td>空载电流</td>
			<td><s:textfield theme="simple" name="trans.unLoadCurrent" cssClass="float i290" value="%{result.unLoadCurrent}"/></td>
		</tr>
		<tr>
			<td>短路损耗</td>
			<td><s:textfield theme="simple" name="trans.shortCircuitLoss" cssClass="float i290" value="%{result.shortCircuitLoss}"/></td>
			<td>空载损耗</td>
			<td><s:textfield theme="simple" name="trans.unLoadLoss" cssClass="float i290" value="%{result.unLoadLoss}"/></td>
		</tr>
		<tr>
			<td>接线组别</td>
			<td><s:textfield theme="simple" name="trans.wiringGroup" cssClass="str i290" value="%{result.wiringGroup}"/></td>
			<td>电压比</td>
			<td><s:textfield theme="simple" name="trans.voltageRatio" cssClass="str i290" value="%{result.voltageRatio}"/></td>
		</tr>
		<tr>
			<td>高压侧额定电流</td>
			<td><s:textfield theme="simple" name="trans.highPreCurrent" cssClass="float i290" value="%{result.highPre Current}"/></td>
			<td>低压侧额定电流</td>
			<td><s:textfield theme="simple" name="trans.lowPreCurrent" cssClass="float i290" value="%{result.lowPre Current}"/></td>
		</tr>
		<tr>
			<td>油号</td>
			<td><s:textfield theme="simple" name="trans.oilNumber" cssClass="str i290" value="%{result.oilNumber}"/></td>
			<td>油重</td>
			<td><s:textfield theme="simple" name="trans.oilWeight" cssClass="float i290" value="%{result.oilWeight}"/></td>
		</tr>
		<tr>
			<td>总重</td>
			<td><s:textfield theme="simple" name="trans.totalWeight" cssClass="float i290" value="%{result.totalWeight}"/></td>
			<td>温控器厂家</td>
			<td><s:textfield theme="simple" name="trans.thermostatManufacturers" cssClass="str i290" value="%{result.thermostatManufactures}"/></td>
		</tr>
		<tr>
			<td>温控器型号</td>
			<td><s:textfield theme="simple" name="trans.thermostatModel" cssClass="str i290" value="%{result.thermostatModel}"/></td>
			<td>　</td>
			<td></td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
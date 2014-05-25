<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>塔杆</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">塔杆基本属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="tower.autoId" value="%{result.autoId}" cssClass="int" /></s:if>
			<s:hidden theme="simple" name="tower.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">
			<s:textfield theme="simple" name="tower.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="tower.objectType">
				<option value="1020102">塔 杆</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>经度</td>
			<td><s:textfield theme="simple" name="tower.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/></td>
			<td>电压等级</td>
			<td>			<select name="tower.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
		</tr>
		<tr>
			<td>纬度</td>
			<td><s:textfield theme="simple" name="tower.latitude" cssClass="float i290" id="lat" value="%{result.latitude}"/></td>
			<td>运行状态</td>
			<td>			<select name="tower.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
		</tr>
		<tr>
			<td>所属道路</td>
			<td>			
			<s:textfield theme="simple" name="towerroad" id="birds" cssClass="ignore i290" value="%{road}"/>
			<s:hidden theme="simple" name="tower.belongsRoad" cssClass="" value="%{result.belongsRoad}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchRoad.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="tower.belongsRoad"]').val(ui.item.id);
					}
				});
			});
			</script>
			
			</td>
			
			<td style="display:none;">所属线路</td>
			<td style="display:none;">			
			<s:textfield theme="simple" name="tra" id="birds2" cssClass="ignore i290" value="%{clName}"/>
			<s:hidden theme="simple" name="tower.belongsCableline" cssClass="" value="%{result.belongsCableline}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="tower.belongsCableline"]').val(ui.item.id);
					}
				});
			});
			</script>
			
			</td>
		</tr>
		
		<tr><th colspan="2">塔杆其他属性</th></tr>
		<tr>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="tower.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="tower.builder" cssClass="str i290" value="%{result.builder}"/></td>
		</tr>
		<tr>
			
			<td>高程</td>
			<td><s:textfield theme="simple" name="tower.elevation" cssClass="float i290" value="%{result.elevation}"/></td>
		
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="tower.owner" cssClass="str i290" value="%{result.owner}"/></td>
			
		</tr>
		<tr>
			<td  style="display:none;">竣工资料附件ID</td>
			<td style="display:none;">			<select name="tower.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="tower.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			
			<td>杆塔编号</td>
			<td><s:textfield theme="simple" name="tower.towerCode" cssClass="str i290" value="%{result.towerCode}"/></td>
		
			
			<td>杆塔型号</td>
			<td><s:textfield theme="simple" name="tower.towerModel" cssClass="str i290" value="%{result.towerModel}"/></td>
		</tr>
		<tr>
			<td>生产厂家</td>
			<td><s:textfield theme="simple" name="tower.manufacturer" cssClass="str i290" value="%{result.manufacturer}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="tower.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>杆塔性质</td>
			<td>			<select name="tower.towerProperties">
			<option value ="0" <s:if test="result.towerProperties==0">selected</s:if>>直线</option>
			<option value ="1" <s:if test="result.towerProperties==1">selected</s:if>>耐张</option>
			<option value ="2" <s:if test="result.towerProperties==2">selected</s:if>>转角</option>
			<option value ="3" <s:if test="result.towerProperties==3">selected</s:if>>分支</option>
			<option value ="4" <s:if test="result.towerProperties==4">selected</s:if>>扳线</option>
			</select></td>
			<td>杆塔材质</td>
			<td>			<select name="tower.towerMaterial">
			<option value ="0" <s:if test="result.towerMaterial==0">selected</s:if>>角钢塔</option>
			<option value ="1" <s:if test="result.towerMaterial==1">selected</s:if>>钢管塔</option>
			<option value ="2" <s:if test="result.towerMaterial==2">selected</s:if>>钢管杆</option>
			<option value ="3" <s:if test="result.towerMaterial==3">selected</s:if>>砼杆</option>
			<option value ="4" <s:if test="result.towerMaterial==4">selected</s:if>>木杆</option>
			</select></td>
		</tr>
		<tr>
			<td>杆高(m)</td>
			<td>			
			<s:textfield theme="simple" name="tower.height" cssClass="int i290" value="%{result.height}"/>
			</td>
			<td>档距(m)</td>
			<td>		
			<s:textfield theme="simple" name="tower.span" cssClass="int i290" value="%{result.span}"/>
			</td>
		</tr>
		<tr>
			<td>是否高低压同杆架设</td>
			<td>			<select name="tower.highLowPressure">
			<option value =true <s:if test="result.highLowPressure==0">selected</s:if>>是</option>
			<option value =false <s:if test="result.highLowPressure==1">selected</s:if>>否</option>
			</select></td>
			<td>同杆架设回路数</td>
			<td>			
			<s:textfield theme="simple" name="tower.circuitCount" cssClass="int i290" value="%{result.circuitCount}"/>
			</td>
		</tr>
		<tr>
			<td>基础型式</td>
			<td><s:textfield theme="simple" name="tower.baseType" cssClass="str i290" value="%{result.baseType}"/></td>
			<td>拉线数量</td>
			<td>			
			<s:textfield theme="simple" name="tower.cableCount" cssClass="int i290" value="%{result.cableCount}"/>
			</td>
		</tr>
		<tr>
			<td>接地电阻</td>
			<td><s:textfield theme="simple" name="tower.groundResistance" cssClass="float i290" value="%{result.groundResistance}"/></td>
			<td>有无通信</td>
			<td>			<select name="tower.hasCommunication">
			<option value ="0" <s:if test="result.hasCommunication==0">selected</s:if>>有</option>
			<option value ="1" <s:if test="result.hasCommunication==1">selected</s:if>>无</option>
			</select></td>
		</tr>
		<tr>
			<td>埋深(mm)</td>
			<td>		
			<s:textfield theme="simple" name="tower.depth" cssClass="int i290" value="%{result.depth}"/>
			</td>
			<td>出厂编号</td>
			<td><s:textfield theme="simple" name="tower.manufactureCode" cssClass="str i290" value="%{result.manufactureCode}"/></td>
		</tr>
		<tr>
			<td>有无路灯</td>
			<td>			<select name="tower.hasLamp">
			<option value ="0" <s:if test="result.hasLamp==0">selected</s:if>>有</option>
			<option value ="1" <s:if test="result.hasLamp==1">selected</s:if>>无</option>
			</select></td>
			
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
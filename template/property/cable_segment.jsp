<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电缆段</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">电缆段基本属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="cableSeg.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="cableSeg.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="cableSeg.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="cableSeg.objectType">
			<option value="1010402" >电缆段</option>
			
			</select>
			</td>
		</tr>
		<tr>
			<td>所属配电线路</td>
			<td>			
			<input name="cableB" type="text" id="birds" Class="ignore readonly i290" readonly="readonly" value="<s:property value="%{clName}"/>">
			<s:hidden theme="simple" name="cableSeg.belongsCableline" cssClass="int" value="%{result.belongsCableline}"/>
			</td>
			<td>电压等级</td>
			<td>
			<select name="cableSeg.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>通信电缆</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>0.4KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>10KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>35KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>110KV</option>
			<option value ="5" <s:if test="result.voltageGrade==5">selected</s:if>>220KV</option>
			<option value ="6" <s:if test="result.voltageGrade==6">selected</s:if>>330KV</option>
			<option value ="7" <s:if test="result.voltageGrade==7">selected</s:if>>500KV</option>
			<option value ="8" <s:if test="result.voltageGrade==8">selected</s:if>>750KV</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>起点设备</td>
			<td>			
			<s:textfield theme="simple" name="cal" id="birds2" cssClass="ignore i290" value="%{startObj}"/>
			<s:hidden theme="simple" name="cableSeg.startContainer" cssClass="int" value="%{result.startContainer}"/>
			<script type="text/javascript">
			var type = {
					'1010201'	: "变电站",
					'1010202'	: "开关站",
					'1010203'	: "变压器",
					'1010204'	: "环网柜",
					'1010205'	: "分支箱",
					'1010206'	: "分支接头",			
					'1010208'	: "中间接头"
			}
			
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchContainer.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="cableSeg.startContainer"]').val(ui.item.id);
						$('input[name="cableSeg.startContainerType"]').val(ui.item.type);
						
						
						$('input[name="startType"]').val(type[ui.item.type]);
					}
				});
			});
			</script>
			</td>
			<td>终点设备</td>
			<td>			
			<s:textfield theme="simple" name="cal" id="birds3" cssClass="ignore i290" value="%{endObj}"/>
			<s:hidden theme="simple" name="cableSeg.endContainer" cssClass="" value="%{result.endContainer}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds3" ).autocomplete({
					source: "getSearchContainer.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="cableSeg.endContainer"]').val(ui.item.id);
						$('input[name="cableSeg.endContainerType"]').val(ui.item.type);
						$('input[name="endType"]').val(type[ui.item.type]);
					}
				});
			});
			</script>
			</td>
		</tr>
		<tr>
			<s:if test="result.startContainerType == 1010201">
				<s:set name="st" value="'变电站'"/>
			</s:if>		
			<s:if test="result.startContainerType == 1010202">
				<s:set name="st" value="'开关站'"/>
			</s:if>
			<s:if test="result.startContainerType == 1010203">
				<s:set name="st" value="'变压器'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010204">
				<s:set name="st" value="'环网柜'"/>
			</s:if>						
			<s:if test="result.startContainerType == 1010205">
				<s:set name="st" value="'分支箱'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010206">
				<s:set name="st" value="'分支接头'"/>
			</s:if>		
			<s:if test="result.startContainerType == 1010208">
				<s:set name="st" value="'中间接头'"/>
			</s:if>
			<s:if test="result.startContainerType == 1020102">
				<s:set name="st" value="'塔杆'"/>
			</s:if>				
			<td>起点类型</td>
			<td>			
			<input name="startType" type="text"  Class="ignore readonly i290" readonly="readonly" value="<s:property value="#st"/>">
			<s:hidden theme="simple" name="cableSeg.startContainerType" cssClass="int" value="%{result.startContainerType}"/>
			</td>
			<s:if test="result.endContainerType == 1010201">
				<s:set name="et" value="'变电站'"/>
			</s:if>			
			<s:if test="result.endContainerType == 1010202">
				<s:set name="et" value="'开关站'"/>
			</s:if>
			<s:if test="result.endContainerType == 1010203">
				<s:set name="et" value="'变压器'"/>
			</s:if>				
			<s:if test="result.endContainerType == 1010204">
				<s:set name="et" value="'环网柜'"/>
			</s:if>				
			<s:if test="result.endContainerType == 1010205">
				<s:set name="et" value="'分支箱'"/>
			</s:if>	
			<s:if test="result.endContainerType == 1010206">
				<s:set name="et" value="'分支接头'"/>
			</s:if>	
			<s:if test="result.endContainerType == 1010208">
				<s:set name="et" value="'中间接头'"/>
			</s:if>
			<s:if test="result.endContainerType == 1020102">
				<s:set name="et" value="'塔杆'"/>
			</s:if>				
			<td>终点类型</td>
			<td>
			<input name="endType" type="text"  Class="ignore readonly i290" readonly="readonly" value="<s:property value="#et"/>">
			<s:hidden theme="simple" name="cableSeg.endContainerType" cssClass="int" value="%{result.endContainerType}"/>
			</td>
		</tr>
		 
		<tr>
			<td>线缆段型号</td>
			<td><s:textfield theme="simple" name="cableSeg.wirecableSegmengModel" cssClass="str i290" value="%{result.wirecableSegmengModel}"/></td>
			<td>生产厂家</td>
			<td><s:textfield theme="simple" name="cableSeg.manufacturer" cssClass="str i290" value="%{result.manufacturer}"/></td>
		</tr>
		<tr>
			<td>出厂日期</td>
			<td><s:textfield theme="simple" name="cableSeg.manufacturerDate" cssClass="date i290" value="%{result.manufacturerDate}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="cableSeg.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="cableSeg.builder" cssClass="str i290" value="%{result.builder}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="cableSeg.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><s:textfield theme="simple" name="cableSeg.remark" cssClass="str i290" value="%{result.remark}"/></td>
			<td>运行状态</td>
			<td>			<select name="cableSeg.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="cableSeg.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>竣工资料附件ID</td>
			<td>			<select name="cableSeg.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>电缆长度(m)</td>
			<td>			
			<s:textfield theme="simple" name="cableSeg.length" cssClass="int i290" value="%{result.length}"/>
			</td>
			<td>中间接头数</td>
			<td>			
			<s:textfield theme="simple" name="cableSeg.centerTerminalCount" cssClass="int i290" value="%{result.centerTerminalCount}"/>
			</td>
		</tr>
		<tr>
			<td>额定电压</td>
			<td><s:textfield theme="simple" name="cableSeg.ratedVoltage" cssClass="float i290" value="%{result.ratedVoltage}"/></td>
			<td>电缆截面(mm<SUP>2</SUP>)</td>
			<td><s:textfield theme="simple" name="cableSeg.section" cssClass="float i290" value="%{result.section}"/></td>
		</tr>
		<tr>
			<td>载流量</td>
			<td><s:textfield theme="simple" name="cableSeg.currentCarryingCapacity" cssClass="float i290" value="%{result.currentCarryingCapacity}"/></td>
			<td>线芯材料</td>
			<td>			<select name="cableSeg.coreMaterial">
			<option value ="0" <s:if test="result.coreMaterial==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.coreMaterial==1">selected</s:if>>铜</option>
			<option value ="2" <s:if test="result.coreMaterial==2">selected</s:if>>铝</option>
			</select></td>
		</tr>
		<tr>
			<td>芯数</td>
			<td>			<select name="cableSeg.coresCount">
			<option value ="0" <s:if test="result.coresCount==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.coresCount==1">selected</s:if>>单芯</option>
			<option value ="3" <s:if test="result.coresCount==3">selected</s:if>>三芯</option>
			
			</select></td>
			<td>敷设方式</td>
			<td><s:textfield theme="simple" name="cableSeg.layingMenthod" cssClass="str i290" value="%{result.layingMethod}"/></td>
		</tr>
		<tr>
			<td>图纸编号</td>
			<td><s:textfield theme="simple" name="cableSeg.drawingNumber" cssClass="str i290" value="%{result.drawingNumber}"/></td>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>

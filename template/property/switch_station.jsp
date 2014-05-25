<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开关站</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">开关站属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="switchSta.autoId" value="%{result.autoId}"  cssClass="int"/></s:if>
			<s:hidden theme="simple" name="switchSta.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="switchSta.name" id="feature_name" cssClass="str i290" value="%{result.name}"/> 
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="switchSta.objectType">
			<option value ="1010202">开关站</option>
			</select></td>
		</tr>
		<tr>
			<td>经度</td>
			<td><s:textfield theme="simple" name="switchSta.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/></td>
			<td>所在位置</td>			
			<td>
			<s:textfield theme="simple" name="pps" id="birds2" cssClass="ignore i290" value="%{building}"/>
			<s:hidden theme="simple" name="switchSta.locationId" cssClass="int" value="%{result.locationId}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchBuildingHost.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="switchSta.locationId"]').val(ui.item.id);
						$('input[name="switchSta.locationType"]').val(ui.item.type);
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
			<td>纬度</td>
			<td><s:textfield theme="simple" name="switchSta.latitude" cssClass="float i290" id="lat" readonly="readonly"  value="%{result.latitude}"/></td>
			<td>所在类型</td>
			<td>
			<input type="text" name="locationType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.locationType==1020101">工 井</s:if><s:if test="result.locationType==1020102">塔 杆</s:if><s:if test="result.locationType==1020203">虚拟管沟</s:if><s:if test="result.locationType==1020211">排 管</s:if><s:if test="result.locationType==1020212">桥 架</s:if><s:if test="result.locationType==1020213">沟 道</s:if><s:if test="result.locationType==1020214">直 埋</s:if><s:if test="result.locationType==1020215">隧 道</s:if><s:if test="result.locationType==1020216">顶 管</s:if>"/>
			<s:hidden theme="simple" name="switchSta.locationType" cssClass="int i290" value="%{result.locationType}"/>
			</td>
		</tr>
		<tr>
			<td style="display:none;">所属配电线路</td>
			<td style="display:none;">
			<s:textfield theme="simple" name="sw" id="birds" cssClass="ignore i290" value="%{clName}"/>
			<s:hidden theme="simple" name="switchSta.belongsCableline" cssClass="" value="%{result.belongsCableline}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="switchSta.belongsCableline"]').val(ui.item.id);
					}
				});
			});
			</script>
			</td>
			<td>运行状态</td>
			<td>			<select name="switchSta.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
		</tr>
		<tr><th colspan="2">开关站其他属性</th></tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="switchSta.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="switchSta.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>站址</td>
			<td><s:textfield theme="simple" name="switchSta.stationAddress" cssClass="str i290" value="%{result.stationAddress}"/></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="switchSta.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="switchSta.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>高程(m)</td>
			<td><s:textfield theme="simple" name="switchSta.elevation" cssClass="float i290" value="%{result.elevation}"/></td>
		</tr>
		
		<tr style="display:none;">
			<td>一次接线图ID</td>
			<td>			<select name="switchSta.wiringDiagramId">
			<option value ="0" <s:if test="result.wiringDiagramId==0">selected</s:if>> </option>
			</select></td>
			<td>竣工资料附件ID</td>
			<td>			<select name="switchSta.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>运行编号</td>
			<td><s:textfield theme="simple" name="switchSta.runCode" cssClass="str i290" value="%{result.runCode}"/></td>
			<td>是否独立建筑物</td>
			<td>			<select name="switchSta.independentStructure">
			<option value =true <s:if test="result.independentStructure==0">selected</s:if>>是</option>
			<option value =false <s:if test="result.independentStructure==1">selected</s:if>>否</option>
			</select></td>
		</tr>
		<tr>
			<td>接地电阻</td>
			<td><s:textfield theme="simple" name="switchSta.groundResistance" cssClass="float i290" value="%{result.groundResistance}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="switchSta.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>进线间隔回路数</td>
			<td>			
			<s:textfield theme="simple" name="switchSta.circuitCountIn" cssClass="int i290" value="%{result.circuitCountIn}"/>
			</td>
			<td>出线间隔回路数</td>
			<td>			
			<s:textfield theme="simple" name="switchSta.circuitCountOut" cssClass="int i290" value="%{result.circuitCountOut}"/>
			</td>
		</tr>
		<tr>
			<td>备用进出线间隔数</td>
			<td>		
			<s:textfield theme="simple" name="switchSta.backupCompartmentCount" cssClass="int i290" value="%{result.backupCompartmentCount}"/>
			</td>
			<td>有无低压出线</td>
			<td>			<select name="switchSta.lowPressureOut">
			<option value ="0" <s:if test="result.lowPressureOut==0">selected</s:if>>有</option>
			<option value ="1" <s:if test="result.lowPressureOut==1">selected</s:if>>无</option>
			</select></td>
		</tr>
		<tr>
			<td>无功补偿容量</td>
			<td><s:textfield theme="simple" name="switchSta.reactiveCompensation" cssClass="float i290" value="%{result.reactiveCompensation}"/></td>
			<td>防误方式</td>
			<td>			<select name="switchSta.antiErrorMethod">
			<option value ="0" <s:if test="result.antiErrorMethod==0">selected</s:if>>机械闭锁</option>
			<option value ="1" <s:if test="result.antiErrorMethod==1">selected</s:if>>电气闭锁</option>
			<option value ="2" <s:if test="result.antiErrorMethod==2">selected</s:if>>微机五防</option>
			</select></td>
		</tr>
	
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
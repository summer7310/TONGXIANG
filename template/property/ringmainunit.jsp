<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>环网柜</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">环网柜属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="ringmainunit.autoId" value="%{result.autoId}" cssClass="int" /></s:if>
			<s:hidden theme="simple" name="ringmainunit.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="ringmainunit.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="ringmainunit.objectType">
			<option value="1010204">环网柜</option>
			</select></td>
		</tr>
		<tr>
			<td>经 度</td>
			<td>
			<s:textfield theme="simple" name="ringmainunit.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/>
			</td>
			<td>所在位置</td>
			<td>
			<s:textfield theme="simple" name="pps" id="birds2" cssClass="ignore i290" value="%{building}"/>
			<s:hidden theme="simple" name="ringmainunit.locationId" cssClass="int" value="%{result.locationId}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchBuildingHost.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="ringmainunit.locationId"]').val(ui.item.id);
						$('input[name="ringmainunit.locationType"]').val(ui.item.type);
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
			<td><s:textfield theme="simple" name="ringmainunit.latitude" cssClass="float i290" id="lat" value="%{result.latitude}"/></td>
			<td>所在类型</td>
			<td>
			<input type="text" name="locationType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.locationType==1020101">工 井</s:if><s:if test="result.locationType==1020102">塔 杆</s:if><s:if test="result.locationType==1020203">虚拟管沟</s:if><s:if test="result.locationType==1020211">排 管</s:if><s:if test="result.locationType==1020212">桥 架</s:if><s:if test="result.locationType==1020213">沟 道</s:if><s:if test="result.locationType==1020214">直 埋</s:if><s:if test="result.locationType==1020215">隧 道</s:if><s:if test="result.locationType==1020216">顶 管</s:if>"/>
			<s:hidden theme="simple" name="ringmainunit.locationType" cssClass="int i290" value="%{result.locationType}"/>
			</td>
		</tr>
		<tr>
			<td style="display:none;">所属配电线路</td>
			<td style="display:none;">			
			<s:textfield theme="simple" name="rin" id="birds" cssClass="ignore i290" value="%{clName}"/>
			<s:hidden theme="simple" name="ringmainunit.belongsCableline" cssClass="" value="%{result.belongsCableline}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="ringmainunit.belongsCableline"]').val(ui.item.id);
					}
				});
			});
			</script>
			</td>
			<td>运行状态</td>
			<td>			<select name="ringmainunit.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
		</tr>
		
		<tr><th colspan="2">环网柜其他属性</th></tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="ringmainunit.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="ringmainunit.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>站址</td>
			<td><s:textfield theme="simple" name="ringmainunit.stationAddress" cssClass="str i290" value="%{result.stationAddress}"/></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="ringmainunit.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="ringmainunit.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>高程(m)</td>
			<td><s:textfield theme="simple" name="ringmainunit.elevation" cssClass="float i290" value="%{result.elevation}"/></td>
		</tr>
		
		<tr style="display:none;">
			<td>一次接线图ID</td>
			<td>			<select name="ringmainunit.wiringDiagramId">
			<option value ="0" <s:if test="result.wiringDiagramId==0">selected</s:if>> </option>
			</select></td>
			<td>竣工资料附件ID</td>
			<td>			<select name="ringmainunit.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>运行编号</td>
			<td><s:textfield theme="simple" name="ringmainunit.runCode" cssClass="str i290" value="%{result.runCode}"/></td>
			<td>备用进出线间隔数</td>
			<td>			
			<s:textfield theme="simple" name="ringmainunit.backupCompartmentCount" cssClass="int i290" value="%{result.backupCompartmentCount}"/>
			</td>
		</tr>
		<tr>
			<td>接地电阻</td>
			<td><s:textfield theme="simple" name="ringmainunit.groundResistance" cssClass="float i290" value="%{result.groundResistance}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="ringmainunit.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>安装位置</td>
			<td>			<select name="ringmainunit.position">
			<option value ="0" <s:if test="result.position==0">selected</s:if>>户 内</option>
			<option value ="1" <s:if test="result.position==1">selected</s:if>>户 外</option>
			</select></td>
			<td>环网柜型号</td>
			<td><s:textfield theme="simple" name="ringmainunit.ringmainunitModel" cssClass="str i290" value="%{result.ringmainunitModel}"/></td>
		</tr>
		<tr>
			<td>生产厂家</td>
			<td><s:textfield theme="simple" name="ringmainunit.manufacturer" cssClass="str i290" value="%{result.manufacturer}"/></td>
			<td>出厂编号</td>
			<td><s:textfield theme="simple" name="ringmainunit.manufactureCode" cssClass="str i290" value="%{result.manufactureCode}"/></td>
		</tr>
		<tr>
			<td>出厂日期</td>
			<td><s:textfield theme="simple" name="ringmainunit.manufactureDate" cssClass="date i290" value="%{result.manufactureDate}"/></td>
			<td>出线间隔回路数</td>
			<td>			
			<s:textfield theme="simple" name="ringmainunit.circuitCountOut" cssClass="int i290" value="%{result.circuitCountOut}"/>
			</td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变电站</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">变电站基本属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="transSub.autoId" value="%{result.autoId}" cssClass="int"/></s:if>
			<s:hidden theme="simple" name="transSub.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">
			<s:textfield theme="simple" name="transSub.name" cssClass="str i290" id="feature_name" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			<select name="transSub.objectType">
			<option value="1010201">变电站</option>		
			</select></td>
		</tr>
		<tr>
			<td>经 度</td>
			<td><s:textfield theme="simple" name="transSub.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/></td>
			<td>所在位置</td>
			<td>
			<s:textfield theme="simple" name="pps" id="birds2" cssClass="ignore i290" value="%{building}"/>
			<s:hidden theme="simple" name="transSub.locationId" cssClass="int" value="%{result.locationId}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchBuildingHost.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="transSub.locationId"]').val(ui.item.id);
						$('input[name="transSub.locationType"]').val(ui.item.type);
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
			<td>
			<s:textfield theme="simple" name="transSub.latitude" cssClass="float i290" id="lat" value="%{result.latitude}"/>
			</td>
			<td>所在类型</td>
			<td>
			<input type="text" name="locationType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.locationType==1020101">工 井</s:if><s:if test="result.locationType==1020102">塔 杆</s:if><s:if test="result.locationType==1020203">虚拟管沟</s:if><s:if test="result.locationType==1020211">排 管</s:if><s:if test="result.locationType==1020212">桥 架</s:if><s:if test="result.locationType==1020213">沟 道</s:if><s:if test="result.locationType==1020214">直 埋</s:if><s:if test="result.locationType==1020215">隧 道</s:if><s:if test="result.locationType==1020216">顶 管</s:if>"/>
			<s:hidden theme="simple" name="transSub.locationType" cssClass="int i290" value="%{result.locationType}"/>
			</td>
		</tr>
		<tr>
			<td style="display:none;">所属配电线路</td>
			<td style="display:none;">			
			<s:textfield theme="simple" name="tra" id="birds" cssClass="ignore i290" value="%{clName}"/>
			<s:hidden theme="simple" name="transSub.belongsCableline" cssClass="" value="%{result.belongsCableline}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="transSub.belongsCableline"]').val(ui.item.id);
					}
				});
			});
			</script>
			</td>
			<td>运行状态</td>
			<td>			<select name="transSub.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
			
			<td>电压等级</td>
			<td>			<select name="transSub.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
		</tr>
		<tr>
			<td>高 程(m)</td>
			<td>
			<s:textfield theme="simple" name="transSub.elevation" cssClass="float i290" value="%{result.elevation}"/>
			</td>
			
		</tr>
		<tr><th colspan="2">变电站其他属性</th></tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="transSub.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="transSub.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
		</tr>
		<tr>
			<td>站址</td>
			<td><s:textfield theme="simple" name="transSub.stationAddress" cssClass="str i290" value="%{result.stationAddress}"/></td>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="transSub.owner" cssClass="str i290" value="%{result.owner}"/></td>
		</tr>
		
		
		<tr style="display:none;">
			<td>一次接线图ID</td>
			<td>			<select name="transSub.wiringDiagramId">
			<option value ="0" <s:if test="result.wiringDiagramId==0">selected</s:if>> </option>
			</select></td>
			<td>竣工资料附件ID</td>
			<td>			<select name="transSub.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>运行编号</td>
			<td>
		<s:textfield theme="simple" name="transSub.runCode" cssClass="str i290" value="%{result.runCode}"/>
			</td>
			<td>值班方式</td>
			<td>			
			<s:textfield theme="simple" name="transSub.dutyMode" cssClass="str i290" value="%{result.dutyMode}"/>
			</td>
		</tr>
		<tr>
			<td>接地电阻</td>
			<td><s:textfield theme="simple" name="transSub.groundResistance" cssClass="float i290" value="%{result.groundResistance}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="transSub.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td>运行班组</td>
			<td><s:textfield theme="simple" name="transSub.runingGroup" cssClass="str i290" value="%{result.runingGroup}"/></td>
			<td>资产性质</td>
			<td><s:textfield theme="simple" name="transSub.assetsNature" cssClass="str i290" value="%{result.assetsNature}"/></td>
		</tr>
		
		<tr>
			<td>布置方式</td>
			<td><s:textfield theme="simple" name="transSub.layout" cssClass="str i290" value="%{result.layout}"/></td>
			<td>是否枢纽站</td>
			<td>			<select name="transSub.isJunctionStation">
			<option value =true <s:if test="result.isJunctionStation==0">selected</s:if>>是</option>
			<option value =false <s:if test="result.isJunctionStation==1">selected</s:if>>否</option>
			</select></td>
		</tr>
		<tr>
			<td>变电站性质</td>
			<td>			<select name="transSub.nature">
			<option value ="0" <s:if test="result.nature==0">selected</s:if>> </option>
			<option value ="1" <s:if test="result.nature==1">selected</s:if>>国家电网公司</option>
			<option value ="2" <s:if test="result.nature==2">selected</s:if>>区域电网公司</option>
			<option value ="3" <s:if test="result.nature==3">selected</s:if>>省公司</option>
			<option value ="4" <s:if test="result.nature==4">selected</s:if>>县公司</option>
			<option value ="5" <s:if test="result.nature==5">selected</s:if>>用户</option>
			</select></td>
			<td>污秽等级</td>
			<td>			<select name="transSub.pollutionLevel">
			<option value ="0" <s:if test="result.pollutionLevel==0">selected</s:if>>O</option>
			<option value ="1" <s:if test="result.pollutionLevel==1">selected</s:if>>I</option>
			<option value ="2" <s:if test="result.pollutionLevel==2">selected</s:if>>II</option>
			<option value ="3" <s:if test="result.pollutionLevel==3">selected</s:if>>III</option>
			<option value ="4" <s:if test="result.pollutionLevel==4">selected</s:if>>IV</option>
			</select></td>
		</tr>
		<tr>
			<td>占地面积(m<SUP>2</SUP>)</td>
			<td><s:textfield theme="simple" name="transSub.area" cssClass="float i290" value="%{result.area}"/></td>
			<td>建筑面积(m<SUP>2</SUP>)</td>
			<td><s:textfield theme="simple" name="transSub.constructionArea" cssClass="float i290" value="%{result.constructionArea}"/></td>
		</tr>
		<tr>
			
			<td>备注</td>
			<td><s:textfield theme="simple" name="transSub.remark" cssClass="str i290" value="%{result.remark}"/></td>
			<td> </td>
			<td> </td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
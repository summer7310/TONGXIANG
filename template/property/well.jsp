<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工井</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">工井基本属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="well.autoId" value="%{result.autoId}" cssClass="int" /></s:if>
			<s:hidden theme="simple" name="well.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="well.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td>
			<select name="well.objectType">
			<option value="1020101" <s:if test="result.objectType==1020101">selected</s:if>>工 井</option>
			<option value="1020103" <s:if test="result.objectType==1020103">selected</s:if>>虚拟工井</option>
			</select>
			</td>	
		</tr>
		<tr>
			<td>经度</td>
			<td><s:textfield theme="simple" name="well.longitude" cssClass="float i290" id="lon" value="%{result.longitude}"/></td>
			<td>电压等级</td>
			<td>			<select name="well.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
		</tr>
		<tr>
		
			<td>纬度</td>
			<td><s:textfield theme="simple" name="well.latitude" cssClass="float i290" id="lat" value="%{result.latitude}"/></td>
			
			<td>所属道路</td>
			<td>			
			<s:textfield theme="simple" name="wellroad" id="birds" cssClass="ignore i290" value="%{road}"/>
			<s:hidden theme="simple" name="well.belongsRoad" cssClass="" value="%{result.belongsRoad}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchRoad.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="well.belongsRoad"]').val(ui.item.id);
					}
				});
			});
			</script>
			
			</td>
			
			
		</tr>
		<tr><th colspan="2">工井其他属性</th></tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="well.owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="well.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
		</tr>
		<tr>
			<td style="display:none;">竣工资料附件ID</td>
			<td style="display:none;">			<select name="well.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="well.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="well.builder" cssClass="str i290" value="%{result.builder}"/></td>
			
			<td>运行编号</td>
			<td><s:textfield theme="simple" name="well.runCode" cssClass="str i290 ignore" value="%{result.runCode}"/></td>
		</tr>
		<tr>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="well.operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
			<td>所在区域</td>
			<td><s:textfield theme="simple" name="well.location" cssClass="str i290" value="%{result.location}"/></td>
		</tr>
		<tr>
			<td>井类型</td>
			<td>			<select name="well.wellType">
			<option value ="0" <s:if test="result.wellType==0">selected</s:if>>其他</option>
			<option value ="1" <s:if test="result.wellType==1">selected</s:if>>排管井</option>
			<option value ="2" <s:if test="result.wellType==2">selected</s:if>>沟道井</option>
			<option value ="3" <s:if test="result.wellType==3">selected</s:if>>隧道井</option>
			<option value ="4" <s:if test="result.wellType==4">selected</s:if>>通风井</option>
			<option value ="5" <s:if test="result.wellType==5">selected</s:if>>施工井</option>
			<option value ="6" <s:if test="result.wellType==6">selected</s:if>>虚拟井</option>
			</select></td>
			<td>结构</td>
			<td>			<select name="well.structure">
			<option value ="0" <s:if test="result.structure==0">selected</s:if>>二通</option>
			<option value ="1" <s:if test="result.structure==1">selected</s:if>>三通</option>
			<option value ="2" <s:if test="result.structure==2">selected</s:if>>四通</option>
			<option value ="3" <s:if test="result.structure==3">selected</s:if>>异形</option>
			</select></td>
		</tr>
		<tr>
			<td>井顶深(m)</td>
			<td><s:textfield theme="simple" name="well.topDepth" cssClass="float i290" value="%{result.topDepth}"/></td>
			<td>内底深(mm)</td>
			<td><s:textfield theme="simple" name="well.bottomDepth" cssClass="float i290" value="%{result.bottomDepth}"/></td>
		</tr>
		<tr>
			<td>井宽(mm)</td>
			<td><s:textfield theme="simple" name="well.wellWidth" cssClass="float i290" value="%{result.wellWidth}"/></td>
			<td>井长(mm)</td>
			<td><s:textfield theme="simple" name="well.wellLenth" cssClass="float i290" value="%{result.wellLenth}"/></td>
		</tr>
		<tr>
			<td>平台层数</td>
			<td>			
			<s:textfield theme="simple" name="well.platformLayers" cssClass="int i290" value="%{result.platformLayers}"/>
			</td>
			<td>施工日期</td>
			<td><s:textfield theme="simple" name="well.commencementDate" cssClass="date i290" value="%{result.commencementDate}"/></td>
		</tr>
		<tr>
			<td>竣工日期</td>
			<td><s:textfield theme="simple" name="well.completionDate" cssClass="date i290" value="%{result.completionDate}"/></td>
			<td>图纸编号</td>
			<td><s:textfield theme="simple" name="well.drawingNumber" cssClass="str i290" value="%{result.drawingNumber}"/></td>
		</tr>
		<tr>
			<td>通道数</td>
			<td>			
			<s:textfield theme="simple" name="well.channelCount" cssClass="int i290" value="%{result.channelCount}"/>
			</td>
			<td>是否为虚拟井</td>
			<td>			<select name="well.isVirtualWell">
			<option value =true <s:if test="result.isVirtualWell==0">selected</s:if>>否</option>
			<option value =false <s:if test="result.isVirtualWell==1">selected</s:if>>是</option>
			</select></td>
		</tr>
		<tr>
			<td>圆井盖尺寸(mm)</td>
			<td><s:textfield theme="simple" name="well.coversSizeRound" cssClass="float i290" value="%{result.coversSizeRound}"/></td>
			<td>圆井盖数量</td>
			<td>			
			<s:textfield theme="simple" name="well.coversCountRound" cssClass="int i290" value="%{result.coversCountRound}"/>
			</td>
		</tr>
		<tr>
			<td>圆井盖材质</td>
			<td>			<select name="well.coversMaterialRound">
			<option value ="0" <s:if test="result.coversMaterialRound==0">selected</s:if>> </option>
			<option value ="1" <s:if test="result.coversMaterialRound==1">selected</s:if>>铸铁</option>
			<option value ="2" <s:if test="result.coversMaterialRound==2">selected</s:if>>复合材料</option>
			<option value ="3" <s:if test="result.coversMaterialRound==3">selected</s:if>>砼</option>
			</select></td>
			<td>圆井盖生产厂家</td>
			<td><s:textfield theme="simple" name="well.coversManufactureRound" cssClass="str i290" value="%{result.coversManufactureRound}"/></td>
		</tr>
		<tr>
			<td>圆井盖出厂日期</td>
			<td><s:textfield theme="simple" name="well.manufactureDateRound" cssClass="date i290" value="%{result.manufactureDateRound}"/></td>
			<td>方井盖尺寸(mm)</td>
			<td><s:textfield theme="simple" name="well.coversSizeSquare" cssClass="str i290" value="%{result.coversSizeSquare}"/></td>
		</tr>
		<tr>
			<td>方井盖数量</td>
			<td>		
			<s:textfield theme="simple" name="well.coversCountSquare" cssClass="int i290" value="%{result.coversCountSquare}"/>
			</td>
			<td>方井盖材质</td>
			<td>			<select name="well.coversMaterialSquare">
			<option value ="0" <s:if test="result.coversMaterialSquare==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>方井盖生产厂家</td>
			<td><s:textfield theme="simple" name="well.coversManufactureSquare" cssClass="str i290" value="%{result.coversManufactureSquare}"/></td>
			<td>方井盖出厂日期</td>
			<td><s:textfield theme="simple" name="well.manufactureDateSquare" cssClass="date i290" value="%{result.manufactureDateSquare}"/></td>
		</tr>
		<tr>
			<td>支架型号规格</td>
			<td><s:textfield theme="simple" name="well.kickSpecification" cssClass="str i290" value="%{result.kickSpecification}"/></td>
			<td>支架材质</td>
			<td><s:textfield theme="simple" name="well.kickStandMaterial" cssClass="str i290" value="%{result.kickStandMaterial}"/></td>
		</tr>
		<tr>
			<td>支架数量</td>
			<td>		
			<s:textfield theme="simple" name="well.kickStandCount" cssClass="int i290" value="%{result.kickStandCount}"/>
			</td>
			<td style="display:none;">工井展开图ID</td>
			<td style="display:none;">			<select name="well.wellExpansionPlanId">
			<option value="0" <s:if test="result.wellExpansionPlanId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			
			<td>高程(m)</td>
			<td><s:textfield theme="simple" name="well.elevation" cssClass="float i290" value="%{result.elevation}"/></td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
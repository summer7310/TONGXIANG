<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顶管</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">顶管的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="jacking[0].autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="jacking[0].staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="jacking[0].name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td>
			<input name="jacking[0].objectType" type="hidden" class="int" value="1020211">
			<input name="jacking.objectType" type="text"  class="readonly ignore i290" readonly="readonly" value="顶管">
			</td>
		</tr>
		
		<tr>
			<td>起点</td>
			<td>			
			<input name="jacking[0].startBuilding" type="hidden" id="start" class="int" value="<s:property value="%{result.startBuilding}" />">
			<input name="jackingStartBuilding" type="text" id="startName" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{startBuilding}" />">
			</td>
			<td>起点类型</td>
			<td>			
			<input name="jacking[0].startBuildingType" type="hidden" id="start_type" class="int" value="<s:property value="%{result.startBuildingType}" />">
			<input name="jackingStartBuildingType" type="text" id="startTypeName" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.startBuildingType==1020101">工 井</s:if><s:if test="result.startBuildingType==1020102">塔 杆</s:if><s:if test="result.startBuildingType==1020103">虚拟工井</s:if>">
			</td>
		</tr>
		<tr>
			<td>终点</td>
			<td>	
			<input name="jacking[0].endBuilding" type="hidden" id="end" class="int" value="<s:property value="%{result.endBuilding}" />">
			<input name="jackingEndBuilding" type="text" id="endName" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{endBuilding}" />">
			</td>
			<td>终点类型</td>
			<td>			
			<input name="jacking[0].endBuildingType" type="hidden" id="end_type" class="int" value="<s:property value="%{result.endBuildingType}" />">
			<input name="jackingEndBuildingType" type="text" id="endTypeName" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.endBuildingType==1020101">工 井</s:if><s:if test="result.endBuildingType==1020102">塔 杆</s:if><s:if test="result.endBuildingType==1020103">虚拟工井</s:if>">
			</td>
		</tr>
		<tr><s:hidden theme="simple" name="jacking[0].sectionId"  cssClass="int" value="%{result.sectionId}"/>
			<td style="display:none;">RFID</td>
			<td style="display:none;">			<select name="jacking[0].rfid">
			<option value ="0" <s:if test="result.rfid==0">selected</s:if>> </option>
			</select></td>
			<td>所属管沟线</td>
			<td>			
			<s:textfield theme="simple" name="rack" id="birds" cssClass="ignore i290" value="%{pipeline}"/>
			<s:hidden theme="simple" name="jacking[0].lineBelongId" cssClass="int" value="%{result.lineBelongId}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchPLInfo.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="jacking[0].lineBelongId"]').val(ui.item.id);
					}
				});
			});
			</script>
			</td>
		</tr>
		<tr>
			<td>管沟段编号</td>
			<td><s:textfield theme="simple" name="jacking[0].segmentCode" cssClass="str i290" value="%{result.segmentCode}"/></td>
			<td>运行单位</td>
			<td><s:textfield theme="simple" name="jacking[0].operatingUnit" cssClass="str i290" value="%{result.operatingUnit}"/></td>
		</tr>
		<tr>
			<td>所在区域</td>
			<td><s:textfield theme="simple" name="jacking[0].location" cssClass="str i290" value="%{result.location}"/></td>
			<td>档案名称</td>
			<td><s:textfield theme="simple" name="jacking[0].archiveName" cssClass="str i290" value="%{result.archiveName}"/></td>
		</tr>
		<tr>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="jacking[0].builder" cssClass="str i290" value="%{result.builder}"/></td>
			<td>竣工日期</td>
			<td><s:textfield theme="simple" name="jacking[0].completionDate" cssClass="date i290" value="%{result.completionDate}"/></td>
		</tr>
		<tr>
			<td>图纸编号</td>
			<td><s:textfield theme="simple" name="jacking[0].drawingNumber" cssClass="str i290" value="%{result.drawingNumber}"/></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="jacking[0].remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="jacking[0].owner" cssClass="str i290" value="%{result.owner}"/></td>
			<td>断面尺寸(宽)(mm)</td>
			<td><s:textfield theme="simple" name="jacking[0].sectionSize width" cssClass="float i290" value="%{result.sectionSize width}"/></td>
		</tr>
		<tr>
			<td>断面尺寸(高)(mm)</td>
			<td><s:textfield theme="simple" name="jacking[0].sectionSize height" cssClass="float i290" value="%{result.sectionSize height}"/></td>
			<td style="display:none;">竣工资料附件ID</td>
			<td style="display:none;">			<select name="jacking[0].attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
		</tr>
		<tr>
			<td>截面类型</td>
			<td><s:textfield theme="simple" name="jacking[0].sectionType" cssClass="str i290" value="%{result.sectionType}"/></td>
			<td>材料1</td>
			<td>			<select name="jacking[0].material1">
			<option value ="0" <s:if test="result.material1==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.material1==1">selected</s:if>>砼</option>
			<option value ="2" <s:if test="result.material1==2">selected</s:if>>钢管</option>
			<option value ="3" <s:if test="result.material1==3">selected</s:if>>波纹管</option>
			<option value ="4" <s:if test="result.material1==4">selected</s:if>>PE管</option>
			<option value ="5" <s:if test="result.material1==5">selected</s:if>>复合材料</option>
			</select></td>
		</tr>
		<tr>
			<td>管径1(mm)</td>
			<td>			
			<s:textfield theme="simple" name="jacking[0].pipeDiameter" cssClass="int i290" value="%{result.pipeDiameter}"/>
			</td>
			<td>管道长度(m)</td>
			<td><s:textfield theme="simple" name="jacking[0].length" cssClass="str i290" value="%{result.length}"/></td>
		</tr>
		<tr>
			<td>穿管数量</td>
			<td>			
			<s:textfield theme="simple" name="jacking[0].cableConduit" cssClass="int i290" value="%{result.cableConduit}"/>
			</td>
			<td>上顶埋深(mm)</td>
			<td><s:textfield theme="simple" name="jacking[0].buriedDepth" cssClass="float i290" value="%{result.buriedDepth}"/></td>
		</tr>
		<tr>
			<td>材料2</td>
			<td>			<select name="jacking[0].material2">
			<option value ="0" <s:if test="result.material2==0">selected</s:if>>缺省</option>
			<option value ="1" <s:if test="result.material2==1">selected</s:if>>角钢</option>
			<option value ="2" <s:if test="result.material2==2">selected</s:if>>复合材料</option>
			</select>
			
			</td>
			<td>管径2</td>
			<td>			
			<s:textfield theme="simple" name="jacking[0].pipeDiameter2" cssClass="int i290" value="%{result.pipeDiameter2}"/>
			</td>
		</tr>
		<tr>
			<td>管孔总数</td>
			<td>			
			<s:textfield theme="simple" name="jacking[0].holeCount" cssClass="int i290" value="%{result.holeCount}"/>
			</td>
			<td>空余管孔数</td>
			<td>		
			<s:textfield theme="simple" name="jacking[0].freePore" cssClass="int i290" value="%{result.freePore}"/>
			</td>
		</tr>
		 
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
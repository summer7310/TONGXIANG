<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管沟线</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">管沟线的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="pipeline.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
		<tr>
			<td width="80px">名称</td>
			<td width="340px"><s:textfield theme="simple" name="pipeline.name" id="feature_name" cssClass="str i290" value="%{result.name}"/></td>
			<td width="80px">对象类型</td>
			<td width="300px">			
			<select name="pipeline.objectType">
				<option value="1020200">管沟线</option>
			</select></td>
		</tr>
		<tr style="display:none;">
			<td  style="display:none;">RFID</td>
			<td  style="display:none;">			<select name="pipeline.rfid">
			<option value ="0" <s:if test="result.rfid==0">selected</s:if>>RFID0</option>
			<option value ="1" <s:if test="result.rfid==1">selected</s:if>>RFID1</option>
			<option value ="2" <s:if test="result.rfid==2">selected</s:if>>RFID2</option>
			</select></td>
			<td>静态ID</td>
			<td>			
			<s:hidden theme="simple" name="pipeline.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
			</td>
		</tr>
		<tr>
			<td>起点土建</td>
			<td>
			<s:textfield theme="simple" name="pps" id="birds" cssClass="ignore i290" value="%{startBuilding}"/>
			<s:hidden theme="simple" name="pipeline.startBuilding" cssClass="int" value="%{result.startBuilding}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds" ).autocomplete({
					source: "getSearchBuilding.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="pipeline.startBuilding"]').val(ui.item.id);
						$('input[name="pipeline.startBuildingType"]').val(ui.item.type);
						if(ui.item.type == 1020101){
							$('input[name="startType"]').val("工井");
						} else if(ui.item.type == 1020102){
							$('input[name="startType"]').val("塔杆");
						} else {
							$('input[name="startType"]').val("错误的类型号");
						}
					}
				});
			});
			</script>
			
			</td>
			<td>起点类型</td>
			<td>
		
			<input type="text" name="startType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.startBuildingType==1020101">工 井</s:if><s:if test="result.startBuildingType==1020102">塔 杆</s:if>"/>
			<s:hidden theme="simple" name="pipeline.startBuildingType" cssClass="int i290" value="%{result.startBuildingType}"/>
			</td>
		</tr>
		<tr>
			<td>终点土建</td>
			<td>
			<s:textfield theme="simple" name="pps" id="birds2" cssClass="ignore i290" value="%{endBuilding}"/>
			<s:hidden theme="simple" name="pipeline.endBuilding" cssClass="int" value="%{result.endBuilding}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchBuilding.action",
					minLength: 2,
					select: function( event, ui ) {
						$('input[name="pipeline.endBuilding"]').val(ui.item.id);
						$('input[name="pipeline.endBuildingType"]').val(ui.item.type);
						if(ui.item.type == 1020101){
							$('input[name="endType"]').val("工井");
						} else if(ui.item.type == 1020102){
							$('input[name="endType"]').val("塔杆");
						} else {
							$('input[name="endType"]').val("错误的类型号");
						}
					}
				});
			});
			</script>
			
			</td>
			<td>终点类型</td>
			<td>			
			<input type="text" name="endType" class="readonly ignore i290" readonly="readonly" value="<s:if test="result.endBuildingType==1020101">工 井</s:if><s:if test="result.endBuildingType==1020102">塔 杆</s:if>"/>
			<s:hidden theme="simple" name="pipeline.endBuildingType" cssClass="int i290" value="%{result.endBuildingType}"/>
			</td>
		</tr>
		<tr>
			<td>所属道路</td>
			<td>			
			<input type="text" name="roadname" class="readonly ignore i290" readonly="readonly" value="<s:property value="%{road}"/>"/>
			<s:hidden theme="simple" name="pipeline.belongsRoad" cssClass="int i290" value="%{result.belongsRoad}"/>
			</td>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
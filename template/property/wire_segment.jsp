<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导线段</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">导线段的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="wireSeg.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="wireSeg.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="wireSeg.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td width="300px">			<select name="wireSeg.objectType">
			<option value="1010403">导线段</option>
			
			</select></td>
		</tr>
		<tr>
			<td>所属配电线路</td>
			<td>			
			<input name="wireB" type="text" id="birds" Class="ignore readonly i290" readonly="readonly" value="<s:property value="%{clName}"/>">
			<s:hidden theme="simple" name="wireSeg.belongsCableline" cssClass="int" value="%{result.belongsCableline}"/>
			</td>
			<td>电压等级</td>
			<td>			<select name="wireSeg.voltageGrade">
			<option value ="0" <s:if test="result.voltageGrade==0">selected</s:if>>10KV</option>
			<option value ="1" <s:if test="result.voltageGrade==1">selected</s:if>>20KV</option>
			<option value ="2" <s:if test="result.voltageGrade==2">selected</s:if>>35KV</option>
			<option value ="3" <s:if test="result.voltageGrade==3">selected</s:if>>110KV</option>
			<option value ="4" <s:if test="result.voltageGrade==4">selected</s:if>>220KV</option>
			</select></td>
		</tr>
		<tr>
			<td>起点设备</td>
			<td>			
			<s:textfield theme="simple" name="cal" id="birds2" cssClass="ignore i290" value="%{startObj}"/>
			<s:hidden theme="simple" name="wireSeg.startContainer" cssClass="int" value="%{result.startContainer}"/>
			<script type="text/javascript">
			var type = {
					'1010202'	: "开关站",
					'1010205'	: "分支箱",
					'1010204'	: "环网柜",
					'1010203'	: "变压器",
					'1010208'	: "中间接头",
					'1020101'	: "工井",
					'1020102'  : "杆塔"
			}
			
			$(function() {
				$( "#birds2" ).autocomplete({
					source: "getSearchContainer.action",
					minLength: 1,
					select: function( event, ui ) {
						$('input[name="wireSeg.startContainer"]').val(ui.item.id);
						$('input[name="wireSeg.startContainerType"]').val(ui.item.type);
						
						
						$('input[name="startType"]').val(type[ui.item.type]);
					}
				});
			});
			</script>
			</td>
			<td>终点设备</td>
			<td>			
			<s:textfield theme="simple" name="cal" id="birds3" cssClass="ignore i290" value="%{endObj}"/>
			<s:hidden theme="simple" name="wireSeg.endContainer" cssClass="" value="%{result.endContainer}"/>
			<script type="text/javascript">
			$(function() {
				$( "#birds3" ).autocomplete({
					source: "getSearchContainer.action",
					minLength: 1,
					select: function( event, ui ) {
						$('input[name="wireSeg.endContainer"]').val(ui.item.id);
						$('input[name="wireSeg.endContainerType"]').val(ui.item.type);
						$('input[name="endType"]').val(type[ui.item.type]);
					}
				});
			});
			</script>
			</td>
		</tr>
		<tr>
			<s:if test="result.startContainerType == 1010202">
				<s:set name="st" value="'开关站'"/>
			</s:if>
			<s:if test="result.startContainerType == 1010205">
				<s:set name="st" value="'分支箱'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010204">
				<s:set name="st" value="'环网柜'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010203">
				<s:set name="st" value="'变压器'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010208">
				<s:set name="st" value="'中间接头'"/>
			</s:if>
			<s:if test="result.startContainerType == 1020102">
				<s:set name="st" value="'杆塔'"/>
			</s:if>	
			<td>起点类型</td>
			<td>			
			<input name="startType" type="text"  Class="ignore readonly i290" readonly="readonly" value="<s:property value="#st"/>">
			<s:hidden theme="simple" name="wireSeg.startContainerType" cssClass="int" value="%{result.startContainerType}"/>
			</td>
			<s:if test="result.endContainerType == 1010202">
				<s:set name="et" value="'开关站'"/>
			</s:if>
			<s:if test="result.startContainerType == 1010205">
				<s:set name="et" value="'分支箱'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010204">
				<s:set name="et" value="'环网柜'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010203">
				<s:set name="et" value="'变压器'"/>
			</s:if>	
			<s:if test="result.startContainerType == 1010208">
				<s:set name="et" value="'中间接头'"/>
			</s:if>
			<s:if test="result.startContainerType == 1020102">
				<s:set name="et" value="'杆塔'"/>
			</s:if>	
			<td>终点类型</td>
			<td>
			<input name="endType" type="text"  Class="ignore readonly i290" readonly="readonly" value="<s:property value="#et"/>">
			<s:hidden theme="simple" name="wireSeg.endContainerType" cssClass="int" value="%{result.endContainerType}"/>
			</td>
		</tr>
		<tr>
			<td>额定载流量</td>
			<td><s:textfield theme="simple" name="wireSeg.ratedCapacity" cssClass="float i290" value="%{result.ratedCapacity}"/></td>
			<td>线缆段型号</td>
			<td><s:textfield theme="simple" name="wireSeg.wirecableSegmengModel" cssClass="str i290" value="%{result.wirecableSegmengModel}"/></td>
		</tr>
		<tr>
			<td>生产厂家</td>
			<td><s:textfield theme="simple" name="wireSeg.manufacturer" cssClass="str i290" value="%{result.manufacturer}"/></td>
			<td>出厂日期</td>
			<td><s:textfield theme="simple" name="wireSeg.manufacturerDate" cssClass="date i290" value="%{result.manufacturerDate}"/></td>
		</tr>
		<tr>
			<td>投运日期</td>
			<td><s:textfield theme="simple" name="wireSeg.commissioningDate" cssClass="date i290" value="%{result.commissioningDate}"/></td>
			<td>施工单位</td>
			<td><s:textfield theme="simple" name="wireSeg.builder" cssClass="str i290" value="%{result.builder}"/></td>
		</tr>
		<tr>
			<td>资产编号</td>
			<td><s:textfield theme="simple" name="wireSeg.assetCode" cssClass="str i290" value="%{result.assetCode}"/></td>
			<td>备注</td>
			<td><s:textfield theme="simple" name="wireSeg.remark" cssClass="str i290" value="%{result.remark}"/></td>
		</tr>
		<tr>
			<td>运行状态</td>
			<td>			<select name="wireSeg.serviceStatus">
			<option value ="0" <s:if test="result.serviceStatus==0">selected</s:if>>设 计</option>
			<option value ="1" <s:if test="result.serviceStatus==1">selected</s:if>>施 工</option>
			<option value ="2" <s:if test="result.serviceStatus==2">selected</s:if>>运 行</option>
			<option value ="3" <s:if test="result.serviceStatus==3">selected</s:if>>退 役</option>
			</select></td>
			<td>资产单位</td>
			<td><s:textfield theme="simple" name="wireSeg.owner" cssClass="str i290" value="%{result.owner}"/></td>
		</tr>
		<tr>
			<td style="display:none;">竣工资料附件ID</td>
			<td style="display:none;">			<select name="wireSeg.attachmentId">
			<option value ="0" <s:if test="result.attachmentId==0">selected</s:if>> </option>
			</select></td>
			<td>导线类型</td>
			<td>			<select name="wireSeg.wireSegmetType">
			<option value ="0" <s:if test="result.wireSegmetType==0">selected</s:if>>绝缘导线</option>
			<option value ="1" <s:if test="result.wireSegmetType==1">selected</s:if>>裸导线</option>
			</select></td>
		</tr>
		<tr>
			<td>导线排列方式</td>
			<td>			<select name="wireSeg.wireSegmetArrangement">
			<option value ="0" <s:if test="result.wireSegmetArrangement==0">selected</s:if>>水平</option>
			<option value ="1" <s:if test="result.wireSegmetArrangement==1">selected</s:if>>三角</option>
			<option value ="2" <s:if test="result.wireSegmetArrangement==2">selected</s:if>>垂直</option>
			</select></td>
			<td>根数</td>
			<td>			<select name="wireSeg.wireSegmetCount">
			<option value ="0" <s:if test="result.wireSegmetCount==0">selected</s:if>>2</option>
			<option value ="1" <s:if test="result.wireSegmetCount==1">selected</s:if>>3</option>
			<option value ="2" <s:if test="result.wireSegmetCount==2">selected</s:if>>4</option>
			<option value ="3" <s:if test="result.wireSegmetCount==3">selected</s:if>>5</option>
			</select></td>
		</tr>
		<tr>
			<td>导线截面(mm<SUP>2</SUP>)</td>
			<td><s:textfield theme="simple" name="wireSeg.wireSegmetSection" cssClass="float i290" value="%{result.wireSegmetSection}"/></td>
			
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>剖面</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">剖面的属性</th></tr>
		<s:if test="result.autoId != null"><s:hidden theme="simple" name="profile.autoId" value="%{result.autoId}" cssClass="int" /></s:if>
			<s:hidden theme="simple" name="profile.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">名 称</td>
			<td width="340px">			
			<s:textfield theme="simple" name="profile.name" id="feature_name" cssClass="str i290" value="%{result.name}"/>
			</td>
			<td width="80px">对象类型</td>
			<td>
			<select name="profile.objectType">
				<option value="1010501">剖面</option>
			</select>
			</td>	
		</tr>
		<tr>
			<td>所属管沟</td>
			<td>
			<input type="text" name="profileppSegmentId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{ppSeg}"/>" />
			</td>
			<td>所属工井</td>
			<td>
			<input type="text" name="profileWellId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{well2}"/>" />
			</td>
		</tr>
		<tr>
			<td>宽 度(mm)</td>
			<td>
			<input type="text" name="profileWellId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.profileWidth}"/>" />
			</td>
			<td>高 度(mm)</td>
			<td>
			<input type="text" name="profileWellId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.profileHigh}"/>" />
			</td>
		</tr>
		<tr>
			<td>管孔数</td>
			<td>
			<input type="text" name="pr" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.pipeNum}"/>" />
			<input type="hidden" name="profile.wellId" class="str readonly i290" value="" />
			</td>
			<td> </td>
			<td>
			
			</td>
		</tr>
		
		<tr>
			<td>起始横间距(mm)</td>
			<td>
			<input type="text" name="profileppSegmentId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.startX}"/>" />
			<input type="hidden" name="profile.wellId" class="str readonly i290" value="" />
			</td>
			<td>起始纵间距(mm)</td>
			<td>
			<input type="text" name="profileWellId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.startY}"/>" />
			</td>
		</tr>
		
		<tr>
			<td>横间隔(mm)</td>
			<td>
			<input type="text" name="profileppSegmentId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.spaceX}"/>" />
			<input type="hidden" name="profile.wellId" class="str readonly i290" value="" />
			</td>
			<td>纵间隔(mm)</td>
			<td>
			<input type="text" name="profileWellId" class="ignore readonly i290" readonly="readonly" value="<s:property value="%{result.spaceY}"/>" />
			</td>
		</tr>
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
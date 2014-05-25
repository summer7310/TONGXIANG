<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支架</title>
</head>
<body>
<div class="property">
	<table class="Tinput_list">
		<tr><th colspan="2">支架的属性</th></tr>
			<s:if test="result.autoId != null"><s:hidden theme="simple" name="bracket.autoId" cssClass="int" value="%{result.autoId}"/></s:if>
			<s:hidden theme="simple" name="bracket.staticId" id="staticId" cssClass="int" value="%{result.staticId}"/>
		<tr>
			<td width="80px">建造单位</td>
			<td width="340px">			
			<s:textfield theme="simple" name="bracket.builder" cssClass="str i290" value="%{result.builder}"/>
			</td>
			<td width="80px">类 型</td>
			<td width="300px">
			<select name="bracket.ObjectType">
			<option value="1010503" <s:if test="result.ObjectType==0">selected</s:if>>支 架</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>材 料</td>
			<td>
			<select name="bracket.material">
			<option value ="0" <s:if test="result.material==0">selected</s:if>>金 属</option>
			<option value ="1" <s:if test="result.material==1">selected</s:if>>水 泥</option>
			<option value ="2" <s:if test="result.material==2">selected</s:if>>木 材</option>
			<option value ="3" <s:if test="result.material==3">selected</s:if>>塑 料</option>
			</select>
			</td>
			<td>数 量</td>
			<td>	
			<s:textfield theme="simple" name="bracket.numbers" id="bracket_numbers" cssClass="int i290" value="%{result.numbers}"/>
			</td>			
		</tr>
		<tr>
			<td>间 隔(mm)</td>
			<td>			
			<s:textfield theme="simple" name="bracket.bracketInterval" id="bracket_interval" cssClass="int i290" value="%{result.bracketInterval}"/>
			</td>
			<td>规格</td>
			<td>			
			<s:textfield theme="simple" name="bracket.bracketModel" id="bracket_model" cssClass="str i290" value="%{result.bracketModel}"/>
			<br/>注:长(mm)x宽(mm)x厚(mm)</td>	
		</tr>				
</table>
</div>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>

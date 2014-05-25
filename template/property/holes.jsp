<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="property">
	<table class="Tinput_list"> 
		<tr><th colspan="2"> </th></tr>
		<tr> 
			<td width="80px">电 缆</td>
			<td width="340px">
				<select id="cable">
					<s:iterator value="jsonResult">
						<option value ="<s:property value="%{staticId}"/>"><s:property value="%{name}"/></option>
					</s:iterator>
				</select></td> 
				<s:iterator value="jsonResult">
					<s:hidden theme="simple" name="%{staticId}"  value="%{voltageGrade}" cssClass="int" />
				</s:iterator>
		</tr>
		<tr>					
			<td width="80px">直 径(mm)</td> 
			<td width="340px"> 
				<select id="cableDiameter">
						<option value ="10">10</option>
						<option value ="20">20</option>
				</select>
			</td> 
		</tr> 
		
	</table>
	<div class="clear"></div>
</div>
</body>
</html>
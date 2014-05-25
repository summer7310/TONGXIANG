<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="0" width="100%">
	<tr>
		<th width="75%">已穿孔电缆</th><th width="25%">管孔号</th>
	</tr>
	<s:iterator value="wireDone">
		<tr>
			<td id="<s:property value="%{sid}"/>"><s:property value="%{name}"/></td>
			<td><s:property value="%{code}"/></td>
		</tr>
	</s:iterator>		
</table>
<table border="0" width="100%">
	<tr class="bortop">
		<th width="75%">未穿孔电缆</th><th width="25%"> </th>
	</tr>
	<s:iterator value="wireUndone">
		<tr>
			<td id="<s:property value="%{staticId}"/>"><s:property value="%{name}"/></td>
			<td> </td>
		</tr>
	</s:iterator>	
</table>
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
		<tr><th colspan="2">剖面信息</th></tr>
		<tr> 			
			<td width="80px">剖面编号</td>
			<td width="340px"><input name="profile[0].profileId" type="text" id="pnumber" class="int input i290"/></td>  
		</tr> 
		<tr> 
			<td width="80px">剖面长(mm)</td>
			<td width="340px"><input name="profile[0].profileWidth" type="text" id="plengthx" class="float input i290"/></td> 
			<td width="80px">剖面宽(mm)</td> 
			<td width="300px"><input name="profile[0].profileHigh" type="text" id="plengthy" class="float input i290"/></td> 
		</tr> 
	</table>
	<!--
	<div class="clear"></div>
		<table class="Tinput_list"> 
		<tr><th colspan="2">管孔信息</th></tr>
		<tr> 
			
			<td width="80px">行 数</td>
			<td width="340px"><input name="a" type="text" id="pholex" class="input i290 ignore" value="2"/></td> 
			<td width="80px">列数</td> 
			<td width="300px"><input name="b" type="text" id="pholey" class="input i290 ignore" value="3"/></td> 
		</tr> 
		<tr> 
			<td width="80px">起始横坐标</td>
			<td width="340px"><input name="c" type="text" id="pholex_start" class="input i290 ignore" value="10"/></td> 
			<td width="80px">埋深</td> 
			<td width="300px"><input name="d" type="text" id="pholey_start" class="input i290 ignore" value="10"/></td> 
		</tr> 
		<tr> 
			<td width="80px">横向间隔</td>
			<td width="340px"><input name="e" type="text" id="pholex_span" class="input i290 ignore" value="10"/></td> 
			<td width="80px">纵向间隔</td> 
			<td width="300px"><input name="f" type="text" id="pholey_span" class="input i290 ignore" value="10"/></td> 
		</tr>
		<tr> 
			<td width="80px">直径</td>
			<td width="340px"><input name="g" type="text" id="pholed" class="input i290 ignore" value="10"/></td>  
		</tr>		 		
	</table>
	-->
</div>
<script type="text/javascript">
Plugins.check.form();
</script>
</body>
</html>
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
		<tr><th colspan="2">管孔信息</th></tr>
		<tr> 			
			<td width="80px">行 数</td>
			<td width="340px"><input name="line" type="text" id="pholex" class="input i290 ignore"/></td> 
			<td width="80px">列 数</td> 
			<td width="300px"><input name="row" type="text" id="pholey" class="input i290 ignore"/></td> 
		</tr> 
		<tr> 
			<td width="80px">起始横间距(mm)</td>
			<td width="340px"><input name="startX" type="text" id="pholex_start" class="input i290"/></td> 
			<td width="80px">起始纵间隔(mm)</td> 
			<td width="300px"><input name="startY" type="text" id="pholey_start" class="input i290"/></td> 
		</tr> 
		<tr> 
			<td width="80px">行间距(mm)</td>
			<td width="340px"><input name="spaceX" type="text" id="pholex_span" class="input i290"/></td> 
			<td width="80px">列间距(mm)</td> 
			<td width="300px"><input name="spaceY" type="text" id="pholey_span" class="input i290"/></td> 
		</tr> 
		<tr> 
			<td width="80px">管孔直径(mm)</td>
			<td width="340px"><input name="diameter" type="text" id="pholed" class="input i290 ignore"/></td> 
			<td width="80px">起始编号</td> 
			<td width="300px"><input name="code" type="text" id="code" class="input i290 ignore"/></td> 
		</tr> 
	</table>
	<div class="clear"></div>
</div>
<script type="text/javascript">
Plugins.check.form();
</script>
</body>
</html>
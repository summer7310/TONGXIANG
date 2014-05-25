<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>剖面</title>
<style>
	.rmpoint{cursor:pointer;}
	.rmpoint:hover{text-decoration:underline;}
</style>
</head>
<body>
<div class="property">
	<table class="Tinput_list" id="points">
		
		
		<s:set name="start" value="1" />   
		<s:set name="end" value="%{points}" />   
		<s:bean name="org.apache.struts2.util.Counter" id="counter">
		        <s:param name="first" value="%{#start}" /><!-- 可以控制开始和结束 -->
		        <s:param name="last" value="%{#end}" />
		        <s:iterator>
		        	<tr><th colspan="1">测绘端点 </th><th colspan="1"><span class="rmpoint">删除</span></th></tr>  
		            <tr class="locate">
						<td width="80px">经 度</td>
						<td width="340px">			
						<s:textfield theme="simple" name="point<s:property/>x"   cssClass="float i290 pointx" value=""/>
						</td>
						<td width="80px">纬 度</td>
						<td width="300px">
						<s:textfield theme="simple" name="point<s:property/>y"  cssClass="float i290 pointy" value=""/> 
						</td>	
					</tr>
		        </s:iterator>
		 </s:bean>
	</table>
	<table>
		<tr><td width="80px"> </td>
			<td width="300px"><input type="button" id="addpoint" value="新增一个点">
			</td><td></td><td></td>
		</tr>
	</table>
	
</div>
<script type="text/javascript">
var surtmp = '<tr><th colspan="1">测绘端点</th><th colspan="1"><span class="rmpoint">删除</span></th></tr>'  
			+ '<tr>'
			+ '<td width="80px">经 度</td>'
			+ '<td width="340px">'
			+ '<input name="" type="text" class="float i290 pointx" value="" />'
			+ '</td><td width="80px">纬 度</td><td width="300px">'
			+ '<input name=""  type="text" class="float i290 pointy" value="" />'
			+ '</td></tr>';
			
$('#addpoint').click(function(){
	Plugins.dialog.fix();
	$('#points').append(surtmp);
	Plugins.check.form();
	rm();
});

rm();
function rm(){
	$('.rmpoint').click(function(){
		if($('.locate').size() <4){
			alert("端点不能小于三个");
			return ;
		}
		$(this).parents('tr').next().remove();
		$(this).parents('tr').remove();
	});	
}

</script>
<script type="text/javascript">Plugins.check.form();</script>
</body>
</html>
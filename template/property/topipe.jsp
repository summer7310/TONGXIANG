<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title></title>
</head>
<body>
<div class="property">
	<table class="Tinput_list"> 
		<tr><th colspan="2">选择管沟</th></tr>
		<tr>
			<td width="80px">操作段</td> 
			<td width="340px">
				<input type="text" name="topipeSegmentHaveWirecable" class="ignore readonly i290" readonly="readonly" />
				<s:hidden theme="simple" name="topipe.segmentHaveWirecable" cssClass="int" value=""/>
			</td>  
			
			<td width="80px">目标管沟</td>
			<td width="300px">
				<s:textfield theme="simple" name="bra" id="birds" cssClass="ignore i290" value=""/>
				<s:hidden theme="simple" name="topipe.wirecableBelongsSegment" cssClass="int" value=""/>
				<script type="text/javascript">
				$(function() {
					$( "#birds" ).autocomplete({
						source		: "getSearchPPInfo.action",
						minLength	: 2,
						select		: function( event, ui ) {
							$('input[name="topipe.wirecableBelongsSegment"]').val(ui.item.id);
						}
					});
				});
				</script>
			</td>

		</tr> 	
	</table>
	<div class="clear"></div>
</div>
</body>
</html>
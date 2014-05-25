<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备附件</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>">
</head>
<body>
<div class="">
<ul class="atttab">
		<li rel="1" <s:if test="%{type==\"photo\"}">class="current"</s:if>>照片</li>
		<li rel="2" <s:if test="%{type==\"auxi\"}">class="current"</s:if>>附件</li>
</ul>
</div>
<div class="clear"></div>
<div class="atttab_div">
<div class="attachment pic atttab_1" <s:if test="%{type==\"auxi\"}">style="display:none;"</s:if>>
	<input type="file" name="upFile" id="file_upload" />
	<ul>
	<s:iterator value="photos" var="photo">
	  
		<li>
			<span id="<s:property value="%{autoId}"/>" class="photo">X</span>
			<a href="uploads/<s:property value="%{photoPath}"/>" rel="[gall1]" title="<s:property value="%{name}"/>">
			<img src="uploads/<s:property value="%{previewPhotoPath}"/>" alt="<s:property value="%{name}"/>" width="160"   /></a>
		</li>

	</s:iterator>	
		
	</ul>
	<div class="clear"></div>
</div>
<div class="attachment atttab_2" <s:if test="%{type==\"photo\"}">style="display:none;"</s:if>>
	<input type="file" name="upFile2" id="file_upload2" />
	<ul>
	<s:iterator value="auxi" var="aux">
		<li>
			<span id="<s:property value="%{autoId}"/>" class="auxi">X</span>
			[<s:property value="%{auxiliaryType}"/>]<a target="_blank" href="uploads/<s:property value="%{auxiliaryPath}"/>"  ><s:property value="%{name}"/></a>
			 
		</li>
	</s:iterator>	
	</ul>
	<div class="clear"></div>
</div>		
</div>
<script type="text/javascript">
	$(document).ready(function(){
	  $('.pic').find('a').foxibox();
	  Plugins.slide_attachment.init();
	  Plugins.init_tabs(".atttab", function(){});
	});
</script>
<script type="text/javascript">
var photoSId = <s:property value="%{sid}" />;
    $(function() {
        $('#file_upload').uploadify({
        	'fileObjName'   : "upFile",
        	'fileTypeExts' : '*.gif; *.jpg',
        	'formData'     	: {
				'staticId' 	: <s:property value="%{sid}" />,
				'type'		: 'photo'
			},
            'swf'      		: Global.option.URL +　'/js/uploadify.swf',
            'uploader' 		: Global.option.URL + '/uploadsPhotos.action',
            'onQueueComplete' : function(queueData) {
            	Plugins.slide_attachment.refresh(<s:property value="%{sid}" />, 'photo');
            }
        });
    });
</script>

<script type="text/javascript">
 
    $(function() {
        $('#file_upload2').uploadify({
        	'fileObjName'   : "upFile",
        	'buttonText'	: "上传新文件",
        	'formData'     	: {
				'staticId' 	: <s:property value="%{sid}" />,
				'type'		: 'auxiliary'
			},
            'swf'      		: Global.option.URL +　'/js/uploadify.swf',
            'uploader' 		: Global.option.URL + '/uploadsPhotos.action',
            'onQueueComplete' : function(queueData) {
            	Plugins.slide_attachment.refresh(<s:property value="%{sid}" />, 'auxi');
            }
        });
    });
</script> 
</body>
</html>
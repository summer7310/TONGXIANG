<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录系统</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>">
<link href="style/base.css" rel="stylesheet" type="text/css"/>
<style>
body{background:#f2f2f2}
#login-logo{padding:10px 0;text-align:center;background:#3B5998;border-top-left-radius:5px;border-top-right-radius:5px}
#login-logo img{margin:0 auto;}
#login-box{background:#fff;border:1px #ccc solid;width:340px;margin:0 auto;border-radius:5px;margin-top:80px;}
#login-box{
	box-shadow: 0px 2px 6px #c0c0c0;-webkit-box-shadow: 0px 2px 6px #c0c0c0;-moz-box-shadow: 0px 2px 6px #c0c0c0;
}
#login-box table{margin:15px 20px;}
#login-box table td{padding:5px 0}
#login-box table .tdLabel{width:60px;}
#login-box input[type="text"]{width:220px;}
#login-box input[type="password"]{width:220px;}
#login-box input[type="submit"]{width:70px;padding:5px 0}
#login-box .errorMessage{color:red;padding-left:60px;}
</style>
</head>
<body>
<div id="login-box" style="padding:15px 0">
注销登录成功，您可以请选择 <a href="login.action?mode=login">重新登录</a>
</div>
</body>
</html>
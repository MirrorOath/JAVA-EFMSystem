<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link href="${pageContext.request.contextPath }/css/unit.css" rel="stylesheet" type="text/css">
<title>Admin controller</title>
</head>
<body>
	<div id="navigation">
				<ul>
				<li class="selected"></li>
				<li><a href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/getAllUsers.action">管理用户</a></li>
                <li><a href="${pageContext.request.contextPath }/admin/CopyMeter.jsp">抄电表</a></li>
				<li></li>
				</ul>
	</div>
	<h1>Welcome to the admin control</h1>
</body>
</html>
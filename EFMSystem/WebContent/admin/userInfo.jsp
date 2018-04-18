<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link href="${pageContext.request.contextPath }/css/unit.css" rel="stylesheet" type="text/css">
<title>userInfo</title>
</head>
<body>
	<div id="navigation">
				<ul>
				<li class="selected"></li>
				<li><a href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a></li>
				<li><a href="${pageContext.request.contextPath }/admin/control.jsp">Admin首页</a></li>
				<li></li>
				</ul>
	</div>
	<div>
		<a href="${pageContext.request.contextPath }/admin/getAllUsers.action">刷新</a>
		<table>
			<tr>
				<th>用户ID</th><th>用户名</th><th>用户密码</th><th>用户年龄</th><th>用户注册日期</th>
			</tr>
			<c:forEach var="user" items="${users }" varStatus="sta">
				<tr>
					<td>${user.id }</td><td>${user.user_name }</td><td>${user.password }</td><td>${user.age }</td><td>${user.date }</td>
					<td><a href="${pageContext.request.contextPath }/admin/delUser.action?userId=${user.id }" >删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
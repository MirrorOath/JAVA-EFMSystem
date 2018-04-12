<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Info</title>
<link
	href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"></script>
<script
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
	signInRt:${signInRt }
	</br>

	<a href="${pageContext.request.contextPath }/user/searchRecords.action">searchRecords</a>
	</br>

	<c:forEach var="Record" items="${Records }" varStatus="sta">
		<div>
			<p>****************************************************</br>
			Record.id:${Record.id }</br>
			Record.rcd_time:${Record.rcd_time }</br>
			Record.cur_used:${Record.cur_used }</br>
			Record.user_id:${Record.user_id }</br>
            ****************************************************</p>
		</div>
	</c:forEach>

	<div>
		userInfo:</br> id:${userInfo.id }</br> user_name:${userInfo.user_name }</br>
		user_id:${userInfo.user_id }</br> password:${userInfo.password }</br>
		age:${userInfo.age }</br> address:${userInfo.address }</br> role:${userInfo.role }</br>
		tactics:${userInfo.tactics }</br>
	</div>

</body>
</html>
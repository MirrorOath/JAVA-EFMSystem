<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
<link
	href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

</head>
<body>
	<script src="${pageContext.request.contextPath }/easyUI/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<a href="${pageContext.request.contextPath }/admin/getAllUsers.action">searchRecords</a>
	</br>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-2 column">
				<div class="panel-group" id="panel-774231">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title" data-toggle="collapse"
								data-parent="#panel-774231" href="#panel-element-873825">Collapsible
								Group Item #1</a>
						</div>
						<div id="panel-element-873825" class="panel-collapse in">
							<div class="panel-body">Anim pariatur cliche...</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title collapsed" data-toggle="collapse"
								data-parent="#panel-774231" href="#panel-element-906999">Collapsible
								Group Item #2</a>
						</div>
						<div id="panel-element-906999" class="panel-collapse collapse">
							<div class="panel-body">Anim pariatur cliche...</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 column">
				<table class="table">
					<thead>
						<tr>
							<th>id</th>
							<th>user_name</th>
							<th>user_id</th>
							<th>password</th>
							<th>age</th>
							<th>address</th>
							<th>role</th>
							<th>tactics</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users }" varStatus="sta">
							<c:if test="${sta.index%2==0}">
								<tr class="success">
							</c:if>
							<c:if test="${sta.index%2==1}">
								<tr class="info">
							</c:if>
							<td>${user.id }</td>
							<td>${user.user_name }</td>
							<td>${user.user_id }</td>
							<td>${user.password }</td>
							<td>${user.age }</td>
							<td>${user.address }</td>
							<td>${user.role }</td>
							<td>${user.tactics }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-4 column">
				<button type="button" class="btn btn-default">按钮</button>
			</div>
		</div>
	</div>
</body>
</html>
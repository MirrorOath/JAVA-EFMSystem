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

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-2 column">
				<div class="panel-group" id="panel-862198">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title collapsed" data-toggle="collapse"
								data-parent="#panel-862198" href="#panel-element-803306">账单</a>
						</div>
						<div id="panel-element-803306" class="panel-collapse collapse">
							<div class="panel-body">
								<a
									href="${pageContext.request.contextPath }/user/searchRecords.action">预算计费</a>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a class="panel-title collapsed" data-toggle="collapse"
								data-parent="#panel-862198" href="#panel-element-471267">个人信息</a>
						</div>
						<div id="panel-element-471267" class="panel-collapse collapse">
							<div class="panel-body">修改个人信息</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6 column">
				<div class="jumbotron">

					<h4>
						计费方式1(谷峰计费)费用:${tactics1 }</br> 计费方式2(普通计费)费用:${tactics2 }</br>
					</h4>

					<div>
						<table class="table">
							<thead>
								<tr>
									<!-- <th>Record.id</th> -->
									<th>抄表时间</th>
									<th>抄表值/度电</th>
									<!-- <th>Record.user_id</th> -->
								</tr>
							</thead>
							<c:forEach var="Record" items="${Records }" varStatus="sta">
								<tbody>
									<c:if test="${sta.index%2==0}">
										<tr class="success">
									</c:if>
									<c:if test="${sta.index%2==1}">
										<tr class="info">
									</c:if>
									<!-- <td>${Record.id }</td> -->
									<td>${Record.rcd_time }</td>
									<td>${Record.cur_used }</td>
									<!-- <td>${Record.user_id }</td> -->
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-4 column">

				<div>
					<h2>userInfo</h2>
					<p>
						id:${userInfo.id }</br> user_name:${userInfo.user_name }</br>
						user_id:${userInfo.user_id }</br> password:${userInfo.password }</br>
						age:${userInfo.age }</br> address:${userInfo.address }</br>
						role:${userInfo.role }</br> tactics:${userInfo.tactics }</br>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
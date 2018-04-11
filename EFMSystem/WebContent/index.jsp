<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EFMSystem</title>
<link
	href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"></script>
<script
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>

<body>
	<a href="${pageContext.request.contextPath }/test/hi.action">ActionTest</a></br>
	<%-- <a href="${pageContext.request.contextPath }/user/register.action">ActionTest</a></br> --%>
	${test }


	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/user/register.action"
		method="post">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
			<div class="col-sm-10">
				<input name="user_name" class="easyui-validatebox form-control"
					type="text" required="true" validType="userName" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">密码</label>
			<div class="col-sm-10">
				<input name="password" class="form-control" type="password"
					required="true" />
			</div>
		</div>
		<%-- <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">年龄</label>
			<div class="col-sm-10">
				<input name="age" class="form-control" type="text" required="true" />
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">注册</button>
			</div>
		</div>
	</form>

	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/user/signIn.action"
		method="post">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
			<div class="col-sm-10">
				<input name="user_name" class="easyui-validatebox form-control"
					type="text" required="true" validType="userName" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">密码</label>
			<div class="col-sm-10">
				<input name="password" class="form-control" type="password"
					required="true" />
			</div>
		</div>
		<%-- <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">年龄</label>
			<div class="col-sm-10">
				<input name="age" class="form-control" type="text" required="true" />
			</div>
		</div> --%>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">登录</button>
			</div>
		</div>
	</form>

    <div>
    registerRt:${registerRt }</br>
    </div>

</body>

</html>
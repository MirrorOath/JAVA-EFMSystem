<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	name="viewport" content="width=device-width, initial-scale=1.0">
<Link href="${pageContext.request.contextPath }/css/unit.css"
	rel="stylesheet" type="text/css">
<title>Welcome</title>
<link
	href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

</head>
<body>
	<script src="${pageContext.request.contextPath }/easyUI/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

	<div id="navigation">
		<ul>
			<li class="selected"></li>
			<li><a
				href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a></li>
			<li><a
				href="${pageContext.request.contextPath }/admin/control.jsp">Admin首页</a></li>
			<li></li>
		</ul>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form class="form-horizontal" role="form"
					action="${pageContext.request.contextPath}/admin/copyMeter.action"
					method="post">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input name="user_name" class="easyui-validatebox form-control"
								type="text" required="true" validType="userName" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">日期</label>
						<div class="col-sm-10">
							<input name="dateStr" class="form-control" type="text"
								required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">抄表数</label>
						<div class="col-sm-10">
							<input name="copyNumber" class="form-control" type="text"
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
							<button type="submit" class="btn btn-default">录入</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div>
		copyMeterRt:${copyMeterRt }</br>
	</div>

</body>
</html>
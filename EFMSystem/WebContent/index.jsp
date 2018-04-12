<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>

	<div></div>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-8 column">
				<div class="jumbotron">
					<%
					    if (session.getAttribute("registerRt") != null) {
					        out.println("registerRt:" + session.getAttribute("registerRt") + "</br>");
                            session.removeAttribute("registerRt");
					    }
					    if (session.getAttribute("signInRt") != null) {
					        out.println("signInRt:" + session.getAttribute("signInRt") + "</br>");
                            session.removeAttribute("signInRt");
					    }
					%>
					<h1>Hello, world!</h1>
					<p>This is a template for a simple marketing or informational
						website. It includes a large callout called the hero unit and
						three supporting pieces of content. Use it as a starting point to
						create something more unique.</p>
					<p>
						<a class="btn btn-primary btn-large" href="#">Learn more</a>
					</p>
				</div>
			</div>
			<div class="col-md-4 column">
				<div class="tabbable" id="tabs-421777">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-836193" data-toggle="tab">注册</a></li>
						<li><a href="#panel-312880" data-toggle="tab">登录</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-836193">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath}/user/register.action"
								method="post">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
									<div class="col-sm-10">
										<input name="user_name"
											class="easyui-validatebox form-control" type="text"
											required="true" validType="userName" />
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">密码</label>
									<div class="col-sm-10">
										<input name="password" class="form-control" type="password"
											required="true" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">注册</button>
									</div>
								</div>
							</form>
						</div>
						<div class="tab-pane" id="panel-312880">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath}/user/signIn.action"
								method="post">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
									<div class="col-sm-10">
										<input name="user_name"
											class="easyui-validatebox form-control" type="text"
											required="true" validType="userName" />
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">密码</label>
									<div class="col-sm-10">
										<input name="password" class="form-control" type="password"
											required="true" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-default">登录</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
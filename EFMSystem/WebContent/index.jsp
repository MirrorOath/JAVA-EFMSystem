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
<body background="image/1.jpg" style="background-size:cover" onload="conBase()" >
	<script src="${pageContext.request.contextPath }/easyUI/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
    <script
        src="${pageContext.request.contextPath }/js/index.js"></script>

    <jsp:include page="user/util.jsp" />
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span><span
							class="icon-bar"></span><span class="icon-bar"></span><span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a>
				</div>
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a
							href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=login">${uname }${userInfo.userName }</a>
						</li>
						<li><a
							href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=register">${unameNext }</a>
						</li>
						<li><a
							href="${pageContext.request.contextPath }/user/more.jsp">更多</a></li>
						<li><a
							href="${pageContext.request.contextPath }/user/aboutUs.jsp">关于我们</a>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">更多<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a
									href="#"
									target=_blank>查看个人信息</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-left" role="search"
						action="#"
						method="post">
						<div class="form-group">
							<input class="form-control" type="text" maxlenght=100
								name="commodityName" />
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>
				</div>
				</nav>
			</div>
		</div>
	</div>
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
					<h2>
						<b>居民电费缴费管理系统</b>
					</h2>
					<p>
						在此处</br>选择更优的计费方式</br>事实查询您的扣费情况</br>你可以获取您的最新抄表记录
					</p>
					<p>
						<a class="btn btn-primary btn-large" href="#">了解更多</a>
					</p>
				</div>
			</div>
			<div class="col-md-4 column">
				<div class="tabbable" id="tabs-421777">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-836193" data-toggle="tab">注册</a></li>
						<li><a href="#panel-312880" data-toggle="tab">用户登录登录</a></li>
                        <li><a href="#panel-312881" data-toggle="tab">管理员登录</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-836193">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath}/user/register.action"
								method="post">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">账号</label>
									<div class="col-sm-10">
										<input name="userName"
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
										<input name="userName"
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
                        <div class="tab-pane" id="panel-312881">
                            <form class="form-horizontal" role="form"
                                action="${pageContext.request.contextPath}/user/adminSignIn.action"
                                method="post">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
                                    <div class="col-sm-10">
                                        <input name="userName"
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
                                        <button type="submit" class="btn btn-default">管理员登录</button>
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
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

<body background="../image/back.jpg" style="background-size: cover">
	<script src="${pageContext.request.contextPath }/easyUI/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/Homepage.js"></script>

	<jsp:include page="util.jsp" />
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
								<li><a href="#" target=_blank>查看个人信息</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-left" role="search" action="#"
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
			<div class="col-md-12 column">

				<div class="tabbable" id="tabs-421777">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-836193" data-toggle="tab">抄表记录</a></li>
						<li><a href="#panel-312880" data-toggle="tab">缴费记录</a></li>
						<li><a href="#panel-312881" data-toggle="tab">查询单日电表信息</a></li>
						<li><a href="#panel-312882" data-toggle="tab">更新用户信息</a></li>
						<li><a href="#panel-312883" data-toggle="tab">在线充值</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-836193">
							<div>
								<p>
									<a
										href="${pageContext.request.contextPath }/user/searchRecords.action">刷新</a>
								</p>

								<p>
									谷峰计费: 22:00--6:00 1元/度电 6:00--22:00 2元/度电 </br> 普通计费: 全天2元/度电<br />
									每月一号抄上月电表
								</p>

								<table class="table">
									<thead>
										<tr>
											<!-- <th>Record.id</th> -->
											<th>月份</th>
											<th>当月用量</th>
											<!-- 			<th>电费使用量</th> -->
											<!-- <th>Record.user_id</th> -->
										</tr>
									</thead>
									<c:forEach var="monthUsed" items="${monthsUsed }"
										varStatus="sta">
										<tbody>
											<c:if test="${sta.index%2==0}">
												<tr class="success">
											</c:if>
											<c:if test="${sta.index%2==1}">
												<tr class="info">
											</c:if>
											<!-- <td>${Record.id }</td> -->
											<td>${monthUsed.year + 1900 }-${monthUsed.month }</td>
											<td>${monthUsed.used }</td>
											<%-- 		<td>${Record.cost }</td> --%>
											<!-- <td>${Record.user_id }</td> -->
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="tab-pane" id="panel-312880">
							<div>
								<p>
									<a href="javascript:getBillings()">刷新</a>
								</p>

								<div id="billings"></div>
							</div>
						</div>
                        <div class="tab-pane" id="panel-312881">
                            <div>
                                
                                <div class="form-group">
                                    <input class="form-control" type="date" maxlenght=100
                                        id="forDate" />
                                </div>
                                <button onclick="javascript:forDate()"
                                    class="btn btn-default">查询</button>
                                <div id="forDateOut"></div>
                            </div>
                        </div>
						<div class="tab-pane" id="panel-312883">
							<div>
								<img alt="Error" src="../image/pay.png">
								<div class="form-group">
									<input class="form-control" type="text" maxlenght=100
										id="Number" />
								</div>
								<button type="submit" onclick="javascript:pushMoney()"
									class="btn btn-default">充值</button>
							</div>
						</div>
						<div class="tab-pane" id="panel-312882">
							<div>
								<h2>当前用户信息</h2>

								<!--                                 <p>
                                    <a href="javascript:getUserInfo()">刷新</a>
                                </p> -->
								<form class="form-horizontal" role="form"
									action="${pageContext.request.contextPath}/user/updateUserInfo.action"
									method="post">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">账号</label>
										<div class="col-sm-9">
											<input name="userName"
												class="easyui-validatebox form-control" type="text"
												required="true" validType="userName" readonly="true"
												value="${userInfo.userName }" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">原密码</label>
										<div class="col-sm-9">
											<input name="OldPassword" class="form-control"
												type="password" required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">新密码</label>
										<div class="col-sm-9">
											<input name="password" class="form-control" type="password"
												required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">电话</label>
										<div class="col-sm-9">
											<input name="tell" class="form-control" type="password"
												required="true" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">地址</label>
										<div class="col-sm-9">
											<input name="address" class="form-control" type="text"
												required="true" value="${userInfo.address }" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">余额</label>
										<div class="col-sm-9">
											<input name="money" class="form-control" type="text"
												required="true" id="lastMoney" value="${userInfo.money }" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">计费方式</label>
										<div class="col-sm-9">
											<input name="tactics" class="form-control" type="text"
												required="true" value="${userInfo.tactics }" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-default">提交修改</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
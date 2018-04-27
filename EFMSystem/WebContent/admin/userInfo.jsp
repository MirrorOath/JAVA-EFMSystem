<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link href="${pageContext.request.contextPath }/css/unit.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui/demo/demo.css">
<script type="text/javascript" src="../jquery-easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui/plugins/jquery.datagrid.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="http://www.w3cschool.cc/try/jeasyui/jquery.edatagrid.js"></script>
<style type="text/css">
form {
	margin: 0;
	padding: 0;
}

.dv-table td {
	border: 0;
}

.dv-table input {
	border: 1px solid #ccc;
}
</style>
<script type="text/javascript">
                $(function () {
                    $('#dg').edatagrid({
                        url: '../admin/easyUIGetUsers.action',
                        saveUrl: '../admin/easyUISaveUser.action',
                        updateUrl: '../admin/easyUIUpdateUser.action',
                        destroyUrl: '../admin/easyUIDelUser.action'
                    });
                });
            </script>
<title>管理用户</title>
</head>

<body>
    <div id="navigation">
        <ul>
            <li class="selected"></li>
            <li><a
                href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/userInfo.jsp">管理用户</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/meter.jsp">管理抄表记录</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/billings.jsp">管理账单</a></li>
        </ul>
    </div>

	<div>
		<div class="demo-info" style="margin-bottom: 10px">
			<div class="demo-tip icon-tip">&nbsp;</div>
			<div>双击进行修改.</div>
		</div>

		<table id="dg" title="用户信息" style="width: 700px; height: 250px"
			toolbar="#toolbar" pagination="true" idField="id" rownumbers="true"
			fitColumns="true" singleSelect="true">
			<thead>
				<tr>
                    <th field="id" width="50"
                        editor="{type:'validatebox',options:{readonly:true}}">用户id</th>
					<th field="userName" width="50"
						editor="{type:'validatebox',options:{required:true}}">用户名</th>
					<th field="password" width="50"
						editor="{type:'validatebox',options:{required:true}}">密码</th>
                    <th field="tell" width="50"
                        editor="{type:'validatebox',options:{required:true}}">电话</th>
<!--                     <th field="money" width="50"
                        editor="{type:'validatebox',options:{required:true}}">余额</th>
                    <th field="isUnusually" width="50"
                        editor="{type:'validatebox',options:{required:true}}">是否异常</th>
					<th field="tactics" width="50" 
						editor="{type:'validatebox',options:{required:true}}">计费方式</th> -->
					<th field="address" width="50"
						editor="{type:'validatebox',options:{required:true}}">地址</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="javascript:$('#dg').edatagrid('addRow')">添加</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a> <a
				href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"
				onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a> <a
				href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true"
				onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
		</div>
	</div>
</body>

</html>
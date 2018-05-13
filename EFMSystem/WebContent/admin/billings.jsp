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

<script src="../jexcel/Blob.js"></script>
<script src="../jexcel/FileSaver.js"></script>
<script src="../jexcel/tableExport.js"></script>
<script src="../js/unit.js"></script>
<script type="text/javascript">
    $(function() {
        $('#dg').edatagrid({
            url : '../easyUI/easyUIGetBillings.action',
            saveUrl : '../easyUI/easyUISaveBilling.action',
            updateUrl : '../easyUI/easyUIUpdateBilling.action',
            destroyUrl : '../easyUI/easyUIDelBilling.action'
        });
    });
</script>
<title>管理账单</title>
</head>

<body>
    <div id="navigation">
        <ul>
            <li class="selected"></li>
            <li><a
                href="${pageContext.request.contextPath }/user/rg_lg_do.action?rorl=index">首页</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/billings.jsp">管理账单</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/userInfo.jsp">管理用户</a></li>
            <li><a
                href="${pageContext.request.contextPath }/admin/meter.jsp">管理抄表记录</a></li>
        </ul>
    </div>

    <div>
        <div class="demo-info" style="margin-bottom: 10px">
            <div class="demo-tip icon-tip">&nbsp;</div>
            <div>双击进行修改.</div>
        </div>

        <table id="dg" title="账单信息" style="width: 1000px; height: 700px"
            toolbar="#toolbar" pagination="true" idField="id"
            rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="userId" width="50"
                        editor="{type:'validatebox',options:{readonly:true}}">用户id</th>
                    <th field="userName" width="50"
                        editor="{type:'validatebox',options:{readonly:true}}">用户名</th>
                    <th field="tactics" width="50"
                        editor="{type:'validatebox',options:{required:true}}">计费方式</th>
                    <th field="curUsed" width="50"
                        editor="{type:'validatebox',options:{required:true}}">用量</th>
                    <th field="cost" width="50"
                        editor="{type:'validatebox',options:{required:true}}">费用</th>
                    <th field="exCost" width="50"
                        editor="{type:'validatebox',options:{required:true}}">滞纳金</th>
                    <th field="isPaid" width="50"
                        editor="{type:'validatebox',options:{required:true}}">付费状态</th>
                    <th field="date" width="50"
                        editor="{type:'validatebox',options:{required:true}}">账单生成日</th>
                </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add"
                plain="true"
                onclick="javascript:$('#dg').edatagrid('addRow')">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove"
                plain="true"
                onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save"
                plain="true"
                onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-undo"
                plain="true"
                onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
        </div>
    </div>

    <div>
        <h2>付费状态: 1:已付费 0:未付费</h2>
    </div>

    <div>
        <a href="javascript:getBillingTable()">生成可输出excle表格</a>
    </div>

    <div id="dudutable"></div>

    <div id="export">
        <a data-type="xls" href="">导出excel</a>
    </div>


    <script>
        var $exportLink = document.getElementById('export');
        $exportLink.addEventListener('click', function(e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('billings', 'out', e.target
                        .getAttribute('data-type'));
            }
        }, false);
    </script>
</body>
</html>
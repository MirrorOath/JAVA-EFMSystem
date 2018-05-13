
function checkpwd(box){
	if(box.value == null || box.value.length == 0)
		document.getElementsByName("subbutton")[0].disabled = true;
	else
		document.getElementsByName("subbutton")[0].disabled = false;
}

function getBillingTable() {

    $.ajax({
        url : "../easyUI/easyUIGetBillings.action",
        type : "post",
        dateType : "json",
        success : function(data) {
            if (data.success == false)
                alert("查询失败，请检查抄表记录");
            else {
                str = ""
                str += "<table id='billings'><thead><tr>" 
                        + "<th>主键</th>" 
                        + "<th>用户ID</th>" 
                        + "<th>计费方式</th>" 
                        + "<th>抄表用量</th>" 
                        + "<th>费用</th>" 
                        + "<th>额外费用</th>" 
                        + "<th>账单生成日期</th>" 
                        + "<th>是否已付费</th>" 
                        + "</tr></thead><tbody>";
                $.each(data, function(n, index){
                    str += "<tr>"
                    + "<td>" + index.id + "</td>"
                    + "<td>" + index.userId + "</td>"
                    + "<td>" + index.tactics + "</td>"
                    + "<td>" + index.curUsed + "</td>"
                    + "<td>" + index.cost + "</td>"
                    + "<td>" + index.exCost + "</td>"
                    + "<td>" + index.date + "</td>"
                    + "<td>" + index.isPaid + "</td>"
                    + "</tr>";
                });
                str += "</tbody></table>";
                document.getElementById("dudutable").innerHTML = str;
            }
        }
    })
}

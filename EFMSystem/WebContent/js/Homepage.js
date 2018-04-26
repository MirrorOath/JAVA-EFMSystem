function getBillings() {
    $.ajax({
        url : "../admin/getBillings.action",
        type : "post",
        dateType : "json",
        success : function(data) {
            if (data == null)
                alert("无信息或未登录")
            var str = "<div class='panel-group' id='panel-92507'>";
            $.each(data, function(n, index) {
                str += "<div class='panel panel-default'>"
                        + "<div class='panel-heading formStyle'>"
                        + "<a class='panel-title collapsed orderIdStyle' "
                        + "data-toggle='collapse' "
                        + "data-parent='#panel-92507' "
                        + "href='#panel-element-" + index.id
                        + "' >账单号:" + index.id
                        + "</a> </div><div id=panel-element-" + index.id
                        + " class='panel-collapse collapse' >"
                        + "<div class='panel-body'>" 
                        + "用户名:   " + index.userName + "<br/>"
                        + "计费方式:  " + (index.tactics == 2 ? "普通计费" : "谷峰计费") + "<br/>"
                        + "当月用量:  " + index.curUsed + "<br/>"
                        + "账单费用:  " + index.cost + "<br/>"
                        + "滞纳金(每天千分之一):   " + index.exCost + "<br/>"
                        + "账单生成日期: " + index.date + "<br/>"
                        + "是否已付费: " + (index.isPaid == 1 ? "是" : ("否" 
                        + "<br/><a href='javascript:payNow(" 
                        + index.id + "," + index.exCost + ")'>现在付费</a>")
                        ) + "<br/>"
                        + "</div></div></div>"
            });
            str += "</div>";
            document.getElementById("billings").innerHTML = str;

        }
    })
}

function payNow(id, exCost) {
    $.ajax({
        url : "../admin/payNow.action",
        type : "post",
        data : {
            id : id,
            exCost : exCost
        },
        dateType : "json",
        success : function(data) {
            if (data == false)
                alert("扣费失败");
            else
                alert("扣费成功，请刷新查看");
        }
    })
}

function pushMoney() {
    var psel = document.getElementById("Number").value;
    $.ajax({
        url : "../admin/pushMoney.action",
        type : "post",
        data : {
            Number : psel
        },
        dateType : "json",
        success : function(data) {
            if (data == false)
                alert("充值失败");
            else {
                alert("充值成功");
            }
        }
    })
}


function getUserInfo() {
    var psel = document.getElementById("Number").value;
    $.ajax({
        url : "../admin/getUserInfo.action",
        type : "post",
        dateType : "json",
        success : function(data) {
        }
    })
}

function getMoney() {
    $.ajax({
        url : "../user/getMoney.action",
        type : "post",
        dateType : "json",
        success : function(data) {
            document.getElementById("histroyP").innerHTML = "历史充值"
                    + data.history + "元";
            document.getElementById("thismonthP").innerHTML = "当月使用"
                    + data.thismonthcost + "元";
            document.getElementById("cur_moneyP").innerHTML = "余额"
                    + data.cur_money + "元";
        }
    })
}

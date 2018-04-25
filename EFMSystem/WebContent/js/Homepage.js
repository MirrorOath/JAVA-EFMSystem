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
                        + "用户id" + index.userId + "<br/>"
                        + "账单费用" + index.cost + "<br/>"
                        + "账单时间戳" + index.date + "<br/>"
                        + "是否已付费: " + (index.ispaid == 1 ? "是" : "否") + "<br/>"
                        + "</div></div></div>"
            });
            str += "</div>";
            document.getElementById("billings").innerHTML = str;

        }
    })
}

function pushMoney() {
    var psel = document.getElementById("Number").value;
    $.ajax({
        url : "../user/pushMoney.action",
        type : "post",
        data : {
            Number : psel
        },
        dateType : "json",
        success : function(data) {
            if (data == false)
                alert("充值失败");
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

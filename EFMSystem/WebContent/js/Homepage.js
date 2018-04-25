function getMEBill() {
    $
            .ajax({
                url : "../user/MEBilling.action",
                type : "post",
                dateType : "json",
                success : function(data) {
                    if (data.month == 0)
                        top.location = '../index.jsp'
                    document.getElementById("monthP").innerHTML = "当前"
                            + data.month + "月";
                    document.getElementById("usedP").innerHTML = "当月使用"
                            + data.used + "度电";

                    document.getElementById("tacticsP").innerHTML = data.tactics == 2 ? "计费标准: 普通计费"
                            : "计费标准: 峰谷计费";
                    document.getElementById("costP").innerHTML = "当月费用"
                            + data.cost;
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

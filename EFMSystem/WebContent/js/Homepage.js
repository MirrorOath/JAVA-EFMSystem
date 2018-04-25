function getMEBill() {

    var id = $
            .ajax({
                url : "../user/MEBilling.action",
                data : {},
                type : "post",
                dateType : "json",
                success : function(data) {
                    // if(data.month == 0)
                    // top.location='../index.jsp'
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
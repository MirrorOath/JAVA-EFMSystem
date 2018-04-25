function conBase() {
    $.ajax({
        url : "special/conBase.action",
        data : {},
        type : "post",
        dateType : "json",
        success : function(data) {
        }
    })
}

function sendAjax() {
    $.ajax({
        url : "test/test.action",
        data : {
            "id" : 1
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            alert(data.id);
            alert(data.name);
            alert(data.thing);
            alert(data.date);
        }
    });
}

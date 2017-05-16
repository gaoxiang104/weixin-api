$(function () {
    $('#wx_code').text(code);

    $.ajax({// 加载wxConfig参数
        type: 'post',
        url: "/weixin-api/user/authGetOpenid.ctrl",
        data: code,
        contentType: "application/json",
        async: true,
        dataType: 'json',
        success: function (data) {
            var object = data.object;
            var error = data.error;
            if (typeof (error) == "undefined") {
                $('#openId').text(object);
            } else {
                $('#openId').text("error:" + error);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error:" + XMLHttpRequest + " " + textStatus + " " + errorThrown);
        }
    });

});
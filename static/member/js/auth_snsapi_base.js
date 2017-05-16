$(function () {

    $.ajax({// 加载wxConfig参数
        type: 'post',
        url: "/weixin-api/user/authGetUserInfo.ctrl",
        data: code,
        contentType: "application/json",
        async: true,
        dataType: 'json',
        success: function (data) {
            var object = data.object;
            var error = data.error;
            if (typeof (error) == "undefined") {
                $('#head_img_div').attr('src', object.headimgurl);
                $('#nickname').text(object.nickname);
                $('#sex').text(object.sex==1?'男':'女');
                $('#country').text(object.country);
                $('#diqu').text(object.province+' '+object.city);
                
            } else {
                console.log(error);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error:" + XMLHttpRequest + " " + textStatus + " " + errorThrown);
        }
    });

});
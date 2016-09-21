$(function () {
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });
    var ue = UE.getEditor('editor');
    $("#send").bind('click', function () {
        var content = ue.getContent()
        console.debug(content)
    })
    $("#login-btn").bind('click', function () {
        var username = $('#username').val()
        var password = $('#password').val()
        $.post("/user/login", {username: username, password: password}, function (result) {
            if (result.success) {
                alert("登录成功")
                window.loginUser = result.result
            } else {
                alert("登录失败")
            }
        })
    })

})

function initChats() {
    $.get("/user/getFriends",function(){

    })
}
function initChatGroups() {
    $.get("/user/getChatGroups",function(){

    })
}
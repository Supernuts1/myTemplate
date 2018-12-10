/*<![CDATA[*/
$(function () {
    var validateUsername = false;//验证结果
    var validatePassword1 = false;
    var validatePassword2 = false;
    var updateFalg = false;//修改的验证标志
    //判断用户名是否存在
    $("#username").blur(function () {
        $.ajax({
            type: "post",
            url: "/user/userExis",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            dataType: "json",
            data: {
                "username": $.trim($("#username").val())
            },
            success: function (result) {
                result = $.trim(result);
                if (result == "" || result == null) {
                    $("#usernamelabel").text("");
                    validateUsername = true;
                    updateFalg = true;
                } else {
                    $("#usernamelabel").text(result);
                    validateUsername = false;
                    updateFalg = false;
                }
            },
            error: function (e) {

            }
        });
    });
    $("#password1").blur(function () {
        var pwd1 = $.trim($("#password1").val());
        if (pwd1.length < 6 || pwd1.length > 12) {
            $("#password1label").text("密码长度应在6-12位之间！");
            validatePassword1 = false;
            updateFalg = false;
        } else {
            $("#password1label").text("");
            validatePassword1 = true;
            updateFalg = true;
        }
    });
    $("#password2").blur(function () {
        var pwd1 = $.trim($("#password1").val());
        var pwd2 = $.trim($("#password2").val());
        if (pwd1 != pwd2) {
            $("#password2label").text("两次密码不一致！");
            validatePassword2 = false;
            updateFalg = false;
        } else {
            $("#password2label").text("");
            validatePassword2 = true;
            updateFalg = true;
        }
    });
    //新增提交
    $("#addUser").click(function () {
        if (validateUsername && validatePassword1 && validatePassword2) {
            var username = $.trim($("#username").val());
            var password = $.trim($("#password1").val());
            var gender = $("input[name='gender']:checked").val();
            $.ajax({
                type: "post",
                url: "/user/add",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                },
                dataType: "json",
                data: {
                    "username": username,
                    "password": password,
                    "gender": gender
                },
                success: function (result) {
                    validateUsername = false;
                    validatePassword1 = false;
                    validatePassword2 = false;
                    $("#username").val("");
                    $("#password1").val("");
                    $("#password2").val("");
                    $("input[name='gender']:eq(0)").prop("checked", true);
                    Modal.alert(
                        {
                            msg: result,
                            title: '添加结果',
                            btnok: '确定',
                            btncl: '取消'
                        });
                },
                error: function (e) {
                    alert(e);
                }
            });
        }
        return false;
    });
    //更新提交
    $("#updateSubmit").click(function () {
        if (validateUsername && validatePassword1 && validatePassword2) {
            var id = $("#updateUserID").val();
            var username = $.trim($("#username").val());
            var password = $.trim($("#password1").val());
            var gender = $("input[name='gender']:checked").val();
            $.ajax({
                type: "post",
                url: "/user/update",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
                },
                dataType: "json",
                data: {
                    "id": id,
                    "username": username,
                    "password": password,
                    "gender": gender
                },
                success: function (result) {
                    validateUsername = false;
                    validatePassword1 = false;
                    validatePassword2 = false;
                    $("#username").val("");
                    $("#password1").val("");
                    $("#password2").val("");
                    $("input[name='gender']:eq(0)").prop("checked", true);
                    Modal.alert(
                        {
                            msg: result,
                            title: '修改结果',
                            btnok: '确定',
                            btncl: '取消'
                        });
                    $("#updateUser").modal('hide');
                },
                error: function (e) {
                    alert(e);
                }
            });
        }
        return false;
    });
});
/*]]>*/
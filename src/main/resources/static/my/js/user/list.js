$(function () {
    //日期插件
    laydate.render({
        elem: '#beginDate' //指定元素
    });
    laydate.render({
        elem: '#endDate' //指定元素
    });
    //点击删除，弹出删除确认框
    $(".deleteBtn").click(function () {
        var delUri = $(this).attr("del_uri");
        Modal.confirm(
            {
                msg: "是否删除用户：" + $(this).parent().parent().children().first().next().text() + "？"
            })
            .on(function (e) {
                if (e) {
                    $("#deleteUserForm").attr("action", delUri).submit();
                }
            });
        return false;
    });
    //点击修改触发弹出修改框
    $(".updateBtn").click(function () {
        var updUri = $(this).attr("upd_uri");
        $.ajax({
            type: "post",
            url: updUri,
            dataType: "json",
            data: {}
        }).then(function(data){
            $("#updateUserID").val(data.id);
            $("#username").val(data.username);
            $("#password1").val(data.password);
            $("#password2").val(data.password);
            $("input[name='gender'][value='"+data.gender+"']").attr("checked",true);
            $("#updateUser").modal('show');
        });
        return false;
    });
    //清空按钮
    $("#clear").click(function () {
        $("#qid").val("");
        $("#qusername").val("");
        $("#beginDate").val("");
        $("#endDate").val("");
        $("#qgender").find("option[value=0]").prop("selected", true);
    });
    $("#updateSubmit").click(function () {
        
    });
});
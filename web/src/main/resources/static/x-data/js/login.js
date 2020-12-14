if (window.top !== window.self) {
    window.top.location = window.location
}
;
layui.use(['element'], function () {
    var $ = layui.jquery;
    $(document).on('click', '.captcha-img', function () {
        var src = this.src.split("?")[0];
        this.src = src + "?" + Math.random();
    });
    //只要点击提交它就会提交表单
    $(document).on('click', '.ajax-login', function (e) {
        e.preventDefault();
        var form = $(this).parents("form");
        var url = "/login"
        var serializeArray = form.serializeArray();
        if (serializeArray[0].value !== "" && serializeArray[1].value !== "" && serializeArray[2].value !== "") {
            $.ajax({
                url: '/login',
                data: serializeArray,
                type: "post",
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        layer.msg(result.msg, {icon: 1, time: 500}, function () {
                            location.href = "/index";
                        })
                    } else {
                        layer.msg(result.msg, {icon: 2, time: 500}, function () {
                            $('.captcha-img').click();
                        });
                    }
                }
            });
        }
    });
    $('.layui-layer-loading').hide();
});
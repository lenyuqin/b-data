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
        var url = "http://localhost:2333//login"
        var serializeArray = form.serializeArray();
        console.log(serializeArray);
        if(serializeArray[0].value!==""&&serializeArray[1].value!==""&&serializeArray[2].value!=="") {
            $.post(url, serializeArray, function (result) {
                if (result.code !== 200) {
                    $('.captcha-img').click();
                }
                $.fn.Messager(result);
            });
        }

    });
    $('.layui-layer-loading').hide();
});
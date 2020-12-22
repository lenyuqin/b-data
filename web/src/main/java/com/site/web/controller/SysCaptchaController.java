package com.site.web.controller;

import com.site.web.web.base.BaseController;
import com.site.web.web.domain.response.Result;


import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Describe: 验证码控制器
 * 这里就不需要进行修改了
 * @author lenyuqin
 */
@RestController
@RequestMapping("system/captcha")
public class SysCaptchaController extends BaseController {

    /**
     * 验证码生成
     *
     * @param request  请求报文
     * @param response 响应报文
     */
    @RequestMapping("generate")
    public void generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    /**
     * 异步验证
     *
     * @param request 请求报文
     * @param captcha 验证码
     * @return 验证结果
     */
    @RequestMapping("verify")
    public Result verify(HttpServletRequest request, String captcha) {
        if (CaptchaUtil.ver(captcha, request)) {
            return success();
        }
        return failure();
    }

}

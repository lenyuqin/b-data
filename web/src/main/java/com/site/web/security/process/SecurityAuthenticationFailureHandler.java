package com.site.web.security.process;

import com.alibaba.fastjson.JSON;
import com.site.web.exception.auth.CaptchaException;
import com.site.web.domain.Logging;
import com.site.web.plugins.logging.enums.BusinessType;
import com.site.web.plugins.logging.enums.LoggingType;
import com.site.web.service.LoggingService;
import com.site.web.utils.sequence.SequenceUtil;
import com.site.web.utils.servlet.ServletUtil;
import com.site.web.web.domain.response.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户登录失败处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 引 入 日 志 服 务
     * */
    @Resource
    private LoggingService loggingService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        Result result = Result.failure(500,"登录失败");

        if(e instanceof CaptchaException){
            result.setMsg("验证码有误");
        }
        if(e instanceof UsernameNotFoundException){
            result.setMsg("用户名不存在");
        }
        if(e instanceof LockedException){
            result.setMsg("用户冻结");
        }
        if(e instanceof BadCredentialsException){
            result.setMsg("账户密码不正确");
        }
        if(e instanceof DisabledException){
            result.setMsg("用户未启用");
        }
        Logging logging = new Logging();
        logging.setId(SequenceUtil.makeStringId());
        logging.setTitle("登录");
        logging.setDescription(result.getMsg());
        logging.setBusinessType(BusinessType.OTHER);
        logging.setSuccess(false);
        logging.setLoggingType(LoggingType.LOGIN);
        loggingService.save(logging);
        ServletUtil.write(JSON.toJSONString(result));
    }
}

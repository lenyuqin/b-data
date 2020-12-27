package com.site.web.security.process;

import com.alibaba.fastjson.JSON;
import com.site.web.entity.Logging;
import com.site.web.plugins.logging.enums.BusinessType;
import com.site.web.plugins.logging.enums.LoggingType;
import com.site.web.service.LoggingService;
import com.site.component.utils.sequence.SequenceUtil;
import com.site.component.utils.servlet.ServletUtil;
import com.site.web.web.domain.response.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户未登陆处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private LoggingService loggingService;

    //@Resource
    //private ISysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 记录日志
        Logging logging = new Logging();
        logging.setId(SequenceUtil.makeStringId());
        logging.setTitle("登录");
        logging.setDescription("登录成功");
        logging.setBusinessType(BusinessType.OTHER);
        logging.setSuccess(true);
        logging.setLoggingType(LoggingType.LOGIN);
        loggingService.save(logging);

        // 更新用户
        //SysUser sysUser = new SysUser();
        //sysUser.setUserId(((SysUser) SecurityUtil.currentUser().getPrincipal()).getUserId());
        //sysUser.setLastTime(LocalDateTime.now());
        //sysUserService.update(sysUser);

        // 响应消息
        Result result = Result.success(200,"登录成功");
        httpServletRequest.getSession().setAttribute("currentUser",authentication.getPrincipal());
        ServletUtil.write(JSON.toJSONString(result));
    }
}

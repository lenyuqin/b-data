package com.site.bdata.annotation;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.site.bdata.dto.result.JwtResultEnums;
import com.site.bdata.dto.result.ResultException;
import com.site.bdata.interceptor.JwtProjectProperties;
import com.site.bdata.util.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Jwt权限注解AOP
 * 通过注解拦截，只需要在需要拦截的接口上添加@JwtPermissions即可
 */
@Aspect
@Component
// @ConditionalOnProperty读取yml配置文件里面的字段值
@ConditionalOnProperty(name = "project.jwt.pattern-anno", havingValue = "true", matchIfMissing = true)
public class JwtPermissionsAop {

    @Autowired
    private JwtProjectProperties jwtProperties;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(com.site.bdata.annotation.JwtPermissions)")
    public void jwtPermissions() {
    }

    @Around("jwtPermissions()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {

        // 获取请求对象头部token数据
        String token = JwtUtil.getRequestToken(request);

        // 验证token数据是否正确
        try {
            JwtUtil.verifyToken(token, jwtProperties.getSecret());
        } catch (TokenExpiredException e) {
            throw new ResultException(JwtResultEnums.TOKEN_EXPIRED);
        } catch (JWTVerificationException e) {
            throw new ResultException(JwtResultEnums.TOKEN_ERROR);
        }

        return point.proceed();
    }
}

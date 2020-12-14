package com.site.bdata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理session超时问题拦截器
 */
@Slf4j
public class UserAuthFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        log.info("=========isAccessAllowed=====");
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            //游客，未登录 false
            return subject.getPrincipal() != null && (subject.isRemembered() || subject.isAuthenticated());
        }
    }


    /**
     * 请求头为空，那么就会重定向到登陆
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);

        log.info("=========onAccessDenied======");

        if (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
        } else {
            redirectToLogin(request, response);
        }
        return false;
    }
}

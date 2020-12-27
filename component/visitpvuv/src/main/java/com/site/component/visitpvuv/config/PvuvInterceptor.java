package com.site.component.visitpvuv.config;

import cn.hutool.core.date.DateUtil;
import com.site.component.utils.http.IpUtil;
import com.site.component.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author lenyuqin
 * @data 2020/12/8
 * @describe 这里通过获取ip，等进行访问量的记录
 */
@Slf4j
@Component
public class PvuvInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddr = IpUtil.getIpAddr(request);
        log.info("访问地址为=>" + ipAddr);
        Date date = DateUtil.date();
        String key = DateUtil.format(date, "yyyyMMdd");
        if (redisUtil.get(key) == null) {
            redisUtil.set(key, 1);
        } else {
            redisUtil.incr(key, 1);
        }
        log.info("redis==>"+redisUtil.get(key));
        redisUtil.pfadd("daily"+key, ipAddr);
        redisUtil.pfadd("count", ipAddr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

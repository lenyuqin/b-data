package com.site.web.plugins.pvuv;

import cn.hutool.core.date.DateUtil;
import com.site.web.utils.http.IpUtil;
import com.site.web.utils.redis.RedisUtil;
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
        String key = PvuvString.PREFIX + DateUtil.format(DateUtil.date(), "yyyyMMdd");
        if (redisUtil.hget("dailyCount", key) == null) {
            redisUtil.hset("dailyCount", key, 1);
        } else {
            redisUtil.hincr("dailyCount", key, 1);
        }
        log.info("redis==>" + redisUtil.hget("dailyCount", key));
        redisUtil.pfadd(PvuvString.COUNT, ipAddr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

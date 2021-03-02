package com.site.permission.security.domain;

import com.site.component.utils.redis.RedisUtil;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Describe: Redis remember me 持久化到 redis
 * Author: John Ming
 * CreateTime: 2020/11/22
 *
 * @author lenyuqin
 */
@Component
public class SecurityUserTokenService implements PersistentTokenRepository {

    private final static String USERNAME_KEY = "spring:security:rememberMe:username_key:";
    private final static String SERIES_KEY = "spring:security:rememberMe:series_key:";
    @Resource
    private RedisUtil redisUtil;


    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        String series = persistentRememberMeToken.getSeries();
        String key = generateKey(series, SERIES_KEY);
        String usernameKey = generateKey(persistentRememberMeToken.getUsername(), USERNAME_KEY);
        deleteIfPresent(usernameKey);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", persistentRememberMeToken.getUsername());
        hashMap.put("token", persistentRememberMeToken.getTokenValue());
        hashMap.put("date", String.valueOf(persistentRememberMeToken.getDate().getTime()));
        redisUtil.hmset(key, hashMap);
        redisUtil.expire(key, 1, TimeUnit.DAYS);
        redisUtil.expire(usernameKey, 1, TimeUnit.DAYS);
        redisUtil.set(usernameKey, series);
    }

    @Override
    public void updateToken(String s, String token, Date date) {
        String key = generateKey(s, SERIES_KEY);
        if (redisUtil.hasKey(key)) {
            redisUtil.hset(key, "token", token);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        String key = generateKey(s, SERIES_KEY);
        Map<Object, Object> map = redisUtil.hmget(key);
        String username = (String) map.get("username");
        String tokenValue = (String) map.get("token");
        String date = (String) map.get("date");
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        long timestamp = Long.parseLong(date);
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(username, s, tokenValue, time);
    }

    @Override
    public void removeUserTokens(String s) {
        String key = generateKey(s, USERNAME_KEY);
        deleteIfPresent(key);
    }

    private void deleteIfPresent(String key) {
        try {
            if (redisUtil.hasKey(key)) {
                String series = generateKey((String) redisUtil.get(key), SERIES_KEY);
                if (redisUtil.hasKey(series)) {
                    redisUtil.del(series);
                    redisUtil.del(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成唯一键
    private String generateKey(String series, String prefix) {
        return prefix + series;
    }

}

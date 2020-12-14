package com.site.bdata.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import com.site.bdata.dto.result.JwtResultEnums;
import com.site.bdata.dto.result.ResultException;
import com.site.bdata.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.site.bdata.util.HttpServletUtil.getRequest;


@Slf4j
public class JwtUtil {

    /**
     * 生成JwtToken
     *
     * @param username 用户名
     * @param secret   秘钥
     * @param amount   过期天数
     */
    public static String getToken(String username, String secret, int amount) {
        User user = new User();
        user.setUsername(username);
        return getToken(user, secret, amount);
    }

    /**
     * 生成JwtToken
     *
     * @param user   用户对象
     * @param secret 秘钥
     * @param amount 过期天数
     */
    public static String getToken(User user, String secret, int amount) {
        // 过期时间
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, amount);

        // 随机Claim
        String random = getRandomString(6);

        // 创建JwtToken对象
        String token = "";
        token = JWT.create()
                // 用户名
                .withSubject(user.getUsername())
                // 发布时间
                .withIssuedAt(new Date())
                // 过期时间
                .withExpiresAt(ca.getTime())
                // 自定义随机Claim
                .withClaim("ran", random)
                .sign(getSecret(secret, random));

        return token;
    }

    /**
     * 获取请求对象中的token数据
     */
    public static String getRequestToken(HttpServletRequest request) {
        // 获取JwtTokens失败
        String authorization = request.getHeader("authorization");
        log.info("token=========>" + authorization);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ResultException(JwtResultEnums.TOKEN_ERROR);
        }
        //因为有前缀，所以要去掉前缀
        return authorization.substring(7);
    }

    /**
     * 获取当前token中的用户名
     */
    public static String getSubject() {
        HttpServletRequest request = getRequest();
        String token = getRequestToken(request);
        return JWT.decode(token).getSubject();
    }

    /**
     * 验证JwtToken
     *
     * @param token JwtToken数据
     * @return true 验证通过
     * @throws TokenExpiredException    Token过期
     * @throws JWTVerificationException 令牌无效（验证不通过）
     */
    public static void verifyToken(String token, String secret) throws JWTVerificationException {
        String ran = JWT.decode(token).getClaim("ran").asString();
        log.info("验证JwtToken");
        JWTVerifier jwtVerifier = JWT.require(getSecret(secret, ran)).build();
        jwtVerifier.verify(token);
    }

    /**
     * 生成Secret混淆数据
     */
    private static Algorithm getSecret(String secret, String random) {
        String salt = "君不见黄河之水天上来，奔流到海不复回。君不见高堂明镜悲白发，朝如青丝暮成雪。";
        //String salt = "元嘉草草，封狼居胥，赢得仓皇北顾。四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓。凭谁问、廉颇老矣，尚能饭否？";
        //String salt = "安能摧眉折腰事权贵，使我不得开心颜。";
        //String salt = "大江东去，浪淘尽，千古风流人物。故垒西边，人道是，三国周郎赤壁。乱石穿空，惊涛拍岸，卷起千堆雪。江山如画，一时多少豪杰。";
        return Algorithm.HMAC256(secret + salt + "(ノ￣▽￣)ノ 皮一下" + random);
    }

    /**
     * 获取随机位数的字符串
     *
     * @param length 随机位数
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // 获取ascii码中的字符 数字48-57 小写65-90 大写97-122
            int range = random.nextInt(75) + 48;
            range = range < 97 ? (range < 65 ? (range > 57 ? 114 - range : range) : (range > 90 ? 180 - range : range)) : range;
            sb.append((char) range);
        }
        return sb.toString();
    }




}

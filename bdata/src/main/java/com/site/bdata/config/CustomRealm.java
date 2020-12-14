package com.site.bdata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {


    private static HashMap<String, String> map = new HashMap<>();


    //模拟数据库  密码都是123
    static {
        //根据用户名从数据库获取密码 MD5Pwd("root","123")
        // 335c38d67ad98270cd236398be193804=(lenyuqin,123)
        // c7b5a4b3d344cd1ee759b954b6a2e75d=(guest,123)
        // 4fbe67902ad1551ceaf1b971bbca64ca=(root,123)
        map.put("lenyuqin", "335c38d67ad98270cd236398be193804");
        map.put("guest", "c7b5a4b3d344cd1ee759b954b6a2e75d");
        map.put("root", "4fbe67902ad1551ceaf1b971bbca64ca");
    }


    /**
     * 限定这个 Realm 只处理 UsernamePasswordToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 查询数据库，将获取到的用户安全数据封装返回
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("-------CustomRealm身份认证方法--------");
        // 从 AuthenticationToken 中获取当前用户
        String username = (String) token.getPrincipal();
        log.info("username======>"+username);
        String pwd = map.get(username);
        // 用户不存在
        if (pwd == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 使用用户名作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username + "salt");

        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象。
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, pwd, credentialsSalt,
                getName());
        return info;
    }

    /**
     * 查询数据库，将获取到的用户的角色及权限信息返回
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("-------身份授权方法--------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        log.info("username===========>" + username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        Set<String> roleSet = new HashSet<>();

        if ("lenyuqin".equals(username)) {
            stringSet.add("user:vip1");
            stringSet.add("user:vip2");
        }
        if ("guest".equals(username)) {
            stringSet.add("user:vip2");
            stringSet.add("user:vip3");
        }
        if ("root".equals(username)) {
            stringSet.add("user:vip1");
            stringSet.add("user:vip2");
            stringSet.add("user:vip3");
        }
        info.setStringPermissions(stringSet);
        return info;
    }

}

package com.site.bdata.controller.login;


import com.site.bdata.config.ProjectProperties;
import com.site.bdata.dto.result.ResultEnum;
import com.site.bdata.dto.result.ResultVo;
import com.site.bdata.dto.result.ResultVoUtil;
import com.site.bdata.interceptor.JwtProjectProperties;
import com.site.bdata.util.CaptchaUtil;
import com.site.bdata.util.HttpServletUtil;
import com.site.bdata.util.JwtUtil;
import com.site.bdata.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author 小懒虫
 * @date 2018/8/14
 */
@Controller
@Slf4j
public class LoginController {


    @Resource
    private JwtProjectProperties jwtProjectProperties;

    /**
     * 跳转到登录页面
     */
    @GetMapping({"/login", "/","/login.html"})
    public String toLogin(Model model) {
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        model.addAttribute("isCaptcha", properties.isCaptchaOpen());
        log.info("login======>");
        return "x-data/login";
    }

    /**
     * 实现登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(String username, String password, String captcha, String rememberMe) throws MalformedURLException {


        // 判断验证码是否正确
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        if (properties.isCaptchaOpen()) {//判断是否了开启验证码
            Session session = SecurityUtils.getSubject().getSession();
            String sessionCaptcha = (String) session.getAttribute("captcha");
            //TODO:验证码过期还没做
            if (StringUtils.isEmpty(sessionCaptcha) || !captcha.toUpperCase().equals(sessionCaptcha.toUpperCase())) {
                log.error("验证码错误");
                return ResultVoUtil.error(ResultEnum.USER_CAPTCHA_ERROR.getCode(), ResultEnum.USER_CAPTCHA_ERROR.getMessage());
            }
            session.removeAttribute("captcha");
        }
        return loginMethod(username, password, rememberMe);

    }

    public ResultVo loginMethod(String username, String password, String rememberMe) throws MalformedURLException {
        //TODO：完成登录相关的操作
        log.info("=========登陆测试===========");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            // 执行认证登陆
            //账户注册的时候要调用MD5Pwd()这个方法，将密码加密，然后后面验证才能通过
            // 判断是否自动登录
            if (rememberMe != null) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            subject.login(token);
        } catch (LockedAccountException lae) {
            log.error("账户已锁定");
            return ResultVoUtil.error(ResultEnum.ERROR.getCode(), "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.error("用户名或密码错误次数过多");
            return ResultVoUtil.error(ResultEnum.ERROR.getCode(), "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            log.error("用户名或密码不正确！");
            return ResultVoUtil.error(ResultEnum.ERROR.getCode(), "用户名或密码不正确");
        }
        if (subject.isAuthenticated()) {
            // 若登录成功，签发 JWT token
            String jWTToken = JwtUtil.getToken(username, jwtProjectProperties.getSecret(), jwtProjectProperties.getExpired());
            HashMap<String, String> map = new HashMap<>();
            log.info("access_token===>" + jWTToken);
            map.put("access_token", jWTToken);
            map.put("url", HttpServletUtil.getRequest().getContextPath() + "/index");
            return ResultVoUtil.success("登录成功", map);
        } else {
            token.clear();
            log.error("登陆失败");
            return ResultVoUtil.error("登陆失败", new URL("/login"));
        }
    }

    /**
     * 验证码图片
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");
        log.info("captcha============>");

        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute("captcha", code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/x-data/login";
    }


}

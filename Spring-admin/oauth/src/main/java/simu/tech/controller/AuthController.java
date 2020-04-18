package simu.tech.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import simu.tech.entity.Result;
import simu.tech.entity.StatusCode;
import simu.tech.service.AuthService;
import simu.tech.util.AuthToken;
import simu.tech.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;


    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam(value = "FROM",required = false,defaultValue = "") String from, Model model) {
        model.addAttribute("from",from);
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {

        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户名不存在");
        }
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("密码不存在");
        }

        AuthToken authToken = authService.login(username, password, clientId, clientSecret);

        this.saveJtiToCookie(authToken.getJti());

        return new Result(true, StatusCode.OK, "登录成功", authToken.getJti());

    }

    //退出
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        //获取cookie中的jti
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if (map == null && map.size() <= 0) {
            throw new RuntimeException("您尚未登录,请先登录");
        }
        String jti = map.get("uid");
        if (jti == null && jti.length() <= 0) {
            throw new RuntimeException("您尚未登录,请先登录");
        }
        //删除redis中的令牌
        authService.logout(jti);
        //删除cookie中的jti
        this.removeCookie(response, "uid");
        return new Result(true, StatusCode.OK, "退出成功");
    }

    //添加cookie
    private void saveJtiToCookie(String jti) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);
    }

    //删除cookie
    private void removeCookie(HttpServletResponse response, String jti) {
        CookieUtil.removeCookie(response, jti);
    }
}

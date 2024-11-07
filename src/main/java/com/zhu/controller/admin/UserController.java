package com.zhu.controller.admin;

import com.wf.captcha.ArithmeticCaptcha;
import com.zhu.pojo.User;
import com.zhu.service.user.UserService;
import com.zhu.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("MyRedisTemplate")
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String login(){
        return "admin/login";
    }

    @RequestMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        //获取验证码
        response.setContentType("image/jpg");
        response.setHeader("Pargam","No-cache");
        response.setHeader("cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        //生成验证码，将验证码放入redis当中
        ArithmeticCaptcha captch = new ArithmeticCaptcha(110, 38, 3);
        //获得用户的ip地址
        String ip = IpUtils.getIpAddr(request);
        redisTemplate.opsForValue().set("captcha:"+ip,captch.text(),300, TimeUnit.SECONDS);
        try {
            captch.out(response.getOutputStream());
        } catch (IOException e) {
            log.error("验证码生成失败",e.getMessage());
        }

    }

    //从Redis缓存中获取验证码
    public String getRedisCaptcha(HttpServletRequest request){
        String ip = IpUtils.getIpAddr(request);
        String captcha = (String) redisTemplate.opsForValue().get("captcha:" + ip);
        return captcha;
    }

    //获取客户端IP地址
    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
            if (ip.equals ("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet.getHostAddress ();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
        }
        return ip;

    }

    @RequestMapping("/toLogin")
    public String getUser(){
            return "redirect:/admin/data/show";
    }
}

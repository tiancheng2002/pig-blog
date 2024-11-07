package com.zhu.config;

import com.zhu.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    @Qualifier("MyRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
//        UserController userController = new UserController();
//        String captcha = userController.getRedisCaptcha(request);
        String ip = IpUtils.getIpAddr(request);
        String captcha = (String) redisTemplate.opsForValue().get("captcha:" + ip);
        if (captcha == null) {
            throw new AuthenticationServiceException("验证码已过期");
        }
        String kaptcha = request.getParameter("captcha");
        if (!StringUtils.isEmpty(kaptcha) && !StringUtils.isEmpty(captcha) && kaptcha.equalsIgnoreCase(captcha)) {
            return super.attemptAuthentication(request, response);
        }
        throw new AuthenticationServiceException("验证码输入错误");
    }
}
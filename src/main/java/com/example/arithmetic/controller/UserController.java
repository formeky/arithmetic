package com.example.arithmetic.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.arithmetic.exception.CaptchaException;
import com.example.arithmetic.mail.SimpleMail;
import com.example.arithmetic.mail.support.OjMailSender;
import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.pojo.enums.StatusEnum;
import com.example.arithmetic.pojo.vo.BaseVo;
import com.example.arithmetic.pojo.vo.SignUpVo;
import com.example.arithmetic.pojo.vo.UserVo;
import com.example.arithmetic.services.UserService;
import com.example.arithmetic.utils.CaptchaUtil;
import com.example.arithmetic.utils.SignUpConvert;
import com.example.arithmetic.utils.UserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcw
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OjMailSender sender;

    @PostMapping("login")
    public BaseVo login(@RequestBody UserVo user){
        User re = userService.getUser(user.getEmail());
        if (re!=null&&re.getPassword().equals(SaSecureUtil.md5(user.getPasswd()))) {
            StpUtil.login(re.getId());
            return new BaseVo(StatusEnum.SUCCESS.getCode(),re.getId(),"登陆成功" );
        }
        return new BaseVo(StatusEnum.PASSWORD_WRONG.getCode(), "用户名或者密码错误" );
    }

    @GetMapping("logout")
    public BaseVo logout(){
        StpUtil.logout();
        return new BaseVo(StatusEnum.SUCCESS.getCode(), "用户已下线" );
    }

    @GetMapping("userDetails")
    public BaseVo userDetails(){
        return new BaseVo(StatusEnum.SUCCESS.getCode(),
                userService.simpleInfo(StpUtil.getLoginIdAsInt()),
                "用户信息" );
    }

    @PostMapping("reset")
    public BaseVo reset(@RequestBody UserVo user){
        return new BaseVo(StatusEnum.SUCCESS.getCode(),
                userService.updateUser(UserConvert.voConvert(user)),
                "用户信息" );
    }

    @PostMapping("signUp")
    public BaseVo signUp(@RequestBody SignUpVo vo) throws CaptchaException {
        if (!redisTemplate.hasKey(vo.getEmail())||!redisTemplate.opsForValue().get(vo.getEmail()).equals(vo.getCaptcha())){
            throw new CaptchaException("验证码有误");
        }
        redisTemplate.delete(vo.getEmail());
        User user = SignUpConvert.voConvert(vo);
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        userService.insertUser(user);
        return new BaseVo(StatusEnum.SUCCESS.getCode(),
                "注册成功" );
    }

    @PostMapping("captcha")
    public BaseVo captcha(@RequestBody String email){
        List<String> addresses = new ArrayList<>();
        addresses.add(email);
        SimpleMail mail = new SimpleMail();
        String code = CaptchaUtil.getRandomNumCode(6);
        mail.setContent("您的验证码为"+ code+",请注意不要泄露给他人");
        mail.setSender("kelab");
        mail.setSubject("注册验证码");
        mail.setAddresses(addresses);
        sender.send(mail);
        redisTemplate.opsForValue().set(email,code);
        return new BaseVo(StatusEnum.SUCCESS.getCode(),
                "发送成功" );
    }

}

package com.example.arithmetic.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.pojo.enums.StatusEnum;
import com.example.arithmetic.pojo.vo.BaseVo;
import com.example.arithmetic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zcw
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public BaseVo login(@RequestBody User user){
        User re = userService.getUser(user.getEmail());
        if (re!=null&&re.getPassword().equals(SaSecureUtil.md5(user.getPassword()))) {
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
}

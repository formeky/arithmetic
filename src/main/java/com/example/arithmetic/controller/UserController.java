package com.example.arithmetic.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.example.arithmetic.constant.StatisticsConstant;
import com.example.arithmetic.pojo.entity.User;
import com.example.arithmetic.pojo.enums.StatusEnum;
import com.example.arithmetic.pojo.vo.BaseVo;
import com.example.arithmetic.pojo.vo.UserVo;
import com.example.arithmetic.services.UserService;
import com.example.arithmetic.utils.UserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("statistics")
    public HashMap<String,Integer> statistics(){
        HashMap map = new HashMap();
        map.put("online",redisTemplate.opsForValue().get(StatisticsConstant.ONLINE_USER));
        map.put("allUser",redisTemplate.opsForValue().get(StatisticsConstant.ALL_USER));
        map.put("problem",redisTemplate.opsForValue().get(StatisticsConstant.PROBLEM));
        map.put("runDay",redisTemplate.opsForValue().get(StatisticsConstant.RUN_DAYS));
        return map;
    }
}

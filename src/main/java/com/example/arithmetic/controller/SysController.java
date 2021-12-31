package com.example.arithmetic.controller;

import com.example.arithmetic.constant.StatisticsConstant;
import com.example.arithmetic.pojo.vo.RankItemVo;
import com.example.arithmetic.services.SystemService;
import com.example.arithmetic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author zcw
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private UserService userService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("statistics")
    public HashMap<String,Integer> statistics(){
        HashMap map = new HashMap(4);
        map.put("online",redisTemplate.opsForValue().get(StatisticsConstant.ONLINE_USER));
        map.put("allUser",redisTemplate.opsForValue().get(StatisticsConstant.ALL_USER));
        map.put("problem",redisTemplate.opsForValue().get(StatisticsConstant.PROBLEM));
        map.put("runDay",redisTemplate.opsForValue().get(StatisticsConstant.RUN_DAYS));
        return map;
    }

    @GetMapping("rank")
    public List<RankItemVo> rank(){
        List<RankItemVo> rank = systemService.rank();
        return rank;
    }
}

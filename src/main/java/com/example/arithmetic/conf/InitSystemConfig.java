package com.example.arithmetic.conf;

import com.example.arithmetic.constant.StatisticsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zcw
 */
@Component
@Order
public class InitSystemConfig implements ApplicationRunner {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        redisTemplate.opsForValue().set(StatisticsConstant.ONLINE_USER,0);
        redisTemplate.opsForValue().set(StatisticsConstant.RUN_DAYS,0);
        redisTemplate.opsForValue().set(StatisticsConstant.ALL_USER,0);
        redisTemplate.opsForValue().set(StatisticsConstant.PROBLEM,0);
    }
}

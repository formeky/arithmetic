package com.example.arithmetic.conf;

import com.example.arithmetic.constant.StatisticsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zcw
 */
@Component
public class InitSystemConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        redisTemplate.opsForValue().set(StatisticsConstant.ONLINE_USER,0);
        redisTemplate.opsForValue().set(StatisticsConstant.RUN_DAYS,0);
        redisTemplate.opsForValue().set(StatisticsConstant.ALL_USER,0);
        redisTemplate.opsForValue().set(StatisticsConstant.PROBLEM,0);

    }
}

package com.example.arithmetic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcw
 */
@MapperScan("com.example.arithmetic.mapper")
@SpringBootApplication
public class ArithmeticApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArithmeticApplication.class, args);
    }

}

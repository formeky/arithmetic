package com.example.arithmetic.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 跨域配置
 */
@Configuration(proxyBeanMethods = false)
public class CorsConfig {

    @Value("${cors.allowedHeader}")
    String allowedHeader;
    @Value("${cors.allowedOrigins}")
    String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedHeaders(allowedHeader)
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("*")
                        .maxAge(3600);
            }

        };
    }

}
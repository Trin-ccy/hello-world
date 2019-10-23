package com.example.demo.config;

import com.example.demo.jwt.JwtFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtConfig {

    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/secure/*");

        return filterRegistrationBean;
    }

    /**
     * 这个加密key一般不开放
     */
    public static final String SECRET_KEY = "abcdefg123456789";

    /**
     * 加密算法
     */
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * Jwt过期时间（1小时）
     */
    public static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis()+3600000);


}

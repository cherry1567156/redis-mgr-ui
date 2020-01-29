package com.xianxin.redis.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 云飞
 */
@Slf4j
@SpringBootApplication
public class RedisAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RedisAdminApplication.class, args);
        log.info("项目运行成功");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        log.info("加载项目配置");
        return builder.sources(RedisAdminApplication.class);
    }
}

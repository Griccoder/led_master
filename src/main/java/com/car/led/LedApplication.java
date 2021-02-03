package com.car.led;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.car.*")
//开启定时
//@EnableScheduling
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.car.led.mapper")
public class LedApplication {

    public static void main(String[] args) {
        SpringApplication.run(LedApplication.class, args);
    }
}


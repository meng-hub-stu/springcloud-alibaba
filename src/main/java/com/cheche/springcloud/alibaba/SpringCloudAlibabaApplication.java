package com.cheche.springcloud.alibaba;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringCloudAlibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaApplication.class, args);
    }

}

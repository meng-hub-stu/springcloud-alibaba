package com.cheche.springcloud.alibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@MapperScan("com.cheche.springcloud.**.mapper")
public class SpringCloudAlibabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaApplication.class, args);
    }

}

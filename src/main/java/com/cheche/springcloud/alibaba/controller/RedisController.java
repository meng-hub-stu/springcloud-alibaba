package com.cheche.springcloud.alibaba.controller;

import com.cheche.springcloud.alibaba.entity.User;
import com.cheche.springcloud.alibaba.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 测试类代码
 * @author Mengdl
 * @date 2021/09/13
 */
@RestController
@ResponseBody
@Api(value = "redis管理", tags = "redis管理")
@Slf4j
public class RedisController {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value = "redis测试接口")
    @GetMapping("/redis-test")
    public String redisTest(){
        redisTemplate.opsForValue().set("123", "测试redis");
        return String.valueOf(redisTemplate.opsForValue().get("123"));
    }

    @ApiOperation(value = "redis登录接口")
    @PostMapping(value = "/redis-login")
    public Result redisLogin(@RequestBody User user){
        log.info("测试数据，请注意观察，请查看[userName-{}]", user.getUserName());
        return Result.success(user);
    }

}

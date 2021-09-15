package com.cheche.springcloud.alibaba.controller;

import com.cheche.springcloud.alibaba.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 测试rabbitmq控制类
 * @author Mengdl
 * @date 2021/09/14
 */
@RestController
@AllArgsConstructor
@Api(value = "rabbitmq推送", tags = "rabbitmq消息推送")
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/sendDirectMessage")
    @ApiOperation(value = "direct推送一对一的消息队列中")
    @ApiOperationSupport(order = 1)
    public Result sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return Result.success("Direct推送消息成功");
    }

    @GetMapping(value = "/sendTopicMessage1")
    @ApiOperation(value = "Topic1推送匹配的消息队列中")
    @ApiOperationSupport(order = 2)
    public Result sendTopicMessage1(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return Result.success("Topic推送消息成功");
    }

    @GetMapping(value = "/sendTopicMessage2")
    @ApiOperation(value = "Topic2推送匹配的消息队列中")
    @ApiOperationSupport(order = 3)
    public Result sendTopicMessage2(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return Result.success("Topic2推送消息成功");
    }

    @GetMapping("/sendFanoutMessage")
    @ApiOperation(value = "fanout推送消息到消息队列中")
    @ApiOperationSupport(order = 4)
    public Result sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return Result.success("Fanout推送消息成功");
    }

    @ApiOperation(value = "消息推送到server，但是在server里找不到交换机")
    @ApiOperationSupport(order = 5)
    @GetMapping("/TestMessageAck")
    public Result TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return Result.success("Fanout推送消息成功");
    }

    @ApiOperation(value = "消息推送到server，找到交换机了，但是没找到队列")
    @ApiOperationSupport(order = 5)
    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        //修改git的提交代码

        return "ok";
    }

}

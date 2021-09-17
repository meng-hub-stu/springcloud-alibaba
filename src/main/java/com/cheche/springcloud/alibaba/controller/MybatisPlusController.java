package com.cheche.springcloud.alibaba.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cheche.springcloud.alibaba.entity.UserTest;
import com.cheche.springcloud.alibaba.mapper.UserTestMapper;
import com.cheche.springcloud.alibaba.service.UserTestService;
import com.cheche.springcloud.alibaba.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mengdl
 * @date 2021/09/16
 */
@RestController
@RequestMapping(value = "mybatis-plus")
@Api(value = "mybatis-plus测试类", tags = "mybatis-plus测试类")
@AllArgsConstructor
public class MybatisPlusController {


    private final UserTestMapper userTestMapper;

    private final UserTestService userTestService;

    @GetMapping(value = "/user-list")
    @ApiOperation(value = "查询所有的用户的数据")
    @ApiOperationSupport(order = 1)
    public Result test(){
        List<UserTest> list = userTestMapper.selectList(Wrappers.<UserTest>lambdaQuery().eq(UserTest::getUsername, "张三"));
        QueryWrapper<UserTest> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .select("username","age")   //需要查询得字段
//                .like("username","张"); //模糊查询

        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("username", "张三");
        queryWrapper.allEq(queryParamsMap);

        List<UserTest> userList = userTestService.list(queryWrapper);

        List<UserTest> users = userTestService.getUserByUserName("张三");


        for (UserTest userTest:users){
            System.out.println(userTest.getUsername());
        }
        return Result.success(users);

    }

}

package com.cheche.springcloud.alibaba.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheche.springcloud.alibaba.entity.UserTest;
import com.cheche.springcloud.alibaba.mapper.UserTestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mengdl
 * @date 2021/09/16
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements UserTestService{

    private final UserTestMapper userTestMapper;

    @Override
    public List<UserTest> getUserByUserName(String param) {
        return userTestMapper.getUserByName(param);
    }
}

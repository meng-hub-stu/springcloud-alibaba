package com.cheche.springcloud.alibaba.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheche.springcloud.alibaba.entity.UserTest;

import java.util.List;

/**
 * @author Mengdl
 * @date 2021/09/16
 */
public interface UserTestService extends IService<UserTest> {
    /**
     * 查询数据
     * @param param
     * @return
     */
    List<UserTest> getUserByUserName(String param);
}

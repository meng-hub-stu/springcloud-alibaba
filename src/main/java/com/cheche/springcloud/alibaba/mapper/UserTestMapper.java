package com.cheche.springcloud.alibaba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheche.springcloud.alibaba.entity.UserTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mengdl
 * @date 2021/09/16
 */
public interface UserTestMapper extends BaseMapper<UserTest> {

    List<UserTest> getUserByName(@Param("param") String param);

}

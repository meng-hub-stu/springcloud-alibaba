package com.cheche.springcloud.alibaba.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Mengdl
 * @date 2021/09/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class User {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String passWord;

}

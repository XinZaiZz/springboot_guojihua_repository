package com.youxin.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author youxin
 * @program guojihua
 * @description 用户登陆信息类
 * @date 2021-10-23 16:56
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体类")
public class User implements Serializable {

    @ApiModelProperty("用户id")
    private int id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户权限")
    private String perms;
}

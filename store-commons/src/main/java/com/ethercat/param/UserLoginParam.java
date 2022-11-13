package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: b2c-store
 * @description: 用户登录尸体
 * @author: Ethercat
 * @create: 2022-11-13 19:20
 **/
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}

package com.ethercat.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: b2c-store
 * @description: 接受前端参数的param
 * TODO： 要使用jsr 303的注解 进行参数的校验
 * @author: Ethercat
 * @create: 2022-11-12 22:20
 * @NotBlank 字符串 不能为null 和 空字符串
 * @NotNull 字符串 不能为null
 * @NotEmpty 集合类型 集合长度能不为0
 **/

@Data
public class UserCheckParam  {

    @NotBlank
    private String userName;
}

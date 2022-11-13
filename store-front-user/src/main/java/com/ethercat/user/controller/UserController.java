package com.ethercat.user.controller;

import com.ethercat.param.UserCheckParam;
import com.ethercat.user.service.UserService;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description: 用户模块控制
 * @author: Ethercat
 * @create: 2022-11-12 23:48
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    /**
     * 检查账号是否可用接口
     * @param userCheckParam  接受检查的账号
     * @param result    获取校验结果的实体对象
     * @return
     */


    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){

        boolean b = result.hasErrors();
        if (b){
            return R.fail("账号为null不可用");
        }

        return userservice.check(userCheckParam);
    }
}

package com.ethercat.admin.controller;

import com.ethercat.admin.service.UserService;
import com.ethercat.param.CartListParam;
import com.ethercat.param.PageParam;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-27 12:40
 **/

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam){
        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userRemove(CartListParam cartListParam){
        return userService.userRemove(cartListParam);
    }

}

package com.ethercat.user.controller;

import com.ethercat.param.AddressListParam;
import com.ethercat.pojo.Address;
import com.ethercat.user.service.AddressService;
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
 * @description: 地址的控制controller
 * @author: Ethercat
 * @create: 2022-11-14 16:19
 **/

@RestController
@RequestMapping("user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result){
        if (result.hasErrors()){
            return R.ok("参数异常，查询失败！");
        }

        return  addressService.list(addressListParam.getUserId());
    }
}

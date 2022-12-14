package com.ethercat.cart.controller;

import com.ethercat.param.CartListParam;
import com.ethercat.param.CartSaveParam;
import com.ethercat.pojo.Cart;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ethercat.cart.service.CartService;

/**
 * @program: b2c-store
 * @description: 购物车
 * @author: Ethercat
 * @create: 2022-11-20 21:51
 **/
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result){
        if (result.hasErrors()) {
            return R.fail("购物车参数为空，添加失败");
        }

        return cartService.save(cartSaveParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result){
        if (result.hasErrors()) {
            return R.fail("购物车查询失败!");
        }
        return cartService.list(cartListParam.getUserId());
    }

    @PostMapping("test")
    public R test(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result){
        if (result.hasErrors()) {
            return R.fail("购物车参数为空，添加失败");
        }

        return R.ok("测试",cartSaveParam);
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart){
        return cartService.update(cart);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){
        return cartService.remove(cart);
    }

    @PostMapping("remove/check")
    public R removeCheck(@RequestBody Integer productId){
        return cartService.removeCheck(productId);
    }
}

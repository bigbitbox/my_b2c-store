package com.ethercat.cart.service;

import com.ethercat.param.CartSaveParam;
import com.ethercat.utils.R;

public interface CartService {
    /**
     * 购物车添加数据方法
     * @param cartSaveParam
     * @return 001成功 002 已经存在 003没有库存
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 返回购物车数据
     * @param userId
     * @return 确保要返回一个数组
     */
    R list(Integer userId);
}

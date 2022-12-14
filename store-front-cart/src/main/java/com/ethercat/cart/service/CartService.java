package com.ethercat.cart.service;

import com.ethercat.param.CartSaveParam;
import com.ethercat.pojo.Cart;
import com.ethercat.utils.R;

import java.util.List;

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

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应id的购物车项
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);

    R removeCheck(Integer productId);
}

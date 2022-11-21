package com.ethercat.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.cart.mapper.CartMapper;
import com.ethercat.cart.service.CartService;
import com.ethercat.clients.ProductClient;
import com.ethercat.param.CartSaveParam;
import com.ethercat.param.ProductCollectParam;
import com.ethercat.param.ProductIdParam;
import com.ethercat.pojo.Cart;
import com.ethercat.pojo.Product;
import com.ethercat.utils.R;
import com.ethercat.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-21 08:54
 **/

@Service
@Slf4j
public class CartServiceimpl implements CartService {
    /**
     * 购物车添加数据方法
     *
     * @param cartSaveParam
     * @return 001成功 002 已经存在 003没有库存
     */
    @Autowired
    private ProductClient productClient;


    @Autowired
    private CartMapper cartMapper;

    @Override
    public R save(CartSaveParam cartSaveParam) {
        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());
        Product product = productClient.productDetail(productIdParam);

        if (product == null) {
            return R.fail("商品已经被删除，无法添加到购物车");
        }
        //检查库存
        if (product.getProductNum() == 0) {
            R ok = R.ok("没有库存数据，无法购买");
            ok.setCode("003");
            log.info("CartServiceimpl.save业务结束，结果：{}",ok);
            return ok;
        }
        //检查是否添加过
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cartSaveParam.getUserId());
        queryWrapper.eq("product_id",cartSaveParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);
        if (cart != null) {
            //证明购物车存在
            //原有的数量+1
            cart.setNum(cart.getNum()+1);
            cartMapper.updateById(cart);
            //返回002  提示即可
            R ok = R.ok("购物车存在此商品，数量+1");
            ok.setCode("002");
            log.info("CartServiceimpl.save业务结束，结果：{}",ok);
            return ok;
        }
        //添加购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int rows = cartMapper.insert(cart);
        log.info("CartServiceimpl.save业务结束，结果：{}",rows);


        //结果封装和返回
        CartVo cartVo = new CartVo(product,cart);
        return R.ok("购物车数据添加成功！",cartVo);
    }

    /**
     * 返回购物车数据
     *
     * @param userId
     * @return 确保要返回一个数组
     */
    @Override
    public R list(Integer userId) {
        //1.用户id查询  购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        List<Cart> carts = cartMapper.selectList(queryWrapper);
        //2.判断是否存在，不存在，返回一个空集合
        if (carts == null || carts.size() == 0) {
            carts = new ArrayList<>();
            return R.ok("购物车空空如也",carts);
        }
        //3.存在获取商品的id集合，并调用商品服务查询
        List<Integer> productIds = new ArrayList<>();
        for (Cart cart : carts) {
            productIds.add(cart.getProductId());
        }
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);
        //商品集合 - 商品map 商品的id = key 商品 = value
        //jdk 8 stream
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //4.进行vo的封装
        List<CartVo> cartVoList = new ArrayList<>();

        for (Cart cart : carts) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            cartVoList.add(cartVo);
        }
        R ok = R.ok("数据库数据查询成功！", cartVoList);

        return ok;
    }
}

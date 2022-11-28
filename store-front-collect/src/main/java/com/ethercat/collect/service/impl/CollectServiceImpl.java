package com.ethercat.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.clients.ProductClient;
import com.ethercat.collect.mapper.CollectMapper;
import com.ethercat.collect.service.CollectService;
import com.ethercat.param.ProductCollectParam;
import com.ethercat.pojo.Collect;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: b2c-store
 * @description: 收藏实体类
 * @author: Ethercat
 * @create: 2022-11-20 12:01
 **/

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;
    /**
     * 保存搜藏的方法
     *
     * @param collect
     * @return 001  004
     */
    @Override
    public R save(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());

        Long count = collectMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("收藏已经添加，无需二次添加");
        }

        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectMapper.insert(collect);

        log.info("CollectServiceImpl.save业务结束，结果：{}",rows);

        return R.ok("收藏添加成功！");
    }

    /**
     * 根据用户id查询商品信息集合
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("product_id");

        List<Object> idsObject = collectMapper.selectObjs(queryWrapper);

        ProductCollectParam productCollectParam = new ProductCollectParam();

        List<Integer> ids = new ArrayList<>();

        for (Object o : idsObject) {
            ids.add((Integer) o);
        }

        productCollectParam.setProductIds(ids);

        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束，结果：{}",productCollectParam);

        return r;
    }

    /**
     * 删除保存的方法
     *
     * @param collect
     * @return
     */
    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());

        int rows = collectMapper.delete(queryWrapper);

        log.info("CollectServiceImpl.remove业务结束，结果：{}",rows);

        return R.ok("收藏移除成功");

    }

    @Override
    public R removeByPid(Integer productId) {

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("product_id",productId);

        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.removeByPid业务结束，结果：{}",rows);
        return R.ok("收藏商品删除成功！");
    }
}

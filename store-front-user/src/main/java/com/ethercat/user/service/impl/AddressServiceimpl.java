package com.ethercat.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.user.mapper.AddressMapper;
import com.ethercat.user.service.AddressService;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: b2c-store
 * @description: 地址业务实现类
 * @author: Ethercat
 * @create: 2022-11-14 16:37
 **/

@Service
@Slf4j
public class AddressServiceimpl implements AddressService {

    /**
     * 根据用户id查询 地址数据
     * @param userId 用户id已经校验完毕
     * @return 001 004
     */
    @Autowired
    private AddressMapper addressMapper;


    @Override
    public R list(Integer userId) {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        return null;
    }
}

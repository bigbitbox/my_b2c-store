package com.ethercat.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.pojo.Address;
import com.ethercat.user.mapper.AddressMapper;
import com.ethercat.user.service.AddressService;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        1、查询
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
//         2、结果封装
        R ok = R.ok("查询成功", addressList);
        log.info("AddressServiceimpl.list业务结束，结果：{ok}");
        return ok;
    }

    @Override
    public R save(Address address) {
        int rows = addressMapper.insert(address);
        if (rows == 0){
            log.info("AddressServiceimpl.save业务结束，结果：{}","地址失败！");
            return R.fail("插入地址失败");
        }

        return list(address.getUserId());           
    }

    @Override
    public R remove(Integer id) {

        int rows = addressMapper.deleteById(id);

        if (rows == 0){
            log.info("AddressServiceimpl.remove业务结束，结果：{},地址删除失败");
            return R.fail("地址删除失败！");

        }

        log.info("AddressServiceimpl.remove业务结束，结果：{},地址删除成功");

        return R.ok("地址删除成功！");

    }
}

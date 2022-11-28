package com.ethercat.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethercat.pojo.Order;
import com.ethercat.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}

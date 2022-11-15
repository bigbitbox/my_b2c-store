package com.ethercat.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethercat.carousel.mapper.CarouselMapper;
import com.ethercat.carousel.service.CarouselService;
import com.ethercat.pojo.Carousel;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: b2c-store
 * @description: 轮播图实现类
 * @author: Ethercat
 * @create: 2022-11-15 21:28
 **/

@Service
@Slf4j
public class CarouselServiceimpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public R list() {

        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("priority");

        List<Carousel> list = carouselMapper.selectList(queryWrapper);

        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());




        return null;
    }
}

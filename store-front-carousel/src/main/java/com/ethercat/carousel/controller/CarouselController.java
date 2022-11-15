package com.ethercat.carousel.controller;

import com.ethercat.carousel.service.CarouselService;
import com.ethercat.pojo.Carousel;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description: 轮播图控制
 * @author: Ethercat
 * @create: 2022-11-15 21:18
 **/


@RequestMapping("carousel")
@RestController
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }
}

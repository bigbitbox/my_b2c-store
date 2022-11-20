package com.ethercat.collect.controller;

import com.ethercat.collect.service.CollectService;
import com.ethercat.pojo.Collect;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-20 10:52
 **/


@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @PostMapping("test")
    public Integer test(@RequestBody Collect collect){
        return collect.getUserId();
    }

    @PostMapping("save")
    public R save(@RequestBody Collect collect){
        return  collectService.save(collect);
    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect){
        return collectService.list(collect.getUserId());
    }

    @PostMapping("remove")
    public R remove(@RequestBody Collect collect){
        return  collectService.remove(collect);
    }
}

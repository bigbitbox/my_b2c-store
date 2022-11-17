package com.ethercat.category.controller;

import com.ethercat.category.service.CategoryService;
import com.ethercat.param.ProductHotParam;
import com.ethercat.utils.R;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: b2c-store
 * @description: 类别控制器
 * @author: Ethercat
 * @create: 2022-11-16 19:54
 **/

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){
        if (StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null，无法查询类别数据");
        }
        return categoryService.byName(categoryName);
    }

    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("类别查询失败");
        }
        return categoryService.hotsCategory(productHotParam);
    }

}

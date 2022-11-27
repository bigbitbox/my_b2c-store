package com.ethercat.admin.controller;

import com.ethercat.admin.service.CategoryService;
import com.ethercat.param.PageParam;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-27 20:11
 **/

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam){
        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R pageSave(Category category){
        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R pageRemove(Integer categoryId){
        return categoryService.remove(categoryId);
    }
}

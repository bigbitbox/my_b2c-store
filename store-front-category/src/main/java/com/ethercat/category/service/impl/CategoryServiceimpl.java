package com.ethercat.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethercat.category.mapper.CategoryMapper;
import com.ethercat.category.service.CategoryService;
import com.ethercat.clients.ProductClient;
import com.ethercat.param.PageParam;
import com.ethercat.param.ProductHotParam;
import com.ethercat.pojo.Category;
import com.ethercat.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: b2c-store
 * @description:
 * @author: Ethercat
 * @create: 2022-11-16 20:00
 **/
@Service
@Slf4j
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        if(category == null){
            log.info("CategoryServiceimpl.byName业务结束，结果：{类别查询失败}");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceimpl.byName业务结束，结果：{类别查询成功}");
        return R.ok("类别查询成功",category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        //封装查询
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");

        //查询数据库
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别查询成功", ids);

        log.info("CategoryServiceimpl.hotsCategory业务结束，结果：{}",ok);
        return ok;
    }

    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        R ok = R.ok("类别全部数据查询成功", categories);

        log.info("CategoryServiceimpl.list业务结束，结果：{}",ok);
        return ok;
    }

    /**
     * f
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {
        IPage<Category> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        page = categoryMapper.selectPage(page,null);

        return R.ok("类别分类查询成功!",page.getRecords(),page.getTotal());
    }

    /**
     * 添加类别
     * 如果类别名称存在则不添加
     *
     * @param category
     * @return
     */
    @Override
    public R adminSave(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());

        Long aLong = categoryMapper.selectCount(queryWrapper);

        if (aLong > 0) {
            return R.fail("类别存在，添加失败");
        }

        int insert = categoryMapper.insert(category);

        log.info("CategoryServiceimpl.adminSave业务结束，结果：{}",insert);

        return R.ok("类别添加成功！");
    }

    @Override
    public R adminRemove(Integer categoryId) {
        Long aLong = productClient.adminCount(categoryId);

        if (aLong > 0) {
            return R.fail("类别删除失败，有"+aLong+"件商品正在引用！");
        }

        int i = categoryMapper.deleteById(categoryId);
        log.info("CategoryServiceimpl.adminRemove业务结束，结果：{}",i);

        return R.ok("类别数据删除成功！");
    }

    @Override
    public R adminUpdate(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());

        Long aLong = categoryMapper.selectCount(queryWrapper);

        if (aLong > 0) {
            return R.fail("类别存在，修改失败");
        }

        int i = categoryMapper.updateById(category);
        log.info("CategoryServiceimpl.adminUpdate业务结束，结果：{}",i);

        return R.ok("类别数据修改成功！");
    }
}

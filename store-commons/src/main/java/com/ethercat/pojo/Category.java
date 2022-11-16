package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: b2c-store
 * @description: 类别实体类
 * @author: Ethercat
 * @create: 2022-11-16 19:50
 **/
@TableName("category")
@Data
public class Category {

    @TableId(type = IdType.AUTO)
    private Integer categoryId;
    private String categoryName;

}

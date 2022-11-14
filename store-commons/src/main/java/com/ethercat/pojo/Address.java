package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: b2c-store
 * @description: 地址实体类
 * @author: Ethercat
 * @create: 2022-11-13 22:32
 **/

@Data
@TableName("address")
public class Address implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String linkman;
    private String phone;
    private String address;
    @TableField("user_id")
    private Integer userId;
}

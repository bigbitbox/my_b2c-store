package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: b2c-store
 * @description: 轮播图
 * @author: Ethercat
 * @create: 2022-11-15 21:00
 **/

@Data
@TableName("carousel")
public class Carousel  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    private String  imgPath;
    private String  describes;
    @JsonProperty("product_id")
    private Integer productId;
    //优先级
    private Integer priority;

}
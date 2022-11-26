package com.ethercat.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: b2c-store
 * @description: 后台管理用户的实体类
 * @author: Ethercat
 * @create: 2022-11-26 18:27
 **/

@Data
@TableName("admin_user")
public class AdminUser  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;
    private Date createTime;
    private Integer userRole;

}

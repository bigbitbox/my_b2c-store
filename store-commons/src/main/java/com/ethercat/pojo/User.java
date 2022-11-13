package com.ethercat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.javassist.SerialVersionUID;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {

    public static final Long SerialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String userPhonenumber;
}

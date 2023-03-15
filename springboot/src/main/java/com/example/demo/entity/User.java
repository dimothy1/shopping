package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_user")
@Data
public class User {
    @TableId(type= IdType.AUTO)
    private  Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;

}

package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;


@TableName("tb_item_cat")
@Data
public class Cat {
    @TableId(type= IdType.AUTO)
    private BigInteger id;
    private BigInteger parentId;
    private String name;
    private Integer sortOrder;
    private Date created;
    private Date updated;

}
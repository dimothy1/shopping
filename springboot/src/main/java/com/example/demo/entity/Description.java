package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@TableName("tb_item_desc")
@Data
public class Description {
    @TableId(type= IdType.AUTO)
    private BigInteger itemId;
    private String itemDesc;
    private Date created;
    private Date updated;

}
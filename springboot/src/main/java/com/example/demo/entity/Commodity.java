package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@TableName("tb_item")
@Data
public class Commodity {
    @TableId(type= IdType.AUTO)
    private  BigInteger id;
    private String title;
    private String sellPoint;
    private BigInteger price;
    private Integer num;
    private String barcode;
    private String image;
    private BigInteger cid;
    private Integer status;
    private Date created;
    private Date updated;

}
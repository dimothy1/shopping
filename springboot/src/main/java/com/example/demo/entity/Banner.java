package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@TableName("content")
@Data
public class Banner {
    @TableId(type= IdType.AUTO)
    private BigInteger id;
    private String name;
    private Integer type;
    private String pic;
    private Date startTime;
    private Date endTime;
    private String url;
    private Integer sort;

}
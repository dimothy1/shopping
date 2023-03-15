package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@TableName("tb_order")
@Data
public class Order {
    @TableId(type= IdType.AUTO)
    private BigInteger orderId;
    private BigDecimal payment;
    private BigDecimal postFee;
    private int status;
    private Date createTime;

    private Date paymentTime;
    private BigInteger userId;
    private BigInteger itemId;
    private String itemName;
    private String itemSrc;

}

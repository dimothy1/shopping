package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    com.example.demo.mapper.OrderMapper OrderMapper;
    @PostMapping
    public Result<?> save(@RequestBody Order Order){
        Order.setCreateTime(new Date());
        OrderMapper.insert(Order);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Order Order){
        OrderMapper.updateById(Order);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        OrderMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){
        return Result.success(OrderMapper.selectById(id));
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Order> wrapper= Wrappers.<Order>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Order::getOrderId,search);
        }
        Page<Order> OrderPage=OrderMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(OrderPage);
    }

}
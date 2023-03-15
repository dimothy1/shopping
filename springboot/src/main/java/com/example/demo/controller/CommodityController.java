package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Commodity;
import com.example.demo.mapper.CommodityMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Resource
    CommodityMapper CommodityMapper;
    @PostMapping
    public Result<?> save(@RequestBody Commodity Commodity){
        Commodity.setCreated(new Date());
        Commodity.setUpdated(new Date());
        CommodityMapper.insert(Commodity);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Commodity Commodity){
        Commodity.setUpdated(new Date());
        CommodityMapper.updateById(Commodity);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        CommodityMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){
        return Result.success(CommodityMapper.selectById(id));
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Commodity> wrapper=Wrappers.<Commodity>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Commodity::getId,search);
        }
        Page<Commodity> CommodityPage=CommodityMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(CommodityPage);
    }

}

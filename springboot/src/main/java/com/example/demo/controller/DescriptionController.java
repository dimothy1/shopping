package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Description;
import com.example.demo.mapper.DescriptionMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/description")
public class DescriptionController {
    @Resource
    com.example.demo.mapper.DescriptionMapper DescriptionMapper;
    @PostMapping
    public Result<?> save(@RequestBody Description Description){
        Description.setCreated(new Date());
        Description.setUpdated(new Date());
        DescriptionMapper.insert(Description);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Description Description){
        Description.setUpdated(new Date());
        DescriptionMapper.updateById(Description);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        DescriptionMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{itemId}")
    public Result<?> getByItemid(@RequestParam(defaultValue = "1") Integer itemId){
        List<Description> res=DescriptionMapper.Select(itemId);
        return Result.success(res);
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Description> wrapper= Wrappers.<Description>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Description::getItemId,search);
        }
        Page<Description> DescriptionPage=DescriptionMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(DescriptionPage);
    }

}
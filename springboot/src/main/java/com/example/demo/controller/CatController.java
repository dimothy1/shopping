package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Cat;
import com.example.demo.entity.Commodity;
import com.example.demo.entity.ProCat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    @Resource
    com.example.demo.mapper.CatMapper CatMapper;
    @PostMapping
    public Result<?> save(@RequestBody Cat Cat){
        Cat.setCreated(new Date());
        Cat.setUpdated(new Date());
        CatMapper.insert(Cat);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Cat Cat){
        Cat.setUpdated(new Date());
        CatMapper.updateById(Cat);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        CatMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){
        return Result.success(CatMapper.selectById(id));
    }
    @GetMapping
    public Result<?> findPages(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search,@RequestParam(defaultValue = "0") Integer parentId){
        LambdaQueryWrapper<Cat> wrapper= Wrappers.<Cat>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Cat::getId,search);
        }
        wrapper.like(Cat::getParentId,parentId);
        Page<Cat> CatPage=CatMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(CatPage);
    }
    @GetMapping("category")
    public Result<?> getcategory(@RequestParam(defaultValue = "") Integer parentId){
        return Result.success(CatMapper.GetCatory(parentId));
    }
    @GetMapping("/menus")
    public Result<?> select(){
        List<ProCat> res= CatMapper.Select();
        return Result.success(res);
    };
    @GetMapping("/pros")
    public Result<?> getpros( @RequestParam(defaultValue = "") Integer parentId){
        List<Commodity> wrapper=CatMapper.GetName(parentId);
        return Result.success(wrapper);
    }

}


package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Banner;
import com.example.demo.mapper.BannerMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    com.example.demo.mapper.BannerMapper BannerMapper;
    @PostMapping
    public Result<?> save(@RequestBody Banner Banner){
        BannerMapper.insert(Banner);
        return Result.success();
    }
    @PutMapping
    public Result<?> update(@RequestBody Banner Banner){
        BannerMapper.updateById(Banner);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        BannerMapper.deleteById(id);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id){
        return Result.success(BannerMapper.selectById(id));
    }
    @GetMapping("/menu")
    public Result<?> getBanner(){
        List<Banner> res=BannerMapper.Select();
        return Result.success(res);
    }

}

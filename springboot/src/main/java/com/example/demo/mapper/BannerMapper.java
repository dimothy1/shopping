package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Banner;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BannerMapper extends BaseMapper<Banner> {
    @Select("select * from content")
    List<Banner> Select();
}

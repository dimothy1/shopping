package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Description;
import com.example.demo.entity.ProCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DescriptionMapper extends BaseMapper<Description> {
    @Select("SELECT * FROM tb_item_desc  WHERE item_id =#{itemId}")
    List<Description> Select(Integer itemId);
}

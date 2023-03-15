package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Cat;
import com.example.demo.entity.Commodity;
import com.example.demo.entity.ProCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CatMapper extends BaseMapper<Cat> {
    @Select("SELECT pc.id,pc.`name` as name FROM tb_item_cat  pc WHERE pc.parent_id =0")
    List<ProCat> Select();
    @Select("SELECT * FROM tb_item as p  WHERE p.cid IN( SELECT pc2.id FROM tb_item_cat pc2 WHERE pc2.parent_id = #{id}) ")
    List<Commodity> GetName(Integer id);
    @Select("SELECT * FROM tb_item_cat where parent_id=#{id}")
    List<Cat> GetCatory(Integer id);
}

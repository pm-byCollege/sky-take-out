package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    //根据菜品id集合查询套餐id集合
    List<Integer> getSetmealIdsByDishIds(List<Integer> dishIds);
}

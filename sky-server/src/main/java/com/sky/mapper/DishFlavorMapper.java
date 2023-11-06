package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /*
    * 批量插入口味
    * */
    void insertBatch(List<DishFlavor> dishFlavors);

    @Delete("delete from dish_flavor where dish_id = #{id}")
    void delByDishId(Long id);

    void delByDishIds(List<Integer> dishIds);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Integer dishId);
}

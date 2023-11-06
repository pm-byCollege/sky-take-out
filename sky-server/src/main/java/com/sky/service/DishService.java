package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /*
     * 新增菜品
     * */
    void saveWithFlavors(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /*
    * 删除
    * */
    void delDish(List<Integer> ids);

    /*
    * 查询菜品
    * */
    DishVO getByIdWithFlavor(Integer id);

    void update(DishDTO dishDTO);
}

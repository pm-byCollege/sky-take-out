package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
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
    void delDish(List<Long> ids);

    /*
    * 查询菜品
    * */
    DishVO getByIdWithFlavor(Long id);

    void update(DishDTO dishDTO);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /*
     * 修改菜品起售状态
     * */
    void startOrStop(Integer status, Long id);

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}

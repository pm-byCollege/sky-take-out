package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;

import java.util.List;

public interface SetmealService {

    /*
    * 条件查询套餐
    * */
    List<Setmeal> list(Setmeal setmeal);

    /*
    * 根据id查询菜品选项
    * */
    List<DishItemVO> dishList(Long id);
}

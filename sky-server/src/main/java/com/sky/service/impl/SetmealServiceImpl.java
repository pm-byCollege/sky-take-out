package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    private SetmealMapper setmealMapper;

    /*
     * 条件查询套餐
     * */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }


    /*
    * 根据id查询菜品选项
    * */
    public List<DishItemVO> dishList(Long setMealId) {
        return setmealMapper.getDishListBySetmealId(setMealId);
    }
}

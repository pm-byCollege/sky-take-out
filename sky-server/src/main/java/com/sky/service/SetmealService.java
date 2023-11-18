package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

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

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     *
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和套餐菜品关系
     *
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     *
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 套餐起售、停售
     *
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}

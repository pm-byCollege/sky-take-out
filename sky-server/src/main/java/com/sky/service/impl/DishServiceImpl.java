package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {


    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    /*
     * 新增菜品
     * */
    @Transactional
    public void saveWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // 菜品插入
        dishMapper.insert(dish);
        // 获取insert语句产生的id值
        Long dishId = dish.getId();
        log.info("insert语句产生dishId:{}", dishId);

        // 口味表插入
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        if (dishFlavors != null && dishFlavors.size() > 0) {
            for (DishFlavor dishFlavor : dishFlavors) {
                dishFlavor.setDishId(dishId);
            }
            dishFlavorMapper.insertBatch(dishFlavors);
        }
    }


    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Transactional
    public void delDish(List<Integer> ids) {
        for (Integer id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        List<Integer> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && setmealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

//        for (Integer id : ids) {
//            dishMapper.delById(id);
//            dishFlavorMapper.delByDishId(id);
//        }

        dishMapper.delByIds(ids);
        dishFlavorMapper.delByDishIds(ids);
    }

    public DishVO getByIdWithFlavor(Integer id) {
        Dish dish = dishMapper.getById(id);
        List<DishFlavor> dishFlavor = dishFlavorMapper.getByDishId(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavor);
        return dishVO;
    }

    /*
    * 修改菜品
    * */
    public void update(DishDTO dishDTO) {
         Dish dish = new Dish();
         BeanUtils.copyProperties(dishDTO, dish);
         dishMapper.update(dish);
         dishFlavorMapper.delByDishId(dishDTO.getId());
         List<DishFlavor> dishFlavors = dishDTO.getFlavors();
         if (dishFlavors != null && dishFlavors.size() > 0) {
             for (DishFlavor dishFlavor : dishFlavors) {
                 dishFlavor.setDishId(dishDTO.getId());
             }
             dishFlavorMapper.insertBatch(dishFlavors);
         }
    }
}

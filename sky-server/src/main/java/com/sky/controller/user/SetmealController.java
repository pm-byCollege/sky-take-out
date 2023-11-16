package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@Api(tags = "c端套餐浏览")
@RequestMapping("/user/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    @ApiOperation("根据分类id查询套餐列表")
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId") // setmealCache::100
    public Result<List<Setmeal>> list(Long categoryId) {
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        List<Setmeal> list = setmealService.list(setmeal);
        return Result.success(list);
    }

    /*
    * 根据套餐id查询菜品
    * */
    @GetMapping("/dish/{id}")
    @ApiOperation("根据套餐id查询菜品")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        List<DishItemVO> list = setmealService.dishList(id);
        return Result.success(list);
    }

}

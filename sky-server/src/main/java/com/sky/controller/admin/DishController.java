package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品管理")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;
    /*
     * 新增菜品
     * */
    @ApiOperation("新增菜品")
    @PostMapping("")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavors(dishDTO);

        // 处理redis缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        cleanChche(key);

        return Result.success();
    }


    /*
    * 查询
    * */
    @GetMapping("/page")
    @ApiOperation("菜品查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品查询 {}", dishPageQueryDTO);
        PageResult pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result delDish(@RequestParam List<Long> ids) {
        log.info("删除菜品 {}", ids);
        dishService.delDish(ids);

        cleanChche("dish_*");

        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品 {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品 {}", dishDTO);
        dishService.update(dishDTO);

        // 处理redis缓存数据
        cleanChche("dish_*");

        return Result.success();
    }

    /*
    * 根据分类id查询菜品
    * */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }


    /*
    * 修改菜品起售状态
    * */
    @PutMapping("/status/{status}")
    @ApiOperation("菜品起售状态")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        return null;
    }

    private void cleanChche(String pattern) {
        // 处理redis缓存数据
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}

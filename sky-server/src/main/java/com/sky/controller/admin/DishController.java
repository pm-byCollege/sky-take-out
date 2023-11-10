package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品管理")
public class DishController {

    @Autowired
    private DishService dishService;
    /*
     * 新增菜品
     * */
    @ApiOperation("新增菜品")
    @PostMapping("")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavors(dishDTO);
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
        return Result.success();
    }
}

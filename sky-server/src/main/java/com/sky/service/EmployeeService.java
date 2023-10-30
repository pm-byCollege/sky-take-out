package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /*
     * 新增员工
     * @param employeeDTO
     * */
    void save(EmployeeDTO employeeDTO);

    /*
     * 员工信息查询
     * @param employeePageQueryDTO
     * */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /*
     * 状态开启或关闭
     * @param status ids
     * */
    void startOrStop(Integer status, Long id);
}

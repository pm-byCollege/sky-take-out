package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     * @param employee
     */
    @Insert("insert into employee(username,id_number,password,name,sex,phone,status,create_time,update_time) values(#{username},#{idNumber},#{password},#{name},#{sex},#{phone},#{status},#{createTime},#{updateTime})")
    void insert(Employee employee);

    /**
     * 查询员工数据
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 查询员工数据
     * @param employee
     * @return
     */
    void updateStatus(Employee employee);
}

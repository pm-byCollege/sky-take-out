package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
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
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    /**
     * 查询员工数据
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 更新员工状态
     * @param employee
     * @return
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateStatus(Employee employee);

    /*
    * 根据id查询员工信息
    * */
    @Select("select  * from employee where id = #{id}")
    Employee getById(Integer id);
}

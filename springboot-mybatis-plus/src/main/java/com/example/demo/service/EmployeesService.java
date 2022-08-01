package com.example.demo.service;

import com.example.demo.base.entity.Employees;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description:
 * --------------------------------------
 * @ClassName: EmployeesService.java
 * @Date: 2021/03/26 11:48:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface EmployeesService extends IService<Employees> {

    Employees cacheTest(int id);

    Employees othersDbTest(int id);

}

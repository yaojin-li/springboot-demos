package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.base.vo.ExcelInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: ExcelService.java
 * @Date: 2021/2/22 21:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public interface ExcelService extends IService<ExcelInfo> {

    List<Map<String, Object>> selectAll();

}

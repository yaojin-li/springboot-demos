package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.base.dao.BorrowerInfoMapper;
import com.example.demo.base.vo.BorrowerInfo;
import com.example.demo.service.ExcelService;
import org.springframework.stereotype.Service;

/**
 * @Description: --------------------------------------
 * @ClassName: ExcelService.java
 * @Date: 2021/2/22 21:19
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Service
public class ExcelServiceImpl extends ServiceImpl<BorrowerInfoMapper, BorrowerInfo> implements ExcelService {


}

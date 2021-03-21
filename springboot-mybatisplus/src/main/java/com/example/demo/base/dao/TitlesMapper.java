package com.example.demo.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.base.entity.Titles;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: --------------------------------------
 * @ClassName: TitlesMapper.java
 * @Date: 2021/03/10 22:58:38
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */
public interface TitlesMapper extends BaseMapper<Titles> {
    /**
     * 通过map查询
     * 通过 @Param() 注解指定参数
     */
    Titles selectByNewMap(@Param("conditionMap") Map<String, Object> conditionMap);

    /**
     * 通过object查询
     */
    Titles selectByObj(@Param("conditionObj") Titles conditionObj);


    /**
     * 通过基本类型传参
     */
    List<Titles>  paramBasicType(@Param("no") Integer no, @Param("title") String title);


    /**
     * 通过list遍历
     * 此时mapper返回类型为list<>
     * */
    List<Titles> paramList(@Param("list") List<String> list);


    /**
     * 比较参数作为条件
     * */
    List<Titles> queryCompare(@Param("conditionMap") Map<String, Object> map);


}

package com.example.demo.base.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: --------------------------------------
 * @ClassName: MessageRequestVo.java
 * @Date: 2021/9/28 17:02
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Data
public class MessageRequestVo implements Serializable {
    private Long unionId;

    private Integer current = 1;

    private Integer size = 10;
}

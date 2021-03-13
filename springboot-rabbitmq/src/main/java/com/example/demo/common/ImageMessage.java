package com.example.demo.common;

import com.example.demo.base.entity.Images;
import lombok.Data;

/**
 * @Description: --------------------------------------
 * @ClassName: ImageMessage.java
 * @Date: 2021/3/13 17:25
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Data
public class ImageMessage {
    private Images images;

    /**
     * 流水号
     * */
    private Integer serialNo;

}

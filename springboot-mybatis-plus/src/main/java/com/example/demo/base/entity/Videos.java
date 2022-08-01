package com.example.demo.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Description: Videos实体类
 * --------------------------------------
 * @ClassName: Videos.java
 * @Date: 2021/03/28 11:43:05
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 */

@Data
@TableName("videos")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Videos对象", description = "Videos对象")
public class Videos implements Serializable{

private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "视频ID")
        @TableId(value = "ID", type = IdType.AUTO)
                private Integer id;

        @ApiModelProperty(value = "视频名称")
    @TableField("TITLE")
            private String title;

        @ApiModelProperty(value = "视频重命名")
    @TableField("NEW_TITLE")
            private String newTitle;

        @ApiModelProperty(value = "视频类型")
    @TableField("TYPE")
            private String type;

        @ApiModelProperty(value = "视频大小")
    @TableField("SIZE")
            private Integer size;

        @ApiModelProperty(value = "视频位置")
    @TableField("POSITION")
            private String position;

        @ApiModelProperty(value = "视频唯一ID")
    @TableField("UUID")
            private String uuid;

        @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
            private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
            private LocalDateTime updateTime;

        @ApiModelProperty(value = "视频上传者")
    @TableField("UPLOADER")
            private String uploader;

        @ApiModelProperty(value = "表注释")
    @TableField("DESCRIPTION")
            private String description;

        @ApiModelProperty(value = "表备注")
    @TableField("REMARK")
            private String remark;

        @ApiModelProperty(value = "扩展字段1")
    @TableField("NOTE1")
            private String note1;

        @ApiModelProperty(value = "扩展字段2")
    @TableField("NOTE2")
            private String note2;


    public static final String ID ="ID";

    public static final String TITLE ="TITLE";

    public static final String NEW_TITLE ="NEW_TITLE";

    public static final String TYPE ="TYPE";

    public static final String SIZE ="SIZE";

    public static final String POSITION ="POSITION";

    public static final String UUID ="UUID";

    public static final String CREATE_TIME ="CREATE_TIME";

    public static final String UPDATE_TIME ="UPDATE_TIME";

    public static final String UPLOADER ="UPLOADER";

    public static final String DESCRIPTION ="DESCRIPTION";

    public static final String REMARK ="REMARK";

    public static final String NOTE1 ="NOTE1";

    public static final String NOTE2 ="NOTE2";

        }
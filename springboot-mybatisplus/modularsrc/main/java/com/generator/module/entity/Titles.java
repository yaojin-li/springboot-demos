package com.generator.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @auther CodeGenAuthor
 * @create 2021-03-07 20:48:07
 * @describe 实体类
 */
@TableName("titles")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Titles对象", description = "")
public class Titles implements Serializable{

private static final long serialVersionUID=1L;

            @TableId(value = "emp_no", type = IdType.UUID)
    private Integer empNo;

    @TableField("title")
private String title;

    @TableField("from_date")
private Date fromDate;

    @TableField("to_date")
private Date toDate;


    public Integer getEmpNo(){
            return empNo;
            }

        public Titles setEmpNo(Integer empNo){
            this.empNo = empNo;
                return this;
            }

    public String getTitle(){
            return title;
            }

        public Titles setTitle(String title){
            this.title = title;
                return this;
            }

    public Date getFromDate(){
            return fromDate;
            }

        public Titles setFromDate(Date fromDate){
            this.fromDate = fromDate;
                return this;
            }

    public Date getToDate(){
            return toDate;
            }

        public Titles setToDate(Date toDate){
            this.toDate = toDate;
                return this;
            }

@Override
public String toString() {
        return "Titles{" +
                "empNo=" + empNo +
                ", title=" + title +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
        "}";
        }
        }
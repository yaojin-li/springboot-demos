package com.example.demo.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Salaries类")
public class Salaries extends SalariesKey {

    @ApiModelProperty("薪水")
    private Integer salary;

    @ApiModelProperty("到期日")
    private Date toDate;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
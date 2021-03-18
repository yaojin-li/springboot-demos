package com.example.demo.base.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Images {
    private Long id;

    private String title;

    private String imgType;

    private BigDecimal imgSize;

    private String uuid;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String note1;

    private String note2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType == null ? null : imgType.trim();
    }

    public BigDecimal getImgSize() {
        return imgSize;
    }

    public void setImgSize(BigDecimal imgSize) {
        this.imgSize = imgSize;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1 == null ? null : note1.trim();
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2 == null ? null : note2.trim();
    }
}
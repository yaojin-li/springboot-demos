package com.example.demo.page;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 缓存分页-分页工具类
 * --------------------------------------
 * @ClassName: PageData.java
 * @Date: 2021/12/28 0028  10:16
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
public class PageData<T> {
    /**
     * 数据集合
     */
    protected List<T> result = Lists.newArrayList();
    /**
     * 数据总数
     */
    protected int totalCount = 0;
    /**
     * 总页数
     */
    protected long pageCount = 0;
    /**
     * 每页记录
     */
    protected int pageSize = 15;
    /**
     * 初始当前页
     */
    protected int pageNo = 1;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}





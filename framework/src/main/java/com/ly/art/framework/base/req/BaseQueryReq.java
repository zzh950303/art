package com.ly.art.framework.base.req;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 查询请求基类
 */
public class BaseQueryReq extends BaseReq {

    private static final long serialVersionUID = -7222862556719983202L;

    /*** 页码 */
    private int curn = 1;

    /**
     * 页面大小
     */
    private int ps = 15;

    /**
     * 总页数
     */
    private long totaln;

    /**
     * 总记录数
     */
    private long count;

    /**
     * map 条件
     */
    private Map<String, Object> cond;

    public BaseQueryReq() {
    }

    public BaseQueryReq(int curn, int ps) {
        this.curn = (curn <= 0) ? 1 : curn;
        this.ps = ps;
    }

    /**
     * 元素起始位置
     */
    public int getIndex() {
        return (this.curn - 1) * this.ps;
    }

    /**
     * 页码
     */
    public int getCurn() {
        return curn;
    }

    public void setCurn(int curn) {
        this.curn = curn;
    }

    /**
     * 页面大小
     */
    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    /**
     * 总页数
     */
    public long getTotaln() {
        return totaln;
    }

    public void setTotalCount(long itemCount) {
        this.count = itemCount;
        this.totaln = calculatePageCount(itemCount, this.ps);
    }

    public long getTotalPageCount() {
        return totaln;
    }

    /**
     * 总记录数
     */
    public long getCount() {
        return count;
    }

    /**
     * 计算页数
     *
     * @param rowCount
     * @param pageSize
     * @return
     */
    private long calculatePageCount(long rowCount, int pageSize) {
        long pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }

        if (pageCount == 0) {
            pageCount = 1;
        }

        return pageCount;
    }

    public Map<String, Object> getCond() {
        cond = Maps.newHashMap();
        cond.put("pageIndex", this.getIndex());
        cond.put("pageSize", this.ps);

        return cond;
    }

    public void setCond(Map<String, Object> cond) {
        this.cond = cond;
    }
}

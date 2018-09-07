package cn.learn.cloud_storage.base.request;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页请求
 *
 * @author caoquanlong
 */
public abstract class BasePageReq {

    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        } else {
            this.pageNum = 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0 && pageSize <= 200) {
            this.pageSize = pageSize;
        } else if (pageSize > 200) {
            this.pageSize = 200;
        } else {
            this.pageSize = 20;
        }
    }

    public RowBounds buildRowBounds() {
        return new RowBounds((pageNum - 1) * pageSize, pageSize);
    }
}

package com.basara.pojo;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 /**
 * page 是分页的模型对象
 * @param <T> 是具体的模块的JavaBean类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;//每页显示数量
    //当前页码
    private Integer pageNum;

    //url地址
    private String url;

    //PageInfo中存储了其他分页信息
    private PageInfo<T> pageInfo;

    public Page() {
    }

    /**
     *
     * @param pageNum  当前页面
     * @param pages     总页数
     */
    public void setPageNum(Integer pageNum,Integer pages) {
        //数据边界的有效检查
        if(pageNum < 1){
            pageNum = 1;
        }
        if(pageNum > pages){
            pageNum = pages;
        }
        this.pageNum = pageNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", url='" + url + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }
}

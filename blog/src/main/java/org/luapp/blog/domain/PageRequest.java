package org.luapp.blog.domain;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lum on 2015/7/21.
 */
public class PageRequest {
    private int page;

    private int per_page;
    private String sort;
    private String order;
    private String q;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}

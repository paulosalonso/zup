package com.github.paulosalonso.zup.domain.service.vo;

import java.util.List;

import static java.util.Collections.emptyList;

public class SearchVO {
    private int page = 1;
    private int size = 100;
    private List<String> order = emptyList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }
}

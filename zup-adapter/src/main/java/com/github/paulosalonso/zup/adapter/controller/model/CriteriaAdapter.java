package com.github.paulosalonso.zup.adapter.controller.model;

import com.github.paulosalonso.zup.usecase.Criteria;

import java.util.Map;

import static java.util.Collections.emptyMap;

public abstract class CriteriaAdapter {
    private int page = 1;
    private int size = 100;
    private Map<String, Criteria.SortDirection> sort = emptyMap();

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

    public Map<String, Criteria.SortDirection> getSort() {
        return sort;
    }

    public void setSort(Map<String, Criteria.SortDirection> sort) {
        this.sort = sort;
    }

    public enum SortDirection {
        ASC,
        DESC
    }
}

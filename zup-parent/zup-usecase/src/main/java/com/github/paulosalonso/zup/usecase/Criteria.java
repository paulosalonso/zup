package com.github.paulosalonso.zup.usecase;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public class Criteria {
    private int page = 1;
    private int size = 100;
    private Map<String, SortDirection> sort = emptyMap();

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

    public Map<String, SortDirection> getSort() {
        return sort;
    }

    public void setSort(Map<String, SortDirection> sort) {
        this.sort = sort;
    }

    public enum SortDirection {
        ASC,
        DESC
    }
}

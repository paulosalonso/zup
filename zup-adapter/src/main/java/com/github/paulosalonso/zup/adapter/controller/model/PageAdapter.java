package com.github.paulosalonso.zup.adapter.controller.model;

//import io.swagger.annotations.ApiModel;

import java.util.List;

//@ApiModel("Page")
public class PageAdapter<T> {
    private List<T> content;
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalSize;

    public static Builder of() {
        return new Builder();
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public static final class Builder<T> {

        private PageAdapter<T> pageAdapter;

        private Builder() {
            pageAdapter = new PageAdapter<>();
        }

        public Builder content(List<T> content) {
            pageAdapter.content = content;
            return this;
        }

        public Builder page(int page) {
            pageAdapter.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            pageAdapter.pageSize = pageSize;
            return this;
        }

        public Builder totalPages(int totalPages) {
            pageAdapter.totalPages = totalPages;
            return this;
        }

        public Builder totalSize(int totalSize) {
            pageAdapter.totalSize = totalSize;
            return this;
        }

        public PageAdapter build() {
            return pageAdapter;
        }
    }
}

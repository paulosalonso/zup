package com.github.paulosalonso.zup.domain.service.vo;

import java.util.List;

public class PageVO<T> {
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

        private PageVO<T> pageVO;

        private Builder() {
            pageVO = new PageVO<>();
        }

        public Builder content(List<T> content) {
            pageVO.content = content;
            return this;
        }

        public Builder page(int page) {
            pageVO.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            pageVO.pageSize = pageSize;
            return this;
        }

        public Builder totalPages(int totalPages) {
            pageVO.totalPages = totalPages;
            return this;
        }

        public Builder totalSize(int totalSize) {
            pageVO.totalSize = totalSize;
            return this;
        }

        public PageVO build() {
            return pageVO;
        }
    }
}

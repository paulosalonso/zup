package com.github.paulosalonso.zup.application.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Page")
public class PageDTO<T> {

    private List<T> content;

    @ApiModelProperty(example = "0")
    private int page;

    @ApiModelProperty(example = "100")
    private int pageSize;

    @ApiModelProperty(example = "5")
    private int totalPages;

    @ApiModelProperty(example = "500")
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

        private PageDTO<T> pageDTO;

        private Builder() {
            pageDTO = new PageDTO<>();
        }

        public Builder content(List<T> content) {
            pageDTO.content = content;
            return this;
        }

        public Builder page(int page) {
            pageDTO.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            pageDTO.pageSize = pageSize;
            return this;
        }

        public Builder totalPages(int totalPages) {
            pageDTO.totalPages = totalPages;
            return this;
        }

        public Builder totalSize(int totalSize) {
            pageDTO.totalSize = totalSize;
            return this;
        }

        public PageDTO build() {
            return pageDTO;
        }
    }
}

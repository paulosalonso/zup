package com.github.paulosalonso.zup.adapter.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import static java.util.Collections.emptyList;

@ApiModel("Criteria")
public class CriteriaDTO {
    @ApiModelProperty("Number of page, based on zero. The default value is 0.")
    private int page = 0;
    @ApiModelProperty("Number of records per page. The default value is 100.")
    private int size = 100;
    @ApiModelProperty("Fields to order the query result. The expected pattern is property.orderType (e.g. name.asc).")
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

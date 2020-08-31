package com.github.paulosalonso.zup.adapter.repository;

import com.github.paulosalonso.zup.usecase.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class PageableBuilder {

    private PageableBuilder() {}

    private static final String DESC = "desc";

    public static Pageable buildPageable(Criteria criteria) {
        return PageRequest.of(criteria.getPage(),
                criteria.getSize(), buildSort(criteria.getOrder()));
    }

    private static Sort buildSort(List<String> orders) {
        return Sort.by(orders.stream()
                .map(PageableBuilder::buildOrder)
                .collect(toList()));
    }

    private static Order buildOrder(String order) {
        String[] orderParams = order.split("\\.");

        if (orderParams.length > 1 && orderParams[1].equals(DESC)) {
            return Order.desc(orderParams[0]);
        }

        return Order.asc(orderParams[0]);
    }

}

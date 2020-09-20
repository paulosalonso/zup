package com.github.paulosalonso.zup.adapter.repository.mapper;

import com.github.paulosalonso.zup.usecase.Criteria;
import com.github.paulosalonso.zup.usecase.Criteria.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Map;

import static java.util.stream.Collectors.toList;

public abstract class PageableMapper {

    private PageableMapper() {}

    public static Pageable from(Criteria criteria) {
        return PageRequest.of(criteria.getPage(),
                criteria.getSize(), buildSort(criteria.getSort()));
    }

    private static Sort buildSort(Map<String, SortDirection> sort) {
        return Sort.by(sort.entrySet().stream()
                .map(PageableMapper::buildOrder)
                .collect(toList()));
    }

    private static Order buildOrder(Map.Entry<String, SortDirection> order) {
        if (SortDirection.DESC.equals(order.getValue())) {
            return Order.desc(order.getKey());
        }

        return Order.asc(order.getKey());
    }

}

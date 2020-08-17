package com.github.paulosalonso.zup.infrastructure.service.page;

import com.github.paulosalonso.zup.domain.service.vo.SearchVO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class PageableBuilder {

    private PageableBuilder() {}

    private static final String DESC = "desc";

    public static Pageable buildPageable(SearchVO searchVO) {
        return PageRequest.of(searchVO.getPage(),
                searchVO.getSize(), buildSort(searchVO.getOrder()));
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

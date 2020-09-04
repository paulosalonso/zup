package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.usecase.Criteria.SortDirection;
import org.springframework.data.domain.Pageable;

import java.util.Map;

import static com.github.paulosalonso.zup.usecase.Criteria.SortDirection.ASC;
import static com.github.paulosalonso.zup.usecase.Criteria.SortDirection.DESC;
import static java.util.stream.Collectors.toMap;

public interface SortMapper {

    static Map<String, SortDirection> to(Pageable pageable) {
        return pageable.getSort().stream()
                .collect(toMap(order -> order.getProperty(), order -> order.isAscending() ? ASC : DESC));
    }

}

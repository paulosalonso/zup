package com.github.paulosalonso.zup.infrastructure.service.page;

import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import org.springframework.data.domain.Page;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public interface PageBuilder {

    static <I, O> PageVO<O> buildPage(Page<I> page, Function<I, O> mapper) {
        return PageVO.of()
                .page(page.getNumber())
                .pageSize(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .totalSize(Long.valueOf(page.getTotalElements()).intValue())
                .content(page.getContent()
                        .stream()
                        .map(mapper)
                        .collect(toList()))
                .build();
    }

}

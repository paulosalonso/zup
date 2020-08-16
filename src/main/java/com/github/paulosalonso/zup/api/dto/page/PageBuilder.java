package com.github.paulosalonso.zup.api.dto.page;

import com.github.paulosalonso.zup.api.dto.PageDTO;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import org.springframework.data.domain.Page;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public interface PageBuilder {

    static <I, O> PageDTO<O> buildPageDTO(PageVO<I> page, Function<I, O> mapper) {
        return PageDTO.of()
                .page(page.getPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalSize(page.getTotalSize())
                .content(page.getContent()
                        .stream()
                        .map(mapper)
                        .collect(toList()))
                .build();
    }

}

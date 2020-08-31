package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.PageDTO;
import com.github.paulosalonso.zup.usecase.Page;

import java.util.List;
import java.util.function.Function;

public interface PageDTOMapper {

    static <I, O> PageDTO<O> from(Page<I> page, Function<List<I>, List<O>> contentMapper) {
        return PageDTO.of()
                .page(page.getPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalSize(page.getTotalSize())
                .content(contentMapper.apply(page.getContent()))
                .build();
    }

}

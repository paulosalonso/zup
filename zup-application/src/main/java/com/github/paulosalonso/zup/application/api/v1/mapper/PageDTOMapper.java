package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.PageAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.PageDTO;

import java.util.List;
import java.util.function.Function;

public interface PageDTOMapper {

    static <I, O> PageDTO<O> from(PageAdapter<I> page, Function<List<I>, List<O>> contentMapper) {
        return PageDTO.of()
                .page(page.getPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalSize(page.getTotalSize())
                .content(contentMapper.apply(page.getContent()))
                .build();
    }

}

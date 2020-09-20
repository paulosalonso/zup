package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.PageAdapter;
import com.github.paulosalonso.zup.usecase.Page;

import java.util.List;
import java.util.function.Function;

public interface PageAdapterMapper {

    static <I, O> PageAdapter<O> from(Page<I> page, Function<List<I>, List<O>> contentMapper) {
        return PageAdapter.of()
                .page(page.getPage())
                .pageSize(page.getPageSize())
                .totalPages(page.getTotalPages())
                .totalSize(page.getTotalSize())
                .content(contentMapper.apply(page.getContent()))
                .build();
    }

}

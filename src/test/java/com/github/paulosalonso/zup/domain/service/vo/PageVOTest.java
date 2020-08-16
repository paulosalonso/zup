package com.github.paulosalonso.zup.domain.service.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageVOTest {

    @Test
    public void whenBuildPageVOThenSuccess() {
        List<Integer> content = List.of(1, 2, 3);

        PageVO<Integer> pageVO = PageVO.of()
                .content(content)
                .page(1)
                .pageSize(3)
                .totalPages(1)
                .totalSize(3)
                .build();

        assertThat(pageVO.getContent()).isEqualTo(content);
        assertThat(pageVO.getPage()).isEqualTo(1);
        assertThat(pageVO.getPageSize()).isEqualTo(3);
        assertThat(pageVO.getTotalPages()).isEqualTo(1);
        assertThat(pageVO.getTotalSize()).isEqualTo(3);
    }
}

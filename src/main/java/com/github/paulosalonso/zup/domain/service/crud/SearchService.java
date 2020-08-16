package com.github.paulosalonso.zup.domain.service.crud;

import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.SearchVO;

public interface SearchService<O, S extends SearchVO> {
    PageVO<O> search(S search);
}

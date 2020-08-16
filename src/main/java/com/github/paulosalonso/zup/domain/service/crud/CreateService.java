package com.github.paulosalonso.zup.domain.service.crud;

public interface CreateService<I, O> {
    O create(I entity);
}

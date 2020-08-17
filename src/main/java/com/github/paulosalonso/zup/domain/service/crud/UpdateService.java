package com.github.paulosalonso.zup.domain.service.crud;

public interface UpdateService<ID, I, O> {
    O update(ID id, I entity);
}

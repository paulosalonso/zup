package com.github.paulosalonso.zup.domain.service.crud;

public interface ReadService<O, ID> {
    O read(ID id);
}

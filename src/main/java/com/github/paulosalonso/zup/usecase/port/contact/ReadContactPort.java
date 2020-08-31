package com.github.paulosalonso.zup.usecase.port.contact;

import com.github.paulosalonso.zup.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ReadContactPort {
    boolean existsById(Long customerId, Long contactId);
    Optional<Contact> findById(Long customerId, Long contactId);
    List<Contact> findAll(Long customerId);
}

package com.github.paulosalonso.zup.usecase.port.contact;

import com.github.paulosalonso.zup.domain.Contact;

public interface DeleteContactPort {
    void delete(Contact contact);
    void deleteById(Long customerId, Long contactId);
}

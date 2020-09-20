package com.github.paulosalonso.zup.adapter.controller.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactUpdateAdapter;
import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.domain.Customer;

public interface ContactAdapterMapper {

    static Contact to(ContactResponseAdapter dto) {
        return Contact.of()
                .customer(Customer.of()
                        .id(dto.getCustomerId())
                        .build())
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static Contact to(Long customerId, ContactCreateAdapter dto) {
        return Contact.of()
                .customer(Customer.of()
                        .id(customerId)
                        .build())
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static Contact to(ContactUpdateAdapter from, Contact to) {
        to.setType(from.getType());
        to.setContact(from.getContact());

        return to;
    }

    static ContactResponseAdapter from(Contact contact) {
        return ContactResponseAdapter.of()
                .id(contact.getId())
                .customerId(contact.getCustomer().getId())
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }
}

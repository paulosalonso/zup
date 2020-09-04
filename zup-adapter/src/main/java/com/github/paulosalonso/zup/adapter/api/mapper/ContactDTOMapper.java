package com.github.paulosalonso.zup.adapter.api.mapper;

import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactDTO;
import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactUpdateDTO;
import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.domain.Customer;

public interface ContactDTOMapper {

    static Contact to(ContactDTO dto) {
        return Contact.of()
                .customer(Customer.of()
                        .id(dto.getCustomerId())
                        .build())
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static Contact to(Long customerId, ContactCreateDTO dto) {
        return Contact.of()
                .customer(Customer.of()
                        .id(customerId)
                        .build())
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static Contact to(ContactUpdateDTO from, Contact to) {
        to.setType(from.getType());
        to.setContact(from.getContact());

        return to;
    }

    static ContactDTO from(Contact contact) {
        return ContactDTO.of()
                .id(contact.getId())
                .customerId(contact.getCustomer().getId())
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }
}

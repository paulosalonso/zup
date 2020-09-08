package com.github.paulosalonso.zup.application.api.v1.mapper;

import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactUpdateAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactUpdateDTO;
import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.domain.Customer;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ContactDTOMapper {

    static Contact to(ContactResponseDTO dto) {
        return Contact.of()
                .customer(Customer.of()
                        .id(dto.getCustomerId())
                        .build())
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static ContactCreateAdapter to(ContactCreateDTO dto) {
        return ContactCreateAdapter.of()
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static ContactUpdateAdapter to(ContactUpdateDTO dto) {
        return ContactUpdateAdapter.of()
                .type(dto.getType())
                .contact(dto.getContact())
                .build();
    }

    static ContactResponseDTO from(ContactResponseAdapter contact) {
        return ContactResponseDTO.of()
                .id(contact.getId())
                .customerId(contact.getCustomerId())
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }

    static List<ContactResponseDTO> from(List<ContactResponseAdapter> contacts) {
        return contacts.stream()
                .map(ContactDTOMapper::from)
                .collect(toList());
    }
}

package com.github.paulosalonso.zup.adapter.repository.contact;

import com.github.paulosalonso.zup.adapter.repository.customer.CustomerEntityMapper;
import com.github.paulosalonso.zup.domain.Contact;

import java.util.List;
import java.util.stream.Collectors;

public interface ContactEntityMapper {

    static ContactEntity from(Contact contact) {
        return ContactEntity.of()
                .id(contact.getId())
                .customer(CustomerEntityMapper.from(contact.getCustomer()))
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }

    static Contact to(ContactEntity contact) {
        return Contact.of()
                .id(contact.getId())
                .customer(CustomerEntityMapper.to(contact.getCustomer()))
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }

    static List<Contact> toList(List<ContactEntity> entities) {
        return entities.stream()
                .map(ContactEntityMapper::to)
                .collect(Collectors.toList());
    }

}

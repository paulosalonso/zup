package com.github.paulosalonso.zup.domain.service.mapper;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;

public interface ContactMapper {

    static Contact contactCreateVOToContactEntity(ContactCreateVO contactCreateVO) {
        return Contact.of()
                .customer(Customer.of()
                        .id(contactCreateVO.getCustomerId())
                        .build())
                .type(contactCreateVO.getType())
                .contact(contactCreateVO.getContact())
                .build();
    }

    static ContactVO contactEntityToContactVO(Contact contact) {
        return ContactVO.of()
                .id(contact.getId())
                .customerId(contact.getCustomer().getId())
                .type(contact.getType())
                .contact(contact.getContact())
                .build();
    }

    static Contact contactUpdateVOToContactEntity(ContactUpdateVO contactUpdateVO, Contact contact) {
        contact.setType(contactUpdateVO.getType());
        contact.setContact(contactUpdateVO.getContact());
        return contact;
    }
}

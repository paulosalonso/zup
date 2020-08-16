package com.github.paulosalonso.zup.domain.mapper;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.model.ContactType;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;
import org.junit.jupiter.api.Test;

import static com.github.paulosalonso.zup.domain.service.mapper.ContactMapper.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactMapperTest {

    @Test
    public void whenMapContactCreateVOToContactEntityThenSuccess() {
        ContactCreateVO contactCreateVO = ContactCreateVO.of()
                .customerId(1L)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        Contact contact = contactCreateVOToContactEntity(contactCreateVO);

        assertThat(contact.getId()).isNull();
        assertThat(contact.getCustomer())
                .isNotNull()
                .satisfies(customer -> assertThat(customer.getId()).isEqualTo(1L));
        assertThat(contact.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contact.getContact()).isEqualTo("mail@mail.com");
    }

    @Test
    public void whenMapContactEntityToContactVOThenSuccess() {
        Contact contact = Contact.of()
                .customer(Customer.of()
                        .id(1L)
                        .build())
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        ContactVO contactVO = contactEntityToContactVO(contact);

        assertThat(contactVO.getId()).isNull();
        assertThat(contactVO.getCustomerId()).isEqualTo(1L);
        assertThat(contactVO.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contactVO.getContact()).isEqualTo("mail@mail.com");
    }

    @Test
    public void whenMapContactUpdateVOToContactEntityThenSuccess() {
        Contact contact = Contact.of()
                .id(1L)
                .customer(Customer.of()
                        .id(1L)
                        .build())
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        ContactUpdateVO contactUpdateVO = ContactUpdateVO.of()
                .type(ContactType.PHONE)
                .contact("(00) 00000-0000")
                .build();

        contactUpdateVOToContactEntity(contactUpdateVO, contact);

        assertThat(contact.getId()).isEqualTo(1L);
        assertThat(contact.getCustomer())
                .isNotNull()
                .satisfies(customer -> assertThat(customer.getId()).isEqualTo(1L));
        assertThat(contact.getType()).isEqualTo(ContactType.PHONE);
        assertThat(contact.getContact()).isEqualTo("(00) 00000-0000");
    }
}

package com.github.paulosalonso.zup.domain.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactTest {

    @Test
    public void whenBuildContactThenSuccess() {
        Customer customer = Customer.of()
                .id(1L)
                .build();

        Contact contact = Contact.of()
                .id(ContactId.of()
                        .customer(customer)
                        .type(ContactType.EMAIL)
                        .build())
                .contact("mycontact@mail.com")
                .build();

        assertThat(contact.getId()).satisfies(id -> {
            assertThat(id.getCustomer()).isEqualTo(customer);
            assertThat(id.getType()).isEqualTo(ContactType.EMAIL);
        });
        assertThat(contact.getContact()).isEqualTo("mycontact@mail.com");
    }

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Contact.class)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .usingGetClass()
                .verify();

        EqualsVerifier.forClass(ContactId.class)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .usingGetClass()
                .verify();
    }
}

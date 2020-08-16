package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactCreateVOTest {

    @Test
    public void whenBuildContactCreateVOThenSuccess() {
        ContactCreateVO contactCreateVO = ContactCreateVO.of()
                .customerId(1L)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        assertThat(contactCreateVO.getCustomerId()).isEqualTo(1L);
        assertThat(contactCreateVO.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contactCreateVO.getContact()).isEqualTo("mail@mail.com");
    }
}

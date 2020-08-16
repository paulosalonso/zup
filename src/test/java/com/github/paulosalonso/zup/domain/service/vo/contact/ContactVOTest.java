package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactVOTest {

    @Test
    public void whenBuildContactVOThenSuccess() {
        ContactVO contactVO = ContactVO.of()
                .id(1L)
                .customerId(1L)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        assertThat(contactVO.getId()).isEqualTo(1L);
        assertThat(contactVO.getCustomerId()).isSameAs(1L);
        assertThat(contactVO.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contactVO.getContact()).isEqualTo("mail@mail.com");
    }
}

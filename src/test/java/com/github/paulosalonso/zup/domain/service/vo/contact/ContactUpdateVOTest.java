package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactUpdateVOTest {

    @Test
    public void whenBuildContactCreateVOThenSuccess() {
        ContactUpdateVO contactUpdateVO = ContactUpdateVO.of()
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        assertThat(contactUpdateVO.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contactUpdateVO.getContact()).isEqualTo("mail@mail.com");
    }
}

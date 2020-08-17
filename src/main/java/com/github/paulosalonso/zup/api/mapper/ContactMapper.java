package com.github.paulosalonso.zup.api.mapper;

import com.github.paulosalonso.zup.api.dto.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.api.dto.contact.ContactDTO;
import com.github.paulosalonso.zup.api.dto.contact.ContactUpdateDTO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;

public interface ContactMapper {

    static ContactDTO contactVOToContactDTO(ContactVO contactVO) {
        return ContactDTO.of()
                .id(contactVO.getId())
                .customerId(contactVO.getCustomerId())
                .type(contactVO.getType())
                .contact(contactVO.getContact())
                .build();
    }

    static ContactCreateVO contactCreateDTOToContactCreateVO(Long customerId, ContactCreateDTO contactCreateDTO) {
        return ContactCreateVO.of()
                .customerId(customerId)
                .type(contactCreateDTO.getType())
                .contact(contactCreateDTO.getContact())
                .build();
    }

    static ContactUpdateVO contactUpdateDTOToContactUpdateVO(ContactUpdateDTO contactCreateDTO) {
        return ContactUpdateVO.of()
                .type(contactCreateDTO.getType())
                .contact(contactCreateDTO.getContact())
                .build();
    }
}

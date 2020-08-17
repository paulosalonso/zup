package com.github.paulosalonso.zup.domain.service;

import com.github.paulosalonso.zup.domain.service.crud.CreateService;
import com.github.paulosalonso.zup.domain.service.crud.DeleteService;
import com.github.paulosalonso.zup.domain.service.crud.ReadService;
import com.github.paulosalonso.zup.domain.service.crud.UpdateService;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;

import java.util.List;

public interface ContactService extends CreateService<ContactCreateVO, ContactVO> {

    List<ContactVO> findByCustomerId(Long customerId);
    ContactVO read(Long customerId, Long contactId);
    ContactVO update(Long customerId, Long contactId, ContactUpdateVO contactUpdateVO);
    void delete(Long customerId, Long contactId);
}

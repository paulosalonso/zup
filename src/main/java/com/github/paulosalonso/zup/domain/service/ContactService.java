package com.github.paulosalonso.zup.domain.service;

import com.github.paulosalonso.zup.domain.service.crud.CreateService;
import com.github.paulosalonso.zup.domain.service.crud.DeleteService;
import com.github.paulosalonso.zup.domain.service.crud.ReadService;
import com.github.paulosalonso.zup.domain.service.crud.UpdateService;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;

import java.util.List;

public interface ContactService extends CreateService<ContactCreateVO, ContactVO>, ReadService<ContactVO, Long>,
        UpdateService<Long, ContactUpdateVO, ContactVO>, DeleteService<Long> {

    List<ContactVO> findByCustomerId(Long customerId);
}

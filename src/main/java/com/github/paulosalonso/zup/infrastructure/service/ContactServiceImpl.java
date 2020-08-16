package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.repository.ContactRepository;
import com.github.paulosalonso.zup.domain.service.ContactService;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.mapper.ContactMapper;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.paulosalonso.zup.domain.service.mapper.ContactMapper.*;
import static java.util.stream.Collectors.toList;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public ContactVO create(ContactCreateVO contactCreateVO) {
        Contact contact = contactCreateVOToContactEntity(contactCreateVO);
        contact = contactRepository.save(contact);
        return contactEntityToContactVO(contact);
    }

    @Override
    public ContactVO read(Long id) {
        return contactRepository.findById(id)
                .map(ContactMapper::contactEntityToContactVO)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public ContactVO update(Long id, ContactUpdateVO contactUpdateVO) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        contact = contactUpdateVOToContactEntity(contactUpdateVO, contact);

        return contactEntityToContactVO(contact);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new NotFoundException();
        }

        contactRepository.deleteById(id);
    }

    @Override
    public List<ContactVO> findByCustomerId(Long customerId) {
        return contactRepository.findByCustomerId(customerId)
                .stream()
                .map(ContactMapper::contactEntityToContactVO)
                .collect(toList());
    }
}

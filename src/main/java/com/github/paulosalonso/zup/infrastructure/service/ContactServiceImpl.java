package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.repository.ContactRepository;
import com.github.paulosalonso.zup.domain.repository.CustomerRepository;
import com.github.paulosalonso.zup.domain.service.ContactService;
import com.github.paulosalonso.zup.domain.service.CustomerService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.DeleteException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.exception.UpdateException;
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
    private CustomerRepository customerRepository;

    public ContactServiceImpl(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public ContactVO create(ContactCreateVO contactCreateVO) {
        if (!customerRepository.existsById(contactCreateVO.getCustomerId())) {
            throw new CreateException("Customer not found");
        }

        Contact contact = contactCreateVOToContactEntity(contactCreateVO);
        contact = contactRepository.save(contact);
        return contactEntityToContactVO(contact);
    }

    @Override
    @Transactional
    public ContactVO update(Long customerId, Long contactId, ContactUpdateVO contactUpdateVO) {
        if (!customerRepository.existsById(customerId)) {
            throw new UpdateException("Customer not found");
        }

        Contact contact = contactRepository.findByIdAndCustomerId(contactId, customerId)
                .orElseThrow(NotFoundException::new);

        contact = contactUpdateVOToContactEntity(contactUpdateVO, contact);

        return contactEntityToContactVO(contact);
    }

    @Override
    @Transactional
    public void delete(Long customerId, Long contactId) {
        if (!customerRepository.existsById(customerId)) {
            throw new DeleteException("Customer not found");
        } else if (!contactRepository.existsByIdAndCustomerId(contactId, customerId)) {
            throw new NotFoundException();
        }

        contactRepository.deleteByIdAndCustomerId(contactId, customerId);
    }

    @Override
    public List<ContactVO> findByCustomerId(Long customerId) {
        return contactRepository.findByCustomerId(customerId)
                .stream()
                .map(ContactMapper::contactEntityToContactVO)
                .collect(toList());
    }

    @Override
    public ContactVO read(Long customerId, Long contactId) {
        return contactRepository.findByIdAndCustomerId(contactId, customerId)
                .map(ContactMapper::contactEntityToContactVO)
                .orElseThrow(NotFoundException::new);
    }
}

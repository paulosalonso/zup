package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.model.ContactType;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.repository.ContactRepository;
import com.github.paulosalonso.zup.domain.repository.CustomerRepository;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void whenFindContactsByCustomerIdThenReturnContactList() {
        Customer customer = Customer.of()
                .id(1L)
                .build();

        Contact email = Contact.of()
                .id(1L)
                .customer(customer)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        Contact phone = Contact.of()
                .id(2L)
                .customer(customer)
                .type(ContactType.PHONE)
                .contact("(00) 00000-0000")
                .build();

        when(contactRepository.findByCustomerId(1L)).thenReturn(List.of(email, phone));

        List<ContactVO> contacts = contactService.findByCustomerId(1L);

        assertThat(contacts).hasSize(2);
        assertThat(contacts.get(0)).satisfies(contact -> {
            assertThat(contact.getId()).isEqualTo(1L);
            assertThat(contact.getCustomerId()).isEqualTo(1L);
            assertThat(contact.getType()).isEqualTo(ContactType.EMAIL);
            assertThat(contact.getContact()).isEqualTo("mail@mail.com");
        });
        assertThat(contacts.get(1)).satisfies(contact -> {
            assertThat(contact.getId()).isEqualTo(2L);
            assertThat(contact.getCustomerId()).isEqualTo(1L);
            assertThat(contact.getType()).isEqualTo(ContactType.PHONE);
            assertThat(contact.getContact()).isEqualTo("(00) 00000-0000");
        });

        verify(contactRepository).findByCustomerId(1L);
    }

    @Test
    public void whenCreateThenReturnContact() {
        Contact contactToCreate = buildContactWithoutId();
        Contact createdContact = buildContactById(1L);

        when(customerRepository.existsById(1L)).thenReturn(true);
        when(contactRepository.save(contactToCreate)).thenReturn(createdContact);

        ContactCreateVO contactCreateVO = ContactCreateVO.of()
                .customerId(1L)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();

        ContactVO contactVO = contactService.create(contactCreateVO);

        assertContactVO(contactVO);

        verify(customerRepository).existsById(1L);

        ArgumentCaptor<Contact> contactCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepository).save(contactCaptor.capture());
        Contact contactArgument = contactCaptor.getValue();
        assertThat(contactArgument).isEqualTo(contactToCreate);
    }

    @Test
    public void whenFindByIdAndCustomerIdThenReturnContact() {
        when(contactRepository.findByIdAndCustomerId(1L, 1L)).thenReturn(
                Optional.of(Contact.of()
                        .id(1L)
                        .customer(Customer.of()
                                .id(1L)
                                .build())
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build()));

        assertContactVO(contactService.read(1l, 1L));

        verify(contactRepository).findByIdAndCustomerId(1L, 1L);
    }

    @Test
    public void whenFindByIdAndCustomerIdThenThrowsNotFoundException() {
        when(contactRepository.findByIdAndCustomerId(1L, 1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.read(1L, 1L))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(contactRepository).findByIdAndCustomerId(1L, 1L);
        verifyNoMoreInteractions(contactRepository);
    }

    @Test
    public void whenUpdateThenReturnContact() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        when(contactRepository.findByIdAndCustomerId(1L, 1L))
                .thenReturn(Optional.of(buildContactById(1L)));

        ContactUpdateVO contactUpdateVO = ContactUpdateVO.of()
                .contact("mail-updated@mail.com")
                .type(ContactType.EMAIL)
                .build();

        ContactVO updatedContact = contactService.update(1L, 1L, contactUpdateVO);

        assertThat(updatedContact.getId()).isEqualTo(1L);
        assertThat(updatedContact.getCustomerId()).isEqualTo(1L);
        assertThat(updatedContact.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(updatedContact.getContact()).isEqualTo("mail-updated@mail.com");

        verify(customerRepository).existsById(1L);
        verify(contactRepository).findByIdAndCustomerId(1L, 1L);
    }

    @Test
    public void whenUpdateThenThrowsNotFoundException() {
        when(customerRepository.existsById(1L)).thenReturn(true);

        when(contactRepository.findByIdAndCustomerId(1L, 1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.update(1L, 1L, ContactUpdateVO.of().build()))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(customerRepository).existsById(1L);
        verify(contactRepository).findByIdAndCustomerId(1L, 1L);
        verifyNoMoreInteractions(contactRepository);
    }

    @Test
    public void whenDeleteThenSuccess() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(contactRepository.existsByIdAndCustomerId(1L, 1L)).thenReturn(true);

        contactService.delete(1L, 1L);

        verify(contactRepository).existsByIdAndCustomerId(1L, 1L);
        verify(contactRepository).deleteByIdAndCustomerId(1L, 1L);
    }

    @Test
    public void whenDeleteThenThrowsNotFountException() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        when(contactRepository.existsByIdAndCustomerId(1L, 1L)).thenReturn(false);

        assertThatThrownBy(() -> contactService.delete(1L, 1L))
                .isExactlyInstanceOf(NotFoundException.class);

        verify(customerRepository).existsById(1L);
        verify(contactRepository).existsByIdAndCustomerId(1L, 1L);
        verifyNoMoreInteractions(contactRepository);
    }

    private Contact buildContactWithoutId() {
        return buildContactById(null);
    }

    private Contact buildContactById(Long id) {
        Customer customer = Customer.of()
                .id(1L)
                .build();

        return Contact.of()
                .id(id)
                .customer(customer)
                .type(ContactType.EMAIL)
                .contact("mail@mail.com")
                .build();
    }

    private void assertContactVO(ContactVO contactVO) {
        assertThat(contactVO.getId()).isEqualTo(1L);
        assertThat(contactVO.getCustomerId()).isEqualTo(1L);
        assertThat(contactVO.getType()).isEqualTo(ContactType.EMAIL);
        assertThat(contactVO.getContact()).isEqualTo("mail@mail.com");
    }

}

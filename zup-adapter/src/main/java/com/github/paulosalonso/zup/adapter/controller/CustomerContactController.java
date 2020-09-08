package com.github.paulosalonso.zup.adapter.controller;

import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactUpdateAdapter;
import com.github.paulosalonso.zup.adapter.controller.mapper.ContactAdapterMapper;
import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.usecase.contact.CreateContact;
import com.github.paulosalonso.zup.usecase.contact.DeleteContact;
import com.github.paulosalonso.zup.usecase.contact.ReadContact;
import com.github.paulosalonso.zup.usecase.contact.UpdateContact;

import java.util.List;

import static com.github.paulosalonso.zup.adapter.controller.mapper.ContactAdapterMapper.from;
import static java.util.stream.Collectors.toList;

public class CustomerContactController {

    private final CreateContact createContact;
    private final ReadContact readContact;
    private final UpdateContact updateContact;
    private final DeleteContact deleteContact;

    public CustomerContactController(CreateContact createContact,
            ReadContact readContact, UpdateContact updateContact, DeleteContact deleteContact) {
        this.createContact = createContact;
        this.readContact = readContact;
        this.updateContact = updateContact;
        this.deleteContact = deleteContact;
    }

    public List<ContactResponseAdapter> getContactsByCustomer(Long customerId) {
        return readContact.findAll(customerId)
                .stream()
                .map(ContactAdapterMapper::from)
                .collect(toList());
    }

    public ContactResponseAdapter get(Long customerId, Long contactId) {
        return from(readContact.findById(customerId, contactId));
    }

    public ContactResponseAdapter create(Long customerId, ContactCreateAdapter contactCreateAdapter) {
        Contact contact = ContactAdapterMapper.to(customerId, contactCreateAdapter);
        return from(createContact.create(contact));
    }

    public ContactResponseAdapter update(Long customerId, Long contactId, ContactUpdateAdapter contactUpdateAdapter) {
        Contact current = readContact.findById(customerId, contactId);
        return from(updateContact.update(ContactAdapterMapper.to(contactUpdateAdapter, current)));
    }

    public void delete(Long customerId, Long contactId) {
        deleteContact.deleteById(customerId, contactId);
    }
}

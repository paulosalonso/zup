package com.github.paulosalonso.zup.adapter.api.controller;

import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactDTO;
import com.github.paulosalonso.zup.adapter.api.dto.contact.ContactUpdateDTO;
import com.github.paulosalonso.zup.adapter.api.mapper.ContactDTOMapper;
import com.github.paulosalonso.zup.domain.Contact;
import com.github.paulosalonso.zup.usecase.contact.CreateContact;
import com.github.paulosalonso.zup.usecase.contact.DeleteContact;
import com.github.paulosalonso.zup.usecase.contact.ReadContact;
import com.github.paulosalonso.zup.usecase.contact.UpdateContact;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.DeleteException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static com.github.paulosalonso.zup.adapter.api.mapper.ContactDTOMapper.to;
import static java.util.stream.Collectors.toList;

@Api(tags = "Customers")
@RestController
@RequestMapping("/customers/{customerId}/contacts")
public class CustomerContactController {

    private final CreateContact createContact;
    private final ReadContact readContact;
    private final UpdateContact updateContact;
    private final DeleteContact deleteContact;

    public CustomerContactController(CreateContact createContact, ReadContact readContact, UpdateContact updateContact, DeleteContact deleteContact) {
        this.createContact = createContact;
        this.readContact = readContact;
        this.updateContact = updateContact;
        this.deleteContact = deleteContact;
    }

    @ApiOperation("Get contacts by customer id")
    @GetMapping
    public List<ContactDTO> getContactsByCustomer(@PathVariable Long customerId) {
        try {
            return readContact.findAll(customerId)
                    .stream()
                    .map(ContactDTOMapper::from)
                    .collect(toList());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
        }
    }

    @ApiOperation("Get a contact by id and customer id")
    @GetMapping("/{contactId}")
    public ContactDTO get(@PathVariable Long customerId, @PathVariable Long contactId) {
        return ContactDTOMapper.from(readContact.findById(customerId, contactId));
    }

    @ApiOperation("Create a customer contact")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@PathVariable Long customerId, @RequestBody @Valid ContactCreateDTO contactCreateDTO) {
        try {
            Contact contact = ContactDTOMapper.to(customerId, contactCreateDTO);
            return ContactDTOMapper.from(createContact.create(contact));
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Update a customer contact")
    @PutMapping("/{contactId}")
    public ContactDTO update(@PathVariable Long customerId, @PathVariable Long contactId, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO) {
        try {
            Contact current = readContact.findById(customerId, contactId);
            return ContactDTOMapper.from(updateContact.update(ContactDTOMapper.to(contactUpdateDTO, current)));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a customer contact")
    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long customerId, @PathVariable Long contactId) {
        try {
            deleteContact.deleteById(customerId, contactId);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

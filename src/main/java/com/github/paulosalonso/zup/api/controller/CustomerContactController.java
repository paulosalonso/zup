package com.github.paulosalonso.zup.api.controller;

import com.github.paulosalonso.zup.api.dto.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.api.dto.contact.ContactDTO;
import com.github.paulosalonso.zup.api.dto.contact.ContactUpdateDTO;
import com.github.paulosalonso.zup.api.mapper.ContactMapper;
import com.github.paulosalonso.zup.domain.service.ContactService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.DeleteException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.exception.UpdateException;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.contact.ContactVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static com.github.paulosalonso.zup.api.mapper.ContactMapper.*;
import static java.util.stream.Collectors.toList;

@Api(tags = "Customers")
@RestController
@RequestMapping("/customers/{customerId}/contacts")
public class CustomerContactController {
    
    private ContactService contactService;

    public CustomerContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ApiOperation("Get contacts by customer id")
    @GetMapping
    public List<ContactDTO> getContactsByCustomer(@PathVariable Long customerId) {
        return contactService.findByCustomerId(customerId)
                .stream()
                .map(ContactMapper::contactVOToContactDTO)
                .collect(toList());
    }

    @ApiOperation("Get a contact by id and customer id")
    @GetMapping("/{contactId}")
    public ContactDTO get(@PathVariable Long customerId, @PathVariable Long contactId) {
        ContactVO contactVO = contactService.read(customerId, contactId);
        return contactVOToContactDTO(contactVO);
    }

    @ApiOperation("Create a customer contact")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@PathVariable Long customerId, @RequestBody @Valid ContactCreateDTO contactCreateDTO) {
        try {
            ContactCreateVO contactCreateVO = contactCreateDTOToContactCreateVO(customerId, contactCreateDTO);
            ContactVO contactVO = contactService.create(contactCreateVO);
            return contactVOToContactDTO(contactVO);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Update a customer contact")
    @PutMapping("/{contactId}")
    public ContactDTO update(@PathVariable Long customerId, @PathVariable Long contactId, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO) {
        try {
            ContactUpdateVO contactUpdateVO = contactUpdateDTOToContactUpdateVO(contactUpdateDTO);
            ContactVO contactVO = contactService.update(customerId, contactId, contactUpdateVO);
            return contactVOToContactDTO(contactVO);
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a customer contact")
    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long customerId, @PathVariable Long contactId) {
        try {
            contactService.delete(customerId, contactId);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

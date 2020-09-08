package com.github.paulosalonso.zup.application.api.v1.controller;

import com.github.paulosalonso.zup.adapter.controller.CustomerContactController;
import com.github.paulosalonso.zup.adapter.controller.model.contact.ContactResponseAdapter;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.contact.ContactUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.paulosalonso.zup.application.api.v1.mapper.ContactDTOMapper.from;
import static com.github.paulosalonso.zup.application.api.v1.mapper.ContactDTOMapper.to;

@Api(tags = "Customers")
@RestController
@RequestMapping("/v1/customers/{customerId}/contacts")
public class CustomerContactAPIController {

    private final CustomerContactController delegate;

    public CustomerContactAPIController(CustomerContactController delegate) {
        this.delegate = delegate;
    }

    @ApiOperation("Get contacts by customer id")
    @GetMapping
    public List<ContactResponseDTO> getContactsByCustomer(@PathVariable Long customerId) {
        return from(delegate.getContactsByCustomer(customerId));
    }

    @ApiOperation("Get a contact by id and customer id")
    @GetMapping("/{contactId}")
    public ContactResponseDTO get(@PathVariable Long customerId, @PathVariable Long contactId) {
        return from(delegate.get(customerId, contactId));
    }

    @ApiOperation("Create a customer contact")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO create(@PathVariable Long customerId, @RequestBody @Valid ContactCreateDTO contactCreateAdapter) {
        return from(delegate.create(customerId, to(contactCreateAdapter)));
    }

    @ApiOperation("Update a customer contact")
    @PutMapping("/{contactId}")
    public ContactResponseDTO update(
            @PathVariable Long customerId,
            @PathVariable Long contactId,
            @RequestBody @Valid ContactUpdateDTO contactUpdateDTO) {
        ContactResponseAdapter current = delegate.get(customerId, contactId);
        return from(delegate.update(customerId, contactId, to(contactUpdateDTO)));
    }

    @ApiOperation("Delete a customer contact")
    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long customerId, @PathVariable Long contactId) {
        delegate.delete(customerId, contactId);
    }
}

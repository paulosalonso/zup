package com.github.paulosalonso.zup.adapter.api.controller;

import com.github.paulosalonso.zup.adapter.api.dto.PageDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCriteriaDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerUpdateDTO;
import com.github.paulosalonso.zup.adapter.api.mapper.CustomerCriteriaDTOMapper;
import com.github.paulosalonso.zup.adapter.api.mapper.CustomerDTOMapper;
import com.github.paulosalonso.zup.adapter.api.mapper.PageDTOMapper;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.customer.CreateCustomer;
import com.github.paulosalonso.zup.usecase.customer.DeleteCustomer;
import com.github.paulosalonso.zup.usecase.customer.ReadCustomer;
import com.github.paulosalonso.zup.usecase.customer.UpdateCustomer;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.adapter.api.mapper.CustomerDTOMapper.from;
import static com.github.paulosalonso.zup.adapter.api.mapper.CustomerDTOMapper.to;

@Api(tags = "Customers")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomer createCustomer;
    private final ReadCustomer readCustomer;
    private final UpdateCustomer updateCustomer;
    private final DeleteCustomer deleteCustomer;

    public CustomerController(CreateCustomer createCustomer, ReadCustomer readCustomer, UpdateCustomer updateCustomer, DeleteCustomer deleteCustomer) {
        this.createCustomer = createCustomer;
        this.readCustomer = readCustomer;
        this.updateCustomer = updateCustomer;
        this.deleteCustomer = deleteCustomer;
    }

    @ApiOperation("Customer search")
    @GetMapping
    public PageDTO<CustomerDTO> search(CustomerCriteriaDTO customerCriteriaDTO, Pageable pageable) {
        Page<Customer> page = readCustomer.findByCriteria(CustomerCriteriaDTOMapper.to(customerCriteriaDTO, pageable));
        return PageDTOMapper.from(page, CustomerDTOMapper::fromList);
    }

    @ApiOperation("Create a customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        try {
            return from(createCustomer.create(to(customerCreateDTO)));
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Get a customer by id")
    @GetMapping("/{id}")
    public CustomerDTO read(@ApiParam(required = true) @PathVariable Long id) {
        try {
            Customer customer = readCustomer.findById(id);
            return from(customer);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update a customer")
    @PutMapping("/{id}")
    public CustomerDTO update(
            @ApiParam(required = true) @PathVariable Long id,
            @RequestBody @Valid CustomerUpdateDTO customerUpdateDTO) {

        try {
            Customer customer = readCustomer.findById(id);
            customer = updateCustomer.update(to(customerUpdateDTO, customer));
            return from(customer);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a customer by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable Long id) {
        try {
            deleteCustomer.deleteById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

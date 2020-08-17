package com.github.paulosalonso.zup.api.controller;

import com.github.paulosalonso.zup.api.dto.PageDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerSearchDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerUpdateDTO;
import com.github.paulosalonso.zup.api.mapper.CustomerMapper;
import com.github.paulosalonso.zup.domain.service.CustomerService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.api.dto.page.PageBuilder.buildPageDTO;
import static com.github.paulosalonso.zup.api.mapper.CustomerMapper.*;

@Api(tags = "Customers")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("Customer search")
    @GetMapping
    public PageDTO<CustomerDTO> search(CustomerSearchDTO customerSearchDTO) {
        PageVO<CustomerVO> page = customerService.search(customerSearchDTOToCustomerSearchVO(customerSearchDTO));
        return buildPageDTO(page, CustomerMapper::customerVOToCustomerDTO);
    }

    @ApiOperation("Create a customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        try {
            CustomerVO customerVO = customerService.create(customerCreateDTOToCustomerVO(customerCreateDTO));
            return customerVOToCustomerDTO(customerVO);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @ApiOperation("Get a customer by id")
    @GetMapping("/{id}")
    public CustomerDTO read(@ApiParam(required = true) @PathVariable Long id) {
        try {
            CustomerVO customer = customerService.read(id);
            return customerVOToCustomerDTO(customer);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update a customer")
    @PutMapping("/{id}")
    public CustomerDTO update(
            @ApiParam(required = true) @PathVariable Long id,
            @RequestBody @Valid CustomerUpdateDTO cityUpdateDTO) {

        try {
            CustomerVO customer = customerService.update(id, customerUpdateDTOToCustomerUpdateVO(cityUpdateDTO));
            return customerVOToCustomerDTO(customer);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete a customer by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable Long id) {
        try {
            customerService.delete(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

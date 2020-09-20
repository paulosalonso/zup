package com.github.paulosalonso.zup.application.api.v1.controller;

import com.github.paulosalonso.zup.adapter.controller.CustomerController;
import com.github.paulosalonso.zup.adapter.controller.model.PageAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerResponseAdapter;
import com.github.paulosalonso.zup.application.api.v1.mapper.CustomerDTOMapper;
import com.github.paulosalonso.zup.application.api.v1.mapper.PageDTOMapper;
import com.github.paulosalonso.zup.application.api.v1.model.PageDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerCriteriaDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerResponseDTO;
import com.github.paulosalonso.zup.application.api.v1.model.customer.CustomerUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.github.paulosalonso.zup.application.api.v1.mapper.CustomerCriteriaDTOMapper.to;
import static com.github.paulosalonso.zup.application.api.v1.mapper.CustomerDTOMapper.from;
import static com.github.paulosalonso.zup.application.api.v1.mapper.CustomerDTOMapper.to;

@Api(tags = "Customers")
@RestController
@RequestMapping("/v1/customers")
public class CustomerAPIController {

    private final CustomerController delegate;

    public CustomerAPIController(CustomerController delegate) {
        this.delegate = delegate;
    }

    @ApiOperation("Customer search")
    @GetMapping
    public PageDTO<CustomerResponseDTO> search(CustomerCriteriaDTO customerCriteriaDTO, Pageable pageable) {
        PageAdapter<CustomerResponseAdapter> page = delegate.search(to(customerCriteriaDTO, pageable));
        return PageDTOMapper.from(page, CustomerDTOMapper::fromList);
    }

    @ApiOperation("Create a customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO create(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        return from(delegate.create(to(customerCreateDTO)));
    }

    @ApiOperation("Get a customer by id")
    @GetMapping("/{id}")
    public CustomerResponseDTO read(@ApiParam(required = true) @PathVariable Long id) {
        return from(delegate.read(id));
    }

    @ApiOperation("Update a customer")
    @PutMapping("/{id}")
    public CustomerResponseDTO update(
            @ApiParam(required = true) @PathVariable Long id,
            @RequestBody @Valid CustomerUpdateDTO customerUpdateDTO) {
        return from(delegate.update(id, to(customerUpdateDTO)));
    }

    @ApiOperation("Delete a customer by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(required = true) @PathVariable Long id) {
        delegate.delete(id);
    }
}

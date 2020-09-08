package com.github.paulosalonso.zup.adapter.controller;

import com.github.paulosalonso.zup.adapter.controller.model.PageAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCriteriaAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerUpdateAdapter;
import com.github.paulosalonso.zup.adapter.controller.mapper.CustomerCriteriaAdapterMapper;
import com.github.paulosalonso.zup.adapter.controller.mapper.CustomerAdapterMapper;
import com.github.paulosalonso.zup.adapter.controller.mapper.PageAdapterMapper;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.customer.CreateCustomer;
import com.github.paulosalonso.zup.usecase.customer.DeleteCustomer;
import com.github.paulosalonso.zup.usecase.customer.ReadCustomer;
import com.github.paulosalonso.zup.usecase.customer.UpdateCustomer;
import org.springframework.data.domain.Pageable;

import static com.github.paulosalonso.zup.adapter.controller.mapper.CustomerAdapterMapper.from;
import static com.github.paulosalonso.zup.adapter.controller.mapper.CustomerAdapterMapper.to;
import static com.github.paulosalonso.zup.adapter.controller.mapper.CustomerCriteriaAdapterMapper.to;

public class CustomerController {

    private final CreateCustomer createCustomer;
    private final ReadCustomer readCustomer;
    private final UpdateCustomer updateCustomer;
    private final DeleteCustomer deleteCustomer;

    public CustomerController(CreateCustomer createCustomer,
            ReadCustomer readCustomer, UpdateCustomer updateCustomer, DeleteCustomer deleteCustomer) {
        this.createCustomer = createCustomer;
        this.readCustomer = readCustomer;
        this.updateCustomer = updateCustomer;
        this.deleteCustomer = deleteCustomer;
    }

    public PageAdapter<CustomerResponseAdapter> search(CustomerCriteriaAdapter customerCriteriaAdapter) {
        Page<Customer> page = readCustomer.findByCriteria(to(customerCriteriaAdapter));
        return PageAdapterMapper.from(page, CustomerAdapterMapper::fromList);
    }

    public CustomerResponseAdapter create(CustomerCreateAdapter customerCreateAdapter) {
        return from(createCustomer.create(to(customerCreateAdapter)));
    }

    public CustomerResponseAdapter read(Long id) {
        Customer customer = readCustomer.findById(id);
        return from(customer);
    }

    public CustomerResponseAdapter update(Long id, CustomerUpdateAdapter customerUpdateAdapter) {
        Customer customer = readCustomer.findById(id);
        customer = updateCustomer.update(to(customerUpdateAdapter, customer));
        return from(customer);
    }

    public void delete(Long id) {
        deleteCustomer.deleteById(id);
    }
}

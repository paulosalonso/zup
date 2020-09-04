package com.github.paulosalonso.zup.usecase.port.customer;

import com.github.paulosalonso.zup.domain.City;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.Page;

import java.util.List;
import java.util.Optional;

public interface ReadCustomerPort {

    boolean existsById(Long id);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Page<Customer> findByCriteria(CustomerCriteria criteria);

}

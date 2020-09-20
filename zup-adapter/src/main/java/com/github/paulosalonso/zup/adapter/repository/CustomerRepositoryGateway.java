package com.github.paulosalonso.zup.adapter.repository;

import com.github.paulosalonso.zup.adapter.repository.mapper.CustomerEntityMapper;
import com.github.paulosalonso.zup.adapter.repository.mapper.PageMapper;
import com.github.paulosalonso.zup.adapter.repository.mapper.PageableMapper;
import com.github.paulosalonso.zup.domain.Customer;
import com.github.paulosalonso.zup.usecase.Page;
import com.github.paulosalonso.zup.usecase.exception.CreateException;
import com.github.paulosalonso.zup.usecase.exception.NotFoundException;
import com.github.paulosalonso.zup.usecase.port.customer.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.paulosalonso.zup.adapter.repository.mapper.CustomerEntityMapper.*;
import static com.github.paulosalonso.zup.adapter.repository.specification.CustomerSpecificationFactory.findByCustomerCriteria;

@Repository
public class CustomerRepositoryGateway implements ReadCustomerPort, CreateCustomerPort, DeleteCustomerPort, UpdateCustomerPort {

    private final CustomerRepository repository;

    public CustomerRepositoryGateway(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        try {
            return to(repository.save(from(customer)));
        } catch (DataIntegrityViolationException e) {
            throw new CreateException("There is already a customer with this cpf");
        }
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(from(customer));
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(CustomerEntityMapper::to);
    }

    @Override
    public List<Customer> findAll() {
        return toList(repository.findAll());
    }

    @Override
    public Page<Customer> findByCriteria(CustomerCriteria criteria) {
        return PageMapper.to(repository.findAll(findByCustomerCriteria(criteria),
                PageableMapper.from(criteria)), CustomerEntityMapper::toList);
    }

    @Override
    public Customer update(Customer customer) {
        return to(repository.save(from(customer)));
    }
}

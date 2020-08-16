package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.repository.CustomerRepository;
import com.github.paulosalonso.zup.domain.service.CustomerService;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerSearchVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper.*;
import static com.github.paulosalonso.zup.infrastructure.repository.specification.CustomerSpecificationFactory.findByCustomerSearch;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageBuilder.buildPage;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder.buildPageable;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public PageVO<CustomerVO> search(CustomerSearchVO searchCriteria) {
        Page<Customer> page = customerRepository.findAll(
                findByCustomerSearch(searchCriteria), buildPageable(searchCriteria));

        return buildPage(page, CustomerMapper::customerEntityToCustomerVO);
    }

    @Override
    @Transactional
    public CustomerVO create(CustomerCreateVO customerCreateVO) {
        Customer customer = customerCreateVOToCustomerEntity(customerCreateVO);
        customer = customerRepository.save(customer);
        return customerEntityToCustomerVO(customer);
    }

    @Override
    public CustomerVO read(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::customerEntityToCustomerVO)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public CustomerVO update(Long id, CustomerUpdateVO customerUpdateVO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        customer = customerUpdateVOToCustomerEntity(customerUpdateVO, customer);

        return customerEntityToCustomerVO(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException();
        }

        customerRepository.deleteById(id);
    }
}

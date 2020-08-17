package com.github.paulosalonso.zup.infrastructure.service;

import com.github.paulosalonso.zup.domain.model.Address;
import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.repository.CustomerRepository;
import com.github.paulosalonso.zup.domain.service.CityService;
import com.github.paulosalonso.zup.domain.service.CustomerService;
import com.github.paulosalonso.zup.domain.service.exception.CreateException;
import com.github.paulosalonso.zup.domain.service.exception.NotFoundException;
import com.github.paulosalonso.zup.domain.service.mapper.CityMapper;
import com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper;
import com.github.paulosalonso.zup.domain.service.vo.PageVO;
import com.github.paulosalonso.zup.domain.service.vo.city.CityVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerSearchVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.github.paulosalonso.zup.domain.service.mapper.CityMapper.cityVOToCityEntity;
import static com.github.paulosalonso.zup.domain.service.mapper.CustomerMapper.*;
import static com.github.paulosalonso.zup.infrastructure.repository.specification.CustomerSpecificationFactory.findByCustomerSearch;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageBuilder.buildPageVO;
import static com.github.paulosalonso.zup.infrastructure.service.page.PageableBuilder.buildPageable;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CityService cityService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CityService cityService) {
        this.customerRepository = customerRepository;
        this.cityService = cityService;
    }

    @Override
    public PageVO<CustomerVO> search(CustomerSearchVO searchCriteria) {
        Page<Customer> page = customerRepository.findAll(
                findByCustomerSearch(searchCriteria), buildPageable(searchCriteria));

        return buildPageVO(page, CustomerMapper::customerEntityToCustomerVO);
    }

    @Override
    @Transactional
    public CustomerVO create(CustomerCreateVO customerCreateVO) {
        try {
            Customer customer = customerCreateVOToCustomerEntity(customerCreateVO);
            resolveAddressCity(customer.getAddress());
            customer = customerRepository.save(customer);
            return customerEntityToCustomerVO(customer);
        } catch (DataIntegrityViolationException e) {
            throw new CreateException("There is already a customer with this cpf", e);
        }
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
        resolveAddressCity(customer.getAddress());

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

    private void resolveAddressCity(Address address) {
        CityVO city;

        if (address.getCity() == null) {
            try {
                city = cityService.findCityByPostalCode(address.getPostalCode());
            } catch (NotFoundException e) {
                city = cityService.createCityByPostalCode(address.getPostalCode());
            }

            address.setCity(cityVOToCityEntity(city));
        }
    }
}

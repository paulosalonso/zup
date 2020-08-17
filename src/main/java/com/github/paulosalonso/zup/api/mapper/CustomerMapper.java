package com.github.paulosalonso.zup.api.mapper;

import com.github.paulosalonso.zup.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerSearchDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerUpdateDTO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerSearchVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;

import static com.github.paulosalonso.zup.api.mapper.AddressMapper.addressDTOToAddressVO;

public interface CustomerMapper {

    static CustomerSearchVO customerSearchDTOToCustomerSearchVO(CustomerSearchDTO customerSearchDTO) {
        return CustomerSearchVO.of()
                .page(customerSearchDTO.getPage())
                .size(customerSearchDTO.getSize())
                .order(customerSearchDTO.getOrder())
                .name(customerSearchDTO.getName())
                .gender(customerSearchDTO.getGender())
                .birthDate(customerSearchDTO.getBirthDate())
                .cpf(customerSearchDTO.getCpf())
                .build();
    }

    static CustomerDTO customerVOToCustomerDTO(CustomerVO customerVO) {
        return CustomerDTO.of()
                .id(customerVO.getId())
                .name(customerVO.getName())
                .address(AddressMapper.addressVOToAddressDTO(customerVO.getAddress()))
                .gender(customerVO.getGender())
                .birthDate(customerVO.getBirthDate())
                .cpf(customerVO.getCpf())
                .build();
    }

    static CustomerCreateVO customerCreateDTOToCustomerVO(CustomerCreateDTO customerCreateDTO) {
        return CustomerCreateVO.of()
                .name(customerCreateDTO.getName())
                .address(addressDTOToAddressVO(customerCreateDTO.getAddress()))
                .gender(customerCreateDTO.getGender())
                .birthDate(customerCreateDTO.getBirthDate())
                .cpf(customerCreateDTO.getCpf())
                .build();
    }

    static CustomerUpdateVO customerUpdateDTOToCustomerUpdateVO(CustomerUpdateDTO customerUpdateDTO) {
        return CustomerUpdateVO.of()
                .name(customerUpdateDTO.getName())
                .address(addressDTOToAddressVO(customerUpdateDTO.getAddress()))
                .gender(customerUpdateDTO.getGender())
                .build();
    }
}

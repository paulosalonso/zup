package com.github.paulosalonso.zup.domain.service;

import com.github.paulosalonso.zup.domain.service.crud.*;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerCreateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerSearchVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerUpdateVO;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;

public interface CustomerService extends SearchService<CustomerVO, CustomerSearchVO>,
        CreateService<CustomerCreateVO, CustomerVO>, ReadService<CustomerVO, Long>,
        UpdateService<Long, CustomerUpdateVO, CustomerVO>, DeleteService<Long> {}

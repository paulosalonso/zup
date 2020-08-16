package com.github.paulosalonso.zup.infrastructure.repository.specification;

import com.github.paulosalonso.zup.domain.model.Customer;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerSearchVO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.github.paulosalonso.zup.infrastructure.repository.specification.LikeValueResolver.contains;

public interface CustomerSpecificationFactory {

    static Specification<Customer> findByCustomerSearch(CustomerSearchVO customerSearchVO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (customerSearchVO.getName() != null) {
                predicates.add(criteriaBuilder.like(
                        root.get("name"), contains(customerSearchVO.getName())));
            }

            if (customerSearchVO.getCpf() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cpf"), customerSearchVO.getCpf()));
            }

            if (customerSearchVO.getGender() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), customerSearchVO.getGender()));
            }

            if (customerSearchVO.getBirthDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("birthDate"), customerSearchVO.getBirthDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.github.paulosalonso.zup.adapter.repository.specification;

import com.github.paulosalonso.zup.adapter.repository.city.CityEntity;
import com.github.paulosalonso.zup.usecase.port.city.CityCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.github.paulosalonso.zup.adapter.repository.specification.LikeValueResolver.contains;

public interface CitySpecificationFactory {

    static Specification<CityEntity> findByCityCriteria(CityCriteria cityCriteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (cityCriteria.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), contains(cityCriteria.getName())));
            }

            if (cityCriteria.getState() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state"), cityCriteria.getState()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

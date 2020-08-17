package com.github.paulosalonso.zup.infrastructure.repository.specification;

import com.github.paulosalonso.zup.domain.model.City;
import com.github.paulosalonso.zup.domain.service.vo.city.CitySearchVO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.github.paulosalonso.zup.infrastructure.repository.specification.LikeValueResolver.contains;

public interface CitySpecificationFactory {

    static Specification<City> findByCitySearch(CitySearchVO citySearchVO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (citySearchVO.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), contains(citySearchVO.getName())));
            }

            if (citySearchVO.getState() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state"), citySearchVO.getState()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

package com.github.paulosalonso.zup.adapter.repository.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String>, JpaSpecificationExecutor<CityEntity> {}

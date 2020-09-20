package com.github.paulosalonso.zup.adapter.repository;

import com.github.paulosalonso.zup.adapter.repository.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String>, JpaSpecificationExecutor<CityEntity> {}

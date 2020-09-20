package com.github.paulosalonso.zup.adapter.repository;

import com.github.paulosalonso.zup.adapter.repository.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    boolean existsByCustomerIdAndId(Long customerId, Long id);
    Optional<ContactEntity> findByCustomerIdAndId(Long customerId, Long id);
    List<ContactEntity> findByCustomerId(Long customerId);
}

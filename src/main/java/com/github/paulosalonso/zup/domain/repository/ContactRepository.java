package com.github.paulosalonso.zup.domain.repository;

import com.github.paulosalonso.zup.domain.model.Contact;
import com.github.paulosalonso.zup.domain.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {

    List<Contact> findByCustomerId(Long customerId);
    Optional<Contact> findByIdAndCustomerId(Long id, Long customerId);
    boolean existsByIdAndCustomerId(Long id, Long customerId);
    void deleteByIdAndCustomerId(Long id, Long customerId);

}

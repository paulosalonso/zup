package com.github.paulosalonso.zup.adapter.repository.contact;

import com.github.paulosalonso.zup.adapter.repository.customer.CustomerEntity;
import com.github.paulosalonso.zup.domain.ContactType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "Contact")
@Table(name = "contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @NotBlank
    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static Builder of() {
        return new Builder();
    }

    public static final class Builder {

        private ContactEntity contact;

        private Builder() {
            contact = new ContactEntity();
        }

        public Builder id(Long id) {
            contact.setId(id);
            return this;
        }

        public Builder customer(CustomerEntity customer) {
            contact.setCustomer(customer);
            return this;
        }

        public Builder type (ContactType type) {
            contact.setType(type);
            return this;
        }

        public Builder contact(String contact) {
            this.contact.setContact(contact);
            return this;
        }

        public ContactEntity build() {
            return contact;
        }
    }
}

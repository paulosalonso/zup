package com.github.paulosalonso.zup.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return Objects.equals(id, contact1.id) &&
                Objects.equals(customer, contact1.customer) &&
                type == contact1.type &&
                Objects.equals(contact, contact1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, type, contact);
    }

    public static final class Builder {

        private Contact contact;

        private Builder() {
            contact = new Contact();
        }

        public Builder id(Long id) {
            contact.setId(id);
            return this;
        }

        public Builder customer(Customer customer) {
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

        public Contact build() {
            return contact;
        }
    }
}

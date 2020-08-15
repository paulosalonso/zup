package com.github.paulosalonso.zup.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class ContactId {

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private ContactType type;
    
    public static Builder of() {
        return new Builder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactId contactId = (ContactId) o;
        return Objects.equals(customer, contactId.customer) &&
                type == contactId.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, type);
    }
    
    public static final class Builder {

        private ContactId contactId;

        private Builder() {
            contactId = new ContactId();
        }

        public Builder customer(Customer customer) {
            contactId.setCustomer(customer);
            return this;
        }

        public Builder type (ContactType type) {
            contactId.setType(type);
            return this;
        }

        public ContactId build() {
            return contactId;
        }
    }
}

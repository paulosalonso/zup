package com.github.paulosalonso.zup.domain;

public class Contact {

    private Long id;
    private Customer customer;
    private ContactType type;
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

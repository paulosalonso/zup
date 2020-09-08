package com.github.paulosalonso.zup.adapter.controller.model.contact;

import com.github.paulosalonso.zup.domain.ContactType;

public class ContactResponseAdapter {

    private Long id;
    private Long customerId;
    private ContactType type;
    private String contact;

    public static Builder of() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public ContactType getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public static final class Builder {

        private ContactResponseAdapter contactResponseAdapter;

        private Builder() {
            this.contactResponseAdapter = new ContactResponseAdapter();
        }

        public Builder id(Long id) {
            contactResponseAdapter.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            contactResponseAdapter.customerId = customerId;
            return this;
        }

        public Builder type(ContactType type) {
            contactResponseAdapter.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactResponseAdapter.contact = contact;
            return this;
        }

        public ContactResponseAdapter build() {
            return contactResponseAdapter;
        }
    }
}

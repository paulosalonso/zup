package com.github.paulosalonso.zup.adapter.controller.model.contact;

import com.github.paulosalonso.zup.domain.ContactType;

public class ContactUpdateAdapter {

    private ContactType type;
    private String contact;

    public static Builder of() {
        return new Builder();
    }

    public ContactType getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    public static final class Builder {

        private ContactUpdateAdapter contactVO;

        private Builder() {
            this.contactVO = new ContactUpdateAdapter();
        }

        public Builder type(ContactType type) {
            contactVO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactVO.contact = contact;
            return this;
        }

        public ContactUpdateAdapter build() {
            return contactVO;
        }
    }
}

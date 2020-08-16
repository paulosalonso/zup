package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;

public class ContactVO {

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

        private ContactVO contactVO;

        private Builder() {
            this.contactVO = new ContactVO();
        }

        public Builder id(Long id) {
            contactVO.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            contactVO.customerId = customerId;
            return this;
        }

        public Builder type(ContactType type) {
            contactVO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactVO.contact = contact;
            return this;
        }

        public ContactVO build() {
            return contactVO;
        }
    }
}

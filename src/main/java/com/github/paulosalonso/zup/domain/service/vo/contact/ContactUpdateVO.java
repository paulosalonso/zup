package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;

public class ContactUpdateVO {

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

        private ContactUpdateVO contactVO;

        private Builder() {
            this.contactVO = new ContactUpdateVO();
        }

        public Builder type(ContactType type) {
            contactVO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactVO.contact = contact;
            return this;
        }

        public ContactUpdateVO build() {
            return contactVO;
        }
    }
}

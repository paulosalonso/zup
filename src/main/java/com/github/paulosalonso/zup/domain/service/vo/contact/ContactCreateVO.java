package com.github.paulosalonso.zup.domain.service.vo.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;
import com.github.paulosalonso.zup.domain.service.vo.customer.CustomerVO;

public class ContactCreateVO {

    private Long customerId;
    private ContactType type;
    private String contact;

    public static Builder of() {
        return new Builder();
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

        private ContactCreateVO contactVO;

        private Builder() {
            this.contactVO = new ContactCreateVO();
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

        public ContactCreateVO build() {
            return contactVO;
        }
    }
}

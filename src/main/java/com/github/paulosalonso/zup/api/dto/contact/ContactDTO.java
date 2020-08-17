package com.github.paulosalonso.zup.api.dto.contact;

import com.github.paulosalonso.zup.domain.model.ContactType;
import io.swagger.annotations.ApiModel;

@ApiModel("Contact")
public class ContactDTO {

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

        private ContactDTO contactDTO;

        private Builder() {
            this.contactDTO = new ContactDTO();
        }

        public Builder id(Long id) {
            contactDTO.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            contactDTO.customerId = customerId;
            return this;
        }

        public Builder type(ContactType type) {
            contactDTO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactDTO.contact = contact;
            return this;
        }

        public ContactDTO build() {
            return contactDTO;
        }
    }
}

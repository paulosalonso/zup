package com.github.paulosalonso.zup.adapter.api.dto.contact;

import com.github.paulosalonso.zup.domain.ContactType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ContactUpdate")
public class ContactUpdateDTO {

    @ApiModelProperty(example = "EMAIL", required = true)
    private ContactType type;

    @ApiModelProperty(example = "mail@mail.com", required = true)
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

        private ContactUpdateDTO contactVO;

        private Builder() {
            this.contactVO = new ContactUpdateDTO();
        }

        public Builder type(ContactType type) {
            contactVO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactVO.contact = contact;
            return this;
        }

        public ContactUpdateDTO build() {
            return contactVO;
        }
    }
}

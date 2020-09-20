package com.github.paulosalonso.zup.application.api.v1.model.contact;

import com.github.paulosalonso.zup.domain.ContactType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Contact")
public class ContactResponseDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1")
    private Long customerId;

    @ApiModelProperty(example = "EMAIL")
    private ContactType type;

    @ApiModelProperty(example = "mail@mail.com")
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

        private ContactResponseDTO contactResponseDTO;

        private Builder() {
            this.contactResponseDTO = new ContactResponseDTO();
        }

        public Builder id(Long id) {
            contactResponseDTO.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            contactResponseDTO.customerId = customerId;
            return this;
        }

        public Builder type(ContactType type) {
            contactResponseDTO.type = type;
            return this;
        }

        public Builder contact(String contact) {
            contactResponseDTO.contact = contact;
            return this;
        }

        public ContactResponseDTO build() {
            return contactResponseDTO;
        }
    }
}

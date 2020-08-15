package com.github.paulosalonso.zup.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Contact {

    @EmbeddedId
    private ContactId id;

    @NotBlank
    private String contact;

    public ContactId getId() {
        return id;
    }

    public void setId(ContactId id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return Objects.equals(id, contact1.id) &&
                Objects.equals(contact, contact1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact);
    }
    
    public static final class Builder {

        private Contact contact;

        private Builder() {
            contact = new Contact();
        }

        public Builder id(ContactId id) {
            contact.setId(id);
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

package com.github.paulosalonso.zup.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Entity
public class City {

	@Id
    private String ibgeCode;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 2)
    private String state;

    public static Builder of() {
        return new Builder();
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(ibgeCode, city.ibgeCode) &&
                Objects.equals(name, city.name) &&
                Objects.equals(state, city.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibgeCode, name, state);
    }
    
    public static final class Builder {

        private City city;

        private Builder() {
            city = new City();
        }

        public Builder ibgeCode(String ibgeCode) {
            city.setIbgeCode(ibgeCode);
            return this;
        }

        public Builder name(String name) {
            city.setName(name);
            return this;
        }

        public Builder state(String state) {
            city.setState(state);
            return this;
        }

        public City build() {
            return city;
        }
    }
}

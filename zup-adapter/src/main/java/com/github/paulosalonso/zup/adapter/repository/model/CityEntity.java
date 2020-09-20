package com.github.paulosalonso.zup.adapter.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.util.Objects;


@Entity(name = "City")
@Table(name = "city")
public class CityEntity {

	@Id
    private String ibgeCode;

//    @NotBlank
//    @Size(max = 30)
    private String name;

//    @NotBlank
//    @Size(max = 2)
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
    
    public static final class Builder {

        private CityEntity city;

        private Builder() {
            city = new CityEntity();
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

        public CityEntity build() {
            return city;
        }
    }
}

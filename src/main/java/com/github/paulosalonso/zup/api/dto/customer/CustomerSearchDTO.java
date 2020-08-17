package com.github.paulosalonso.zup.api.dto.customer;

import com.github.paulosalonso.zup.api.dto.SearchDTO;
import com.github.paulosalonso.zup.domain.model.Gender;
import com.github.paulosalonso.zup.domain.service.vo.SearchVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class CustomerSearchDTO extends SearchDTO {

    @ApiModelProperty("Returns customers where the name contains this value")
    private String name;

    @ApiModelProperty("Returns customers where the cpf is equal to this value")
    private String cpf;

    @ApiModelProperty("Returns customers where the gender is equal to this value")
    private Gender gender;

    @ApiModelProperty("Returns customers where the birthDate is equal to this value")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    public static Builder of() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public static final class Builder {

        private final CustomerSearchDTO customerSearchVO;

        private Builder() {
            customerSearchVO = new CustomerSearchDTO();
        }

        public Builder page(int page) {
            customerSearchVO.setPage(page);
            return this;
        }

        public Builder size(int size) {
            customerSearchVO.setSize(size);
            return this;
        }

        public Builder order(List<String> order) {
            customerSearchVO.setOrder(order);
            return this;
        }

        public Builder name(String name) {
            customerSearchVO.name = name;
            return this;
        }

        public Builder cpf(String cpf) {
            customerSearchVO.cpf = cpf;
            return this;
        }

        public Builder gender(Gender gender) {
            customerSearchVO.gender = gender;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            customerSearchVO.birthDate = birthDate;
            return this;
        }

        public CustomerSearchDTO build() {
            return customerSearchVO;
        }

    }
}

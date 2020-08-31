package com.github.paulosalonso.zup.adapter.api.controller;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityResponseDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.AddressDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.customer.CustomerUpdateDTO;
import com.github.paulosalonso.zup.domain.Gender;
import com.github.paulosalonso.zup.domain.PostalCodeInfo;
import com.github.paulosalonso.zup.usecase.port.city.GetPostalCodeInfoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomerControllerIT extends BaseIT {

    @MockBean
    private GetPostalCodeInfoPort getPostalCodeInfoPort;

    @BeforeEach
    public void setUp() {
        super.setUp();
        createCity();
    }

    @Test
    public void whenSearchCustomersWithoutParametersThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(2))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue(), notNullValue()))
                .body("content.name", hasItems("name-a", "name-b"))
                .body("content.cpf", hasItems("cpf-a", "cpf-b"))
                .body("content.address.city.ibgeCode", contains("3550308", "3550308"))
                .body("content.address.street", contains("street", "street"))
                .body("content.address.number", contains("number", "number"))
                .body("content.address.complement", contains("complement", "complement"))
                .body("content.address.district", contains("district", "district"))
                .body("content.address.postalCode", contains("00000000", "00000000"))
                .body("content.gender", hasItems("MALE", "FEMALE"))
                .body("content.birthDate", hasItems("1988-02-26", "1991-12-29"));
    }

    @Test
    public void whenSearchFirstPageOfUnorderedCustomersThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchSecondPageOfUnorderedCustomersThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(1))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-b"))
                .body("content.cpf", hasItems("cpf-b"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("FEMALE"))
                .body("content.birthDate", hasItems("1991-12-29"));
    }

    @Test
    public void whenSearchFirstPageOfCustomersAscendedByNameThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("order", "name")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchFirstPageOfCustomersDescendedByNameThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("order", "name.desc")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-b"))
                .body("content.cpf", hasItems("cpf-b"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("FEMALE"))
                .body("content.birthDate", hasItems("1991-12-29"));
    }

    @Test
    public void whenSearchCustomersFilteredByNameThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("name", "name-a")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchCustomersFilteredByCpfThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("cpf", "cpf-a")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchCustomersFilteredByGenderThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("gender", "MALE")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchCustomersFilteredByBirthDateThenReturnOk() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("birthDate", "1988-02-26")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains("3550308"))
                .body("content.address.street", contains("street"))
                .body("content.address.number", contains("number"))
                .body("content.address.complement", contains("complement"))
                .body("content.address.district", contains("district"))
                .body("content.address.postalCode", contains("00000000"))
                .body("content.gender", hasItems("MALE"))
                .body("content.birthDate", hasItems("1988-02-26"));
    }

    @Test
    public void whenSearchCitiesFilteredThenReturnOkWithEmptyList() {
        createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateDTO.of()
                .name("name-b")
                .cpf("cpf-b")
                .gender(Gender.FEMALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1991, Month.DECEMBER, 29))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("cpf", "cpf-c")
                .when()
                .get("/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(0))
                .body("totalPages", equalTo(0))
                .body("totalSize", equalTo(0))
                .body("content", hasSize(0));
    }

    @Test
    public void whenReadCustomerThenReturnOk() {
        Integer id = createCustomer(CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(id))
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo("3550308"))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo("00000000"))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));
    }

    @Test
    public void whenReadNonExistentCustomerThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/customers/{anyId}", 1)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", equalTo("Not found"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenCreateCityThenReturnCreated() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .when()
                .post("/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo("3550308"))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo("00000000"))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));
    }

    @Test
    public void whenCreateCustomerWithoutAddressCityTheReturnOk() {
        mockViaCepJoinville();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(AddressDTO.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode("89201110")
                                .build())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .when()
                .post("/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo("4209102"))
                .body("address.city.name", equalTo("Joinville"))
                .body("address.city.state", equalTo("SC"))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo("89201110"))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));

        verify(getPostalCodeInfoPort, times(2)).getPostalCodeInfo("89201110");
    }

    @Test
    public void whenCreateCustomerWithDuplicatedCpfThenReturnBadRequest() {
        CustomerCreateDTO customerCreateDTO = CustomerCreateDTO.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateDTO)
                .when()
                .post("/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateDTO)
                .when()
                .post("/customers")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("There is already a customer with this cpf"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenCreateCustomerWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .address(AddressDTO.of().build())
                        .build())
                .when()
                .post("/customers")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(5))
                .body("violations.context", hasItems("name", "cpf", "gender",
                        "address.street", "address.postalCode"));
//                .body("violations.message", hasItems("não deve ser nulo", "não deve estar em branco"));
    }

    @Test
    public void whenUpdateCustomerThenReturnOk() {
        Integer id = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .name("name")
                        .cpf("cpf")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .post("/customers")
                .path("id");

        CustomerUpdateDTO customerUpdateDTO = CustomerUpdateDTO.of()
                .name("name-updated")
                .gender(Gender.FEMALE)
                .address(AddressDTO.of()
                        .city(CityResponseDTO.of()
                                .ibgeCode("3550308")
                                .build())
                        .street("street-updated")
                        .number("number-updated")
                        .complement("complement-updated")
                        .district("district-updated")
                        .postalCode("11111111")
                        .build())
                .build();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerUpdateDTO)
                .when()
                .put("/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-updated"))
                .body("cpf", equalTo("cpf"))
                .body("address.city.ibgeCode", equalTo("3550308"))
                .body("address.street", equalTo("street-updated"))
                .body("address.number", equalTo("number-updated"))
                .body("address.complement", equalTo("complement-updated"))
                .body("address.district", equalTo("district-updated"))
                .body("address.postalCode", equalTo("11111111"))
                .body("gender", equalTo("FEMALE"))
                .body("birthDate", equalTo("1988-02-26"));
    }

    @Test
    public void whenUpdateCustomerWithoutAddressCityTheReturnOk() {
        mockViaCepJoinville();
        mockViaCepSaoPaulo();

        Integer id = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(AddressDTO.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode("89201110")
                                .build())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .post("/customers")
                .path("id");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateDTO.of()
                        .name("name-a")
                        .gender(Gender.MALE)
                        .address(AddressDTO.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode("01001000")
                                .build())
                        .build())
                .when()
                .put("/customers/{id}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo("3550308"))
                .body("address.city.name", equalTo("São Paulo"))
                .body("address.city.state", equalTo("SP"))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo("01001000"))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));

        verify(getPostalCodeInfoPort, times(2)).getPostalCodeInfo("89201110");
        verify(getPostalCodeInfoPort).getPostalCodeInfo("01001000");
    }

    @Test
    public void whenUpdateCustomerWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateDTO.of()
                        .address(AddressDTO.of().build())
                        .build())
                .when()
                .put("/customers/{anyId}", 1)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(4))
                .body("violations.context", hasItems("name", "gender", "address.street", "address.postalCode"));
//                .body("violations.message", hasItems("não deve ser nulo", "não deve estar em branco"));
    }

    @Test
    public void whenUpdateNonExistentCustomerThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateDTO.of()
                        .name("name")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .build())
                .when()
                .put("/customers/{anyId}", 1)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", equalTo("Not found"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenDeleteCityThenReturnNoContent() {
        Integer id = createCustomer(CustomerCreateDTO.of()
                .name("name")
                .cpf("cpf")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        when()
                .delete("/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .accept(JSON)
                .when()
                .get("/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenDeleteNonExistentCityThenReturnNoContent() {
        when()
                .delete("/customers/{customerId}", 1)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private Integer createCustomer(CustomerCreateDTO customerCreateDTO) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateDTO)
                .post("/customers")
                .path("id");
    }

    private String createCity() {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .post("/cities")
                .path("ibgeCode");
    }

    private AddressDTO buildAddress() {
        return AddressDTO.of()
                .city(CityResponseDTO.of()
                        .ibgeCode("3550308")
                        .build())
                .street("street")
                .number("number")
                .complement("complement")
                .district("district")
                .postalCode("00000000")
                .build();
    }

    private void mockViaCepSaoPaulo() {
        PostalCodeInfo postalCodeInfo = PostalCodeInfo.of()
                .street("Praça da Sé")
                .stateInitials("SP")
                .postalCode("01001000")
                .ibgeCode("3550308")
                .district("Sé")
                .complement("lado ímpar")
                .cityName("São Paulo")
                .build();

        Mockito.when(getPostalCodeInfoPort.getPostalCodeInfo("01001000"))
                .thenReturn(Optional.of(postalCodeInfo));
    }

    private void mockViaCepJoinville() {
        PostalCodeInfo postalCodeInfo = PostalCodeInfo.of()
                .street("Rua Padre Carlos")
                .stateInitials("SC")
                .postalCode("89201110")
                .ibgeCode("4209102")
                .district("Centro")
                .complement("")
                .cityName("Joinville")
                .build();

        Mockito.when(getPostalCodeInfoPort.getPostalCodeInfo("89201110"))
                .thenReturn(Optional.of(postalCodeInfo));
    }

}

package com.github.paulosalonso.zup.application.api.v1.controller;

import com.github.paulosalonso.zup.application.api.BaseIT;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityResponseAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.AddressAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.customer.CustomerUpdateAdapter;
import com.github.paulosalonso.zup.domain.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Mockito.verify;

public class CustomerAPIControllerIT extends BaseIT {

    @BeforeEach
    public void setUp() {
        super.setUp();
        createSaoPauloCity();
    }

    @Test
    public void whenSearchCustomersWithoutParametersThenReturnOk() {
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(2))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue(), notNullValue()))
                .body("content.name", hasItems("name-a", "name-b"))
                .body("content.cpf", hasItems("cpf-a", "cpf-b"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE, SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(1))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-b"))
                .body("content.cpf", hasItems("cpf-b"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .queryParam("sort", "name,desc")
                .when()
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-b"))
                .body("content.cpf", hasItems("cpf-b"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.id", hasItems(notNullValue()))
                .body("content.name", hasItems("name-a"))
                .body("content.cpf", hasItems("cpf-a"))
                .body("content.address.city.ibgeCode", contains(SAO_PAULO_IBGE_CODE))
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
        createCustomer(CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers")
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
        Integer id = createCustomer(CustomerCreateAdapter.of()
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
                .get("/v1/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(id))
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
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
                .get("/v1/customers/{anyId}", 1)
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
                .body(CustomerCreateAdapter.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .when()
                .post("/v1/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
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
                .body(CustomerCreateAdapter.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(AddressAdapter.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode(JOINVILLE_POSTAL_CODE)
                                .build())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .when()
                .post("/v1/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo(JOINVILLE_IBGE_CODE))
                .body("address.city.name", equalTo("Joinville"))
                .body("address.city.state", equalTo("SC"))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo(JOINVILLE_POSTAL_CODE))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));

        verifyViaCepJoinville(2);
    }

    @Test
    public void whenCreateCustomerWithDuplicatedCpfThenReturnBadRequest() {
        CustomerCreateAdapter customerCreateAdapter = CustomerCreateAdapter.of()
                .name("name-a")
                .cpf("cpf-a")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateAdapter)
                .when()
                .post("/v1/customers")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateAdapter)
                .when()
                .post("/v1/customers")
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
                .body(CustomerCreateAdapter.of()
                        .address(AddressAdapter.of().build())
                        .build())
                .when()
                .post("/v1/customers")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(5))
                .body("violations.context", hasItems("name", "cpf", "gender", "address.street", "address.postalCode"))
                .body("violations.message", hasItems("must not be null", "must not be blank"));
    }

    @Test
    public void whenUpdateCustomerThenReturnOk() {
        Integer id = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateAdapter.of()
                        .name("name")
                        .cpf("cpf")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .post("/v1/customers")
                .path("id");

        CustomerUpdateAdapter customerUpdateAdapter = CustomerUpdateAdapter.of()
                .name("name-updated")
                .gender(Gender.FEMALE)
                .address(AddressAdapter.of()
                        .city(CityResponseAdapter.of()
                                .ibgeCode(SAO_PAULO_IBGE_CODE)
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
                .body(customerUpdateAdapter)
                .when()
                .put("/v1/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-updated"))
                .body("cpf", equalTo("cpf"))
                .body("address.city.ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
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
                .body(CustomerCreateAdapter.of()
                        .name("name-a")
                        .cpf("cpf-a")
                        .gender(Gender.MALE)
                        .address(AddressAdapter.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode(JOINVILLE_POSTAL_CODE)
                                .build())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .post("/v1/customers")
                .path("id");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateAdapter.of()
                        .name("name-a")
                        .gender(Gender.MALE)
                        .address(AddressAdapter.of()
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode(SAO_PAULO_POSTAL_CODE)
                                .build())
                        .build())
                .when()
                .put("/v1/customers/{id}", id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("name", equalTo("name-a"))
                .body("cpf", equalTo("cpf-a"))
                .body("address.city.ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
                .body("address.city.name", equalTo(SAO_PAULO_CITY_NAME))
                .body("address.city.state", equalTo(SP_STATE_INITIALS))
                .body("address.street", equalTo("street"))
                .body("address.number", equalTo("number"))
                .body("address.complement", equalTo("complement"))
                .body("address.district", equalTo("district"))
                .body("address.postalCode", equalTo(SAO_PAULO_POSTAL_CODE))
                .body("gender", equalTo("MALE"))
                .body("birthDate", equalTo("1988-02-26"));

        verifyViaCepJoinville(2);
        verifyViaCepSaoPaulo(1);
    }

    @Test
    public void whenUpdateCustomerWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateAdapter.of()
                        .address(AddressAdapter.of().build())
                        .build())
                .when()
                .put("/v1/customers/{anyId}", 1)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(4))
                .body("violations.context", hasItems("name", "gender", "address.street", "address.postalCode"))
                .body("violations.message", hasItems("must not be null", "must not be blank", "must not be blank"));
    }

    @Test
    public void whenUpdateNonExistentCustomerThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerUpdateAdapter.of()
                        .name("name")
                        .gender(Gender.MALE)
                        .address(buildAddress())
                        .build())
                .when()
                .put("/v1/customers/{anyId}", 1)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", equalTo("Not found"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenDeleteCityThenReturnNoContent() {
        Integer id = createCustomer(CustomerCreateAdapter.of()
                .name("name")
                .cpf("cpf")
                .gender(Gender.MALE)
                .address(buildAddress())
                .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                .build());

        when()
                .delete("/v1/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .accept(JSON)
                .when()
                .get("/v1/customers/{customerId}", id)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenDeleteNonExistentCityThenReturnNoContent() {
        when()
                .delete("/v1/customers/{customerId}", 1)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private Integer createCustomer(CustomerCreateAdapter customerCreateAdapter) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(customerCreateAdapter)
                .post("/v1/customers")
                .path("id");
    }

    private String createSaoPauloCity() {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .post("/v1/cities")
                .path("ibgeCode");
    }

    private AddressAdapter buildAddress() {
        return AddressAdapter.of()
                .city(CityResponseAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .build())
                .street("street")
                .number("number")
                .complement("complement")
                .district("district")
                .postalCode("00000000")
                .build();
    }

}

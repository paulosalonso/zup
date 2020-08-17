package com.github.paulosalonso.zup.api.controller;

import com.github.paulosalonso.zup.api.dto.city.CityDTO;
import com.github.paulosalonso.zup.api.dto.contact.ContactCreateDTO;
import com.github.paulosalonso.zup.api.dto.customer.AddressDTO;
import com.github.paulosalonso.zup.api.dto.customer.CustomerCreateDTO;
import com.github.paulosalonso.zup.domain.model.ContactType;
import com.github.paulosalonso.zup.domain.model.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class CustomerContactControllerIT extends BaseIT {

    @Test
    public void whenGetCustomerContactsThenReturnOk() {
        Integer customerId = createCustomer();

        Integer contactId = given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .post("/customers/{customerId}/contacts", customerId)
                .path("id");

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/customers/{customerId}/contacts", customerId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", hasItems(contactId))
                .body("customerId", hasItems(customerId))
                .body("type", hasItems("EMAIL"))
                .body("contact", hasItems("mail@mail.com"));
    }

    @Test
    public void whenGetNonExistentCustomerContactsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .when()
                .post("/customers/{anyCustomerId}/contacts", 1L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenCreateContactThenReturnCreated() {
        Integer customerId = createCustomer();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .when()
                .post("/customers/{customerId}/contacts", customerId)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("customerId", equalTo(customerId))
                .body("type", equalTo("EMAIL"))
                .body("contact", equalTo("mail@mail.com"));
    }

    @Test
    public void whenCreateContactForNonExistentCustomerThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .when()
                .post("/customers/{anyCustomerId}/contacts", 1L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenUpdateContactThenReturnOk() {
        Integer customerId = createCustomer();

        Integer contactId = given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .when()
                .post("/customers/{customerId}/contacts", customerId)
                .path("id");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.PHONE)
                        .contact("(00) 00000-0000")
                        .build())
                .when()
                .put("/customers/{customerId}/contacts/{contactId}", customerId, contactId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id",equalTo(contactId))
                .body("customerId", equalTo(customerId))
                .body("type", equalTo("PHONE"))
                .body("contact", equalTo("(00) 00000-0000"));
    }

    @Test
    public void whenUpdateContactFromNonExistentCustomerThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.PHONE)
                        .contact("(00) 00000-0000")
                        .build())
                .when()
                .put("/customers/{anyCustomerId}/contacts/{anyContactId}", 1L, 1L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenUpdateNonExistentContactThenReturnNotFound() {
        Integer customerId = createCustomer();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.PHONE)
                        .contact("(00) 00000-0000")
                        .build())
                .when()
                .put("/customers/{customerId}/contacts/{anyContactId}", customerId, 1L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenDeleteContactThenReturnNoContent() {
        Integer customerId = createCustomer();

        Integer contactId = given()
                .contentType(JSON)
                .accept(JSON)
                .body(ContactCreateDTO.of()
                        .type(ContactType.EMAIL)
                        .contact("mail@mail.com")
                        .build())
                .when()
                .post("/customers/{customerId}/contacts", customerId)
                .path("id");

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .delete("/customers/{customerId}/contacts/{contactId}", customerId, contactId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/customers/{customerId}/contacts", customerId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(0));
    }

    @Test
    public void whenDeleteContactFromNonExistentCustomerThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .delete("/customers/{anyCustomerId}/contacts/{anyContactId}", 1L, 1L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenDeleteNonExistentContactThenReturnNotFound() {
        Integer customerId = createCustomer();

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .delete("/customers/{customerId}/contacts/{anyContactId}", customerId, 1L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private Integer createCustomer() {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(CustomerCreateDTO.of()
                        .name("name")
                        .cpf("cpf")
                        .gender(Gender.MALE)
                        .address(AddressDTO.of()
                                .city(CityDTO.of()
                                        .ibgeCode("4209102")
                                        .build())
                                .street("street")
                                .number("number")
                                .complement("complement")
                                .district("district")
                                .postalCode("89217008")
                                .build())
                        .birthDate(LocalDate.of(1988, Month.FEBRUARY, 26))
                        .build())
                .post("/customers")
                .path("id");
    }

}

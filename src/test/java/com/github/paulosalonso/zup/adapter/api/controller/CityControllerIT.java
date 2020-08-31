package com.github.paulosalonso.zup.adapter.api.controller;

import com.github.paulosalonso.zup.adapter.api.dto.city.CityCreateDTO;
import com.github.paulosalonso.zup.adapter.api.dto.city.CityUpdateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class CityControllerIT extends BaseIT {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void whenSearchCitiesWithoutParametersThenReturnOk() {
        createCity(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("São Paulo")
                        .state("SP")
                        .build());

        createCity(CityCreateDTO.of()
                        .ibgeCode("4209102")
                        .name("Joinville")
                        .state("SC")
                        .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(2))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems("3550308", "4209102"))
                .body("content.name", hasItems("São Paulo", "Joinville"))
                .body("content.state", hasItems("SP", "SC"));
    }

    @Test
    public void whenSearchFirstPageOfUnorderedCitiesThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems("3550308"))
                .body("content.name", hasItems("São Paulo"))
                .body("content.state", hasItems("SP"));
    }

    @Test
    public void whenSearchSecondPageOfUnorderedCitiesThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(1))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems("4209102"))
                .body("content.name", hasItems("Joinville"))
                .body("content.state", hasItems("SC"));
    }

    @Test
    public void whenSearchFirstPageOfCitiesAscendedByNameThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("order", "name")
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems("4209102"))
                .body("content.name", hasItems("Joinville"))
                .body("content.state", hasItems("SC"));
    }

    @Test
    public void whenSearchFirstPageOfCitiesDescendedByNameThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("order", "name.desc")
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems("3550308"))
                .body("content.name", hasItems("São Paulo"))
                .body("content.state", hasItems("SP"));
    }

    @Test
    public void whenSearchCitiesFilteredByNameThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("name", "São Paulo")
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.ibgeCode", hasItems("3550308"))
                .body("content.name", hasItems("São Paulo"))
                .body("content.state", hasItems("SP"));
    }

    @Test
    public void whenSearchCitiesFilteredByStateThenReturnOk() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("state", "SC")
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.ibgeCode", hasItems("4209102"))
                .body("content.name", hasItems("Joinville"))
                .body("content.state", hasItems("SC"));
    }

    @Test
    public void whenSearchCitiesFilteredThenReturnOkWithEmptyList() {
        createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build());

        createCity(CityCreateDTO.of()
                .ibgeCode("4209102")
                .name("Joinville")
                .state("SC")
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("state", "RS")
                .when()
                .get("/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(0))
                .body("totalPages", equalTo(0))
                .body("totalSize", equalTo(0))
                .body("content", hasSize(0));
    }

    @Test
    public void whenReadCityThenReturnOk() {
        String ibgeCode = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .post("/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ibgeCode", equalTo("3550308"))
                .body("name", equalTo("São Paulo"))
                .body("state", equalTo("SP"));
    }

    @Test
    public void whenReadNonExistentCityThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/cities/{ibgeCode}", "3550308")
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
                .body(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .when()
                .post("/cities")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("ibgeCode", equalTo("3550308"))
                .body("name", equalTo("São Paulo"))
                .body("state", equalTo("SP"));
    }

    @Test
    public void whenCreateDuplicatedCityThenReturnBadRequest() {
        CityCreateDTO cityCreateDTO = CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SP")
                .build();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateDTO)
                .when()
                .post("/cities")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateDTO)
                .when()
                .post("/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo(String.format(
                        "City with ibge code %s already exists.", cityCreateDTO.getIbgeCode())))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenCreateCityWithInvalidValuesThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .ibgeCode("35503088")
                        .name("São Paulo")
                        .state("SPO")
                        .build())
                .when()
                .post("/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(2))
                .body("violations.context", hasItems("state", "ibgeCode"));
//                .body("violations.message",
//                        hasItems("deve corresponder a \"[A-Z][A-Z]{2}\"", "deve corresponder a \"[0-9]{7}\""));
    }

    @Test
    public void whenCreateCityWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .build())
                .when()
                .post("/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(3))
                .body("violations.context", hasItems("state", "ibgeCode", "name"));
//                .body("violations.message",
//                        hasItems("não deve ser nulo", "não deve estar em branco"));
    }

    @Test
    public void whenUpdateCityThenReturnOk() {
        String ibgeCode = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("S Paulo")
                        .state("SP")
                        .build())
                .post("/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateDTO.of()
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .when()
                .put("/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ibgeCode", equalTo("3550308"))
                .body("name", equalTo("São Paulo"))
                .body("state", equalTo("SP"));
    }

    @Test
    public void whenUpdateCityWithInvalidValuesThenReturnBadRequest() {
        String ibgeCode = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateDTO.of()
                        .ibgeCode("3550308")
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .post("/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateDTO.of()
                        .name("São Paulo")
                        .state("SPO")
                        .build())
                .when()
                .put("/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(1))
                .body("violations.context", hasItems("state"));
//                .body("violations.message", hasItems("deve corresponder a \"[A-Z][A-Z]{2}\""));
    }

    @Test
    public void whenUpdateCityWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateDTO.of()
                        .build())
                .when()
                .put("/cities/{ibgeCode}", "any-ibge-code")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(2))
                .body("violations.context", hasItems("state", "name"));
//                .body("violations.message",
//                        hasItems("não deve ser nulo", "não deve estar em branco"));
    }

    @Test
    public void whenUpdateNonExistentCityThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateDTO.of()
                        .name("São Paulo")
                        .state("SP")
                        .build())
                .when()
                .put("/cities/{ibgeCode}", "3550308")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", equalTo("Not found"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenDeleteCityThenReturnNoContent() {
        String ibgeCode = createCity(CityCreateDTO.of()
                .ibgeCode("3550308")
                .name("São Paulo")
                .state("SC")
                .build());

        when()
                .delete("/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .accept(JSON)
                .when()
                .get("/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenDeleteNonExistentCityThenReturnNotFound() {
        when()
                .delete("/cities/{ibgeCode}", "3550308")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private String createCity(CityCreateDTO cityCreateDTO) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateDTO)
                .post("/cities")
                .path("ibgeCode");
    }

}

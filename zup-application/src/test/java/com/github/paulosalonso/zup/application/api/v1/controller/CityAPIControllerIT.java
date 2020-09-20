package com.github.paulosalonso.zup.application.api.v1.controller;

import com.github.paulosalonso.zup.application.api.BaseIT;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityCreateAdapter;
import com.github.paulosalonso.zup.adapter.controller.model.city.CityUpdateAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class CityAPIControllerIT extends BaseIT {

    @Test
    public void whenSearchCitiesWithoutParametersThenReturnOk() {
        createCity(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build());

        createCity(CityCreateAdapter.of()
                        .ibgeCode(JOINVILLE_IBGE_CODE)
                        .name(JOINVILLE_CITY_NAME)
                        .state(SC_STATE_INITIALS)
                        .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(2))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems(SAO_PAULO_IBGE_CODE, JOINVILLE_IBGE_CODE))
                .body("content.name", hasItems(SAO_PAULO_CITY_NAME, JOINVILLE_CITY_NAME))
                .body("content.state", hasItems(SP_STATE_INITIALS, SC_STATE_INITIALS));
    }

    @Test
    public void whenSearchFirstPageOfUnorderedCitiesThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems(SAO_PAULO_IBGE_CODE))
                .body("content.name", hasItems(SAO_PAULO_CITY_NAME))
                .body("content.state", hasItems(SP_STATE_INITIALS));
    }

    @Test
    public void whenSearchSecondPageOfUnorderedCitiesThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(1))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems(JOINVILLE_IBGE_CODE))
                .body("content.name", hasItems(JOINVILLE_CITY_NAME))
                .body("content.state", hasItems(SC_STATE_INITIALS));
    }

    @Test
    public void whenSearchFirstPageOfCitiesAscendedByNameThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("sort", "name")
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems(JOINVILLE_IBGE_CODE))
                .body("content.name", hasItems(JOINVILLE_CITY_NAME))
                .body("content.state", hasItems(SC_STATE_INITIALS));
    }

    @Test
    public void whenSearchFirstPageOfCitiesDescendedByNameThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("size", 1)
                .queryParam("order", "name.desc")
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(2))
                .body("totalSize", equalTo(2))
                .body("content.ibgeCode", hasItems(SAO_PAULO_IBGE_CODE))
                .body("content.name", hasItems(SAO_PAULO_CITY_NAME))
                .body("content.state", hasItems(SP_STATE_INITIALS));
    }

    @Test
    public void whenSearchCitiesFilteredByNameThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("name", SAO_PAULO_CITY_NAME)
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.ibgeCode", hasItems(SAO_PAULO_IBGE_CODE))
                .body("content.name", hasItems(SAO_PAULO_CITY_NAME))
                .body("content.state", hasItems(SP_STATE_INITIALS));
    }

    @Test
    public void whenSearchCitiesFilteredByStateThenReturnOk() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("state", SC_STATE_INITIALS)
                .when()
                .get("/v1/cities")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("page", equalTo(0))
                .body("pageSize", equalTo(1))
                .body("totalPages", equalTo(1))
                .body("totalSize", equalTo(1))
                .body("content.ibgeCode", hasItems(JOINVILLE_IBGE_CODE))
                .body("content.name", hasItems(JOINVILLE_CITY_NAME))
                .body("content.state", hasItems(SC_STATE_INITIALS));
    }

    @Test
    public void whenSearchCitiesFilteredThenReturnOkWithEmptyList() {
        createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build());

        createCity(CityCreateAdapter.of()
                .ibgeCode(JOINVILLE_IBGE_CODE)
                .name(JOINVILLE_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        given()
                .contentType(JSON)
                .accept(JSON)
                .queryParam("state", "RS")
                .when()
                .get("/v1/cities")
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
                .body(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .post("/v1/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/v1/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
                .body("name", equalTo(SAO_PAULO_CITY_NAME))
                .body("state", equalTo(SP_STATE_INITIALS));
    }

    @Test
    public void whenReadNonExistentCityThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get("/v1/cities/{ibgeCode}", SAO_PAULO_IBGE_CODE)
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
                .body(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
                .body("name", equalTo(SAO_PAULO_CITY_NAME))
                .body("state", equalTo(SP_STATE_INITIALS));
    }

    @Test
    public void whenCreateDuplicatedCityThenReturnBadRequest() {
        CityCreateAdapter cityCreateAdapter = CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SP_STATE_INITIALS)
                .build();

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateAdapter)
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateAdapter)
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo(String.format(
                        "City with ibge code %s already exists.", cityCreateAdapter.getIbgeCode())))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenCreateCityWithInvalidValuesThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateAdapter.of()
                        .ibgeCode("35503088")
                        .name(SAO_PAULO_CITY_NAME)
                        .state("SPO")
                        .build())
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(2))
                .body("violations.context", hasItems("state", "ibgeCode"))
                .body("violations.message",
                        hasItems("must match \"[0-9]{7}\"", "must match \"[A-Z][A-Z]{2}\""));
    }

    @Test
    public void whenCreateCityWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateAdapter.of()
                        .build())
                .when()
                .post("/v1/cities")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(3))
                .body("violations.context", hasItems("state", "ibgeCode", "name"))
                .body("violations.message",
                        hasItems("must not be blank", "must not be null"));
    }

    @Test
    public void whenUpdateCityThenReturnOk() {
        String ibgeCode = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name("S Paulo")
                        .state(SP_STATE_INITIALS)
                        .build())
                .post("/v1/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateAdapter.of()
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .when()
                .put("/v1/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ibgeCode", equalTo(SAO_PAULO_IBGE_CODE))
                .body("name", equalTo(SAO_PAULO_CITY_NAME))
                .body("state", equalTo(SP_STATE_INITIALS));
    }

    @Test
    public void whenUpdateCityWithInvalidValuesThenReturnBadRequest() {
        String ibgeCode = given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityCreateAdapter.of()
                        .ibgeCode(SAO_PAULO_IBGE_CODE)
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .post("/v1/cities")
                .path("ibgeCode");

        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateAdapter.of()
                        .name(SAO_PAULO_CITY_NAME)
                        .state("SPO")
                        .build())
                .when()
                .put("/v1/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(1))
                .body("violations.context", hasItems("state"))
                .body("violations.message", hasItems("must match \"[A-Z][A-Z]{2}\""));
    }

    @Test
    public void whenUpdateCityWithoutRequiredFieldsThenReturnBadRequest() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateAdapter.of()
                        .build())
                .when()
                .put("/v1/cities/{ibgeCode}", "any-ibge-code")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", equalTo("Invalid data"))
                .body("detail", equalTo("Invalid field(s)."))
                .body("timestamp", notNullValue())
                .body("violations", hasSize(2))
                .body("violations.context", hasItems("state", "name"))
                .body("violations.message",
                        hasItems("must not be blank", "must not be null"));
    }

    @Test
    public void whenUpdateNonExistentCityThenReturnNotFound() {
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(CityUpdateAdapter.of()
                        .name(SAO_PAULO_CITY_NAME)
                        .state(SP_STATE_INITIALS)
                        .build())
                .when()
                .put("/v1/cities/{ibgeCode}", SAO_PAULO_IBGE_CODE)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", equalTo("Not found"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenDeleteCityThenReturnNoContent() {
        String ibgeCode = createCity(CityCreateAdapter.of()
                .ibgeCode(SAO_PAULO_IBGE_CODE)
                .name(SAO_PAULO_CITY_NAME)
                .state(SC_STATE_INITIALS)
                .build());

        when()
                .delete("/v1/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        given()
                .accept(JSON)
                .when()
                .get("/v1/cities/{ibgeCode}", ibgeCode)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenDeleteNonExistentCityThenReturnNotFound() {
        when()
                .delete("/v1/cities/{ibgeCode}", SAO_PAULO_IBGE_CODE)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private String createCity(CityCreateAdapter cityCreateAdapter) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(cityCreateAdapter)
                .post("/v1/cities")
                .path("ibgeCode");
    }

}

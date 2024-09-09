package org.java.apiTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.java.dto.Listing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.java.specification.Specifications.requestSpecification;

public class GetRequestTest {

    @Test
    @DisplayName("Получение объявления по его идентификатору")
    public void getListingsById() {
        Listing listing = new Listing(
            "Чехол-2",
            33,
            3452,
            new Listing.Statistics(0, 0, 0)
        );

        RestAssured.given()
            .spec(requestSpecification())
            .get("item/{id}", "7a8fe969-2a57-468e-82c9-1982d22023c5")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .assertThat()
            .body("[0].name", equalTo(listing.name()))
            .body("[0].price", equalTo(listing.price()))
            .body("[0].sellerId", equalTo(listing.sellerId()))
            .body("[0].statistics.contacts", equalTo(listing.statistics().contacts()))
            .body("[0].statistics.likes", equalTo(listing.statistics().likes()))
            .body("[0].statistics.viewCount", equalTo(listing.statistics().viewCount()));
    }

    @Test
    @DisplayName("Пытаемся получить объявление по несуществующему ID")
    public void getListingsByUnknownId() {
        RestAssured.given()
            .spec(requestSpecification())
            .get("item/{id}", "")
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Проверяем, что при получении объявлений продавца возвращается не менее одного объявления")
    public void getListingsBySellerId() {
        RestAssured.given()
            .spec(requestSpecification())
            .get("{sellerID}/item", 3452)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .assertThat()
            .body("size()", Matchers.greaterThanOrEqualTo(1));
    }
}

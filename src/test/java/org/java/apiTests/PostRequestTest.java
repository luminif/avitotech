package org.java.apiTests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.java.dto.Listing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.java.specification.Specifications.requestSpecification;

public class PostRequestTest {
    @Test
    @DisplayName("Создаем объявление с валидными данными")
    public void postRequestExpectedBody() {
        Listing listing = new Listing(
            "BMW X10",
            6560000,
            506199,
            new Listing.Statistics(1111, 2, 8)
        );

        RestAssured.given()
            .spec(requestSpecification())
            .body(listing)
            .post("item")
            .then()
            .statusCode(HttpStatus.SC_OK);
    }
}

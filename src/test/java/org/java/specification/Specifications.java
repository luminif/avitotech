package org.java.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
            .setBaseUri("https://qa-internship.avito.com/api/1/")
            .setRelaxedHTTPSValidation()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();
    }
}

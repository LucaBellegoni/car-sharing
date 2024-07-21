package org.acme.auth;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class AuthenticationFilterTest {

    @Test
    void testUnauthorizedAccess() {
        given()
                .when()
                .get("/bookings")
                .then()
                .statusCode(401);
    }

    @Test
    void testAuthorizedAccess() {
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/bookings")
                .then()
                .statusCode(200);
    }
}

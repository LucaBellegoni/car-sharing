package org.acme.endpoint;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.acme.dto.BookingDto;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class BookingResourceTest {

    @Test
    void testGetAllBookings() {
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/bookings")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", notNullValue());
    }

    @Test
    void testGetBooking() {
        long bookingId = 1; // Assicurati che questo ID esista nel tuo database di test
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/bookings/{bookingId}", bookingId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo((int) bookingId));
    }

    @Test
    void testCreateBooking() {
        BookingDto newBooking = new BookingDto();
        newBooking.setField1("value1"); // Imposta i campi necessari
        newBooking.setField2("value2");

        given()
                .header("Authorization", "Bearer valid_token")
                .contentType(ContentType.JSON)
                .body(newBooking)
                .when()
                .post("/bookings")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.id", notNullValue());
    }

    @Test
    void testUpdateBooking() {
        long bookingId = 1; // Assicurati che questo ID esista nel tuo database di test
        BookingDto updatedBooking = new BookingDto();
        updatedBooking.setField1("newValue1"); // Imposta i campi necessari
        updatedBooking.setField2("newValue2");

        given()
                .header("Authorization", "Bearer valid_token")
                .contentType(ContentType.JSON)
                .body(updatedBooking)
                .when()
                .put("/bookings/{bookingId}", bookingId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo((int) bookingId))
                .body("data.field1", equalTo("newValue1"))
                .body("data.field2", equalTo("newValue2"));
    }

    @Test
    void testDeleteBooking() {
        long bookingId = 1; // Assicurati che questo ID esista nel tuo database di test

        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .delete("/bookings/{bookingId}", bookingId)
                .then()
                .statusCode(204);

        // Verifica che il booking sia stato cancellato
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/bookings/{bookingId}", bookingId)
                .then()
                .statusCode(404);
    }
}

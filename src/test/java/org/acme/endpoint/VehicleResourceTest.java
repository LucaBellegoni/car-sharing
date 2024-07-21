package org.acme.endpoint;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.acme.dto.VehicleDto;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class VehicleResourceTest {

    @Test
    void testGetAllVehicles() {
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/vehicles")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", notNullValue());
    }

    @Test
    void testGetVehicle() {
        long vehicleId = 1; // Assicurati che questo ID esista nel tuo database di test
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/vehicles/{vehicleId}", vehicleId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo((int) vehicleId));
    }

    @Test
    void testCreateVehicle() {
        VehicleDto newVehicle = new VehicleDto();
        newVehicle.setType("value1"); // Imposta i campi necessari
        newVehicle.setManufacturer("value2"); // Imposta i campi necessari
        newVehicle.setModel("value3");
        newVehicle.setRegistrationYear(0);
        newVehicle.setPrice(0);

        given()
                .header("Authorization", "Bearer valid_token")
                .contentType(ContentType.JSON)
                .body(newVehicle)
                .when()
                .post("/vehicles")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.id", notNullValue())
                .body("data.type", equalTo("value1"))
                .body("data.manufacturer", equalTo("value2"))
                .body("data.model", equalTo("value3"))
                .body("data.registrationYear", equalTo(0))
                .body("data.price", equalTo(0.0f));
    }

    @Test
    void testUpdateVehicle() {
        long vehicleId = 1; // Assicurati che questo ID esista nel tuo database di test
        VehicleDto updatedVehicle = new VehicleDto();
        updatedVehicle.setManufacturer("newValue1"); // Imposta i campi necessari
        updatedVehicle.setModel("newValue2");

        given()
                .header("Authorization", "Bearer valid_token")
                .contentType(ContentType.JSON)
                .body(updatedVehicle)
                .when()
                .put("/vehicles/{vehicleId}", vehicleId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo((int) vehicleId))
                .body("data.manufacturer", equalTo("newValue1"))
                .body("data.model", equalTo("newValue2"));
    }

    @Test
    void testDeleteVehicle() {
        long vehicleId = 1; // Assicurati che questo ID esista nel tuo database di test

        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .delete("/vehicles/{vehicleId}", vehicleId)
                .then()
                .statusCode(204);

        // Verifica che il vehicle sia stato cancellato
        given()
                .header("Authorization", "Bearer valid_token")
                .when()
                .get("/vehicles/{vehicleId}", vehicleId)
                .then()
                .statusCode(404);
    }
}

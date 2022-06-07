package co.com.sofka.runners;

import io.opentelemetry.api.trace.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

class Prueba {
    @Test
    void pruebaPost() {
        String users = given()
                .when()
                    .get("https://restful-booker.herokuapp.com/booking")
                .then()
                    .statusCode(200)
                    .extract().body().asString();

        int id = from(users).get("[0].bookingid");
        int id1 = from(users).get("[1].bookingid");
        int usersLent = users.length();
        System.out.println("usersLent = " + usersLent);
        System.out.println("id = " + id);
        System.out.println("id1 = " + id1);

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking");

        Headers headers = response.getHeaders();
        String body = response.body().asString();

        int status = response.getStatusCode();



             given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body();
    }
}

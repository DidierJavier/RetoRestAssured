package co.com.sofka.runners;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.path.json.JsonPath.from;

class Prueba {
    @Test
    void pruebaPost() {
        String users = RestAssured
                .given()
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

        RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body();

             RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body();
    }
}

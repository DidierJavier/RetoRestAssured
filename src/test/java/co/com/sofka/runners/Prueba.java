package co.com.sofka.runners;

import co.com.sofka.jsonclass.BookingGet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.nashorn.api.scripting.JSObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.path.json.JsonPath.from;
import static net.serenitybdd.core.CurrentOS.getType;

public class Prueba {
    @Test
    public void pruebaPost() throws FileNotFoundException {
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

        Object listaJson = RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body();

        final Gson gson = new Gson();
        Reader magos = new FileReader((File) RestAssured
                .given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().body());
        Type tipoMagos = new TypeToken<List<BookingGet>>(){}.getType();
        List<BookingGet> result2 = gson.fromJson(magos, tipoMagos);
        BookingGet bookingDeLista = result2.get(0);
        bookingDeLista.getBookingid();
        System.out.println(bookingDeLista.getBookingid());
        //magoDeLista.setAlias("Monica");
        //System.out.println(magoDeLista.getAlias());
       ;
//        final Type tipoListaBookings = new TypeToken<List<BookingGet>> (){}.getType();
//        final List<BookingGet> bookings = gson.fromJson(users, tipoListaBookings);
//        for (int i = 0; i < 10; i++) {
//            System.out.println("bookings = " + bookings.get(i));
//        }
        //BookingGet bookingGetJson = bookings.get(0);
    }
}

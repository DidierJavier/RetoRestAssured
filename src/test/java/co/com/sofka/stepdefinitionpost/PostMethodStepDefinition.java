package co.com.sofka.stepdefinitionpost;

import co.com.sofka.jsonclass.RequestAutenticacion;
import co.com.sofka.jsonclass.ResponseToken;
import co.com.sofka.setup.ServiceSetupRestfulBookerPost;
import co.com.sofka.stepdefinitionget.GetMethodStepDefinition;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jxl.common.Assert;
import org.apache.hc.core5.util.Asserts;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Claves.PASSWORD;
import static co.com.sofka.util.Claves.USER;
import static co.com.sofka.util.ConstantesNumericas.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.notNullValue;

public class PostMethodStepDefinition extends ServiceSetupRestfulBookerPost {

    private static final Logger LOGGER = LogManager.getLogger(GetMethodStepDefinition.class);

    private Response response;

    private RequestSpecification request;
    @Dado("que el estudiante cuenta con el usuario {string} y la contrasena {string} validas")
    public void queElEstudianteCuentaConElUsuarioYLaContrasenaValidas(String username, String password) {
        RequestAutenticacion user = new RequestAutenticacion();
        user.setUsername(USER.clave);
        user.setPassword(PASSWORD.clave);
        try {
            generalSetup();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(user);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("el estudiante emplea el metodo post para loguearse")
    public void elEstudianteEmpleaElMetodoPostParaLoguearse() {
        try {
            response = request.when()
                    .log()
                    .all()
                    .post(LOGIN_RESOURCE);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("obtiene el token de autenticacion")
    public void obtieneElTokenDeAutenticacion() {
        response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());

        ResponseToken userResponse = response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(ResponseToken.class);
        userResponse.getToken();



        String token = response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().asString();
        LOGGER.info(token);
        LOGGER.info(from(token).get("token"));
        String token2 = token.substring(DIEZ.valor, VEINTICINCO.valor);
        Assertions.assertEquals(QUINCE.valor, token2.length());
        Matchers.hasSize(15);
    }
}

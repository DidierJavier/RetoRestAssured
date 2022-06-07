package co.com.sofka.stepdefinitionpost;

import co.com.sofka.jsonclass.RequestAutenticacion;
import co.com.sofka.setup.ServiceSetupRestfulBookerPost;
import co.com.sofka.stepdefinitionget.GetMethodStepDefinition;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Claves.*;
import static co.com.sofka.util.ConstantesNumericas.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

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
            Assertions.assertEquals(OK_STATUS.valor, response.getStatusCode());
            Assertions.assertEquals(TOKEN.clave, response.getBody().asString().substring(DOS.valor, SIETE.valor));
            LOGGER.info(response.getStatusCode());
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("obtiene el token de autenticacion")
    public void obtieneElTokenDeAutenticacion() {
        String token = response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().asString();
        LOGGER.info(token);
        String tokenLength = token.substring(DIEZ.valor, VEINTICINCO.valor);
        Assertions.assertEquals(QUINCE.valor, tokenLength.length());
        LOGGER.info("Ha obtenido el token de forma correcta, su token es: ");
        LOGGER.info(from(token).get("token"));
    }
}

package co.com.sofka.stepdefinition;

import co.com.sofka.setup.ServiceSetup;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class LoginStepDefinition extends ServiceSetup {
    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);

    private Response response;

    private RequestSpecification request;

    @Dado("que el usuario esta en la pagina de inicio de sesion con el correo de usuario {string} y la contraseña {string}")
    public void queElUsuarioEstaEnLaPaginaDeInicioDeSesionConElCorreoDeUsuarioYLaContrasena(String user, String password) {
        try {
            generalSetup();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(
                            "{\n" +
                                    "    \"email\": \"" + user + "\",\n" +
                                    "    \"password\": \"" + password + "\"\n" +
                                    "}"
                    );
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Cuando("el usuario hace una petición de inicio")
    public void elUsuarioHaceUnaPeticionDeInicio() {
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
    @Entonces("el usuario debera ver un codigo de respuesta exitoso y un token de respuesta")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {
        response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
    }
}

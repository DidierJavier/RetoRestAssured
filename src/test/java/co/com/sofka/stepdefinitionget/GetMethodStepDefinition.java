package co.com.sofka.stepdefinitionget;

import co.com.sofka.setup.ServiceSetupRestfulBookerGet;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.ConstantesNumericas.DOSMIL;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class GetMethodStepDefinition extends ServiceSetupRestfulBookerGet {

    private static final Logger LOGGER = LogManager.getLogger(GetMethodStepDefinition.class);

    private Response response;

    private RequestSpecification request;

    @Dado("que el estudiante hace las configuraciones de codigo necesarias")
    public void queElEstudianteHaceLasConfiguracionesDeCodigoNecesarias() {
        LOGGER.info("Iniciando configuracion");
        try {
            generalSetup();
            request = given()
                    .log().all();
            LOGGER.info("Configuracion exitosa");
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("el estudiante emplea el metodo get")
    public void elEstudianteEmpleaElMetodoGet() {
        try {
            LOGGER.info("Solicitando respuesta");
            response = request.when()
                    .log().all()
                    .get(LOGIN_RESOURCE);
            LOGGER.info("Respuesta solicitada");
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Entonces("obtiene todos los identificadores de los usuarios de la api")
    public void obtieneTodosLosIdentificadoresDeLosUsuariosDeLaApi() {
         String usersId = response.then()
                 .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().asString();
         Assertions.assertNotNull(usersId);
         LOGGER.info(usersId);
         LOGGER.info(from(usersId).get("[0].bookingid"));
         int usersIdLength = usersId.length();
         Assert.assertTrue(usersIdLength > DOSMIL.valor);
         LOGGER.info("Se han obtenido todos los identificadores");
    }
}

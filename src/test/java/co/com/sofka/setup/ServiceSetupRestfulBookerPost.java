package co.com.sofka.setup;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class ServiceSetupRestfulBookerPost {
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    private static final String BASE_PATH = "";
    protected static final String LOGIN_RESOURCE = "/auth";

    protected void generalSetup(){
        setUpLog4j2();
        setUpBases();
    }

    private void setUpLog4j2(){
        PropertyConfigurator.configure(LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    private void setUpBases(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }
}

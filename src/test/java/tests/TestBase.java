package tests;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = System.getProperty("baseUri", "https://reqres.in");
        RestAssured.basePath = System.getProperty("basePath", "/api");


    }
}
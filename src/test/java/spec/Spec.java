package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class Spec {
    public static RequestSpecification successfulRequest = with()
            .filter(withCustomTemplates())
            .log().params()
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);


    public static ResponseSpecification successfulResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification loggingResponse = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification loggingStatus = new ResponseSpecBuilder()
            .log(STATUS)
            .build();

}
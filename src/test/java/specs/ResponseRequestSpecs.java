package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;


public class ResponseRequestSpecs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType("application/json");

    private static ResponseSpecification createResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }

    public static ResponseSpecification responseSpec200 = createResponseSpec(200);
    public static ResponseSpecification responseSpec201 = createResponseSpec(201);
    public static ResponseSpecification responseSpec204 = createResponseSpec(204);

}

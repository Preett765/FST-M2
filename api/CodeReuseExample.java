package examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CodeReuseExample {
    RequestSpecification reqspec;
    ResponseSpecification respspec;

    @BeforeClass
    public void setUp() {
        reqspec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/pet")
                .setContentType(ContentType.JSON)
                .build(); //build is mandatory to make sure complete request is building

        respspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectBody("status", equalTo("alive"))
                .expectBody("name", equalTo("Riley"))
                .build();

    }

    @Test(priority = 1)
    public void postRequestSet() {
        //Request Body
        String reqBody = "{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";

        //generate Response
        Response resp = given().log().all().//log all response details
                spec(reqspec).body(reqBody).when().post();
        //Assertion
       resp.then().spec(respspec);
        System.out.println(resp.time());

    }
    @Test(priority = 2)
    public void getRequestTest(){
        //Generate response
        Response resp= given().spec(reqspec).when().get("/77232");

        resp.then().spec(respspec);
    }
    @Test(priority = 3)
    public void deleteRequestTest(){
        Response resp= given().spec(reqspec).when().delete("/77232");
        resp.then().statusCode(200);
        resp.then().body("messsage",equalTo("77232"));


    }

}

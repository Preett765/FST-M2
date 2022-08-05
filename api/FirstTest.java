package examples;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class FirstTest {
    @Test
    public void GetPetDetails() {
        // Specify the base URL to the RESTful web service
        final String baseURI = "https://petstore.swagger.io/v2/pet";

        // Make a request to the server by specifying the method Type and
        // resource to send the request to.
        // Store the response received from the server for later use.
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/findByStatus?status=sold"); // Run GET request

        // Now let us print the body of the message to see what response
        // we have received from the server
        //String responseBody = response.getBody().asString();
        String responseBody = response.getBody().prettyPrint();
        System.out.println("Response Body is =>  " + responseBody);
        System.out.println("ResponseHeader list " +response.getHeaders().asList());

        String petStatus = response.then().extract().body().path("[1].status");
        Integer petID = response.then().extract().body().path("[1].id");
        String name = response.then().extract().body().path("[0].category[1].name");
        String dateHeader = response.then().extract().header("Date");
        System.out.println("PetStatus :"+petStatus);
        System.out.println("petID "+petID);
        System.out.println("name in response "+name);
        System.out.println("dateHeader : "+dateHeader);


        // Assertions
        response.then().statusCode(200);
        response.then().body("[0].status", equalTo("sold"));

        Assert.assertEquals(petStatus,"sold");
    }

}

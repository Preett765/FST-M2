package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class AddPathParameter_Ex {

    String base_URI="http://ip-api.com/json/{ipAddress}";
    //// No need to add questions mark in the URL.
    //// queryParam() will automatically add it while parsing.
    String query_URI="http://ip-api.com/json";
    String root_URI="https://petstore.swagger.io/v2/pet/{petId}/uploadImage";
    @Test
    public void getIPInformation(){
        Response resp = given().contentType(ContentType.JSON).when().pathParam("ipAddress","107.218.134.107").get(base_URI);

        //print response
        System.out.println(resp.getBody().asPrettyString());

    }
    //Query parameters are added after the URL with a question mark(?):
    @Test
    public void addQueryparameter(){
        Response resp= given().contentType(ContentType.JSON).when().queryParam("fields","query,country,city,timezone")
                .get(query_URI,"/125.219.5.94"); //send GET request

        //print response
        System.out.println(resp.getBody().asPrettyString());

    }
    //example of uploading files
    @Test
    public void addPetImage(){
        File petImage= new File("src/test/test.png");
        Response resp= given().multiPart(petImage) //Add image to upload
        .pathParam("petId","77232") //set pet ID parameter
        .when().post(root_URI); //send POST Request

        //print reponse
        System.out.println(resp.asPrettyString());
        //Assertion
        resp.then().body("code", equalTo(200));
    }
}

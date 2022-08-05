package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class InputFromJSOnFile {
    String base_URI="https://petstore.swagger.io/v2/pet";

    @Test
    public void addpet() throws IOException {
        File file = new File("src/test/jsonfile.json");
        FileInputStream fis = new FileInputStream(file);
        //get all bytes from JSON file
        byte[] bytes= new byte[(int)file.length()];
        fis.read(bytes);
        //Read JSON file as String
        String reqBody = new String(bytes,"UTF-8");
        System.out.println(reqBody);

        Response response = given()
                .contentType(ContentType.JSON) // Set headers
                .body(reqBody) // Pass request body from file
                .when().post(base_URI); // Send POST request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        fis.close();

        // Assertion
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));

    }



}

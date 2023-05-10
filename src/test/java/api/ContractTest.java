package api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContractTest {

    @Test
    public void restGet() {
        given()
                .baseUri("https://fakestoreapi.com")
                .contentType(ContentType.JSON)
                .basePath("/products/1")
                .when()
                .get()
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("product.json"));
    }
}


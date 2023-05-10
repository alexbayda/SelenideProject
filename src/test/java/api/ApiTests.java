package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.saucelabs.models.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.IsEqual.equalTo;

@lombok.extern.slf4j.Slf4j
public class ApiTests extends BaseTest {


    @BeforeMethod
    public void setupApi() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @Test
    public void testGetRequest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/1");

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"))
                .body("price", equalTo(109.95f))
                .body("description", startsWith("Your perfect pack for everyday use"))
                .body("category", equalTo("men's clothing"))
                .body("image", equalTo("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"))
                .body("rating.rate", equalTo(3.9f))
                .body("rating.count", equalTo(120))
                .log()
                .body();
    }

    @Test
    public void testPostRequest() {

        Product product = new Product("Sumka", 69.69, "Style is everything",
                "Men clothing");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products");

        response.then()
                .statusCode(200) //should be 201
                .body("title", equalTo("Sumka"))
                .body("price", equalTo(69.69F))
                .body("description", equalTo("Style is everything"))
                .body("category", equalTo("Men clothing"))
                .log()
                .body();
    }

    @Test
    public void testPutRequest() {
        Product product = new Product("Sumka", 69.69, "Style is everything", "Men clothing");

        Response postResponse = given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products");

        int productId = postResponse.jsonPath().getInt("id");

        Product updatedProduct = new Product("Sumka Adidas", 79.79, "Style to vse", "Stylish clothing");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedProduct)
                .when()
                .put("/products/" + productId);

        response.then()
                .statusCode(200)
                .body("title", equalTo("Sumka Adidas"))
                .body("price", equalTo(79.79F))
                .body("description", equalTo("Style to vse"))
                .body("category", equalTo("Stylish clothing"))
                .log()
                .body();
    }

    @Test
    public void testDeleteRequest() {
        Product product = new Product("Sumka", 69.69, "Style is everything", "Men clothing");

        Response postResponse = given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products");

        int productId = postResponse.jsonPath().getInt("id");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + productId);

        response.then()
                .statusCode(200)
                .log()
                .body();
    }
}

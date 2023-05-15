package setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.saucelabs.models.Product;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ProductController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Product getProduct(int id) {
        return RestAssured.get("/products/" + id).then().extract().as(Product.class);
    }

    public Product createProduct(Product product) {
        return given()
                .contentType("application/json") //move to basetest config
                .body(product)
                .post("/products")
                .prettyPeek()
                .then().statusCode(200).extract().as(Product.class);
    }

    public Product updateProduct(int id, Product updatedProduct) throws IOException {
        return given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(updatedProduct))
                .put("/products/" + id)
                .then().statusCode(200).extract().as(Product.class);
    }

    public Response deleteProduct(int id) {
        return RestAssured.delete("/products/" + id);
    }

    public Response getSchema (int productId) {
        return given()
                .basePath("products")
                .pathParam("id", productId)
                .when()
                .get("/{id}");
    }

}

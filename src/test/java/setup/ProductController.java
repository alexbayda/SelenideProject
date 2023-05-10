package setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.saucelabs.models.Product;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ProductController {
    private static final String BASE_URL = "https://fakestoreapi.com/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductController() {
        RestAssured.baseURI = BASE_URL;
    }

    public Product getProduct(int id) throws IOException {
        Response response = RestAssured.get("/products/" + id);
        return objectMapper.readValue(response.asString(), Product.class);
    }

    public Product createProduct(Product product) throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(product))
                .post("/products");
        return objectMapper.readValue(response.asString(), Product.class);
    }

    public Product updateProduct(int id, Product updatedProduct) throws IOException {
        Response response = given()
                .contentType("application/json")
                .body(objectMapper.writeValueAsString(updatedProduct))
                .put("/products/" + id);
        return objectMapper.readValue(response.asString(), Product.class);
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

package setup;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.saucelabs.models.Product;

import static io.restassured.RestAssured.given;

public class Controller {


    public Product getProduct(int id) {
        return RestAssured.get("/products/" + id).then().extract().as(Product.class);
    }

    public <T> T createEntity(T entity, Class <T> entityType) {
        return given()
                .contentType("application/json")
                .body(entity)
                .post("/products")
                .prettyPeek()
                .then().statusCode(200).extract().as(entityType);
    }

    public <T> T updateEntity(int id, T updatedEntity, Class<T> entityType) {
        return given()
                .contentType("application/json")
                .body(updatedEntity)
                .put("/products/" + id)
                .then().statusCode(200).extract().as(entityType);
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

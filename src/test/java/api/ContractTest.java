package api;

import io.restassured.response.Response;
import org.saucelabs.models.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.ProductController;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ContractTest extends BaseTest {


    private ProductController productController;

    @BeforeMethod
    public void setupApi() {
        productController = new ProductController();
    }

    @Test
    public void restGet() {
        Response response = productController.getSchema(1);

        response.then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("product.json"));
    }
}


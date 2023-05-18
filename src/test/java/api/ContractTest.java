package api;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import setup.BaseTest;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContractTest extends BaseTest {

    @Test
    public void restGet() {
        Response response = controller.getSchema(1);

        assertThat(
                "Response does not match the expected schema",
                response.getBody().asString(),
                matchesJsonSchemaInClasspath("product.json")
        );
    }
}


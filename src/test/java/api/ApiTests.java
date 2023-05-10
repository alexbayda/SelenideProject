package api;

import org.saucelabs.api.data.TestData;
import io.restassured.response.Response;
import org.saucelabs.models.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.ProductController;

import java.io.IOException;

import static assertions.ProductAssertions.assertProductProperties;
import static org.saucelabs.api.data.ProductBuilder.buildProduct;

@lombok.extern.slf4j.Slf4j
public class ApiTests extends BaseTest {

    private ProductController productController;

    @BeforeMethod
    public void setupApi() {
        productController = new ProductController();
    }

    @Test
    public void testGetRequest() throws IOException {
        Product product = productController.getProduct(1);
        assertProductProperties(product);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testPostRequest(String title, double price, String description, String category) throws IOException {
        Product product = buildProduct(title,price,description,category);

        Product productRequest = productController.createProduct(product);
        assertProductProperties(productRequest, title, price, description, category);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testPutRequest(String title, double price, String description, String category) throws IOException {
        Product product = buildProduct(title,price,description,category);

        Product productRequest = productController.createProduct(product);
        int productId = productRequest.getId();

        Product updatedProduct = Product.builder()
                .title("Updated " + title)
                .price(price + 10)
                .description("Updated " + description)
                .category("Updated " + category)
                .build();

        Product putResponse = productController.updateProduct(productId, updatedProduct);
        assertProductProperties(putResponse, "Updated " + title, price + 10, "Updated " + description, "Updated " + category);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testDeleteRequest(String title, double price, String description, String category) throws IOException {
        Product product = buildProduct(title,price,description,category);

        Product postResponse = productController.createProduct(product);
        int productId = postResponse.getId();

        Response response = productController.deleteProduct(productId);
        response.then().statusCode(200);
    }
}

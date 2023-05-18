package api;

import io.restassured.response.Response;
import org.saucelabs.api.data.TestData;
import org.saucelabs.models.Product;
import org.testng.annotations.Test;
import setup.BaseTest;


import static assertions.ProductAssertions.assertProductProperties;
import static org.saucelabs.api.data.ProductBuilder.buildProduct;

@lombok.extern.slf4j.Slf4j
public class ApiTests extends BaseTest {

    @Test
    public void testGetRequest() {
        Product product = controller.getProduct(1);
        assertProductProperties(product);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testPostRequest(String title, double price, String description, String category) {
        Product product = buildProduct(title, price, description, category);
        Product createdProduct = controller.createEntity(product, Product.class);
        assertProductProperties(createdProduct, title, price, description, category);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testPutRequest(String title, double price, String description, String category) {
        Product initialProduct = Product.builder().id(1).price(12.12).build();
        Product createdProduct = controller.createEntity(initialProduct, Product.class);

        Product updatedProduct = Product.builder()
                .title("Updated " + title)
                .price(price + 10)
                .description("Updated " + description)
                .category("Updated " + category)
                .build();

        Product putResponse = controller.updateEntity(createdProduct.getId(), updatedProduct, Product.class);
        assertProductProperties(putResponse, "Updated " + title, price + 10,
                "Updated " + description, "Updated " + category);
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void testDeleteRequest(String title, double price, String description, String category) {
        Product product = buildProduct(title, price, description, category);
        Product createdProduct = controller.createEntity(product, Product.class);

        Response response = controller.deleteProduct(createdProduct.getId());
        response.then().statusCode(200);
    }
}

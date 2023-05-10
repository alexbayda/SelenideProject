//package api;
//
//import io.restassured.response.Response;
//import org.saucelabs.models.Product;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import setup.BaseTest;
//import setup.ProductController;
//
//import java.io.IOException;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.startsWith;
//
//@lombok.extern.slf4j.Slf4j
//public class ApiTestsTest extends BaseTest {
//
//    private ProductController productController;
//
//    @BeforeMethod
//    public void setupApi() {
//        productController = new ProductController();
//    }
//
//    @Test
//    public void testGetRequest() throws IOException {
//        Product product = productController.getProduct(1);
//
//        assertThat(product.getId(), equalTo(1));
//        assertThat(product.getTitle(), equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));
//        assertThat(product.getPrice(), equalTo(109.95));
//        assertThat(product.getDescription(), startsWith("Your perfect pack for everyday use"));
//        assertThat(product.getCategory(), equalTo("men's clothing"));
//        assertThat(product.getImage(), equalTo("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"));
//        assertThat(product.getRating().getRate(), equalTo(3.9));
//        assertThat(product.getRating().getCount(), equalTo(120));
//    }
//
//    @Test
//    public void testPostRequest() throws IOException {
//        Product product = Product.builder()
//                .title("Sumka")
//                .price(69.69)
//                .description("Style is everything")
//                .category("Men clothing")
//                .build();
//
//        Product productResponse = productController.createProduct(product);
//
//        assertThat(productResponse.getTitle(), equalTo("Sumka"));
//        assertThat(productResponse.getPrice(), equalTo(69.69));
//        assertThat(productResponse.getDescription(), equalTo("Style is everything"));
//        assertThat(productResponse.getCategory(), equalTo("Men clothing"));
//    }
//
//    @Test
//    public void testPutRequest() throws IOException {
//        Product product = Product.builder()
//                .title("Sumka")
//                .price(69.69)
//                .description("Style is everything")
//                .category("Men clothing")
//                .build();
//
//        Product postResponse = productController.createProduct(product);
//
//        int productId = postResponse.getId();
//
//        Product updatedProduct = Product.builder()
//                .title("Sumka Adidas")
//                .price(79.79)
//                .description("Style to vse")
//                .category("Stylish clothing")
//                .build();
//
//        Product putResponse = productController.updateProduct(productId, updatedProduct);
//
//        assertThat(putResponse.getTitle(), equalTo("Sumka Adidas"));
//        assertThat(putResponse.getPrice(), equalTo(79.79));
//        assertThat(putResponse.getDescription(), equalTo("Style to vse"));
//        assertThat(putResponse.getCategory(), equalTo("Stylish clothing"));
//    }
//
//    @Test
//    public void testDeleteRequest() throws IOException {
//        Product product = Product.builder()
//                .title("Sumka")
//                .price(69.69)
//                .description("Style is everything")
//                .category("Men clothing")
//                .build();
//
//        Product postResponse = productController.createProduct(product);
//
//        int productId = postResponse.getId();
//
//        Response response = productController.deleteProduct(productId);
//
//        response.then()
//                .statusCode(200);
//    }
//}

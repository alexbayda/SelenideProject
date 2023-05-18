package assertions;

import org.hamcrest.MatcherAssert;
import org.saucelabs.models.Product;
import org.saucelabs.models.User;

import java.util.Objects;
import java.util.function.Predicate;

public class ProductAssertions {

    public static <T> void assertProperty(T object, Predicate<T> predicate, String errorMessage) {
        MatcherAssert.assertThat(errorMessage, predicate.test(object));
    }

    public static void assertProductProperties(Product product) {
        assertProperty(product, p -> p.getId() == 1, "Product ID mismatch");
        assertProperty(product, p -> p.getTitle().equals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"), "Title does not match");
        assertProperty(product, p -> p.getPrice() == 109.95, "Price does not match");
        assertProperty(product, p -> p.getDescription().startsWith("Your perfect pack for everyday use"), "Description does not match");
        assertProperty(product, p -> p.getCategory().equals("men's clothing"), "Category does not match");
        assertProperty(product, p -> p.getImage().equals("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"), "Image does not match");
        assertProperty(product, p -> p.getRating().getRate() == 3.9, "Rating rate does not match");
        assertProperty(product, p -> p.getRating().getCount() == 120, "Rating count does not match");
    }

    public static void assertProductProperties(Product product, String title, double price, String description, String category) {
        assertProperty(product.getTitle(), title::equals, "Title does not match");
        assertProperty(product.getPrice(), p -> p == price, "Price does not match");
        assertProperty(product.getDescription(), description::equals, "Description does not match");
        assertProperty(product.getCategory(), category::equals, "Category does not match");
    }

    public static void assertUserProperties(User user, int id, String date) {
        assertProperty(user.getId(), u -> u == id, "Id does not match");
        assertProperty(user.getDate(), date.equals("null") ? Objects::isNull : date::equals, "Date does not match");
    }
}
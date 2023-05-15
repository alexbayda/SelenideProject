package assertions;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.saucelabs.models.Product;
public class ProductAssertions {

    public static void assertProductProperties(Product product) { //add Predicate or Matcher lambda
        MatcherAssert.assertThat(product.getId(), Matchers.equalTo(1));
        MatcherAssert.assertThat(product.getTitle(), Matchers.equalTo("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));
        MatcherAssert.assertThat(product.getPrice(), Matchers.equalTo(109.95));
        MatcherAssert.assertThat(product.getDescription(), Matchers.startsWith("Your perfect pack for everyday use"));
        MatcherAssert.assertThat(product.getCategory(), Matchers.equalTo("men's clothing"));
        MatcherAssert.assertThat(product.getImage(), Matchers.equalTo("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"));
        MatcherAssert.assertThat(product.getRating().getRate(), Matchers.equalTo(3.9));
        MatcherAssert.assertThat(product.getRating().getCount(), Matchers.equalTo(120));
    }

    public static void assertProductProperties(Product product, String title, double price, String description, String category) {
        MatcherAssert.assertThat(product.getTitle(), Matchers.equalTo(title));
        MatcherAssert.assertThat(product.getPrice(), Matchers.equalTo(price));
        MatcherAssert.assertThat(product.getDescription(), Matchers.equalTo(description));
        MatcherAssert.assertThat(product.getCategory(), Matchers.equalTo(category));
    }
}

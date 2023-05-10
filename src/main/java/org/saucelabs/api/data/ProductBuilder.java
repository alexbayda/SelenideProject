package org.saucelabs.api.data;

import org.saucelabs.models.Product;

public class ProductBuilder {

    public static Product buildProduct(String title, double price, String description, String category) {
        return Product.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .build();
    }
}

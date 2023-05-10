package org.saucelabs.models;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Product {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;

    private Rating rating;

    public Product(String title, double price, String description, String category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
    }

}

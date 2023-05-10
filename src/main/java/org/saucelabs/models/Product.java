package org.saucelabs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    @JsonProperty("rating")
    private Rating rating;

}

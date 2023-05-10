package org.saucelabs.models;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class User {

    private int id;
    private int userId;
    private String date;
    private String[] products;


}

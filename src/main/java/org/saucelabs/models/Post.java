package org.saucelabs.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Post {

    private int id;
    private int userId;
    private String title;
    private String body;

}

import com.google.gson.Gson;
import groovy.util.logging.Slf4j;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.saucelabs.models.Person;
import org.saucelabs.models.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

@Slf4j
@ExtendWith(AllureJunit5.class)
public class ApiTests extends BaseTest {

    private final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(ApiTests.class);


    @Before
    public void setupApi() {
        RestAssured.baseURI = "https://swapi.dev/api/";
    }

    @Test
    public void testGetRequest() {
        Response response = RestAssured
                .given()
                .when()
                .get("people/1/")
                .then()
                .statusCode(200)
                .body("name", equalTo("Luke Skywalker"))
                .body(matchesJsonSchemaInClasspath("person-schema.json"))
                .extract()
                .response();

        Person person = gson.fromJson(response.asString(), Person.class);
        System.out.println("Name: " + person.getName());
        System.out.println("Height: " + person.getHeight());
        System.out.println("Mass: " + person.getMass());
        System.out.println(response);
    }

    @Test
    public void testPostRequest() {
        Post post = new Post();
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("posts/")
                .then()
                .statusCode(201)
                .body("title", equalTo("Sample Post Title"))
                .body("body", equalTo("Sample Post Body"))
                .body("userId", equalTo(1))
                .extract()
                .response();
        Post createdPost = gson.fromJson(response.asString(), Post.class);
        logger.info("Post ID: " + createdPost.getId());
        logger.info("User ID: " + createdPost.getUserId());
        logger.info("Title: " + createdPost.getTitle());
        logger.info("Body: " + createdPost.getBody());
    }
}

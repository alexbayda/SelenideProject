package api;

import org.saucelabs.models.User;
import org.testng.annotations.Test;
import setup.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogToAllureTest extends BaseTest {

    @Test
    public void bodyTest() {
        User user = User.builder()
                .userId(11)
                .date("2020-03-02T00:00:00.000Z")
                .build();

        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
//                .log().body()
                .body("id", equalTo(11))
                .body("date", equalTo("2020-03-02T00:00:00.000Z"));
    }
}

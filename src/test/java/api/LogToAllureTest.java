package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.saucelabs.models.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogToAllureTest {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://fakestoreapi.com/")
            .setContentType(ContentType.JSON)
            .setBasePath("/carts?userId=1")
            .build()
            .filter(new AllureRestAssured());

    @BeforeTest
    public void setFilter(){
        RestAssured.filters(new AllureRestAssured());
    }

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
                .log().body()
                .body("id", equalTo(11))
                .body("date", equalTo("2020-03-02T00:00:00.000Z"));
    }
}

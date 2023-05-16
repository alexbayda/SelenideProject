package setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {



    //implement maven profiles
    //watch maven to Pojo plugin (maybe implement)
    //java 8 functional interfaces //Consumer//Supplier//Predicate

    //RestAssured filters for the logging into console and allure
    //annonimous classes -> lambdas -> methods reference

    protected Controller controller;
    protected RequestSpecification requestSpec;
    private static final String BASE_URL = "https://fakestoreapi.com/";
    public void setUpUI(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.baseUrl = "";

    }

    public void setupAPI(){
        RestAssured.baseURI = BASE_URL;
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBasePath("/carts?userId=1")
                .build()
                .filter(new AllureRestAssured());
        controller = new Controller();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }
    @BeforeClass
    public void init(){
        setUpUI();
        setupAPI();
    }

    @AfterClass
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}

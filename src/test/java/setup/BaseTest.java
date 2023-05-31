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

    //dependency inversion
    //try to reproduce *throws *try/catch and see how it works

    //implement Class Composition and Aggregation
    //Read about association of pattern bridge and fassad //not only refactoring guru!
    //coup management of inner object life
    //how to go around inheritance

    //connection to DB
    //Generics
    //garbage collector + pull of strings + method intern
    //native methods
    //pass by value pass by reference

    protected Controller controller;
    protected RequestSpecification requestSpec;
    protected String environment = System.getProperty("env", "dev");
    private static String BASE_URL;
    public void setUpUI(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.baseUrl = "https://www.saucedemo.com/";

    }

    public void setupAPI(){
        RestAssured.baseURI = BASE_URL;
        requestSpec = new RequestSpecBuilder()
                .setBasePath("/carts?userId=1")
                .setContentType(ContentType.JSON)
                .build();
        controller = new Controller();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new AllureRestAssured());
    }

    public void setupEnv(){
        if("qa".equals(environment)){
            BASE_URL =  null;
        }
        else {
            BASE_URL =  "https://fakestoreapi.com/";
        }
    }
    @BeforeClass
    public void init(){
        setupEnv();
        setUpUI();
        setupAPI();
    }

    @AfterClass
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}

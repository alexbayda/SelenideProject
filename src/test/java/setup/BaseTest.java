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

    //dependency inversion ✓

    //implement Class Composition and Aggregation ✓

    //Read about association of pattern bridge -- focu sing on abstaction(say tv + concrede Sony and Sharp, then we have implementation new remote + old remote -->
    // e.g Tv//abstract oldRemote = new Sony//concreteObject(new OldRemote()//interface) -->
    // oldRemote.callMethod()   ) ✓



    // facade pattern//not only refactoring guru! -- something like dependency inversion, client communicates with high level interface -->
    // and the level interface then interacts with low level entangled classes, facade layer is a class ✓


    //how to go around inheritance -->
    // Composition
    // Interface-based Programming: Rather than relying on class inheritance, you can define interfaces that specify a contract for behavior
    //Strategy Design Pattern - where we substitute inheritance and having multiple object classes with giving 1 object various capabilities via interfaces that are later extended with
    //more implementation

    //Generics ***
    //Decorator Design Pattern ✓
    //native methods ✓
    //pass by value pass by reference ✓

    //failsafe failfast ✓
    //comparator vs comparable ✓
    //collections methods ✓
    //exceptions checked unchecked ✓
    //labels ✓
    //garbage collector + pull of strings + method intern + java memory model ✓
    //Maps methods ✓
    //contact .equals() .hashCode() ✓
    //rabit mq - maybe
    //composition management of inner object life
    //100 questions about Java
    //connection to DB
    //design pattern groups
    //primitive type and type casting, what matches what
    //primitive types


    //continue

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

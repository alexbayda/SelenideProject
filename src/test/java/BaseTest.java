import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.*;
import org.junit.After;
import org.junit.Before;

abstract public class BaseTest {



    //implement maven profiles
    //watch maven to Pojo plugin (maybe implement)

    //https://swapi.dev/ get port put delete + Rest_assured + allure
    //json schema validation + contact testing


    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.baseUrl = "";
    }

    @Before
    public void init(){
        setUp();
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}

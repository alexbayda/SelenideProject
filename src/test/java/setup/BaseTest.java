package setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {



    //implement maven profiles
    //watch maven to Pojo plugin (maybe implement)

    //download allure and try cmd allure serve
    //controller get products post product, remove given when from tests
    //map response to java object (Jackson annotation)


    /*questions -
    where is statusCode verifications?
    do I need to create a method for each builder scenario?
    how to log responses into console? or now they are only seen in debug?*/

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.baseUrl = "";
    }

    @BeforeClass
    public void init(){
        setUp();
    }

    @AfterClass
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}

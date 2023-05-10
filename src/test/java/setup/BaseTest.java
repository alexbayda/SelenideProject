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

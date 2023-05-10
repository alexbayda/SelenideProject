package ui;

import setup.BaseTest;
import org.saucelabs.ui.MainPage;
import org.testng.annotations.Test;

public class SauceLabsTests extends BaseTest {

    private final static String BASE_URL = "https://www.saucedemo.com/";


    @Test
    public void buyE2E(){
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.logIn();
        mainPage.addRandomItemToCart();
        mainPage.fillFormFromCsvAndBuy();
    }
}

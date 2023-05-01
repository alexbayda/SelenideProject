import org.junit.Test;
import org.saucelabs.MainPage;

import static org.saucelabs.utils.RandomSelector.getRandom;

public class SauceLabsTests extends BaseTest{

    private final static String BASE_URL = "https://www.saucedemo.com/";


    @Test
    public void buyE2E(){
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.logIn();
        mainPage.addRandomItemToCart();
        mainPage.fillFormFromCsvAndBuy();
    }
}

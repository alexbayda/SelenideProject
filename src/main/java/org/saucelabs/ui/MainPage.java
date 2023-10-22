package org.saucelabs.ui;

import com.codeborne.selenide.*;
import org.saucelabs.utils.CSVUtilities;
import org.saucelabs.models.UserDto;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.saucelabs.utils.RandomSelector.getRandom;

public class MainPage {

    private final SelenideElement usernameBox = $x("//*[@id=\"user-name\"]");
    private final SelenideElement passwordBox = $x("//*[@id=\"password\"]");
    private final SelenideElement loginButton = $("#login-button");
    private final List<ElementsCollection> addToCardButtonList = Collections.singletonList($$(".btn"));
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_container");
    private final SelenideElement cart = $(".shopping_cart_link");
    private final SelenideElement checkoutButton = $("#checkout");
    private final SelenideElement checkOutFirstName = $("#first-name");
    private final SelenideElement checkOutLastName = $("#last-name");
    private final SelenideElement checkOutPostalCode = $("#postal-code");
    private final SelenideElement checkOutContinueButton = $x("//*[@id=\"continue\"]");
    private final SelenideElement finishCheckoutButton = $("#finish");

    public MainPage(String url) {
        open(url, MainPage.class)
                .passwordBox.sendKeys("blabla"); //don't do it in real life
    }

    //    public <T> T apiLogin(Class<T> mainPage){
//        String token = ApiToken.login(username, password);
//        Selenide.executeJavaScript("window.localStorage.setItem('jvtToken')", token);
//        Selenide.refresh();
//        String relativeUrl = "/";
//        if(mainPage.isAnnotationPresent(Page.class)){
//            relativeUrl = mainPage.getAnnotation(Page.class).url();
//        }
//        return Selenide.open(relativeUrl, mainPage);
//    }

    //Put token in LocalStorage


    public void logIn() {
        usernameBox.sendKeys("standard_user");
        passwordBox.sendKeys("secret_sauce");
        loginButton.click();

    }

    public void addRandomItemToCart() {
        getRandom(addToCardButtonList);
        elementIsDisplayed(shoppingCartBadge);
        cart.should(Condition.enabled).click(ClickOptions.usingJavaScript());
        checkoutButton.click();
    }

    public void elementIsDisplayed(SelenideElement element) {
        assert (element.isDisplayed());
    }

    public void fillFormFromCsvAndBuy() {
        for (UserDto user : CSVUtilities.usersList) {
            checkOutFirstName.sendKeys(user.getName());
            checkOutLastName.sendKeys(user.getSurname());
            checkOutPostalCode.sendKeys(user.getPostalCode());
        }
        checkOutContinueButton.click();
        finishCheckoutButton.click();
    }

}
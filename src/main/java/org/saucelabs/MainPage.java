package org.saucelabs;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import com.codeborne.selenide.impl.SelenideElementIterator;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.saucelabs.utils.RandomSelector.getRandom;

public class MainPage {

    private final SelenideElement usernameBox = $x("//*[@id=\"user-name\"]");
    private final SelenideElement passwordBox = $x("//*[@id=\"password\"]");

    private final SelenideElement loginButton = $("#login-button");

    private final SelenideElementIterator addToCardButtonList = $(".btn");

    private final SelenideElement shoppingCartBadge = $(".shopping_cart_container");

    private final SelenideElement cart = $(".shopping_cart_link");

    private final SelenideElement checkoutButton = $("#checkout");

    public MainPage(String url){
        Selenide.open(url);
    }


    public void logIn(){
        usernameBox.sendKeys();
        passwordBox.sendKeys();
        loginButton.click();

    }

    public void buyRandomItem() {
        getRandom(getAddToCardButtonList());
        elementIsDisplayed(shoppingCartBadge);
        cart.click();
        checkoutButton.click();
    }

    public void elementIsDisplayed(SelenideElement element) {
        assertTrue(element.isDisplayed());
    }

}
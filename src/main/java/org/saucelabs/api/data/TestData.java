package org.saucelabs.api.data;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "productData")
    public static Object[][] createProductData() {
        return new Object[][]{
                {"Sumka", 69.69, "Style is everything", "Men clothing"},
                {"Sumka Adidas", 79.79, "Style to vse", "Stylish clothing"},
                {"Sumka Puma", 89.79, "Style", "Very Stylish clothing"}
        };
    }
}
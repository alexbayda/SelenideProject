package org.saucelabs.api.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class TestData implements Iterable<Object[]> {

    private final List<Object[]> productData = Arrays.asList(
            new Object[]{"Sumka", 69.69, "Style is everything", "Men clothing"},
            new Object[]{"Sumka Adidas", 79.79, "Style to vse", "Stylish clothing"},
            new Object[]{"Sumka Puma", 89.79, "Style", "Very Stylish clothing"}
    );

    @Override
    public Iterator<Object[]> iterator() {
        return productData.iterator();
    }

    @DataProvider(name = "productData")
    public static Object[][] productDataProvider() {
        Iterable<Object[]> iterable = new TestData();
        return StreamSupport.stream(iterable.spliterator(), false).toArray(Object[][]::new);
    }
}

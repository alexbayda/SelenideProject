package api;

import org.saucelabs.models.User;
import org.testng.annotations.Test;
import setup.BaseTest;

import static assertions.ProductAssertions.assertUserProperties;

public class UserTest extends BaseTest {

    @Test
    public void bodyTest() {
        User user = User.builder().id(21).date("2020-03-02T00:00:00.000Z").build();

        User userRequest = controller.createEntity(user, User.class);
        assertUserProperties(userRequest,21,"null");
    }

}

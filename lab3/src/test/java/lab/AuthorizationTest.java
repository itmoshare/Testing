package lab;

import org.junit.Test;
import org.openqa.selenium.*;


import static org.junit.Assert.assertEquals;

public class AuthorizationTest extends TestBase {
    @Test
    public void successfulLogin() throws Exception {
        util.auth(driver, util.getCorrectLogin(), util.getCorrectPassword());
        util.tryClick(driver, By.xpath("//a[text()='"+util.getCorrectLogin()+" ']"));
        util.tryClick(driver, By.xpath("//a[text()='Sign Out']"));
    }

    @Test
    public void wrongPassword() throws Exception {
        util.auth(driver, util.getCorrectLogin(), "asdasdasd");
        assertEquals(true, util.isElementPresent(driver, By.cssSelector("div.alert.alert-danger")));
    }
}

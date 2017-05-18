package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class OpenDiagramTest extends TestBase {
    @Test
    public void TestOpenFromDashboard(){
        util.tryClick(driver, By.cssSelector("div.diagramElement__card"));
        assertEquals(true, util.isElementPresent(driver, By.cssSelector("div.paper-area")));
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        util.auth(driver);
    }
}

package lab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class OpenDiagramTest extends TestBase {
    @Test
    public void TestOpenFromDashboard(){
        util.tryClick(driver, By.cssSelector("a[href='diagram?diagramId=552d2747e4b09f293a53ccfe']"));
        //assertEquals(true, util.isElementPresent(driver, By.className("paper-area")));
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        util.auth(driver);
    }
}

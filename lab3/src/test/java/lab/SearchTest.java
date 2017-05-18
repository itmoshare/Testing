package lab;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;

public class SearchTest extends TestBase {
    private void doSearch(WebDriver driver, String query){
        driver.findElement(By.cssSelector(".dashboardArea__searchDiargam")).clear();
        driver.findElement(By.cssSelector(".dashboardArea__searchDiargam")).sendKeys(query);
    }

    @Test
    public void failedSearch() throws Exception {
        doSearch(driver, "fdgyrtfghdrfhjdrtfhdrtyhxrdtyh");
        assertEquals(false, util.isElementPresent(driver, By.cssSelector("div.diagramElement")));
    }

    @Test
    public void successfulSearch() throws Exception {
        doSearch(driver, "il");
        assertEquals(true, util.isElementPresent(driver, By.cssSelector("div.diagramElement")));
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        util.auth(driver);
    }
}

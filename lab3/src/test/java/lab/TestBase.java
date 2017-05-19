package lab;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    Util util;
    ChromeDriver driver;

    @Before
    public void setUp() throws Exception {
        util = new Util();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        util.prepare(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}

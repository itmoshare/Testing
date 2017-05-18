import lab.Util;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class SeleniumTest {
    @Test
    public void executeChrome() throws MalformedURLException {
        this.execute(DesiredCapabilities.chrome());
    }

    private void execute(final DesiredCapabilities capability) throws MalformedURLException {
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://www.drive2.ru");
        String title = driver.getTitle();
        assertEquals("DRIVE2.RU", title);
        driver.quit();
    }
}

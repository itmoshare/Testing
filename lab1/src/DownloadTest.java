import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

/**
 * Created by Andrey Shumeev on 19.05.2017.
 */
public class DownloadTest {
    private WebDriver _driver;
    private ActionHelper _actionHelper;

    public DownloadTest(WebDriver driver) {
        _driver = driver;
        _actionHelper = new ActionHelper(driver);
    }

    public void performTest() {
        performDownload("png");
        performDownload("svg");
    }

    private void performDownload(String extension) {
        WebElement button = _driver.findElement(By.cssSelector("button[title='Export diagram as "+extension.toUpperCase() + "']"));
        ElementChecker.perfomCheck(button,
                (e)-> {
                    e.click();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                },
                (e)-> new File("C:\\Users\\Andrey Shumeev\\Downloads\\diagram."+extension).exists() ? "Exists" : "Not exists",
                "Downloaded " + extension
        );
    }

}

/**
 * Created by Andrey Shumeev on 17.05.2017.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionHelper {
    private WebDriver _driver;

    public ActionHelper(WebDriver driver) {
        _driver = driver;
    }

    public void dragAndDrop(WebElement element, int xOffset, int yOffset) {
        Actions dragAndDropBuilder = new Actions(_driver);
        Action dragAndDrop = dragAndDropBuilder.clickAndHold(element)
                .moveByOffset(xOffset,yOffset)
                .release()
                .build();
        dragAndDrop.perform();
    }

    public void dragAndDrop(WebElement element, WebElement moveTo) {
        Actions dragAndDropBuilder = new Actions(_driver);
        Action dragAndDrop = dragAndDropBuilder.clickAndHold(element)
                .moveToElement(moveTo)
                .release()
                .build();
        dragAndDrop.perform();
    }
}

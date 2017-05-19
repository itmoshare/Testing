import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Andrey Shumeev on 19.05.2017.
 */
public class AddFromClassTest {
    private WebDriver _driver;
    private ActionHelper _actionHelper;

    public AddFromClassTest(WebDriver driver) {
        _driver = driver;
        _actionHelper = new ActionHelper(driver);
    }

    public void perfomTest() {
        WebElement allDifferentTab = _driver.findElement(By.id("http://www.w3.org/2002/07/owl#AllDifferent_anchor"));
        ElementChecker.perfomCheck(allDifferentTab,
                (e) -> {
                    //actionHelper.dragAndDrop(e, downElement.element);
                },
                (e) -> {
                    WebElement layer = _driver.findElement(By.className("ontodia-element-layer"));
                    try {
                        layer.findElement(By.cssSelector("div[model-id=\"http://www.w3.org/2002/07/owl#AllDifferent\"]"));
                    } catch (Exception ex) {
                        return "Element is on element's layer";
                    }
                    return "Element does no exists on element's layer";
                },
                "All different class"
        );
    }
}

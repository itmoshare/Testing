import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created by Andrey Shumeev on 19.05.2017.
 */
public class ButtonsTest {
    private WebDriver _driver;
    private ActionHelper _actionHelper;
    private String title;
    private String name;
    private Function<WebElement, String> getState;

    public ButtonsTest(WebDriver driver) {
        _driver = driver;
        _actionHelper = new ActionHelper(driver);
    }

    public void perfomTest() {
        testButton("Publish or share diagram", "Share button",
                (e) -> {
                    try{
                        _driver.findElement(By.className("modal-dialog"));
                    } catch (Exception ex) {
                        return "No modal dialog";
                    }
                    return "Modal dialog appeared";
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement closeModalButton = _driver.findElement(By.cssSelector("button.close[data-dismiss='modal']"));
        ElementChecker.perfomCheck(closeModalButton,
                (e) -> closeModalButton.click(),
                (e) -> {
                    try{
                        _driver.findElement(By.className("modal-dialog"));
                    } catch (Exception ex) {
                        return "No modal dialog";
                    }
                    return "Modal dialog opened";
                },
                "Modal dialog"
        );

        String handle = _driver.getWindowHandle();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        testButton("Print diagram", "Print button",
                (e) -> _driver.getWindowHandles().size() <= 2 ? "No print window" : "Print window is opened");


        testButton("Zoom In", "Zoom In",
                (e) -> _driver.findElement(By.cssSelector("svg")).getAttribute("width"));

        testButton("Zoom Out", "Zoom Out",
                (e) -> _driver.findElement(By.cssSelector("svg")).getAttribute("width"));



        WebElement undo = getButtonByTitle("Undo");
        ElementChecker.perfomCheck(undo,
                (e) -> {
                    _driver.findElement(By.cssSelector("div[model-id='http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#Muscadet'"))
                            .sendKeys(Keys.DELETE);
                    e.click();
                },
                (e) -> {
                    try {
                        _driver.findElement(By.cssSelector("div[model-id='http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#Muscadet']"));
                    } catch (Exception el) {
                        return "No element";
                    }
                    return "Element exists";
                },
                "Undo button"
        );




        WebElement saveButton = _driver.findElement(By.cssSelector("button.saveDiagramButton"));

        ElementChecker.perfomCheck(saveButton,
                (e)->  {
                    e.click();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                },
                (e) -> _driver.findElement(By.id("confirmCopyDialog")).isDisplayed() ? "Modal dialog opened" : "No modal dialog",
                "Save button"
        );

    }

    public void testButton(String title, String name, Function<WebElement, String> getState) {
        WebElement button = getButtonByTitle(title);
        ElementChecker.perfomCheck(button,
                (e) -> e.click(),
                getState,
                name
        );
    }


    private WebElement getButtonByTitle(String title) {
        return _driver.findElement(By.cssSelector("button[title='"+title +"']"));
    }
}

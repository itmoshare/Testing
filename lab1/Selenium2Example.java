
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium2Example  {
    public static void main(String[] args) {
        // Create a new instance of the Chrome driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Andrey Shumeev\\Desktop\\testing\\lab1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ActionHelper actionHelper = new ActionHelper(driver);
        // And now use this to visit Google
        driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement email = driver.findElement(By.id("email"));
        ElementChecker.perfomCheck(email,
                (e) -> e.sendKeys("shumantt@gmail.com"),
                (e) -> e.getAttribute("value"),
                "Email"
        );

        WebElement password = driver.findElement(By.id("password"));
        ElementChecker.perfomCheck(password,
                (e) -> e.sendKeys("Password1"),
                (e) -> e.getAttribute("value"),
                "Password"
        );

        WebElement loginForm = driver.findElement(By.id("loginForm"));
        loginForm.submit();


        WebElement titleTextBox = driver.findElement(By.className("titleTextBox"));
        ElementChecker.perfomCheck(titleTextBox,
                (e) -> e.sendKeys(" + additional to name"),
                (e) -> e.getAttribute("value"),
                "Tittle"
        );



        NodeElement dryBlock = new NodeElement("DryRiesling", driver);
        dryBlock.perfomCheck(
                (e) -> {
                    e.click();
                    WebElement removeButton = driver.findElement(By.cssSelector(".handles div[data-action='remove']"));
                    removeButton.click();
                },
                (e) -> {
                    try{
                        e.isDisplayed();
                    } catch (Exception ex) {
                        return "Dry block does not exists anymore";
                    }
                    return "Dry block exists";
                } );


        NodeElement rieslingBlock = new NodeElement("Riesling", driver);
        rieslingBlock.perfomCheck(
                (e) -> {
                    actionHelper.dragAndDrop(e, 5,5);
                },
                (e) -> e.getAttribute("style")
        );

        rieslingBlock.element.click();

        WebElement box = driver.findElement(By.cssSelector("label.box"));
        ElementChecker.perfomCheck(box,
                (e) -> {
                    WebElement rotateButton = driver.findElement(By.cssSelector(".handles div[data-action='rotate']"));
                    actionHelper.dragAndDrop(rotateButton, 0, -10);
                },
                (e) -> e.getText(),
                "Box"
        );


        //click to empty place
        NodeElement downElement = new NodeElement("Pauillac", driver);

        Actions moveToEmptyAndClickBuilder = new Actions(driver);
        Action moveToEmptyAndClick = moveToEmptyAndClickBuilder.moveToElement(downElement.element).moveByOffset(-10,50).click().build();
        moveToEmptyAndClick.perform();



        WebElement linkLine = driver.findElement(By.id("v-257"));

        Actions getCircleBuilder = new Actions(driver);
        Action getCircle = getCircleBuilder.moveToElement(linkLine)
                .click().build();
        getCircle.perform();

        WebElement linkInfo = driver.findElement(By.id("v-261"));

        WebElement circlesGroup = driver.findElement(By.id("v-259"));
        List<WebElement> circles = circlesGroup.findElements(By.tagName("circle"));
        WebElement circle = circles.get(0);

        ElementChecker.perfomCheck(circle,
                (e) ->  actionHelper.dragAndDrop(e, 4,4),
                (e) -> linkInfo.getAttribute("transform"),
                "Line"
        );



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Close the browser
        driver.quit();
    }
}
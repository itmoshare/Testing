import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Andrey Shumeev on 18.05.2017.
 */
public class NodeElement extends ElementChecker {
    private static final String cssSelectionString  = "div[model-id='http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#";
    private WebDriver _driver;
    private String _name;
    public  WebElement element;


    public NodeElement(String name, WebDriver driver) {
        _driver = driver;
        _name = name;
        element = driver.findElement(By.cssSelector(cssSelectionString + name));

    }

    public void perfomCheck(Consumer<WebElement> action, Function<WebElement,String> state) {
        super.perfomCheck(element, action, state, _name);
    }

}

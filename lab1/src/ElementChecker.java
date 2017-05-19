import com.google.common.base.Supplier;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;
import java.util.function.Function;

public class ElementChecker {

    public static void perfomCheck(WebElement element, Consumer<WebElement> action, Function<WebElement, String> state, String name) {
        System.out.println();
        System.out.println("Before state for element \"" + name + "\": " + state.apply(element));
        action.accept(element);
        System.out.println("After state for element \"" + name + "\": " + state.apply(element));
        System.out.println();
    }

}

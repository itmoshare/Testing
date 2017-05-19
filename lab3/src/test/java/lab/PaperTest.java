package lab;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PaperTest  {
    static Util _util;
    static ChromeDriver _driver;

    @BeforeClass
    public static void setUp() throws Exception {
        _util = new Util();
        _driver = new ChromeDriver();
        _driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        _util.prepare(_driver);
        _util.auth(_driver);
            }

    @AfterClass
    public static void tearDown() throws Exception {
        _driver.quit();
    }

    @Test
    public void SelectTest() {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");

        _util.tryClick(_driver, By.cssSelector("div[model-id='http://www.w3.org/TR/2003/PR-owl-guide-20031209/wine#IceWine']"));
        assertEquals(true, _util.isElementPresent(_driver, By.className("links-heading")));
    }

    @Test
    public void SaveTest() {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");

        _util.tryClick(_driver, By.cssSelector("button.saveDiagramButton"));
        _util.isElementPresent(_driver, By.cssSelector("div.modal-dialog"));
    }

    @Test
    public void PrintTest() {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");

        _util.tryClick(_driver, By.cssSelector("button[title='Print diagram']"));
        assertTrue(_driver.getWindowHandles().size() > 1);
    }

    @Test
    public void ExportPngTest() throws InterruptedException {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        try{
            new File("D:\\Downloads\\diagram.png").delete();
        } catch (Exception e){};
        _util.tryClick(_driver, By.cssSelector("button[title='Export diagram as PNG']"));
        Thread.sleep(5000);
        assertEquals(true, new File("D:\\Downloads\\diagram.png").exists());
    }

    @Test
    public void ExportSvgTest() throws InterruptedException {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        try{
            new File("D:\\Downloads\\diagram.svg").delete();
        } catch (Exception e){};
        _util.tryClick(_driver, By.cssSelector("button[title='Export diagram as SVG']"));
        Thread.sleep(5000);
        assertEquals(true, new File("D:\\Downloads\\diagram.svg").exists());
    }

    @Test
    public void ShareTest() throws InterruptedException {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        _util.tryClick(_driver, By.cssSelector("button[title='Publish or share diagram']"));
        assertEquals(true, _util.isElementPresent(_driver, By.id("shareDialogShareToLabel")));
    }

    @Test
    public void ZoomInTest() throws InterruptedException {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        Thread.sleep(2000);
        _util.tryClick(_driver, By.cssSelector("button[title='Zoom In']"));
        assertEquals("1423.7728714942932", _driver.findElement(By.cssSelector("svg")).getAttribute("width"));
        assertEquals("1139.0182971954346", _driver.findElement(By.cssSelector("svg")).getAttribute("height"));
    }

    @Test
    public void ZoomOutTest() throws InterruptedException {
        _driver.get("http://www.ontodia.org/diagram?diagramId=53e4951dd4404128c490600a");
        Thread.sleep(2000);
        _util.tryClick(_driver, By.cssSelector("button[title='Zoom Out']"));
        assertEquals("600.0000089406967", _driver.findElement(By.cssSelector("svg")).getAttribute("width"));
        assertEquals("480.0000071525574", _driver.findElement(By.cssSelector("svg")).getAttribute("height"));
    }
}

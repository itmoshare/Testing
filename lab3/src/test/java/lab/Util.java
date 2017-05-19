package lab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Util {
    public String getBaseUrl() {
        return baseUrl;
    }

    private String baseUrl;
    private String correctLogin = "cadaba@honor-8.com";
    private String correctPassword = "1234567890lab";

    public String getCorrectLogin() {
        return correctLogin;
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public Util() {
        System.setProperty("webdriver.chrome.driver", "D:\\projects\\itmo\\Testing\\chromedriver.exe");
        baseUrl = "http://www.ontodia.org/";
    }

    public void prepare(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(getBaseUrl());
    }

    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tryClick(WebDriver driver, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));

        element.click();
        //element.sendKeys(Keys.ENTER);
    }

    public void auth(WebDriver driver, String login, String password){
        String loginPath = "email";
        String passwordPath = "password";
        driver.get(baseUrl + "login");
        driver.findElement(By.id(loginPath)).clear();
        driver.findElement(By.id(loginPath)).sendKeys(login);
        driver.findElement(By.id(passwordPath)).clear();
        driver.findElement(By.id(passwordPath)).sendKeys(password);

        WebElement loginForm = driver.findElement(By.id("loginForm"));
        loginForm.submit();
    }

    public void auth(WebDriver driver){
        auth(driver, getCorrectLogin(), getCorrectPassword());
    }
}

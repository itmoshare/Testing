using NUnit.Framework;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.MultiTouch;
using OpenQA.Selenium.Remote;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AppiumDotNetSample.Tests
{
    public class AndroidBaseTest
    {
        public AndroidDriver<AppiumWebElement> driver;

        [OneTimeSetUp]
        public void BeforeAll()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.SetCapability("deviceName", "Android Emulator");
            capabilities.SetCapability("platformName", "Android");
            capabilities.SetCapability("app", $"{Environment.CurrentDirectory}/AppiumDotNetSample/Kate.apk");//full path needed((
            capabilities.SetCapability("noReset", true);
            capabilities.SetCapability("fullReset", false);
            capabilities.SetCapability("appActivity", "com.perm.kate.MainActivity");
            driver = new AndroidDriver<AppiumWebElement>(
                                       new Uri("http://127.0.0.1:4723/wd/hub"),
                                           capabilities);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
        }

        

        [OneTimeTearDown]
        public void AfterAll()
        {
            if (driver != null)
            {
                driver.Quit();
            }
        }
    }
}

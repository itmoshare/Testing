using NUnit.Framework;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.MultiTouch;
using OpenQA.Selenium.Remote;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AppiumDotNetSample.Tests
{
    public class AndroidBaseTest
    {
        public AndroidDriver<AppiumWebElement> _driver;
        public Size _size;

        [OneTimeSetUp]
        public void BeforeAll()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.SetCapability("deviceName", "Android Emulator");
            capabilities.SetCapability("platformName", "Android");
            capabilities.SetCapability("app", @"C:\Users\Andrey Shumeev\Desktop\testing\webdriver-java-quickstart-archetype\lab3Android\dotnet\AppiumDotNetSample\Kate.apk");//full path needed((
            capabilities.SetCapability("noReset", true);
            capabilities.SetCapability("fullReset", false);
            capabilities.SetCapability("appActivity", "com.perm.kate.MainActivity");
            _driver = new AndroidDriver<AppiumWebElement>(
                                       new Uri("http://127.0.0.1:4723/wd/hub"),
                                           capabilities);
            _driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
            _size = _driver.Manage().Window.Size;
        }

        

        [OneTimeTearDown]
        public void AfterAll()
        {
            if (_driver != null)
            {
                _driver.Quit();
            }
        }
    }
}

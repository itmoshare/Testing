using NUnit.Framework;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.MultiTouch;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AppiumDotNetSample.Tests
{
    class UnsubscribeFromGroupTest : AndroidBaseTest
    {
        public int AndroidKeys { get; private set; }

        [Test]
        public void UnsubscribeFromGroup()
        {
            //swipe to profile
            _driver.Swipe(100, _size.Height / 2, _size.Width - 100, _size.Height / 2, 1000);

            var groups = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Groups\")");
            groups.Click();

            var habr = _driver.FindElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().textContains(\"Хабрахабр\"));");
            habr.Click();

            Thread.Sleep(1000);

            var openMenu = new TouchAction(_driver);
            openMenu.Tap(_size.Width - 20, 150).Perform();


            var leave = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Leave group\")");
            leave.Click();

            var yes = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Yes\")");
            yes.Click();

            _driver.PressKeyCode(AndroidKeyCode.Back);
            try
            {
                habr = _driver.FindElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().textContains(\"Хабрахабр\"));");
            } catch (Exception e)
            {
                Assert.True(true);
                return;
            }
            Assert.True(false);
        }
    }
}

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
    
    public class AddWidgetTest : AndroidBaseTest
    {
        [Test]
        public void CheckAppRunning()
        {

            _driver.PressKeyCode(AndroidKeyCode.Home);
            Thread.Sleep(1000);

            var clickableCountBefore = _driver.FindElementsByAndroidUIAutomator("new UiSelector().clickable(true)").Count;
            
            var pressAndHold = new TouchAction(_driver);

            var action = pressAndHold.LongPress(500, 700);

            //SUPER DIRTY HACK
            var stepsField = pressAndHold.GetType()
                            .GetField("steps", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance);
            var field = (stepsField.GetValue(pressAndHold) as IEnumerable<object>).ElementAt(0);
            var dic = field.GetType()
                .GetField("parameters", System.Reflection.BindingFlags.NonPublic | System.Reflection.BindingFlags.Instance)
                .GetValue(field) as IDictionary;
            dic["action"] = "longPress";

            action.Perform();

            var widgetsButton = _driver.FindElementByAndroidUIAutomator("new UiSelector().textContains(\"WIDGETS\")");
            widgetsButton.Click();


            var kateSmall = _driver.FindElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().textContains(\"Kate Mobile 1x1\"));");



            var pressAndHoldKateWidget = new TouchAction(_driver)
                .Press(kateSmall.Location.X + 10, kateSmall.Location.Y + 20).Wait(500).MoveTo(100, -200)
                .Wait(2000).Release();
            pressAndHoldKateWidget.Perform();


            var clickableCountAfter = _driver.FindElementsByAndroidUIAutomator("new UiSelector().clickable(true)").Count;

            Assert.True(clickableCountAfter > clickableCountBefore);
        }
    }
}

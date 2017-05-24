using NUnit.Framework;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.MultiTouch;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppiumDotNetSample.Tests
{
    public class SendMessageTest : AndroidBaseTest
    {


        [Test]
        public void SendMessage()
        {
            var rightX = (int)(_size.Width * 0.8);
            var leftX = (int)(_size.Width * 0.2);
            _driver.Swipe(rightX, _size.Height / 2, leftX, _size.Height / 2, 500);
            EnterDialog("Andrey Shumeev");

            var message = Guid.NewGuid().ToString();
            WriteMessage(message);
            try
            {
                var messageElement = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"{message}\")");
                var textFieldWithHint = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Enter answer\")");
            } catch(Exception e)
            {
                Assert.True(false);
            }
            Assert.True(true);
        }

        

        private void EnterDialog(string name)
        {
            var dialog = _driver.FindElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + $"new UiSelector().textContains(\"{name}\"));");
            dialog.Click();
        }

        private void WriteMessage(string text)
        {
            var textField = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Enter answer\")");
            textField.Click();
            _driver.Keyboard.SendKeys(text);
            var touchSend = new TouchAction(_driver);
            touchSend.Tap(_size.Width - 15, _size.Height - 100).Perform();
            
        }
    }
}

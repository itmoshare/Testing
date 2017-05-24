using NUnit.Framework;
using OpenQA.Selenium.Appium.MultiTouch;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace AppiumDotNetSample.Tests
{
    class PostTest : AndroidBaseTest
    {
        [Test, Order(1)]
        public void WritePost()
        {
            var openNewPost = new TouchAction(_driver);
            openNewPost.Tap(_size.Width - 200, 100).Perform();
            _driver.Keyboard.SendKeys("Cool video");

            var attach = new TouchAction(_driver);
            attach.Tap(20, _size.Height - 10).Perform();

            var video = _driver.FindElementByAndroidUIAutomator("new UiSelector().textContains(\"Video\")");
            video.Click();

            var selectVideo = _driver.FindElementByAndroidUIAutomator("new UiSelector().textContains(\"Select video\")");
            selectVideo.Click();

            var sptVideo = _driver.FindElementByAndroidUIAutomator("new UiSelector().textContains(\"SPT\")");
            sptVideo.Click();

            var sendButton = _driver.FindElementByAndroidUIAutomator("new UiSelector().textContains(\"SEND\")");
            sendButton.Click();

            Thread.Sleep(1000);
            //swipe to refresh
            _driver.Swipe(_size.Width / 2, 500, _size.Width / 2, _size.Height - 200, 2000);

            Thread.Sleep(1000);


            try
            {
                _driver.FindElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().textContains(\"Cool video\"));");
            } catch(Exception e)
            {
                Assert.True(false);
            }

            Assert.True(true);
        }

        [Test, Order(2)]
        public void DeletePost()
        {
            //swipe to profile
            _driver.Swipe(100, _size.Height / 2, _size.Width - 100, _size.Height / 2, 1000);

            var wall = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Wall\")");
            wall.Click();

            var openMenu = new TouchAction(_driver);
            openMenu.Press(_size.Width / 2, 300).Wait(3000).Release().Perform();

            var delete = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Delete\")");
            delete.Click();

            var yes = _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Yes\")");
            yes.Click();

            try
            {
                _driver.FindElementByAndroidUIAutomator($"new UiSelector().textContains(\"Cool video\")");
            }
            catch (Exception e)
            {
                Assert.True(true);
                return;
            }

            Assert.True(false);
        }
    }
}

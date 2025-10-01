package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ReusableMethods {

    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageToLoad(int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop() {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void selectFromDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public static void selectFromDropdownByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public static void selectFromDropdownByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public static void clickElement(WebElement element) {
        try {
            waitForClickability(element, 10);
            element.click();
        } catch (Exception e) {
            clickWithJS(element);
        }
    }

    public static void sendKeys(WebElement element, String text) {
        waitForVisibility(element, 10);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebElement element) {
        waitForVisibility(element, 10);
        return element.getText();
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static void assertElementDisplayed(WebElement element, String elementName) {
        Assert.assertTrue(isElementDisplayed(element), elementName + " should be displayed");
    }

    public static void assertElementNotDisplayed(WebElement element, String elementName) {
        Assert.assertFalse(isElementDisplayed(element), elementName + " should not be displayed");
    }

    public static void assertTextContains(WebElement element, String expectedText, String elementName) {
        String actualText = getText(element);
        Assert.assertTrue(actualText.contains(expectedText),
                elementName + " should contain '" + expectedText + "' but was '" + actualText + "'");
    }

    public static void assertTextEquals(WebElement element, String expectedText, String elementName) {
        String actualText = getText(element);
        Assert.assertEquals(actualText, expectedText,
                elementName + " text should be '" + expectedText + "' but was '" + actualText + "'");
    }

    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Page title should contain '" + expectedTitle + "' but was '" + actualTitle + "'");
    }

    public static void assertPageURL(String expectedURL) {
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualURL.contains(expectedURL),
                "Page URL should contain '" + expectedURL + "' but was '" + actualURL + "'");
    }

    public static void assertListSize(List<WebElement> elements, int expectedSize, String listName) {
        Assert.assertEquals(elements.size(), expectedSize,
                listName + " should have " + expectedSize + " elements but had " + elements.size());
    }

    public static void assertListNotEmpty(List<WebElement> elements, String listName) {
        Assert.assertTrue(elements.size() > 0, listName + " should not be empty");
    }

    public static void waitForElementToDisappear(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void switchToNewWindow() {
        for (String windowHandle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(windowHandle);
        }
    }

    public static void switchToOriginalWindow(String originalWindow) {
        Driver.getDriver().switchTo().window(originalWindow);
    }

    public static void takeScreenshot(String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) Driver.getDriver();
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            // You can save this to a file if needed
            System.out.println("Screenshot taken for: " + testName);
        } catch (Exception e) {
            System.out.println("Could not take screenshot: " + e.getMessage());
        }
    }

    public static void printPageInfo() {
        try {
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());
            System.out.println("Page Title: " + Driver.getDriver().getTitle());
            System.out.println("Window Handles: " + Driver.getDriver().getWindowHandles().size());
        } catch (Exception e) {
            System.out.println("Could not get page info: " + e.getMessage());
        }
    }

    // Accept cookies popup if it appears
    public static void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
            By acceptAllButton = By.xpath("//a[@id='wt-cli-accept-all-btn']");
            wait.until(ExpectedConditions.presenceOfElementLocated(acceptAllButton));
            WebElement button = Driver.getDriver().findElement(acceptAllButton);
            if (button.isDisplayed() && button.isEnabled()) {
                try {
                    button.click();
                } catch (Exception clickEx) {
                    clickWithJS(button);
                }
            }
        } catch (Exception ignored) {
            // Popup not present or already accepted; ignore
        }
    }
}
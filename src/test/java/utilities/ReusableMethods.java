package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.AssertJUnit;
import pages.*;
import java.time.Duration;
import java.util.Set;

public class ReusableMethods {

    private static final Logger logger = LogManager.getLogger(ReusableMethods.class);

    // Basic utility methods
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPageToLoad(int seconds) {
        wait(seconds);
    }

    public static void waitForVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static void switchToNewWindow(String originalWindow) {
        Set<String> allWindows = Driver.getDriver().getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                Driver.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void acceptCookies(HomePage homePage) {
        try {
            waitForVisibility(homePage.acceptAllCookiesButton, 5);
            homePage.acceptAllCookiesButton.click();
            logger.info("✓ Cookies accepted");
        } catch (Exception e) {
            logger.info("✓ Cookie banner not present or already accepted");
        }
    }

    // Assertion methods
    public static void assertPageTitle(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Page title should contain '" + expectedTitle + "'. Actual: " + actualTitle);
    }

    public static void assertPageURL(String expectedURL) {
        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualURL.contains(expectedURL),
                "Page URL should contain '" + expectedURL + "'. Actual: " + actualURL);
    }

    public static void assertElementDisplayed(WebElement element, String elementName) {
        Assert.assertTrue(element.isDisplayed(), elementName + " should be displayed");
    }

    public static void verifyLocationsSectionAfterClicks(WebElement locationsSection2, WebElement rightSide, int clickCount) {
        for (int i = 0; i < clickCount; i++) {
            rightSide.click();
            wait(1);
        }
    }


    // ////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Test Case 1: Homepage verification
    public static void verifyInsiderHomePage(HomePage homePage) {
        // Verify page title contains "Insider"
        logger.info("Verifying page title contains 'Insider'...");
        assertPageTitle(ConfigReader.getProperty("insider.title"));
        logger.info("✓ Page title verification successful");

        // Verify page URL contains "useinsider.com"
        logger.info("Verifying page URL contains 'useinsider.com'...");
        assertPageURL("useinsider.com");
        logger.info("✓ Page URL verification successful");

        // Verify main page elements are displayed
        logger.info("Verifying main page elements are displayed...");
        verifyHomePageElements(homePage);
    }

    private static void verifyHomePageElements(HomePage homePage) {
        assertElementDisplayed(homePage.navigationBar, "Navigation Bar");
        logger.info("✓ Navigation Bar is displayed");

        assertElementDisplayed(homePage.companyDropdownMenu, "Company Dropdown Menu");
        logger.info("✓ Company Dropdown Menu is displayed");
    }

    // Test Case 2: Careers page verification
    public static void navigateAndVerifyCareersPage(HomePage homePage, CareersPage careersPage) {
        // Navigate to Careers page
        navigateToCareersPage(homePage);

        // Verify Careers page URL and title
        verifyCareersPageDetails();

        // Verify page sections
        verifyCareersPageSections(careersPage);
    }

    private static void navigateToCareersPage(HomePage homePage) {
        logger.info("Navigating to Careers page...");
        homePage.companyMenu.click();
        logger.info("✓ Company menu clicked");

        homePage.Careers.click();
        logger.info("✓ Careers menu clicked");
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
    }

    private static void verifyCareersPageDetails() {
        logger.info("Verifying Careers page URL and title...");
        assertPageURL("useinsider.com/careers/");
        logger.info("✓ Careers page URL verification successful");

        assertPageTitle(ConfigReader.getProperty("careers.title"));
        logger.info("✓ Careers page title verification successful");
    }

    private static void verifyCareersPageSections(CareersPage careersPage) {
        verifyTeamsSections(careersPage);
        verifyLocationsSections(careersPage);
        verifyLifeAtInsiderSection(careersPage);
    }

    private static void verifyTeamsSections(CareersPage careersPage) {
        logger.info("Verifying Teams sections are displayed...");
        scrollToElement(careersPage.teamsSection1);

        assertElementDisplayed(careersPage.teamsSection1, "Teams Section 1");
        logger.info("✓ Teams Section 1 is displayed");

        assertElementDisplayed(careersPage.teamsSection2, "Teams Section 2");
        logger.info("✓ Teams Section 2 is displayed");

        assertElementDisplayed(careersPage.teamsSection3, "Teams Section 3");
        logger.info("✓ Teams Section 3 is displayed");

        assertElementDisplayed(careersPage.seeAllTeamsButton, "See All Teams Button");
        logger.info("✓ See All Teams Button is displayed");
    }

    private static void verifyLocationsSections(CareersPage careersPage) {
        logger.info("Verifying Locations sections are displayed...");
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));

        scrollToElement(careersPage.locationsSection1);
        assertElementDisplayed(careersPage.locationsSection1, "Locations Section 1");
        logger.info("✓ Locations Section 1 is displayed");

        scrollToElement(careersPage.rightSide);
        verifyLocationsSectionAfterClicks(careersPage.locationsSection2, careersPage.rightSide, 3);
        assertElementDisplayed(careersPage.locationsSection2, "Locations Section 2");
        logger.info("✓ Locations Section 2 is displayed");
    }

    private static void verifyLifeAtInsiderSection(CareersPage careersPage) {
        logger.info("Verifying Life at Insider section is displayed...");
        scrollToElement(careersPage.lifeAtInsiderSection);

        assertElementDisplayed(careersPage.lifeAtInsiderSection, "Life at Insider Section");
        logger.info("✓ Life at Insider Section is displayed");
    }

    // Test Case 3: QA jobs filtering
    public static void navigateAndFilterQAJobs(QualityAssurancePage qualityAssurancePage, AllOpenPositionsPage allOpenPositionsPage) {
        // Navigate to Quality Assurance page
        navigateToQAPage();

        // Click "See all QA jobs" button
        clickSeeAllQAJobsButton(qualityAssurancePage);

        // Apply location filter
        applyIstanbulLocationFilter(allOpenPositionsPage);

        // Verify job list
        verifyJobListPresence(allOpenPositionsPage);
    }

    private static void navigateToQAPage() {
        logger.info("Navigating to QA page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertPageURL("useinsider.com/careers/quality-assurance/");
        assertPageTitle(ConfigReader.getProperty("qa.page.title"));
        logger.info("✓ QA page is successfully opened and verified");
    }

    private static void clickSeeAllQAJobsButton(QualityAssurancePage qualityAssurancePage) {
        qualityAssurancePage.seeAllQAJobsButton.click();
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertPageURL(ConfigReader.getProperty("URL2"));
        assertPageTitle(ConfigReader.getProperty("open.positions.title"));
        logger.info("✓ 'See all QA jobs' button is clicked and All Open Positions page is successfully opened and verified");
    }

    private static void applyIstanbulLocationFilter(AllOpenPositionsPage allOpenPositionsPage) {
        logger.info("Applying location filter for Istanbul, Turkey...");
        int defaultWait = Integer.parseInt(ConfigReader.getProperty("wait.default"));

        wait(defaultWait);
        allOpenPositionsPage.removeAllItemsFilterByLocationDropdownMenu.click();
        logger.info("✓ Existing location filters removed");

        wait(defaultWait);
        allOpenPositionsPage.filterByLocationDropdownMenu.click();
        wait(defaultWait);
        allOpenPositionsPage.filterByLocationDropdownMenu.click();
        logger.info("✓ Location dropdown opened");

        wait(defaultWait);
        scrollToElement(allOpenPositionsPage.istanbulTurkiye);
        wait(defaultWait);
        allOpenPositionsPage.istanbulTurkiye.click();
        logger.info("✓ Istanbul, Turkey location selected");
    }

    private static void verifyJobListPresence(AllOpenPositionsPage allOpenPositionsPage) {
        logger.info("Verifying job list is displayed...");
        scrollToElement(allOpenPositionsPage.jobCard);
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");
        logger.info("✓ Jobs are successfully filtered by Location: 'Istanbul, Turkey' and Department: 'Quality Assurance' and job list is displayed");
    }

    // Test Case 4: Job details verification
    public static void verifyJobDetailsContent(AllOpenPositionsPage allOpenPositionsPage) {
        // Navigate to Open Positions page and apply filter
        navigateToOpenPositionsAndFilter(allOpenPositionsPage);

        // Verify job details (recursive call kaldırıldı)
        verifyJobDetailsContentInternal(allOpenPositionsPage);
    }

    private static void navigateToOpenPositionsAndFilter(AllOpenPositionsPage allOpenPositionsPage) {
        logger.info("Navigating to All Open Positions page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        logger.info("✓ All Open Positions page loaded");

        applyIstanbulLocationFilter(allOpenPositionsPage);

        logger.info("Preparing job card for verification...");
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        scrollToElement(allOpenPositionsPage.jobCard);
        waitForVisibility(allOpenPositionsPage.jobCard, Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");
        logger.info("✓ Job card is displayed and ready for verification");
    }

    private static void verifyJobDetailsContentInternal(AllOpenPositionsPage allOpenPositionsPage) {
        logger.info("Verifying job details content...");
        String qaDepartment = ConfigReader.getProperty("qa.department");

        // Get text content from job details
        String positionText = allOpenPositionsPage.position.getText();
        String departmentText = allOpenPositionsPage.department.getText();
        String locationText = allOpenPositionsPage.location.getText();

        // Verify Position contains "Quality Assurance"
        Assert.assertTrue(positionText.contains(qaDepartment),
                "Position should contain '" + qaDepartment + "'. Actual: " + positionText);
        logger.info("✓ Position contains '" + qaDepartment + "': " + positionText);

        // Verify Department contains "Quality Assurance"
        Assert.assertTrue(departmentText.contains(qaDepartment),
                "Department should contain '" + qaDepartment + "'. Actual: " + departmentText);
        logger.info("✓ Department contains '" + qaDepartment + "': " + departmentText);

        // Verify Location contains "Istanbul, Turkiye"
        Assert.assertTrue(locationText.contains("Istanbul, Turkiye"),
                "Location should contain 'Istanbul, Turkiye'. Actual: " + locationText);
        logger.info("✓ Location contains 'Istanbul, Turkiye': " + locationText);

        logger.info("✓ All job details verified successfully");
    }


    // Test Case 5: Lever application redirect
    public static void verifyLeverApplicationRedirect(AllOpenPositionsPage allOpenPositionsPage, LeverApplicationFormPage leverApplicationFormPage) {
        // Navigate and prepare for View Role button click
        prepareForViewRoleClick(allOpenPositionsPage);

        // Click View Role and verify redirect
        clickViewRoleAndVerifyRedirect(allOpenPositionsPage, leverApplicationFormPage);
    }

    private static void prepareForViewRoleClick(AllOpenPositionsPage allOpenPositionsPage) {
        logger.info("Navigating to All Open Positions page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        logger.info("✓ All Open Positions page loaded");

        applyIstanbulLocationFilter(allOpenPositionsPage);

        logger.info("Preparing to click View Role button...");
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        scrollToElement(allOpenPositionsPage.jobCard);
        hover(allOpenPositionsPage.jobCard);
        logger.info("✓ Hovered over job card to reveal View Role button");

        waitForClickability(allOpenPositionsPage.viewRoleButton, Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
    }

    private static void clickViewRoleAndVerifyRedirect(AllOpenPositionsPage allOpenPositionsPage, LeverApplicationFormPage leverApplicationFormPage) {
        // Save current window handle
        String originalWindow = Driver.getDriver().getWindowHandle();
        logger.info("✓ Original window handle saved");

        // Click View Role button
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        allOpenPositionsPage.viewRoleButton.click();
        logger.info("✓ View Role button clicked");

        // Switch to new window and verify
        logger.info("Switching to new window...");
        switchToNewWindow(originalWindow);
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        logger.info("✓ Successfully switched to new window");

        // Verify Lever Application page
        verifyLeverApplicationPage(leverApplicationFormPage);
    }

    private static void verifyLeverApplicationPage(LeverApplicationFormPage leverApplicationFormPage) {
        logger.info("Verifying Lever Application page...");
        assertPageURL(ConfigReader.getProperty("lever.url"));
        logger.info("✓ Lever Application page URL verification successful");

        assertElementDisplayed(leverApplicationFormPage.applyForThisJobButton, "Apply for this job button");
        logger.info("✓ Apply for this job button is displayed and verified");
    }
}

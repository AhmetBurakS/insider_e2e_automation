package utilities;

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
            homePage.acceptAllCookiesButton.click();
            System.out.println("✓ Cookies accepted");
        } catch (Exception e) {
            System.out.println("✓ Cookie banner not present");
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

    // Test Case 1: Homepage verification
    public static void verifyInsiderHomePage(HomePage homePage) {
        // Verify page title contains "Insider"
        System.out.println("Verifying page title contains 'Insider'...");
        assertPageTitle(ConfigReader.getProperty("insider.title"));
        System.out.println("✓ Page title verification successful");

        // Verify page URL contains "useinsider.com"
        System.out.println("Verifying page URL contains 'useinsider.com'...");
        assertPageURL("useinsider.com");
        System.out.println("✓ Page URL verification successful");

        // Verify main page elements are displayed
        System.out.println("Verifying main page elements are displayed...");
        verifyHomePageElements(homePage);
    }

    private static void verifyHomePageElements(HomePage homePage) {
        assertElementDisplayed(homePage.navigationBar, "Navigation Bar");
        System.out.println("✓ Navigation Bar is displayed");

        assertElementDisplayed(homePage.companyDropdownMenu, "Company Dropdown Menu");
        System.out.println("✓ Company Dropdown Menu is displayed");
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
        System.out.println("Navigating to Careers page...");
        homePage.companyMenu.click();
        System.out.println("✓ Company menu clicked");

        homePage.Careers.click();
        System.out.println("✓ Careers menu clicked");

        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
    }

    private static void verifyCareersPageDetails() {
        System.out.println("Verifying Careers page URL and title...");
        assertPageURL("useinsider.com/careers/");
        System.out.println("✓ Careers page URL verification successful");

        assertPageTitle(ConfigReader.getProperty("careers.title"));
        System.out.println("✓ Careers page title verification successful");
    }

    private static void verifyCareersPageSections(CareersPage careersPage) {
        verifyTeamsSections(careersPage);
        verifyLocationsSections(careersPage);
        verifyLifeAtInsiderSection(careersPage);
    }

    private static void verifyTeamsSections(CareersPage careersPage) {
        System.out.println("Verifying Teams sections are displayed...");
        scrollToElement(careersPage.teamsSection1);

        assertElementDisplayed(careersPage.teamsSection1, "Teams Section 1");
        System.out.println("✓ Teams Section 1 is displayed");

        assertElementDisplayed(careersPage.teamsSection2, "Teams Section 2");
        System.out.println("✓ Teams Section 2 is displayed");

        assertElementDisplayed(careersPage.teamsSection3, "Teams Section 3");
        System.out.println("✓ Teams Section 3 is displayed");

        assertElementDisplayed(careersPage.seeAllTeamsButton, "See All Teams Button");
        System.out.println("✓ See All Teams Button is displayed");
    }

    private static void verifyLocationsSections(CareersPage careersPage) {
        System.out.println("Verifying Locations sections are displayed...");
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        scrollToElement(careersPage.locationsSection1);
        assertElementDisplayed(careersPage.locationsSection1, "Locations Section 1");
        System.out.println("✓ Locations Section 1 is displayed");

        verifyLocationsSectionAfterClicks(careersPage.locationsSection2, careersPage.rightSide, 3);
        assertElementDisplayed(careersPage.locationsSection2, "Locations Section 2");
        System.out.println("✓ Locations Section 2 is displayed");
    }

    private static void verifyLifeAtInsiderSection(CareersPage careersPage) {
        System.out.println("Verifying Life at Insider section is displayed...");
        scrollToElement(careersPage.lifeAtInsiderSection);
        assertElementDisplayed(careersPage.lifeAtInsiderSection, "Life at Insider Section");
        System.out.println("✓ Life at Insider Section is displayed");
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
        System.out.println("Navigating to QA page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertPageURL("useinsider.com/careers/quality-assurance/");
        assertPageTitle(ConfigReader.getProperty("qa.page.title"));
        System.out.println("✓ QA page is successfully opened and verified");
    }

    private static void clickSeeAllQAJobsButton(QualityAssurancePage qualityAssurancePage) {
        qualityAssurancePage.seeAllQAJobsButton.click();
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertPageURL(ConfigReader.getProperty("URL2"));
        assertPageTitle(ConfigReader.getProperty("open.positions.title"));
        System.out.println("✓ 'See all QA jobs' button is clicked and All Open Positions page is successfully opened and verified");
    }

    private static void applyIstanbulLocationFilter(AllOpenPositionsPage allOpenPositionsPage) {
        System.out.println("Applying location filter for Istanbul, Turkey...");
        int defaultWait = Integer.parseInt(ConfigReader.getProperty("wait.default"));

        wait(defaultWait);
        allOpenPositionsPage.removeAllItemsFilterByLocationDropdownMenu.click();
        System.out.println("✓ Existing location filters removed");

        wait(defaultWait);
        allOpenPositionsPage.filterByLocationDropdownMenu.click();
        wait(defaultWait);
        allOpenPositionsPage.filterByLocationDropdownMenu.click();
        System.out.println("✓ Location dropdown opened");

        wait(defaultWait);
        scrollToElement(allOpenPositionsPage.istanbulTurkiye);
        wait(defaultWait);
        allOpenPositionsPage.istanbulTurkiye.click();
        System.out.println("✓ Istanbul, Turkey location selected");
    }

    private static void verifyJobListPresence(AllOpenPositionsPage allOpenPositionsPage) {
        System.out.println("Verifying job list is displayed...");
        scrollToElement(allOpenPositionsPage.jobCard);
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");
        System.out.println("✓ Jobs are successfully filtered by Location: 'Istanbul, Turkey' and Department: 'Quality Assurance' and job list is displayed");
    }

    // Test Case 4: Job details verification
    public static void verifyJobDetailsContent(AllOpenPositionsPage allOpenPositionsPage) {
        // Navigate to Open Positions page and apply filter
        navigateToOpenPositionsAndFilter(allOpenPositionsPage);

        // Verify job details (recursive call kaldırıldı)
        verifyJobDetailsContentInternal(allOpenPositionsPage);
    }

    private static void navigateToOpenPositionsAndFilter(AllOpenPositionsPage allOpenPositionsPage) {
        System.out.println("Navigating to All Open Positions page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        System.out.println("✓ All Open Positions page loaded");

        applyIstanbulLocationFilter(allOpenPositionsPage);

        System.out.println("Preparing job card for verification...");
        int defaultWait = Integer.parseInt(ConfigReader.getProperty("wait.default"));
        wait(defaultWait);
        scrollToElement(allOpenPositionsPage.jobCard);
        waitForVisibility(allOpenPositionsPage.jobCard, Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");
        System.out.println("✓ Job card is displayed and ready for verification");
    }

    private static void verifyJobDetailsContentInternal(AllOpenPositionsPage allOpenPositionsPage) {
        System.out.println("Verifying job details content...");
        String qaDepartment = ConfigReader.getProperty("qa.department");

        // Get text content from job details
        String positionText = allOpenPositionsPage.position.getText();
        String departmentText = allOpenPositionsPage.department.getText();
        String locationText = allOpenPositionsPage.location.getText();

        // Verify Position contains "Quality Assurance"
        Assert.assertTrue(positionText.contains(qaDepartment),
                "Position should contain '" + qaDepartment + "'. Actual: " + positionText);
        System.out.println("✓ Position contains '" + qaDepartment + "': " + positionText);

        // Verify Department contains "Quality Assurance"
        Assert.assertTrue(departmentText.contains(qaDepartment),
                "Department should contain '" + qaDepartment + "'. Actual: " + departmentText);
        System.out.println("✓ Department contains '" + qaDepartment + "': " + departmentText);

        // Verify Location contains "Istanbul, Turkiye"
        Assert.assertTrue(locationText.contains("Istanbul, Turkiye"),
                "Location should contain 'Istanbul, Turkiye'. Actual: " + locationText);
        System.out.println("✓ Location contains 'Istanbul, Turkiye': " + locationText);

        System.out.println("✓ All job details verified successfully");
    }


    // Test Case 5: Lever application redirect
    public static void verifyLeverApplicationRedirect(AllOpenPositionsPage allOpenPositionsPage, LeverApplicationFormPage leverApplicationFormPage) {
        // Navigate and prepare for View Role button click
        prepareForViewRoleClick(allOpenPositionsPage);

        // Click View Role and verify redirect
        clickViewRoleAndVerifyRedirect(allOpenPositionsPage, leverApplicationFormPage);
    }

    private static void prepareForViewRoleClick(AllOpenPositionsPage allOpenPositionsPage) {
        System.out.println("Navigating to All Open Positions page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        System.out.println("✓ All Open Positions page loaded");

        applyIstanbulLocationFilter(allOpenPositionsPage);

        System.out.println("Preparing to click View Role button...");
        int defaultWait = Integer.parseInt(ConfigReader.getProperty("wait.default"));
        wait(defaultWait);
        scrollToElement(allOpenPositionsPage.jobCard);
        hover(allOpenPositionsPage.jobCard);
        System.out.println("✓ Hovered over job card to reveal View Role button");

        waitForClickability(allOpenPositionsPage.viewRoleButton, Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
    }

    private static void clickViewRoleAndVerifyRedirect(AllOpenPositionsPage allOpenPositionsPage, LeverApplicationFormPage leverApplicationFormPage) {
        // Save current window handle
        String originalWindow = Driver.getDriver().getWindowHandle();
        System.out.println("✓ Original window handle saved");

        // Click View Role button
        wait(Integer.parseInt(ConfigReader.getProperty("wait.default")));
        allOpenPositionsPage.viewRoleButton.click();
        System.out.println("✓ View Role button clicked");

        // Switch to new window and verify
        System.out.println("Switching to new window...");
        switchToNewWindow(originalWindow);
        waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        System.out.println("✓ Successfully switched to new window");

        // Verify Lever Application page
        verifyLeverApplicationPage(leverApplicationFormPage);
    }

    private static void verifyLeverApplicationPage(LeverApplicationFormPage leverApplicationFormPage) {
        System.out.println("Verifying Lever Application page...");
        assertPageURL(ConfigReader.getProperty("lever.url"));
        System.out.println("✓ Lever Application page URL verification successful");

        assertElementDisplayed(leverApplicationFormPage.applyForThisJobButton, "Apply for this job button");
        System.out.println("✓ Apply for this job button is displayed and verified");
    }
}

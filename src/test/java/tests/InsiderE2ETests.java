package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class InsiderE2ETests {

    HomePage homePage;
    CareersPage careersPage;
    QualityAssurancePage qualityAssurancePage;
    AllOpenPositionsPage allOpenPositionsPage;
    LeverApplicationFormPage leverApplicationFormPage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("BaseURL"));
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.acceptCookiesIfPresent();
        homePage = new HomePage();
        careersPage = new CareersPage();
        qualityAssurancePage = new QualityAssurancePage();
        allOpenPositionsPage = new AllOpenPositionsPage();
        leverApplicationFormPage = new LeverApplicationFormPage();
    }

    @Test(description = "Test Case 1: Visit https://useinsider.com/ and check Insider home page is opened or not")
    public void test01_VerifyInsiderHomePageOpened() {
        // Verify page title contains "Insider"
        ReusableMethods.assertPageTitle("Insider");

        // Verify page URL contains "useinsider.com"
        ReusableMethods.assertPageURL("useinsider.com");

        // Verify main page elements are displayed
        ReusableMethods.assertElementDisplayed(homePage.navigationBar, "Navigation Bar");
        ReusableMethods.assertElementDisplayed(homePage.pageBody, "Page Body");

        // Verify Company dropdown menu is present
        ReusableMethods.assertElementDisplayed(homePage.companyDropdownMenu, "Company Dropdown Menu");

        System.out.println("✓ Test 1 PASSED: Insider home page is successfully opened and verified");
    }

    @Test(description = "Test Case 2: Select the 'Company' menu in the navigation bar, select 'Careers' and check Career page, its Locations, Teams, and Life at Insider blocks are open or not")
    public void test02_VerifyCareersPageAndSections() {
        // Navigate to Careers page
        homePage.companyMenu.click();
        homePage.Careers.click();
        ReusableMethods.waitForPageToLoad(10);

        // Verify Careers page URL and title
        ReusableMethods.assertPageURL("useinsider.com/careers/");
        ReusableMethods.assertPageTitle("Careers");

        // Verify Teams block and sections are displayed
        ReusableMethods.scrollToElement(careersPage.teamsSection1);
        ReusableMethods.assertElementDisplayed(careersPage.teamsSection1, "Teams Section 1");
        ReusableMethods.assertElementDisplayed(careersPage.teamsSection2, "Teams Section 2");
        ReusableMethods.assertElementDisplayed(careersPage.teamsSection3, "Teams Section 3");
        ReusableMethods.assertElementDisplayed(careersPage.seeAllTeamsButton, "See All Teams Button");

        // Verify Locations block and sections are displayed
        ReusableMethods.scrollToElement(careersPage.locationsSection1);
        ReusableMethods.assertElementDisplayed(careersPage.locationsSection1, "Locations Section 1");
        ReusableMethods.verifyLocationsSectionAfterClicks(careersPage.locationsSection2, careersPage.rightSide, 3);
        ReusableMethods.assertElementDisplayed(careersPage.locationsSection2, "Locations Section 2");

        // Verify Life at Insider block is displayed
        ReusableMethods.scrollToElement(careersPage.lifeAtInsiderSection);
        ReusableMethods.assertElementDisplayed(careersPage.lifeAtInsiderSection, "Life at Insider Section");

        System.out.println("✓ Test 2 PASSED: Careers page and its sections are successfully opened and verified");
    }

    @Test(description = "Test Case 3: Go to https://useinsider.com/careers/quality-assurance/, click 'See all QA jobs', filter jobs by Location: 'Istanbul, Turkey', and Department: 'Quality Assurance', check the presence of the jobs list")
    public void test03_VerifyQAJobsFiltering() {

        // Navigate to Quality Assurance page
        System.out.println("Navigating to QA page...");
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.assertPageURL("useinsider.com/careers/quality-assurance/");
        ReusableMethods.assertPageTitle("quality assurance");
        System.out.println("✓ QA page is successfully opened and verified");

        // Click "See all QA jobs" button
        qualityAssurancePage.seeAllQAJobsButton.click();
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.assertPageURL("https://useinsider.com/careers/open-positions/?department=qualityassurance");
        ReusableMethods.assertPageTitle("Insider Open Positions | Insider");
        System.out.println("✓ 'See all QA jobs' button is clicked and All Open Positions page is successfully opened and verified");

        //Filter jobs by Location: 'Istanbul, Turkey', and Department: 'Quality Assurance'
        ReusableMethods.waitForVisibility(allOpenPositionsPage.filterByLocationDropdownMenu, 10);

        ReusableMethods.performAction(allOpenPositionsPage.filterByLocationDropdownMenu, "hover");
        ReusableMethods.performAction(allOpenPositionsPage.filterByLocationDropdownMenu, "doubleClick");

        ReusableMethods.clickWithJS(allOpenPositionsPage.filterByLocationDropdownMenu);
        ReusableMethods.wait(3);

        // WebDriverWait kullanarak Istanbul seçeneğini bekle ve tıkla
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement istanbulOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(text(), 'Istanbul, Turkey')] | //span[contains(text(), 'Istanbul, Turkey')] | //*[contains(text(), 'Istanbul')]")
        ));
        ReusableMethods.clickWithJS(istanbulOption);

        ReusableMethods.wait(3);
        ReusableMethods.waitForVisibility(allOpenPositionsPage.filterByDepartmentDropdownMenu, 10);
        ReusableMethods.clickWithJS(allOpenPositionsPage.filterByDepartmentDropdownMenu);
        ReusableMethods.wait(3);

        // WebDriverWait kullanarak Quality Assurance seçeneğini bekle ve tıkla
        WebElement qaOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(text(), 'Quality Assurance')] | //span[contains(text(), 'Quality Assurance')] | //*[contains(text(), 'Quality')]")
        ));
        ReusableMethods.clickWithJS(qaOption);

        //Verify the presence of the jobs list
        ReusableMethods.scrollToElement(allOpenPositionsPage.jobCard);
        ReusableMethods.assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");
        System.out.println("✓ Jobs are successfully filtered by Location: 'Istanbul, Turkey' and Department: 'Quality Assurance' and job list is displayed");

        System.out.println("✓ Test 3 PASSED: QA jobs filtering is successfully completed and verified");
    }

    @Test(description = "Test Case 4: Check that all jobs' Position contains 'Quality Assurance', Department contains 'Quality Assurance', and Location contains 'Istanbul, Turkey'")
    public void test04_VerifyJobDetails() {

        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.waitForVisibility(allOpenPositionsPage.jobCard, 20);
        ReusableMethods.scrollToElement(allOpenPositionsPage.jobCard);
        ReusableMethods.assertElementDisplayed(allOpenPositionsPage.jobCard, "Job Card");

        // Position kontrolü
        String positionText = allOpenPositionsPage.position.getText();
        assert positionText.contains("Quality Assurance") : "Position does not contain 'Quality Assurance'. Actual: " + positionText;

        // Department kontrolü
        String departmentText = allOpenPositionsPage.department.getText();
        assert departmentText.contains("Quality Assurance") : "Department does not contain 'Quality Assurance'. Actual: " + departmentText;

        // Location kontrolü
        String locationText = allOpenPositionsPage.location.getText();
        assert locationText.contains("Istanbul, Turkey") : "Location does not contain 'Istanbul, Turkey'. Actual: " + locationText;

        System.out.println("✓ Test 4 PASSED: All job details verified successfully");
    }

    @Test(description = "Test Case 5: Click the 'View Role' button and check that this action redirects us to the Lever Application form page")
    public void test05_VerifyLeverApplicationRedirect() {

        Driver.getDriver().get(ConfigReader.getProperty("URL2"));
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.scrollToElement(allOpenPositionsPage.jobCard);
        ReusableMethods.waitForClickability(allOpenPositionsPage.viewRoleButton, 10);
        allOpenPositionsPage.viewRoleButton.click();
        ReusableMethods.waitForPageToLoad(10);
        ReusableMethods.assertPageURL("https://jobs.lever.co/useinsider/");
        ReusableMethods.assertElementDisplayed(leverApplicationFormPage.applyForThisJobButton, "Application Form");
        System.out.println("✓ Test 5 PASSED: Redirected to Lever Application form page and verified successfully");

    }


    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}

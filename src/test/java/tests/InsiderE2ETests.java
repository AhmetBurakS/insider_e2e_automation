package tests;

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


    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}

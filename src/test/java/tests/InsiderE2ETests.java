package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import io.qameta.allure.*;

@Epic("Insider E2E Tests")
@Feature("Web UI Tests")
public class InsiderE2ETests {

    HomePage homePage;
    CareersPage careersPage;
    QualityAssurancePage qualityAssurancePage;
    AllOpenPositionsPage allOpenPositionsPage;
    LeverApplicationFormPage leverApplicationFormPage;

    @BeforeMethod
    public void setUp() {
        initializePageObjects();
        Driver.getDriver().get(ConfigReader.getProperty("BaseURL"));
        ReusableMethods.waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
        ReusableMethods.acceptCookies(homePage);
    }

    private void initializePageObjects() {
        homePage = new HomePage();
        careersPage = new CareersPage();
        qualityAssurancePage = new QualityAssurancePage();
        allOpenPositionsPage = new AllOpenPositionsPage();
        leverApplicationFormPage = new LeverApplicationFormPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Home Page Verification")
    @Test(description = "Test Case 1: Visit https://useinsider.com/ and check Insider home page is opened or not")
    public void test01_VerifyInsiderHomePageOpened() {
        System.out.println("Starting Test 1: Verifying Insider home page");
        ReusableMethods.verifyInsiderHomePage(homePage);
        System.out.println("✓ Test 1 PASSED: Insider home page is successfully opened and verified");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("Careers Page Verification")
    @Test(description = "Test Case 2: Select the 'Company' menu in the navigation bar, select 'Careers' and check Career page, its Locations, Teams, and Life at Insider blocks are open or not")
    public void test02_VerifyCareersPageAndSections() {
        System.out.println("Starting Test 2: Verifying Careers page and sections");
        ReusableMethods.navigateAndVerifyCareersPage(homePage, careersPage);
        System.out.println("✓ Test 2 PASSED: Careers page and its sections are successfully opened and verified");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("QA Jobs Filtering")
    @Test(description = "Test Case 3: Go to https://useinsider.com/careers/quality-assurance/, click 'See all QA jobs', filter jobs by Location: 'Istanbul, Turkey', and Department: 'Quality Assurance', check the presence of the jobs list")
    public void test03_VerifyQAJobsFiltering() {
        System.out.println("Starting Test 3: Verifying QA jobs filtering");
        ReusableMethods.navigateAndFilterQAJobs(qualityAssurancePage, allOpenPositionsPage);
        System.out.println("✓ Test 3 PASSED: QA jobs filtering is successfully completed and verified");
    }


    @Severity(SeverityLevel.CRITICAL)
    @Story("Job Details Verification")
    @Test(description = "Test Case 4: Check that all jobs' Position contains 'Quality Assurance', Department contains 'Quality Assurance', and Location contains 'Istanbul, Turkey'")
    public void test04_VerifyJobDetails() {
        System.out.println("Starting Test 4: Verifying job details");
        ReusableMethods.verifyJobDetailsContent(allOpenPositionsPage);
        System.out.println("✓ Test 4 PASSED: All job details verified successfully");
    }


    @Severity(SeverityLevel.NORMAL)
    @Story("Application Form Redirect")
    @Test(description = "Test Case 5: Click the 'View Role' button and check that this action redirects us to the Lever Application form page")
    public void test05_VerifyLeverApplicationRedirect() {
        System.out.println("Starting Test 5: Verifying Lever Application redirect");
        ReusableMethods.verifyLeverApplicationRedirect(allOpenPositionsPage, leverApplicationFormPage);
        System.out.println("✓ Test 5 PASSED: Redirected to Lever Application form page and verified successfully");
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}

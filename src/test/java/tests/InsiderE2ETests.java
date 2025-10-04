package tests;

import org.testng.annotations.Test;
import utilities.ReusableMethods;
import io.qameta.allure.*;

@Epic("Insider E2E Tests")
@Feature("Web UI Tests")
public class InsiderE2ETests extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Story("Home Page Verification")
    @Test(description = "Test Case 1: Visit https://useinsider.com/ and check Insider home page is opened or not")
    public void test01_VerifyInsiderHomePageOpened() {
        logger.info("Starting Test 1: Verifying Insider home page");
        ReusableMethods.verifyInsiderHomePage(homePage);
        logger.info("✓ Test 1 PASSED: Insider home page is successfully opened and verified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Careers Page Verification")
    @Test(description = "Test Case 2: Select the 'Company' menu in the navigation bar, select 'Careers' and check Career page, its Locations, Teams, and Life at Insider blocks are open or not")
    public void test02_VerifyCareersPageAndSections() {
        logger.info("Starting Test 2: Verifying Careers page and sections");
        ReusableMethods.navigateAndVerifyCareersPage(homePage, careersPage);
        logger.info("✓ Test 2 PASSED: Careers page and its sections are successfully opened and verified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("QA Jobs Filtering")
    @Test(description = "Test Case 3: Go to https://useinsider.com/careers/quality-assurance/, click 'See all QA jobs', filter jobs by Location: 'Istanbul, Turkey', and Department: 'Quality Assurance', check the presence of the jobs list")
    public void test03_VerifyQAJobsFiltering() {
        logger.info("Starting Test 3: Verifying QA jobs filtering");
        ReusableMethods.navigateAndFilterQAJobs(qualityAssurancePage, allOpenPositionsPage);
        logger.info("✓ Test 3 PASSED: QA jobs filtering is successfully completed and verified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Job Details Verification")
    @Test(description = "Test Case 4: Check that all jobs' Position contains 'Quality Assurance', Department contains 'Quality Assurance', and Location contains 'Istanbul, Turkey'")
    public void test04_VerifyJobDetails() {
        logger.info("Starting Test 4: Verifying job details");
        ReusableMethods.verifyJobDetailsContent(allOpenPositionsPage);
        logger.info("✓ Test 4 PASSED: All job details verified successfully");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Application Form Redirect")
    @Test(description = "Test Case 5: Click the 'View Role' button and check that this action redirects us to the Lever Application form page")
    public void test05_VerifyLeverApplicationRedirect() {
        logger.info("Starting Test 5: Verifying Lever Application redirect");
        ReusableMethods.verifyLeverApplicationRedirect(allOpenPositionsPage, leverApplicationFormPage);
        logger.info("✓ Test 5 PASSED: Redirected to Lever Application form page and verified successfully");
    }
}

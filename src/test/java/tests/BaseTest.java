package tests;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected HomePage homePage;
    protected CareersPage careersPage;
    protected QualityAssurancePage qualityAssurancePage;
    protected AllOpenPositionsPage allOpenPositionsPage;
    protected LeverApplicationFormPage leverApplicationFormPage;

    @BeforeMethod
    @Step("Test ortamı hazırlanıyor")
    public void setUp() {
        try {
            logger.info("Test başlatılıyor...");
            Driver.getDriver().get(ConfigReader.getProperty("BaseURL"));
            logger.info("Tarayıcı başlatıldı ve URL açıldı");
            initializePageObjects();
            ReusableMethods.acceptCookies(homePage);
            ReusableMethods.waitForPageToLoad(Integer.parseInt(ConfigReader.getProperty("wait.page.load")));
            logger.info("Test ortamı hazırlandı");
        } catch (Exception e) {
            logger.error("Test ortamı hazırlanırken hata: " + e.getMessage());
            try {
                if (Driver.getDriver() != null) {
                    Driver.closeDriver();
                }
            } catch (Exception cleanupException) {
                logger.error("Driver cleanup sırasında hata: " + cleanupException.getMessage());
            }
            throw new RuntimeException("Test setup başarısız: " + e.getMessage(), e);
        }
    }

    private void initializePageObjects() {
        homePage = new HomePage();
        careersPage = new CareersPage();
        qualityAssurancePage = new QualityAssurancePage();
        allOpenPositionsPage = new AllOpenPositionsPage();
        leverApplicationFormPage = new LeverApplicationFormPage();
    }

    @AfterMethod
    @Step("Test ortamı temizleniyor")
    public void tearDown() {
        logger.info("Test temizleniyor...");

        if (Driver.getDriver() != null) {
            Driver.closeDriver();
            logger.info("✓ Tarayıcı kapatıldı");
        }

        logger.info("Test tamamlandı");
    }
}

package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                String browser = ConfigReader.getProperty("browser");
                String headless = ConfigReader.getProperty("headless");
                boolean isHeadless = "true".equalsIgnoreCase(headless);

                switch (browser.toLowerCase()) {
                    case "safari":
                        driver = new SafariDriver();
                        break;

                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = new FirefoxDriver(firefoxOptions);
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--disable-notifications");
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    default:
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--incognito");
                        options.addArguments("--disable-notifications");

                        if (isHeadless) {
                            options.addArguments("--headless");
                            options.addArguments("--no-sandbox");
                            options.addArguments("--disable-dev-shm-usage");
                            options.addArguments("--disable-gpu");
                            System.out.println("Chrome running in headless mode");
                        }

                        driver = new ChromeDriver(options);
                        break;
                }

                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            } catch (Exception e) {
                System.err.println("Driver oluşturulamadı: " + e.getMessage());
                throw new RuntimeException("Driver başlatma hatası", e);
            }
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Driver kapatma hatası: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}

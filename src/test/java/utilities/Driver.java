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

    private Driver(){

    }

    public static WebDriver driver;
    public static WebDriver getDriver(){

        String browser = ConfigReader.getProperty("browser");
        String headless = ConfigReader.getProperty("headless");
        boolean isHeadless = "true".equalsIgnoreCase(headless);

        if (driver == null){

            switch (browser){

                case "safari":
                    // Safari headless desteklemiyor
                    driver = new SafariDriver();
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("--headless");
                        System.out.println("Firefox running in headless mode");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-notifications");
                    if (isHeadless) {
                        edgeOptions.addArguments("--headless");
                        edgeOptions.addArguments("--no-sandbox");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                        edgeOptions.addArguments("--disable-gpu");
                        System.out.println("Edge running in headless mode");
                    }
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    // Chrome için seçenekler tanımlanıyor
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito"); // Gizli mod
                    options.addArguments("--disable-notifications"); // Bildirimleri devre dışı bırakma

                    // Headless mod kontrolü
                    if (isHeadless) {
                        options.addArguments("--headless");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                        System.out.println("Chrome running in headless mode");
                    }

                    driver = new ChromeDriver(options); // ChromeDriver oluşturuluyor

            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}

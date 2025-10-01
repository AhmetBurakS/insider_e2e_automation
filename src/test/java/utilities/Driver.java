package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    private Driver(){

    }

    public static WebDriver driver;
    public static WebDriver getDriver(){

        String browser = ConfigReader.getProperty("browser");

        if (driver == null){

            switch (browser){

                case "safari":
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    // Chrome için seçenekler tanımlanıyor
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito"); // Gizli mod
                    options.addArguments("--disable-notifications"); // Bildirimleri devre dışı bırakma

                    driver = new ChromeDriver(options); // ChromeDriver oluşturuluyor
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){
            driver.quit(); // Tarayıcıyı tamamen kapatma
            driver = null;
        }
    }
}

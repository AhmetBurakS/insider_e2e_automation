package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CareersPage {

    public CareersPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//h3[text()='Customer Success']")
    public WebElement customerSuccess;

    @FindBy(xpath = "//h3[text()='Sales']")
    public WebElement sales;

    @FindBy(xpath = "//h3[text()='Product & Engineering']")
    public WebElement productEngineering;

    @FindBy(xpath = "")
    public WebElement newyork;

    @FindBy(xpath = "//i[@class='icon-arrow-right location-slider-next ml-4 text-xsmall text-dark']")
    public WebElement rightSide;
    //3 kez click yap

    @FindBy(xpath = "//p[text()='Helsinki']")
    public WebElement helsinki;

    @FindBy(xpath = "//h2[text()='Life at Insider']")
    public WebElement lifeAtInsider;
}

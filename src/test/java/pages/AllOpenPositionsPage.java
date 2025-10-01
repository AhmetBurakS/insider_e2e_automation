package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AllOpenPositionsPage {

    public AllOpenPositionsPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//span[@title='Remove all items']")
    public WebElement removeAllItems;

    @FindBy(xpath = "//span[@title='Istanbul, Turkiye']")
    public WebElement istanbulTurkiye;

    @FindBy(xpath = ".//p[contains(@class, 'position-title')]")
    public WebElement jobCard;

    @FindBy(xpath = ".//p[contains(@class, 'position-title')]")
    public WebElement position;

    @FindBy(xpath = ".//span[contains(@class, 'position-department')]")
    public WebElement department;

    @FindBy(xpath = ".//div[contains(@class, 'position-location')]")
    public WebElement location;

    @FindBy(xpath = "//a[text()='View Role']")
    public WebElement viewRoleButton;
}

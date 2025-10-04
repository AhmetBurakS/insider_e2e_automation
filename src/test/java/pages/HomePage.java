package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {

    public HomePage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//a[normalize-space(text())='Company']")
    public WebElement companyMenu;

    @FindBy(xpath = "//a[text()='Careers']")
    public WebElement Careers;

    @FindBy(xpath = "//nav[contains(@class, 'navbar')]")
    public WebElement navigationBar;

    @FindBy(xpath = "//a[normalize-space(text())='Company']")
    public WebElement companyDropdownMenu;

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    public WebElement acceptAllCookiesButton;
}

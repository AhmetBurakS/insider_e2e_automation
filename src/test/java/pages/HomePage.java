package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {

    public HomePage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//a[text()='Login']")
    public WebElement homePageIsOpened;

    @FindBy(xpath = "//a[normalize-space(text())='Company']")
    public WebElement companyMenu;

    @FindBy(xpath = "//a[text()='Careers']")
    public WebElement Careers;
}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LeverApplicationFormPage {

    public LeverApplicationFormPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//a[text()='Apply for this job']")
    public WebElement applyForThisJobButton;

}

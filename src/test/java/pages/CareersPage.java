package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CareersPage {

    public CareersPage(){PageFactory.initElements(Driver.getDriver(),this);}

    // Verify Teams
    @FindBy(xpath = "//h3[text()='Customer Success']")
    public WebElement teamsSection1;

    @FindBy(xpath = "//h3[text()='Sales']")
    public WebElement teamsSection2;

    @FindBy(xpath = "//h3[text()='Product & Engineering']")
    public WebElement teamsSection3;

    @FindBy(xpath = "//a[text()='See all teams']")
    public WebElement seeAllTeamsButton;

    // Verify Locations
    @FindBy(xpath = "//p[text()='New York']")
    public WebElement locationsSection1;

    @FindBy(xpath = "//p[text()='Helsinki']")
    public WebElement locationsSection2;

    @FindBy(xpath = "//i[@class='icon-arrow-right location-slider-next ml-4 text-xsmall text-dark']")
    public WebElement rightSide;
    //3 kez click yap

    @FindBy(xpath = "//h2[text()='Life at Insider']")
    public WebElement lifeAtInsiderSection;

}

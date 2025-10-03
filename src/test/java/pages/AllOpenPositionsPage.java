package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AllOpenPositionsPage {

    public AllOpenPositionsPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy(xpath = "//span[@data-select2-id='3']")
    public WebElement removeAllItemsFilterByLocationDropdownMenu;

    @FindBy(xpath = "//span[@data-select2-id='6']")
    public WebElement removeAllItemsFilterByDepartmentDropdownMenu;

    @FindBy(xpath = "(//span[@class='select2-selection__arrow' and @role='presentation'])[1]")
    public WebElement filterByLocationDropdownMenu;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    public WebElement filterByDepartmentDropdownMenu;

    @FindBy(xpath = "//li[@class='select2-results__option' and text()='Istanbul, Turkiye']")
    public WebElement istanbulTurkiye;

    @FindBy(xpath = "//li[@class='select2-results__option' and text()='Quality Assurance']")
    public WebElement qualityAssurance;

    @FindBy(xpath = "//p[@class='position-title font-weight-bold']")
    public WebElement jobCard;

    @FindBy(xpath = ".//p[contains(@class, 'position-title')]")
    public WebElement position;

    @FindBy(xpath = ".//span[contains(@class, 'position-department')]")
    public WebElement department;

    @FindBy(xpath = ".//div[contains(@class, 'position-location')]")
    public WebElement location;

    @FindBy(xpath = "(//div[contains(@class, 'position-list-item')]//a[text()='View Role'])[1]")
    public WebElement viewRoleButton;
}

package pageobjectlist;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private WebDriver driver;
    private String contentToSearchLocator = "input#cb1-edit";

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectThingToSearch(String thingToSearch){
        WebElement selectSearch = driver.findElement(By.cssSelector(contentToSearchLocator));
        selectSearch.sendKeys(thingToSearch);
        selectSearch.sendKeys(Keys.ENTER);
    }
}

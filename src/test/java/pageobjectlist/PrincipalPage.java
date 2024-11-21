package pageobjectlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrincipalPage {
    private WebDriver driver;
    private String countryLocator = "//*[@id='CO']";

    public PrincipalPage(WebDriver driver){
        this.driver = driver;
    }

    public void chooseCountry(String country){
        WebElement selectCountry = driver.findElement(By.xpath(countryLocator));
        selectCountry.click();
    }
}

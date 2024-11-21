package pageobjectlist;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailsTest {
    private WebDriver driver;
    private String nameInDeailLocator = "h1[class*='ui-pdp-title']";
    private String priceInDeailLocator = "span[class*='andes-money-amount__fraction']";

    public DetailsTest(WebDriver driver){
        this.driver = driver;
    }

    public ProductModel detailOnProduct(){
        ProductModel productModelDetail = new ProductModel();
        productModelDetail.setPrice(driver.findElement(By.cssSelector(priceInDeailLocator)).getText());
        productModelDetail.setName(driver.findElement(By.cssSelector(nameInDeailLocator)).getText());

        return productModelDetail;
    }
}

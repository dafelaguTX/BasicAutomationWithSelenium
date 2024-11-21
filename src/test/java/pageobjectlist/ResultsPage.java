package pageobjectlist;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;

public class ResultsPage {
    private WebDriver driver;
    private String elementListLocator = "ol[class*='ui-search-layout'] li";
    private String priceByLocator = "div[class*='poly-component__price'] span[class*='andes-money-amount andes-money-amount--cents-superscript'] span[class*='andes-money-amount__fraction']";
    private String nameByLocator = "h2";

    private List<WebElement> results;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
        this.results = driver.findElements(By.cssSelector(elementListLocator));
    }

    public WebElement getRandomFromList(){
        Random rand = new Random();
        WebElement randomElement = results.get(rand.nextInt(results.size()));
        return randomElement;
    }

    public ProductModel selectElement(){
        WebElement element = getRandomFromList();
        WebElement expectedPrice = element.findElement(By.cssSelector(priceByLocator));
        WebElement expectedName = element.findElement(By.cssSelector(nameByLocator));

        ProductModel productModel = new ProductModel();
        productModel.setName(expectedName.getText());
        productModel.setPrice(expectedPrice.getText());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        expectedName.click();
        return productModel;
    }
    public void listResults(){
        System.out.println("El numero de elementos es" + results.size());

        for(WebElement result: results){
            System.out.println(".........................");
            System.out.println("El precio es: "+ result.findElement(By.cssSelector(priceByLocator)).getText());
            System.out.println("El nombre es: "+ result.findElement(By.cssSelector(nameByLocator)).getText());
            System.out.println(".........................");
        }
    }
}

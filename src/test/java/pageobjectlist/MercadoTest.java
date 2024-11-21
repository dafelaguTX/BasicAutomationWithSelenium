package pageobjectlist;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MercadoTest {
    private WebDriver driver;
    @BeforeEach
    public void setup(){
        WebDriverManager.operadriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void finalTask(){
        driver.quit();
    }

    @Test
    public  void getElement(){
        driver.navigate().to("https://www.mercadolibre.com");
        driver.manage().window().maximize();
        PrincipalPage home =  new PrincipalPage(driver);
        home.chooseCountry("Colombia");
        SearchPage thingToBuy = new SearchPage(driver);
        thingToBuy.selectThingToSearch("guitarra electrica");
        ResultsPage listGuitar = new ResultsPage(driver);
        listGuitar.listResults();
        listGuitar.getRandomFromList();
        ProductModel selectElement = listGuitar.selectElement();
        DetailsTest guitarDetail = new DetailsTest(driver);
        ProductModel selectElementDetail = guitarDetail.detailOnProduct();
        Assertions.assertAll(
                ()-> assertTrue(selectElement.getName().equals(selectElementDetail.getName())),
                ()-> assertTrue(selectElement.getPrice().equals(selectElementDetail.getPrice()))
        );
    }
}

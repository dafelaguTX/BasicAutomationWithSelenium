import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DriverManagerTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.operadriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void contactFormulary() throws InterruptedException {
        driver.navigate().to("file://C:\\Users\\danie\\Downloads//formcontact.html");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebDriverWait espera = new WebDriverWait(driver,10L);
        try {
            espera.until(ExpectedConditions.presenceOfElementLocated(By.id("no esta")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        FluentWait esperaFluida = new FluentWait(driver);
        esperaFluida.withTimeout(3,TimeUnit.SECONDS);
        esperaFluida.pollingEvery(2,TimeUnit.SECONDS);
        esperaFluida.ignoring(NoSuchElementException.class);
        try{
            esperaFluida.until(ExpectedConditions.presenceOfElementLocated(By.id("yo tampoco")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        WebElement customerService = driver.findElement(By.id("id_contact"));
        Select seleccionador = new Select(customerService);
        seleccionador.selectByVisibleText("Webmaster");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("pepito@gmail.com");

        WebElement orderReference = driver.findElement(By.cssSelector("input#id_order"));
        orderReference.sendKeys("Pan");

        WebElement message = driver.findElement(By.xpath("//textarea[@id='message']"));
        message.sendKeys("Hola, ten un gran día");

        WebElement submitMessage = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submitMessage.click();

        WebElement finalMessage = driver.findElement(By.xpath("//p[@class='alert alert-success']"));

        assertTrue(finalMessage.getText().equals("Your message has been successfully sent to our team."));

        Thread.sleep(5000);
    }
}

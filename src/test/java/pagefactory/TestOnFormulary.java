package pagefactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestOnFormulary {
    WebDriver driver;
    @BeforeEach
    public void setup(){
        WebDriverManager.operadriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void sendACompleteFormulary(){
        driver.navigate().to("file://C:\\Users\\danie\\Downloads//formcontact.html");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        ContactFormularyWithPageFactory formulary = new ContactFormularyWithPageFactory(driver);
        formulary.selectCustomerService("Webmaster");
        formulary.enterEmail("pepito@gmail.com");
        formulary.enterOrderReference("Pan");
        formulary.enterMessage("Hola, ten un gran dia");
        formulary.sendMessage();
        formulary.compareTwoMessages("Your message has been successfully sent to our team.");
    }
}

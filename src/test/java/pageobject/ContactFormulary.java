package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactFormulary {
    private WebDriver driver;
    private String customerServiceLocator = "id_contact";
    private String emailLocator = "email";
    private String orderReferenceLocator = "input#id_order";
    private String messageLocator = "//textarea[@id='message']";
    private String submitMessageLocator = "//button[@class='btn btn-primary']";
    private String finalMessageLocator = "//p[@class='alert alert-success']";

    public ContactFormulary(WebDriver driver){
        this.driver = driver;
    }

    public void selectCustomerService(String opcion){
        WebElement customerService = driver.findElement(By.id(customerServiceLocator));
        Select elementSelector = new Select(customerService);
        elementSelector.selectByVisibleText(opcion); //"Webmaster"
    }

    public void enterEmail(String emailAddress){
        WebElement email = driver.findElement(By.id(emailLocator));
        email.sendKeys(emailAddress); //"pepito@gmail.com"
    }

    public void enterOrderReference(String order){
        WebElement orderReference = driver.findElement(By.cssSelector(orderReferenceLocator));
        orderReference.sendKeys(order); //"Pan"
    }

    public void enterMessage(String messageToComunicate){
        WebElement message = driver.findElement(By.xpath(messageLocator));
        message.sendKeys(messageToComunicate); //"Hola, ten un gran dia"
    }

    public void sendMessage(){
        WebElement submitMessage = driver.findElement(By.xpath(submitMessageLocator));
        submitMessage.click();
    }

    public void compareTwoMessages(String messageToCompare){
        WebElement finalMessage = driver.findElement(By.xpath(finalMessageLocator));
        assertTrue(finalMessage.getText().equals(messageToCompare)); //"Your message has been successfully sent to our team."
    }
}

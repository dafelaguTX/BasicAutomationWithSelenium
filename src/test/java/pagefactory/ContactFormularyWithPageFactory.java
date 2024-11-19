package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactFormularyWithPageFactory {
    private WebDriver driver;
    @FindBy(id="id_contact")
    private WebElement customerServiceLocator;
    @FindBy(id = "email")
    private WebElement emailLocator;
    @FindBy(css = "input#id_order")
    private WebElement orderReferenceLocator;
    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement messageLocator;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement submitMessageLocator;
    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement finalMessageLocator;

    public ContactFormularyWithPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectCustomerService(String opcion){
        Select elementSelector = new Select(customerServiceLocator);
        elementSelector.selectByVisibleText(opcion); //"Webmaster"
    }

    public void enterEmail(String emailAddress){
        emailLocator.sendKeys(emailAddress); //"pepito@gmail.com"
    }

    public void enterOrderReference(String order){
        orderReferenceLocator.sendKeys(order); //"Pan"
    }

    public void enterMessage(String messageToComunicate){
        messageLocator.sendKeys(messageToComunicate); //"Hola, ten un gran dia"
    }

    public void sendMessage(){
        submitMessageLocator.click();
    }

    public void compareTwoMessages(String messageToCompare){
        assertTrue(finalMessageLocator.getText().equals(messageToCompare)); //"Your message has been successfully sent to our team."
    }
}

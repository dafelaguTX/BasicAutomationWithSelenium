import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest {
    private  WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\danie\\Documents\\Cursos\\Selenium\\BasicAutomationWithSelenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void atTheEnd(){
        driver.quit();
    }

    @Test
    public void navigateToGoogle() throws InterruptedException {
        driver.get("https://www.netflix.com/browse");
        driver.findElement(By.cssSelector("input[autocomplete='email']")).sendKeys("danielfernandolargo@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Dorlildan7896");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void navigateToFacebook() throws InterruptedException {
        driver.get("https://www.facebook.com");
        driver.findElement(By.id("email")).sendKeys("algo");
        driver.findElement(By.xpath("//input[contains(@placeholder,'Contrase')]")).sendKeys("password");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        Thread.sleep(3000);
    }

    @Test
    public void navigateToMercadoLibre() throws InterruptedException {
        driver.get("https://www.mercadolibre.com");
        String localizacion = String.valueOf(driver.findElement(By.xpath("//*[contains(text(),'Colombia')]")).getLocation());
        System.out.println("Esta es la localizacion del elemento" + localizacion);
        String texto = driver.findElement(By.xpath("//nav/ul/li/a[@id='CO']")).getText();
        System.out.println("Texto en campo: " + texto);
        String textoEnId = driver.findElement(By.xpath("//*[@id='CO']")).getText();
        System.out.println("Texto en campo: " + textoEnId);
        String textoCorrecto = "Colombia";
        driver.findElement(By.cssSelector("li.ml-site-mco")).click();
        assertTrue(textoEnId.equals(textoCorrecto),"Se esperaba que contenga Colombia");
        Thread.sleep(3000);
    }

    @Test
    public void navigateToAutomationExercise() throws InterruptedException {
        driver.get("https://automationexercise.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Subscription')]")));
        //driver.manage().window().fullscreen();
        driver.findElement(By.xpath("//*[@id='susbscribe_email']")).click();
        driver.findElement(By.xpath("//*[@id='susbscribe_email']")).sendKeys("email@email.com");
        driver.findElement(By.xpath("//button[@id='subscribe']")).click();
        Thread.sleep(30000);
        //assertTrue(driver.findElement(By.id("")).isDisplayed());
    }

    @Test
    public void navigateToSelectorGuru99() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        WebElement selector = driver.findElement(By.name("country"));
        Select selectOnWeb = new Select(selector);
        selectOnWeb.selectByVisibleText("ARUBA");
        Thread.sleep(3000);
        selectOnWeb.selectByIndex(3);
        Thread.sleep(3000);
        selectOnWeb.selectByIndex(0);
        Thread.sleep(3000);
        selectOnWeb.selectByValue("BAHRAIN");
    }

    @Test
    public void implicitWaitExample() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://demo.guru99.com/test/newtours/register.php");

        Instant start = Instant.now();

        try{
            WebElement selector = driver.findElement(By.name("world"));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo primera busqueda: " +timeElapsed.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
        }

        start = Instant.now();

        try{
            WebElement selector = driver.findElement(By.name("worldNot"));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo segunda bsuqueda: " +timeElapsed.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
        }
    }
}

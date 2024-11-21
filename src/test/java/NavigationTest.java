import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danie\\Documents\\Cursos\\Selenium\\BasicAutomationWithSelenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void atTheEnd() {
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
        assertTrue(textoEnId.equals(textoCorrecto), "Se esperaba que contenga Colombia");
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

        try {
            WebElement selector = driver.findElement(By.name("world"));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo primera busqueda: " + timeElapsed.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
        }

        start = Instant.now();

        try {
            WebElement selector = driver.findElement(By.name("worldNot"));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo segunda bsuqueda: " + timeElapsed.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
        }
    }

    @Test
    public void explicitWaitExample() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get("https://demo.guru99.com/test/newtours/register.php");

        Instant start = Instant.now();

        try {
            WebElement selector = driver.findElement(By.name("nop"));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo primera busqueda: " + timeElapsed.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
            System.out.println("En esta primera busqueda se usa la espera implicita");
        }
        Instant startSecondTime = Instant.now();
        WebDriverWait explicitWait = new WebDriverWait(driver, 7L);

        try {
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("yeap")));
        } catch (Exception e) {
            Instant end = Instant.now();
            Duration timeElapsedSecondTime = Duration.between(startSecondTime, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo segunda busqueda: " + timeElapsedSecondTime.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
            System.out.println("En esta segunda busqueda se usa la espera explicita, recordar que el programa va preguntando cada x ms");
            System.out.println("si el elemento esta disponible, por eso quizas se pase del tiempo");
            System.out.println("Si la espera explicita es menor a la implicita y no encuentra el elemento, continua");
            System.out.println("hasta completar la espera implicita");
        }
    }

    @Test
    public void fluidWaitExample() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/newtours/register.php");

        FluentWait fluentTime = new FluentWait(driver);

        fluentTime.withTimeout(Duration.ofSeconds(10L));
        fluentTime.pollingEvery(Duration.ofSeconds(3L));
        fluentTime.ignoring(NoSuchElementException.class);

        Instant start = Instant.now();
        try {
            fluentTime.until(ExpectedConditions.presenceOfElementLocated(By.name("fansic")));
        } catch (Exception e) {
            System.out.println("---Excepcion: " + e.getMessage());
            Instant end = Instant.now();
            Duration timeElapsedSecondTime = Duration.between(start, end);
            System.out.println("---------------------------");
            System.out.println("Tiempo segunda busqueda: " + timeElapsedSecondTime.getSeconds() + " segundos");
            System.out.println("Ten en cuenta que si no pones esperas, se espera 0 segundos");
            System.out.println("Espera fluida con 10 seg de espera maxima, pregunta por el elemento cada 3 seg");
            System.out.println("e ignora la excepcion de elemento no encontrado");
            System.out.println("Si no se define excepcion a ignorar espera 0 seg");
            System.out.println("Revisar que el importe de la clase de excepcion sea el de selenium sino, no va a esperar lo indicado");
        }

    }

    @Test
    public void searchOnList() {
        driver.navigate().to("https://www.mercadolibre.com");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id='CO']")).click();

        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.cssSelector("input#cb1-edit"));
        search.sendKeys("guitarra electrica");
        search.sendKeys(Keys.ENTER);

        List<WebElement> results = driver.findElements(By.cssSelector("ol[class*='ui-search-layout'] li"));
        System.out.println("el numero de elementos es" + results.size());

        By priceBy = By.cssSelector("div[class*='poly-component__price'] span[class*='andes-money-amount andes-money-amount--cents-superscript'] span[class*='andes-money-amount__fraction']");
        By nameBy = By.cssSelector("h2");

        for(WebElement result: results){
            System.out.println(".........................");
            System.out.println("el precio es: "+ result.findElement(priceBy).getText());
            System.out.println("el nombre es: "+ result.findElement(nameBy).getText());
            System.out.println(".........................");
        }

        Random rand = new Random();
        WebElement randomElement = results.get(rand.nextInt(results.size()));
        WebElement expectedPriceLocator = randomElement.findElement(priceBy);
        WebElement expectedNameLocator = randomElement.findElement(nameBy);

        String expectedPrice = expectedPriceLocator.getText();
        String expectedName = expectedNameLocator.getText();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", randomElement);

        expectedNameLocator.click();
        By nameInDeailBy = By.cssSelector("h1[class*='ui-pdp-title']");
        By priceInDeailBy = By.cssSelector("span[class*='andes-money-amount__fraction']");

        String actualPrice = driver.findElement(priceInDeailBy).getText();
        String actualName = driver.findElement(nameInDeailBy).getText();

        System.out.println("\nexpected: "+ expectedPrice + " actual: "+ actualPrice);
        System.out.println("\nactual: "+ expectedName + " actual: "+ actualName);
        Assertions.assertAll(
                ()-> assertTrue(expectedPrice.equals(actualPrice),"No coinciden los precios"),
                ()-> assertTrue(expectedName.contains(actualName),"No coinciden los nombres")
        );
    }
}

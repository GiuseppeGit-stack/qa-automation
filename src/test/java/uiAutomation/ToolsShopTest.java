//test Selenium
package uiAutomation;

import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.Assert.assertTrue;

@Epic("ToolsShop UI Test")
@Feature("Ricerca prodotti")
public class ToolsShopTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    @Step("Setup del test: avvio del browser e configurazione")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "G:\\progetti\\pokemon_app\\pokemonapi\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Story("Ricerca di un prodotto con nome 'Pliers'")
    @Description("Verifica che la ricerca di 'Pliers' restituisca solo prodotti pertinenti")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchPliers() {
        driver.get("https://practicesoftwaretesting.com/");

        // 1️⃣ Trova il campo di ricerca
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-query")));
        searchBox.sendKeys("Pliers");

        // 2️⃣ Trova e clicca il pulsante di ricerca
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='search-submit']")));
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        searchButton.click();

        // 3️⃣ Attendi che i risultati vengano caricati
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("h5[data-test='product-name']"), 2));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 4️⃣ Verifica i risultati
        List<WebElement> results = driver.findElements(By.cssSelector("h5[data-test='product-name']"));
        System.out.println("🔍 Prodotti trovati:");
        for (WebElement result : results) {
            System.out.println("📌 " + result.getText());
        }

        long countWithPliers = results.stream()
                .filter(result -> result.getText().toLowerCase().contains("pliers"))
                .count();

        assertTrue("🚨 Non tutti i risultati contengono 'Pliers'!", countWithPliers >= results.size() * 0.9);
    }

    @After
    @Step("Chiudere il browser dopo il test")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

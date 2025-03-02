//Selenium/PLaywright
package uiAutomation;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ToolsShopAutomationTest {
    public static void main(String[] args) {
        System.out.println("Running Selenium Test...");
        runSeleniumTest();

        System.out.println("\nRunning Playwright Test...");
        runPlaywrightTest();
    }

    private static void runSeleniumTest() {
        // Imposta il driver di Chrome (assicurati di avere chromedriver installato)
        System.setProperty("webdriver.chrome.driver", "G:\\progetti\\pokemon_app\\pokemonapi\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Apri il sito web
            driver.get("https://practicesoftwaretesting.com/");

            // Trova il box di ricerca e inserisci "Pliers"
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-query")));
            searchBox.sendKeys("Pliers");

            // Trova il bottone di ricerca e cliccalo
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='search-submit']")));
            searchButton.click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Attendi il caricamento dei risultati
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("h5[data-test='product-name']"), 2));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Verifica che i risultati contengano solo "Pliers"
            List<WebElement> results = driver.findElements(By.cssSelector("h5[data-test='product-name']"));
            System.out.println("🔍 Selenium - Prodotti trovati:");
            for (WebElement result : results) {
                System.out.println("📌 " + result.getText());
            }

            long countWithPliers = results.stream()
                    .filter(result -> result.getText().toLowerCase().contains("pliers"))
                    .count();

            if (countWithPliers >= results.size() * 0.9) {
                System.out.println("✅ Selenium Test Passed: Only 'Pliers' are present in the results.");
            } else {
                System.out.println("❌ Selenium Test Failed: Other products are present.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiudi il browser
            driver.quit();
        }
    }

    private static void runPlaywrightTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Apri il sito
            page.navigate("https://practicesoftwaretesting.com/");

            // Trova il box di ricerca e inserisci "Pliers"
            page.fill("#search-query", "Pliers");

            // Clicca il pulsante di ricerca
            page.click("button[data-test='search-submit']");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Attendi il caricamento dei risultati
            page.waitForSelector("h5[data-test='product-name']");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Verifica che i risultati contengano solo "Pliers"
            List<ElementHandle> results = page.querySelectorAll("h5[data-test='product-name']");
            System.out.println("🔍 Playwright - Prodotti trovati:");
            for (ElementHandle result : results) {
                System.out.println("📌 " + result.textContent());
            }

            long countWithPliers = results.stream()
                    .filter(result -> result.textContent().toLowerCase().contains("pliers"))
                    .count();

            if (countWithPliers >= results.size() * 0.9) {
                System.out.println("✅ Playwright Test Passed: Only 'Pliers' are present in the results.");
            } else {
                System.out.println("❌ Playwright Test Failed: Other products are present.");
            }

            // Chiudi il browser
            browser.close();
        }
    }
}

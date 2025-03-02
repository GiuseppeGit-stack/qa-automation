package runner;  // Assicurati che sia il package corretto

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // ✅ Percorso corretto della cartella features
        glue = "stepDefinition",  // ✅ Percorso corretto degli step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true,  // ✅ Per output leggibile
        publish = true      // ✅ Attiva la pubblicazione automatica su Cucumber Reports
)
public class CucumberTestRunner {
}

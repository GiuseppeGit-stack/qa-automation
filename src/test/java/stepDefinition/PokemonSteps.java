package stepDefinition;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Random;

public class PokemonSteps {
    private String baseUri = "https://pokeapi.co/api/v2";
    private String pokemonName;
    private String abilityName;

    @Given("I make a call to the Pokémon API to retrieve all Pokémon")
    @Step("Effettua una chiamata API per ottenere tutti i Pokémon")
    public void getAllPokemon() {
        Response response = RestAssured.get(baseUri + "/pokemon/");
        Assert.assertEquals(200, response.getStatusCode());

        List<String> results = response.jsonPath().getList("results.name");
        Random rand = new Random();
        pokemonName = results.get(rand.nextInt(results.size()));

        Allure.addAttachment("Pokemon API Response", "application/json", response.asString());
        Allure.addAttachment("Pokemon Selezionato", "text/plain", pokemonName);
    }

    @When("I pick a random Pokémon from the results")
    @Step("Seleziona un Pokémon casuale dalla lista")
    public void pickRandomPokemon() {
        System.out.println("📌 Pokémon selezionato: " + pokemonName);
        Allure.addAttachment("Pokémon scelto", "text/plain", pokemonName);
    }

    @And("I fetch details about this Pokémon")
    @Step("Recupera i dettagli del Pokémon selezionato")
    public void getPokemonDetails() {
        Response response = RestAssured.get(baseUri + "/pokemon/" + pokemonName);
        Assert.assertEquals(200, response.getStatusCode());

        List<String> abilities = response.jsonPath().getList("abilities.ability.name");
        abilityName = abilities.get(0);
        System.out.println("🔹 Abilità selezionata: " + abilityName);

        Allure.addAttachment("Dettagli Pokémon", "application/json", response.asString());
        Allure.addAttachment("Abilità scelta", "text/plain", abilityName);
    }

    @And("I fetch one of its abilities")
    @Step("Recupera i dettagli di una abilità del Pokémon")
    public void getAbilityDetails() {
        Response response = RestAssured.get(baseUri + "/ability/" + abilityName);
        Assert.assertEquals(200, response.getStatusCode());

        List<String> pokemonList = response.jsonPath().getList("pokemon.pokemon.name");
        Assert.assertTrue(pokemonList.contains(pokemonName));

        Allure.addAttachment("Dettagli Abilità", "application/json", response.asString());
    }

    @Then("The ability name should match the search parameter")
    @Step("Verifica che il nome dell'abilità corrisponda a quello cercato")
    public void validateAbility() {
        Response response = RestAssured.get(baseUri + "/ability/" + abilityName);
        Assert.assertEquals(200, response.getStatusCode());

        String fetchedAbility = response.jsonPath().getString("name");
        Assert.assertEquals(abilityName, fetchedAbility);

        Allure.addAttachment("Convalida Abilità", "application/json", response.asString());
    }

    @And("The Pokémon name should be listed in the ability details")
    @Step("Verifica che il Pokémon sia elencato nei dettagli dell'abilità")
    public void validatePokemonInAbility() {
        System.out.println("✅ Test completato con successo!");
        Allure.addAttachment("Risultato", "text/plain", "Test completato con successo!");
    }
}

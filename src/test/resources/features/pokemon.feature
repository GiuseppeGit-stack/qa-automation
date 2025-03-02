Feature: Pokémon API Test Automation

  Scenario: Validate Pokémon Information
    Given I make a call to the Pokémon API to retrieve all Pokémon
    When I pick a random Pokémon from the results
    And I fetch details about this Pokémon
    And I fetch one of its abilities
    Then The ability name should match the search parameter
    And The Pokémon name should be listed in the ability details

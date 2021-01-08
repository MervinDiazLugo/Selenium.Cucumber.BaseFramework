@example
Feature: exampleScenaryKeys
  Optional description of the feature

  @test
  Scenario: Save In Scenario Context
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I click in element Email Confirmacion
    Then Assert if Email Error contains text Este correo electrónico ya está conectado a una cuenta. Inicia sesión.



  @test
  @Security
  Scenario: Recover data from Scenario Context
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I click in element Email Confirmacion
    And I set Email with key value Already.text
    Then Save as Scenario Context key MiVariable with value MiValor

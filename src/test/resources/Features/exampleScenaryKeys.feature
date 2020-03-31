Feature: exampleScenaryKeys
  Optional description of the feature

  @test
  Scenario: Save In Scenario Context
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    Then Assert if Email Error contains text Lo sentimos, este correo ya está registrado.
    And I scroll to element Already
    And I Save text of Already as Scenario Context
    Then Save as Scenario Context key MiVariable with value MiValor



  @test
  Scenario: Recover data from Scenario Context
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    And I set Email with key value Already.text
    Then Save as Scenario Context key MiVariable with value MiValor

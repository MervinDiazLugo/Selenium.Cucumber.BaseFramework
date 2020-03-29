Feature: Examples
  Optional description of the feature

 @test
  Scenario: Get Sites
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    Then I take screenshot: Feature_0001

  @test
  Scenario: Assert contain text
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    Then Assert if Email Error contains text Lo sentimos, este correo ya está registrado.

  @test
  Scenario: Assert not contain text
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    Then Check if Email Error NOT contains text COVID-19

  @test
  Scenario: Take a ScreenShot
    Given I am in App main site
    And I attach a Screenshot to Report


  @test
  Scenario: Elements is/is not present
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    Then Check if Email Error NOT is Displayed
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    Then Assert if Email Error is Displayed

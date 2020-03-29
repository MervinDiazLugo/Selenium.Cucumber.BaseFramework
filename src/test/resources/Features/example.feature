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
  Scenario: Get other Sites
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I clic in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I clic in element Email Confirmacion
    Then Assert if Email Error contains text Lo sentimos, este correo ya está registrado.
    Then Element Email Error NOT contains text COVID

  @test
  Scenario: Take a ScreenShot
    Given I am in App main site
    And I attach a Screenshot to Report


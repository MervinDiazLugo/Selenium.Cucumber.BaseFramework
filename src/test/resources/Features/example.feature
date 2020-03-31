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


  @test
  Scenario: Handle Dropdown
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I set text febrero in dropdown Mes de Nacimiento
    And I attach a Screenshot to Report
    And I set index 03 in dropdown Mes de Nacimiento
    And I attach a Screenshot to Report

  @frames
  Scenario: Handle various functions
      Given I navigate to https://chercher.tech/practice/frames-example-selenium-webdriver
      Then I load the DOM Information frames.json
      Then I take screenshot: Feature_Various1
      And I switch to Frame: Frame2
      And I set text Avatar in dropdown Frame2 Select
      And I switch to parent frame
      And I switch to Frame: Frame1
      And I set element Frame1 input with text Esto es una prueba
      Then I switch to Frame: Frame3
      And I check the checkbox having Frame3 input
      Then I take screenshot: Feature_Various

  @test
  Scenario: Clic in JS elements
    Given I navigate to https://www.amazon.es/
    Then I load the DOM Information Amazon.json
    And I click in JS element Sobre Amazon
    Then I take screenshot: Feature_Various


  @test
  Scenario: Scroll to
    Given I navigate to https://www.amazon.es/
    Then I load the DOM Information Amazon.json
    And I scroll to element Sobre Amazon
    Then I take screenshot: Feature_Various
    #And I scroll to top of page

  @test
  Scenario: wait elements
    Given I navigate to https://www.amazon.es/
    Then I load the DOM Information Amazon.json
    And I wait for element Sobre Amazon to be present
    #And I wait element Sobre Amazon to be visible
    Then I take screenshot: Feature_Various
    #And I scroll to top of page


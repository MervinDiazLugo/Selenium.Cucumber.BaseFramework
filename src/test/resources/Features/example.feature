Feature: Examples
  Optional description of the feature

  Background:
    Given I set UserEmail value in Data Scenario

 @test
  Scenario: Get Sites
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    Then I take screenshot: Feature_0001

  @test
  Scenario: Assert contain text
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I click in element Email Confirmacion
    Then Assert if Email Error contains text Lo sentimos, este correo ya está registrado.
    Then Assert if Email Error is equal to Lo sentimos, este correo ya está registrado.

  @test
  Scenario: Assert not contain text
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    And I set element Email with text mervindiazlugo@gmail.com
    And I click in element Email Confirmacion
    Then Check if Email Error NOT contains text COVID-19

  @test
  Scenario: Take a ScreenShot
    Given I am in App main site
    And I attach a Screenshot to Report
    And I take screenshot: HolyScreen


  @test
  Scenario: Elements is/is not present
    Given I am in App main site
    Then I load the DOM Information Spotify_registro.json
    And I click in element Email
    Then Check if Email Error is NOT Displayed
    And I set element Email with text mervindiazlugo@gmail.com
    And I click in element Email Confirmacion
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
    And I scroll to top of page

  @test
  Scenario: wait elements
    Given I navigate to https://www.amazon.es/
    Then I load the DOM Information Amazon.json
    And I wait for element Sobre Amazon to be present
    And I wait element Sobre Amazon to be visible
    Then I take screenshot: Feature_Various
    And I scroll to top of page

  @test
  Scenario: Open New Tab
    Given I navigate to https://www.amazon.es/
    And I open new tab with URL https://chercher.tech/practice/frames-example-selenium-webdriver
    Then I switch to new window
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame2
    And I set text Avatar in dropdown Frame2 Select
    When I go to Principal window
    Then I load the DOM Information Amazon.json
    And I wait for element Sobre Amazon to be present


  @test
  Scenario: Handle Alerts
    Given I navigate to https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame4 Alerta
    And I click in element Alert
    And I wait 8 seconds
    Then I accept alert
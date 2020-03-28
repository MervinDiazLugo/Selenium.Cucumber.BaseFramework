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
    Then I take screenshot: Feature_0001
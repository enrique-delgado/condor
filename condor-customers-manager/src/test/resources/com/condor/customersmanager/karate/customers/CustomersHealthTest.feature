Feature: Customers API Healthcheck

  Background:
    * url baseUrl

  Scenario: Health endpoint is up
    Given path 'actuator', 'health'
    When method get
    Then status 200
    And match response.status == 'UP'

  Scenario: Info endpoint is available
    Given path 'actuator', 'info'
    When method get
    Then status 200
    And match response == '#object'

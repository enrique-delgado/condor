Feature: Customers API Load Test

  Background:
    * url baseUrl
    * configure headers = { Content-Type: 'application/json' }
    * def randomNumeric =
    """
    function(n){
      var s = '';
      for (var i = 0; i < n; i++) s += Math.floor(Math.random() * 10);
      return s;
    }
    """

  Scenario Outline: Create multiple customers under load
    * def identification = randomNumeric(10)
    Given path 'customers'
    And request { firstName: '<first>', lastName: '<last>', gender: 'M', birthDate: '1990-01-01', identification: '#(identification)', address: 'Load Street', phone: '3000000000', password: 'loadtest', active: true }
    When method post
    Then status 200

    Examples:
      | first   | last   | identification   |
      | Load1   | Test   | L0001            |
      | Load2   | Test   | L0002            |
      | Load3   | Test   | L0003            |
      | Load4   | Test   | L0004            |
      | Load5   | Test   | L0005            |

  Scenario: Get all customers under load
    Given path 'customers'
    When method get
    Then status 200
    And match response == '#[]'

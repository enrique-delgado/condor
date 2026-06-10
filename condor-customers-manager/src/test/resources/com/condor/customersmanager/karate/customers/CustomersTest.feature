Feature: Customers CRUD API

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

  Scenario: Get all customers
    Given path 'customers'
    When method get
    Then status 200
    And match response == '#[]'

  Scenario: Create a new customer
    * def identification = randomNumeric (10)
    Given path 'customers'
    And request { firstName: 'Maria', lastName: 'Lopez', gender: 'F', birthDate: '1992-03-15', identification: '#(identification)', address: 'Calle 123', phone: '3001234567', password: 'secret123', active: true }
    When method post
    Then status 200
    And match response.firstName == 'Maria'

  Scenario: Get customer by id not found
    Given path 'customers', 999
    When method get
    Then status 404
    And match response.message contains 'Customer with id 999 was not found'

  Scenario: Update customer
    * def identification = randomNumeric(10)
    Given path 'customers', 1
    And request { firstName: 'Carlitos', lastName: 'Perez', gender: 'M', birthDate: '1988-07-20', identification: '#(identification)', address: 'Av Siempre Viva', phone: '3119876543', password: '12345678', active: true }
    When method put
    Then status 200
    And match response.firstName == 'Carlitos'


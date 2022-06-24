@SERVICES
Feature: Testing the Services features on the best buy application

  Scenario: Verify if best buy application services can be accessed by users
    When User sends a GET request to the services endpoint
    Then User must get back a valid status code 200

  Scenario:Create a new service & verify if the service is added
    When I create a new service by providing the information name
    Then I verify that the new service is created

  Scenario:Updating the service created and verify it is updated with status 200
    When I update the service with name
    Then I verify that the service information is updated

  Scenario:Deleting the service created and verify it is deleted
    When I delete the service created with id
    Then I verify that the service is deleted and get the status 404


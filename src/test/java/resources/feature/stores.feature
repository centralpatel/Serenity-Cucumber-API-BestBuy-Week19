@STORES
Feature: Testing the Stores features on the best buy application

  Scenario: Verify if best buy application stores can be accessed by users
    When User sends a GET request to the stores endpoint
    Then User must get back valid status code is 200

  Scenario:Create a new store & verify if the store is added
    When I create a new store by providing the information name type address address city state zip lat lng and hours
    Then I verify that the new store is created

  Scenario:Updating the store created and verify it is updated with status 200
    When I update the store with name
    Then I verify that the store information is updated

  Scenario:Deleting the store created and verify it is deleted
    When I delete the store created with id
    Then I verify that the store is deleted and get the status 404


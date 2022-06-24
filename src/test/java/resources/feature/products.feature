@PRODUCTS
Feature: Testing the Products features on the best buy application

  Scenario: Verify if best buy application Products can be accessed by users
    When User sends a GET request to the Products endpoint
    Then User must get back valid status code 200

  Scenario:Create a new product & verify if the product is added
    When I create a new product by providing the information name type price shipping upc description manufacturer model url and image
    Then I verify that the new product is created

  Scenario:Updating the product created and verify it is updated with status 200
    When I update the product with name type and price
    Then I verify that the product information is updated

  Scenario:Deleting the product created and verify it is deleted
    When I delete the product created with id
    Then I verify that the product is deleted and get the status 404


package com.bestbuy.bestbuyinfo.steps;

import com.bestbuy.bestbuyinfo.ProductsSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by HariKrishna
 */
public class ProductsStepdefs {
    static String name = "Rechargeable Battery" + TestUtils.getRandomName();
    static String type = "AA Battery" + TestUtils.getRandomName();
    static Double price = 10.99;
    static Integer shipping = 5;
    static String upc = "98548745496";
    static String description = "Rechargeable energy Battery";
    static String manufacturer = "Duracell";
    static String model = TestUtils.getRandomName();
    static String url = "https://www.bestbuy.com/site/Energizer";
    static String image = "http://img.bystatic.com/BestBuy_US/images/products/4853/48000_sa.jpg";
    static int productId;
    static ValidatableResponse response;

    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a GET request to the Products endpoint$")
    public void userSendsAGETRequestToTheProductsEndpoint() {
        response=productsSteps.getAllProducts();
    }

    @Then("^User must get back valid status code (\\d+)$")
    public void userMustGetBackValidStatusCode(int exp) {
        response.assertThat().statusCode(exp);
    }

    @When("^I create a new product by providing the information name type price shipping upc description manufacturer model url and image$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceShippingUpcDescriptionManufacturerModelUrlAndImage() {
        response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
    }

    @Then("^I verify that the new product is created$")
    public void iVerifyThatTheNewProductIsCreated() {
        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        productId = (int) productMap.get("id");
        System.out.println("Created Product ID: " + productId);
    }

    @When("^I update the product with name type and price$")
    public void iUpdateTheProductWithNameTypeAndPrice() {
        name = name + "_updated";
        productsSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);
    }

    @Then("^I verify that the product information is updated$")
    public void iVerifyThatTheProductInformationIsUpdated() {
        HashMap<String, Object> value = productsSteps.getProductInfoByName(name);
        Assert.assertThat(value, hasValue(name));
    }

    @When("^I delete the product created with id$")
    public void iDeleteTheProductCreatedWithId() {
        productsSteps.deleteProduct(productId).statusCode(200);
    }


    @Then("^I verify that the product is deleted and get the status (\\d+)$")
    public void iVerifyThatTheProductIsDeletedAndGetTheStatus(int exp) {
        productsSteps.getProductById(productId).statusCode(404);
    }
}

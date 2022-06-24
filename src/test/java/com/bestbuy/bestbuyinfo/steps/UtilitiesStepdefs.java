package com.bestbuy.bestbuyinfo.steps;

import com.bestbuy.bestbuyinfo.UtilitiesSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

/**
 * Created by HariKrishna
 */
public class UtilitiesStepdefs {
    static ValidatableResponse response;
    @Steps
    UtilitiesSteps utilitiesSteps;

    @Given("^I am on homepage of utilities endpoint$")
    public void iAmOnHomepageOfUtilitiesEndpoint() {

    }

    @When("^I send a GET request to check version of the utilities endpoint$")
    public void iSendAGETRequestToCheckVersionOfTheUtilitiesEndpoint() {
        response = utilitiesSteps.gettingVersion().statusCode(200);
    }

    @Then("^I must get a valid response code (\\d+) from utilities endpoint$")
    public void iMustGetAValidResponseCodeFromUtilitiesEndpoint(int exp) {
        response.assertThat().statusCode(exp);
    }

    @When("^I send a GET request to check health of the utilities endpoint$")
    public void iSendAGETRequestToCheckHealthOfTheUtilitiesEndpoint() {
        response = utilitiesSteps.gettingHealthCheck().statusCode(200);
    }
}

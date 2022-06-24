package com.bestbuy.bestbuyinfo.steps;

import com.bestbuy.bestbuyinfo.ServicesSteps;
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
public class ServicesStepdefs {
    static String name = "Duracell" + TestUtils.getRandomValue();
    static Integer serviceId;
    static ValidatableResponse response;

    @Steps
    ServicesSteps servicesSteps;

    @When("^User sends a GET request to the services endpoint$")
    public void userSendsAGETRequestToTheServicesEndpoint() {
        response=servicesSteps.getAllServices();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int exp) {
        response.log().all().assertThat().statusCode(exp);
    }

    @When("^I create a new service by providing the information name$")
    public void iCreateANewServiceByProvidingTheInformationName() {
        response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
    }

    @Then("^I verify that the new service is created$")
    public void iVerifyThatTheNewServiceIsCreated() {
        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        serviceId = (int) value.get("id");
        System.out.println("Created Service Id: "+serviceId);
    }

    @When("^I update the service with name$")
    public void iUpdateTheServiceWithName() {
        name = name + " (Updated)";
        servicesSteps.updateService(name, serviceId).log().all().statusCode(200);
    }

    @Then("^I verify that the service information is updated$")
    public void iVerifyThatTheServiceInformationIsUpdated() {
        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        Assert.assertThat(value, hasValue(name));
    }

    @When("^I delete the service created with id$")
    public void iDeleteTheServiceCreatedWithId() {
        servicesSteps.deleteService(serviceId).statusCode(200);
    }

    @Then("^I verify that the service is deleted and get the status (\\d+)$")
    public void iVerifyThatTheServiceIsDeletedAndGetTheStatus(int exp) {
        servicesSteps.getServiceById(serviceId).assertThat().statusCode(exp);
    }
}

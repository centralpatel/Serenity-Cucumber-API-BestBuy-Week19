package com.bestbuy.bestbuyinfo.steps;

import com.bestbuy.bestbuyinfo.CategoriesSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


public class CategoriesStepDef extends TestBase {
    static String name = "Toys" + TestUtils.getRandomName();
    static String id = TestUtils.getRandomName();
    static String categoryId;
    static ValidatableResponse response;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^User sends a GET request to the categories endpoint$")
    public void userSendsAGETRequestToTheCategoriesEndpoint() {
        response=categoriesSteps.getAllCategory();
    }

    @Then("^User must get back a valid status code is (\\d+)$")
    public void userMustGetBackAValidStatusCodeIs(int exp) {
    response.assertThat().statusCode(exp);
    }

    @When("^I create a new category by providing the information name and id$")
    public void iCreateANewCategoryByProvidingTheInformationNameAndId() {
    response=categoriesSteps.createCategory(name,id);
    response.log().all().statusCode(201);
    categoryId=response.extract().path("id");
    System.out.println("Category Id is: " + categoryId);
    }

    @Then("^I verify that the Category with name and id is created$")
    public void iVerifyThatTheCategoryWithNameAndIdIsCreated() {
        HashMap<String,Object> categoryMap=categoriesSteps.getCategoryInfoByName(categoryId);
        Assert.assertThat(categoryMap,hasValue(name));
        System.out.println(categoryMap);
    }

    @When("^I update the Category with name$")
    public void iUpdateTheCategoryWithName() {
        name=name+"_updated";
        categoriesSteps.updatingCategory(categoryId,name,id).log().all().statusCode(200);
    }

    @Then("^I verify that the information is updated in the Category$")
    public void iVerifyThatTheInformationIsUpdatedInTheCategory() {
        HashMap<String,Object> categoryMap=categoriesSteps.getCategoryInfoByName(categoryId);
        Assert.assertThat(categoryMap,hasValue(name));
        System.out.println(categoryMap);
    }

    @When("^I delete the Category created with id$")
    public void iDeleteTheCategoryCreatedWithId() {
        categoriesSteps.deleteCategory(categoryId).statusCode(200);
    }

    @Then("^I verify that the Category is deleted and get the status (\\d+)$")
    public void iVerifyThatTheCategoryIsDeletedAndGetTheStatus(int exp) {
        categoriesSteps.getCategoryByID(categoryId).statusCode(exp);
    }

}
package com.framework.playwright.steps.api;

import com.framework.playwright.api.ApiResponse;
import com.framework.playwright.hooks.ScenarioContext;
import com.framework.playwright.reporting.AllureAttachmentHelper;
import com.framework.playwright.utils.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiSteps extends ApiStepSupport {

    @Given("a user exists with id {string}")
    public void aUserExistsWithId(String userId) {
        ScenarioContext.set(ScenarioContext.Keys.CREATED_ID, userId);
    }

    @When("I request user details for id {string}")
    public void iRequestUserDetailsForId(String userId) {
        ApiResponse response = getApiClient().get("/users/" + userId);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @When("I create a new user with name {string} and email {string}")
    public void iCreateANewUserWithNameAndEmail(String name, String email) {
        Map<String, String> body = Map.of("name", name, "email", email);
        String json = JsonUtils.toJson(body);
        AllureAttachmentHelper.attachApiRequest(json);

        ApiResponse response = getApiClient().post("/users", json);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());

        if (response.isOk()) {
            String createdId = response.jsonPath("id");
            if (createdId != null) {
                ScenarioContext.set(ScenarioContext.Keys.CREATED_ID, createdId);
            }
        }
    }

    @When("I update user {string} with name {string}")
    public void iUpdateUserWithName(String userId, String newName) {
        Map<String, String> body = Map.of("name", newName);
        String json = JsonUtils.toJson(body);
        ApiResponse response = getApiClient().put("/users/" + userId, json);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
    }

    @When("I delete user {string}")
    public void iDeleteUser(String userId) {
        ApiResponse response = getApiClient().delete("/users/" + userId);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
    }

    @When("I request all users")
    public void iRequestAllUsers() {
        ApiResponse response = getApiClient().get("/users");
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @Then("the user name should be {string}")
    public void theUserNameShouldBe(String expectedName) {
        assertThat(getResponse().jsonPath("name")).isEqualTo(expectedName);
    }

    @Then("the user email should be {string}")
    public void theUserEmailShouldBe(String expectedEmail) {
        assertThat(getResponse().jsonPath("email")).isEqualTo(expectedEmail);
    }

    @Then("the response should contain a user id")
    public void theResponseShouldContainAUserId() {
        assertThat(getResponse().jsonPath("id")).isNotNull().isNotEmpty();
    }
}

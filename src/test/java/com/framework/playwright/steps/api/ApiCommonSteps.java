package com.framework.playwright.steps.api;

import com.framework.playwright.api.ApiResponse;
import com.framework.playwright.hooks.ScenarioContext;
import com.framework.playwright.reporting.AllureAttachmentHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCommonSteps extends ApiStepSupport {

    @Given("I have a valid auth token {string}")
    public void iHaveAValidAuthToken(String token) {
        ScenarioContext.set(ScenarioContext.Keys.AUTH_TOKEN, token);
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        ApiResponse response = getApiClient().get(endpoint);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @When("I send a GET request to {string} with headers:")
    public void iSendAGetRequestToWithHeaders(String endpoint, Map<String, String> headers) {
        ApiResponse response = getApiClient().get(endpoint, headers);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @When("I send a POST request to {string} with body:")
    public void iSendAPostRequestToWithBody(String endpoint, String body) {
        AllureAttachmentHelper.attachApiRequest(body);
        ApiResponse response = getApiClient().post(endpoint, body);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @When("I send a PUT request to {string} with body:")
    public void iSendAPutRequestToWithBody(String endpoint, String body) {
        AllureAttachmentHelper.attachApiRequest(body);
        ApiResponse response = getApiClient().put(endpoint, body);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
        AllureAttachmentHelper.attachApiResponse(response.body());
    }

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String endpoint) {
        ApiResponse response = getApiClient().delete(endpoint);
        ScenarioContext.set(ScenarioContext.Keys.API_RESPONSE, response);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertThat(getResponse().statusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response should be successful")
    public void theResponseShouldBeSuccessful() {
        assertThat(getResponse().isOk())
                .as("Expected successful response but got status %d", getResponse().statusCode())
                .isTrue();
    }

    @Then("the response body should contain {string}")
    public void theResponseBodyShouldContain(String expectedText) {
        assertThat(getResponse().body()).contains(expectedText);
    }

    @Then("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String headerName, String expectedValue) {
        assertThat(getResponse().header(headerName)).isEqualTo(expectedValue);
    }

    @Then("the response field {string} should be {string}")
    public void theResponseFieldShouldBe(String jsonPath, String expectedValue) {
        assertThat(getResponse().jsonPath(jsonPath)).isEqualTo(expectedValue);
    }
}

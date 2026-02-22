package com.framework.playwright.steps.ui;

import com.framework.playwright.core.PageManager;
import com.framework.playwright.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeSteps {

    private HomePage getHomePage() {
        return new HomePage(PageManager.getPage());
    }

    @Then("I should be on the home page")
    public void iShouldBeOnTheHomePage() {
        assertThat(getHomePage().getCurrentUrl()).contains("/home");
    }

    @Then("I should see the welcome message {string}")
    public void iShouldSeeTheWelcomeMessage(String expectedMessage) {
        assertThat(getHomePage().getWelcomeMessage()).contains(expectedMessage);
    }

    @Then("I should see the welcome message")
    public void iShouldSeeTheWelcomeMessage() {
        assertThat(getHomePage().isWelcomeMessageVisible()).isTrue();
    }

    @When("I logout")
    public void iLogout() {
        getHomePage().logout();
    }

    @When("I search for {string} from the home page")
    public void iSearchForFromTheHomePage(String query) {
        getHomePage().search(query);
    }
}

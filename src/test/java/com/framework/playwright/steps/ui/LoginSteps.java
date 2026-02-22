package com.framework.playwright.steps.ui;

import com.framework.playwright.core.PageManager;
import com.framework.playwright.hooks.ScenarioContext;
import com.framework.playwright.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private LoginPage getLoginPage() {
        return new LoginPage(PageManager.getPage());
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        getLoginPage().navigateToPage();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        getLoginPage().login(username, password);
        ScenarioContext.set(ScenarioContext.Keys.CURRENT_USER, username);
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        getLoginPage().enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        getLoginPage().enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        getLoginPage().clickLoginButton();
    }

    @When("I check the remember me option")
    public void iCheckTheRememberMeOption() {
        getLoginPage().checkRememberMe();
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        assertThat(getLoginPage().getErrorMessage()).contains(expectedMessage);
    }

    @Then("I should see the login error message")
    public void iShouldSeeTheLoginErrorMessage() {
        assertThat(getLoginPage().isErrorMessageVisible()).isTrue();
    }

    @Then("the login button should be disabled")
    public void theLoginButtonShouldBeDisabled() {
        assertThat(getLoginPage().isLoginButtonEnabled()).isFalse();
    }
}

package com.framework.playwright.steps.common;

import com.framework.playwright.config.ConfigManager;
import com.framework.playwright.core.PageManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class NavigationSteps {

    private GenericPage page() {
        return new GenericPage(PageManager.getPage());
    }

    @Given("I am on the {string} page")
    public void iAmOnThePage(String pagePath) {
        page().navigateTo(ConfigManager.getInstance().getBaseUrl() + pagePath);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        page().navigateTo(url);
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        page().navigateTo(ConfigManager.getInstance().getBaseUrl() + "/home");
    }

    @When("I go back")
    public void iGoBack() {
        page().getPage().goBack();
    }

    @When("I go forward")
    public void iGoForward() {
        page().getPage().goForward();
    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        page().getPage().reload();
    }
}

package com.framework.playwright.steps.common;

import com.framework.playwright.core.PageManager;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionSteps {

    private GenericPage page() {
        return new GenericPage(PageManager.getPage());
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        assertThat(page().getTitle()).isEqualTo(expectedTitle);
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedText) {
        assertThat(page().getTitle()).contains(expectedText);
    }

    @Then("the URL should contain {string}")
    public void theUrlShouldContain(String expectedText) {
        assertThat(page().getCurrentUrl()).contains(expectedText);
    }

    @Then("the URL should be {string}")
    public void theUrlShouldBe(String expectedUrl) {
        String currentUrl = page().getCurrentUrl();
        if (expectedUrl.startsWith("http")) {
            assertThat(currentUrl).isEqualTo(expectedUrl);
        } else {
            assertThat(currentUrl).endsWith(expectedUrl);
        }
    }

    @Then("the element {string} should be visible")
    public void theElementShouldBeVisible(String selector) {
        assertThat(page().isVisible(selector))
                .as("Element '%s' should be visible", selector)
                .isTrue();
    }

    @Then("the element {string} should not be visible")
    public void theElementShouldNotBeVisible(String selector) {
        assertThat(page().isVisible(selector))
                .as("Element '%s' should not be visible", selector)
                .isFalse();
    }

    @Then("the element {string} should contain text {string}")
    public void theElementShouldContainText(String selector, String expectedText) {
        assertThat(page().getText(selector)).contains(expectedText);
    }

    @Then("the element {string} should have text {string}")
    public void theElementShouldHaveText(String selector, String expectedText) {
        assertThat(page().getText(selector).trim()).isEqualTo(expectedText);
    }

    @Then("the element {string} should be enabled")
    public void theElementShouldBeEnabled(String selector) {
        assertThat(page().isEnabled(selector)).isTrue();
    }

    @Then("the element {string} should be disabled")
    public void theElementShouldBeDisabled(String selector) {
        assertThat(page().isEnabled(selector)).isFalse();
    }

    @Then("I should see {int} elements matching {string}")
    public void iShouldSeeElementsMatching(int count, String selector) {
        assertThat(page().getElementCount(selector)).isEqualTo(count);
    }
}

package com.framework.playwright.steps.ui;

import com.framework.playwright.core.PageManager;
import com.framework.playwright.pages.TescoBankHomePage;
import com.framework.playwright.pages.TescoBankLoansPage;
import com.framework.playwright.pages.TescoBankSavingsPage;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class TescoBankSteps {

    private TescoBankHomePage homePage() {
        return new TescoBankHomePage(PageManager.getPage());
    }

    private TescoBankSavingsPage savingsPage() {
        return new TescoBankSavingsPage(PageManager.getPage());
    }

    private TescoBankLoansPage loansPage() {
        return new TescoBankLoansPage(PageManager.getPage());
    }

    // --- Homepage ---

    @Then("I should see the {string} product section")
    public void iShouldSeeProductSection(String section) {
        assertThat(homePage().isProductSectionVisible(section))
                .as("Expected product section '%s' to be visible", section)
                .isTrue();
    }

    @Then("the login link should be visible")
    public void theLoginLinkShouldBeVisible() {
        assertThat(homePage().isLoginLinkVisible()).isTrue();
    }

    @Then("the login link should point to the secure area")
    public void theLoginLinkShouldPointToSecureArea() {
        assertThat(homePage().getLoginLinkHref()).contains("/sss/auth");
    }

    // --- Savings ---

    @Then("I should see the {string} savings product")
    public void iShouldSeeSavingsProduct(String product) {
        assertThat(savingsPage().isProductVisible(product))
                .as("Expected savings product '%s' to be visible", product)
                .isTrue();
    }

    // --- Page heading (works across any page) ---

    @Then("the page heading should be {string}")
    public void thePageHeadingShouldBe(String expectedHeading) {
        String actual = PageManager.getPage().textContent("h1").trim();
        assertThat(actual).isEqualTo(expectedHeading);
    }

    // --- Loans ---

    @Then("I should see the {string} loan type")
    public void iShouldSeeLoanType(String loanType) {
        assertThat(PageManager.getPage().locator("text=" + loanType).count())
                .as("Expected loan type '%s' to be visible on the page", loanType)
                .isGreaterThan(0);
    }
}

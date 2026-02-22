package com.framework.playwright.steps.ui;

import com.framework.playwright.core.PageManager;
import com.framework.playwright.pages.TescoBankHomePage;
import com.framework.playwright.pages.TescoBankLoanCalculatorPage;
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

    private TescoBankLoanCalculatorPage calcPage() {
        return new TescoBankLoanCalculatorPage(PageManager.getPage());
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

    // --- Loan Calculator ---

    @Then("the loan amount input should show {string}")
    public void theLoanAmountInputShouldShow(String expectedAmount) {
        assertThat(calcPage().getDefaultLoanAmount()).isEqualTo(expectedAmount);
    }

    @Then("the loan duration input should show {string} months")
    public void theLoanDurationInputShouldShow(String expectedMonths) {
        assertThat(calcPage().getDefaultDurationMonths()).isEqualTo(expectedMonths);
    }

    @Then("the representative APR should be shown")
    public void theRepresentativeAprShouldBeShown() {
        assertThat(calcPage().getAprText()).contains("APR");
    }

    @Then("the minimum loan amount should be {string}")
    public void theMinimumLoanAmountShouldBe(String expectedMin) {
        assertThat(calcPage().getSliderMinValue()).isEqualTo(expectedMin);
    }

    @Then("the maximum loan amount should be {string}")
    public void theMaximumLoanAmountShouldBe(String expectedMax) {
        assertThat(calcPage().getSliderMaxValue()).isEqualTo(expectedMax);
    }

    @Then("the monthly repayments section should be visible")
    public void theMonthlyRepaymentsSectionShouldBeVisible() {
        calcPage().triggerCalculation();
        assertThat(calcPage().isMonthlyRepaymentsVisible()).isTrue();
    }
}
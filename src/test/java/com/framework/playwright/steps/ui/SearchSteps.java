package com.framework.playwright.steps.ui;

import com.framework.playwright.core.PageManager;
import com.framework.playwright.pages.SearchPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {

    private SearchPage getSearchPage() {
        return new SearchPage(PageManager.getPage());
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        getSearchPage().search(query);
    }

    @When("I filter results by {string}")
    public void iFilterResultsBy(String filter) {
        getSearchPage().filterBy(filter);
    }

    @When("I click on search result {int}")
    public void iClickOnSearchResult(int index) {
        getSearchPage().clickResult(index - 1);
    }

    @Then("I should see {int} search results")
    public void iShouldSeeSearchResults(int expectedCount) {
        assertThat(getSearchPage().getResultCount()).isEqualTo(expectedCount);
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        assertThat(getSearchPage().getResultCount()).isGreaterThan(0);
    }

    @Then("I should see no search results")
    public void iShouldSeeNoSearchResults() {
        assertThat(getSearchPage().hasNoResults()).isTrue();
    }

    @Then("the search results should contain {string}")
    public void theSearchResultsShouldContain(String expectedText) {
        assertThat(getSearchPage().getResultTitles())
                .anyMatch(title -> title.contains(expectedText));
    }

    @Then("the results count should show {string}")
    public void theResultsCountShouldShow(String expectedText) {
        assertThat(getSearchPage().getResultsCountText()).contains(expectedText);
    }
}

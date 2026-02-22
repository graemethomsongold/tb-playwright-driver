package com.framework.playwright.pages;

import com.framework.playwright.pages.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class SearchPage extends BasePage {

    private static final String SEARCH_INPUT = "#search-input";
    private static final String SEARCH_BUTTON = "#search-button";
    private static final String RESULTS_LIST = ".search-results .result-item";
    private static final String RESULT_TITLE = ".result-title";
    private static final String NO_RESULTS = ".no-results";
    private static final String RESULTS_COUNT = ".results-count";
    private static final String FILTER_DROPDOWN = "#filter-select";

    public SearchPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/search";
    }

    @Step("Search for: {query}")
    public void search(String query) {
        fill(SEARCH_INPUT, query);
        click(SEARCH_BUTTON);
        waitForPageLoad();
    }

    public List<String> getResultTitles() {
        return getAllTexts(RESULTS_LIST + " " + RESULT_TITLE);
    }

    public int getResultCount() {
        return getElementCount(RESULTS_LIST);
    }

    public String getResultsCountText() {
        return getText(RESULTS_COUNT);
    }

    public boolean hasNoResults() {
        return isVisible(NO_RESULTS);
    }

    @Step("Click on result at index {index}")
    public void clickResult(int index) {
        page.locator(RESULTS_LIST).nth(index).click();
    }

    @Step("Filter results by: {filterValue}")
    public void filterBy(String filterValue) {
        selectOption(FILTER_DROPDOWN, filterValue);
        waitForPageLoad();
    }

    public String getSearchInputValue() {
        return getInputValue(SEARCH_INPUT);
    }
}

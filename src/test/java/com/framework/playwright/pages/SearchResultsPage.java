package com.framework.playwright.pages;

import com.framework.playwright.pages.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private static final String RESULT_ITEMS = ".result-item";
    private static final String RESULT_TITLE = ".result-title";
    private static final String RESULT_DESCRIPTION = ".result-description";
    private static final String PAGINATION_NEXT = ".pagination .next";
    private static final String PAGINATION_PREV = ".pagination .prev";
    private static final String BREADCRUMB = ".breadcrumb";

    public SearchResultsPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/search/results";
    }

    public List<String> getResultTitles() {
        return getAllTexts(RESULT_ITEMS + " " + RESULT_TITLE);
    }

    public List<String> getResultDescriptions() {
        return getAllTexts(RESULT_ITEMS + " " + RESULT_DESCRIPTION);
    }

    public int getResultCount() {
        return getElementCount(RESULT_ITEMS);
    }

    @Step("Click next page")
    public void clickNextPage() {
        click(PAGINATION_NEXT);
        waitForPageLoad();
    }

    @Step("Click previous page")
    public void clickPreviousPage() {
        click(PAGINATION_PREV);
        waitForPageLoad();
    }

    public boolean hasNextPage() {
        return isVisible(PAGINATION_NEXT) && isEnabled(PAGINATION_NEXT);
    }

    public String getBreadcrumbText() {
        return getText(BREADCRUMB);
    }
}

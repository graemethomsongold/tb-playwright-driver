package com.framework.playwright.pages;

import com.framework.playwright.pages.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class HomePage extends BasePage {

    private static final String WELCOME_MESSAGE = ".welcome-message";
    private static final String USER_MENU = "#user-menu";
    private static final String LOGOUT_LINK = "#logout";
    private static final String SEARCH_INPUT = "#search";
    private static final String NAV_LINKS = "nav a";

    public HomePage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/home";
    }

    public String getWelcomeMessage() {
        return getText(WELCOME_MESSAGE);
    }

    public boolean isWelcomeMessageVisible() {
        return isVisible(WELCOME_MESSAGE);
    }

    @Step("Open user menu")
    public void openUserMenu() {
        click(USER_MENU);
    }

    @Step("Logout")
    public void logout() {
        openUserMenu();
        click(LOGOUT_LINK);
    }

    @Step("Search for: {query}")
    public void search(String query) {
        fill(SEARCH_INPUT, query);
        pressEnter();
    }

    public java.util.List<String> getNavigationLinks() {
        return getAllTexts(NAV_LINKS);
    }
}

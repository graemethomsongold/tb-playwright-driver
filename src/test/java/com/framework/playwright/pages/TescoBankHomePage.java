package com.framework.playwright.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class TescoBankHomePage extends BasePage {

    private static final String LOGIN_LINK = "a:has-text(\"Log in\")";

    public TescoBankHomePage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "";
    }

    @Step("Check product section '{section}' is visible")
    public boolean isProductSectionVisible(String section) {
        try {
            waitForSelectorVisible("h2:has-text(\"" + section + "\")");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginLinkVisible() {
        return isVisible(LOGIN_LINK);
    }

    public String getLoginLinkHref() {
        return getAttribute(LOGIN_LINK, "href");
    }
}

package com.framework.playwright.pages;

import com.microsoft.playwright.Page;

public class TescoBankSavingsPage extends BasePage {

    public TescoBankSavingsPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/savings/";
    }

    public String getHeading() {
        return getText("h1").trim();
    }

    public boolean isProductVisible(String productName) {
        return getPage().locator("text=" + productName).count() > 0;
    }
}

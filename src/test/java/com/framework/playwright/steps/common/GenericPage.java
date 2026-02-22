package com.framework.playwright.steps.common;

import com.framework.playwright.pages.BasePage;
import com.microsoft.playwright.Page;

/**
 * Package-private adapter that gives common step definitions access to BasePage helpers
 * without tying them to a specific page URL.
 */
class GenericPage extends BasePage {

    GenericPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "";
    }
}

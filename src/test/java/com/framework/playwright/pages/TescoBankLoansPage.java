package com.framework.playwright.pages;

import com.microsoft.playwright.Page;

public class TescoBankLoansPage extends BasePage {

    private static final String LOAN_AMOUNT_INPUT = "#loan-amount";

    public TescoBankLoansPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/loans/";
    }

    public String getHeading() {
        return getText("h1").trim();
    }

    public boolean isLoanAmountInputVisible() {
        return isVisible(LOAN_AMOUNT_INPUT);
    }
}

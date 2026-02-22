package com.framework.playwright.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class TescoBankLoanCalculatorPage extends BasePage {

    private static final String LOAN_AMOUNT_INPUT = "#loan-amount";
    private static final String DURATION_INPUT = "#input-duration";
    private static final String APR_DISPLAY = ".font-3";
    private static final String MONTHLY_REPAYMENTS_LABEL = "p[data-text-color]:has-text(\"Monthly repayments\")";

    public TescoBankLoanCalculatorPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/loans/loan-calculator/";
    }

    public String getDefaultLoanAmount() {
        waitForNetworkIdle();
        return getInputValue(LOAN_AMOUNT_INPUT);
    }

    public String getDefaultDurationMonths() {
        return getInputValue(DURATION_INPUT);
    }

    public String getAprText() {
        return getText(APR_DISPLAY).trim();
    }

    public String getSliderMinValue() {
        return getAttribute("#loan-amount-slider-1", "min");
    }

    public String getSliderMaxValue() {
        return getAttribute("#loan-amount-slider-1", "max");
    }

    /**
     * Changes the loan amount slider to a different value so the JS calculator
     * recalculates, then waits for the monthly-repayment result to become visible.
     */
    @Step("Trigger loan calculation")
    public void triggerCalculation() {
        evaluate("() => { " +
                "const s = document.getElementById('loan-amount-slider-1');" +
                "if (s) { " +
                "  s.value = '8000';" +
                "  s.dispatchEvent(new Event('input', { bubbles: true }));" +
                "  s.dispatchEvent(new Event('change', { bubbles: true }));" +
                "}" +
                "}");
        waitForSelectorVisible(MONTHLY_REPAYMENTS_LABEL);
    }

    public boolean isMonthlyRepaymentsVisible() {
        return isVisible(MONTHLY_REPAYMENTS_LABEL);
    }
}

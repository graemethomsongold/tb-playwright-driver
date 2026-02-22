package com.framework.playwright.steps.common;

import com.framework.playwright.core.PageManager;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.When;

public class FormSteps {

    private GenericPage page() {
        return new GenericPage(PageManager.getPage());
    }

    @When("I fill in {string} with {string}")
    public void iFillInWith(String selector, String value) {
        page().fill(selector, value);
    }

    @When("I clear the field {string}")
    public void iClearTheField(String selector) {
        page().fill(selector, "");
    }

    @When("I click on {string}")
    public void iClickOn(String selector) {
        page().click(selector);
    }

    @When("I click the button {string}")
    public void iClickTheButton(String buttonText) {
        page().getByRole(AriaRole.BUTTON, buttonText).click();
    }

    @When("I click the link {string}")
    public void iClickTheLink(String linkText) {
        page().getByRole(AriaRole.LINK, linkText).click();
    }

    @When("I select {string} from {string}")
    public void iSelectFrom(String value, String selector) {
        page().selectOption(selector, value);
    }

    @When("I check the checkbox {string}")
    public void iCheckTheCheckbox(String selector) {
        page().check(selector);
    }

    @When("I uncheck the checkbox {string}")
    public void iUncheckTheCheckbox(String selector) {
        page().uncheck(selector);
    }

    @When("I press {string}")
    public void iPress(String key) {
        page().pressKey(key);
    }

    @When("I type {string} into {string}")
    public void iTypeInto(String text, String selector) {
        page().type(selector, text);
    }
}

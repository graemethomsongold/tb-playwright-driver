package com.framework.playwright.pages;

import com.framework.playwright.pages.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String ERROR_MESSAGE = ".error-message";
    private static final String REMEMBER_ME_CHECKBOX = "#remember-me";

    public LoginPage(Page page) {
        super(page);
    }

    @Override
    protected String getPagePath() {
        return "/login";
    }

    @Step("Login with username '{username}'")
    public void login(String username, String password) {
        fill(USERNAME_INPUT, username);
        fill(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        fill(USERNAME_INPUT, username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        fill(PASSWORD_INPUT, password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    @Step("Check remember me checkbox")
    public void checkRememberMe() {
        check(REMEMBER_ME_CHECKBOX);
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isErrorMessageVisible() {
        return isVisible(ERROR_MESSAGE);
    }

    public boolean isLoginButtonEnabled() {
        return isEnabled(LOGIN_BUTTON);
    }
}

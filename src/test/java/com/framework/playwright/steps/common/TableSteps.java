package com.framework.playwright.steps.common;

import com.framework.playwright.core.PageManager;
import com.microsoft.playwright.Locator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TableSteps {

    private GenericPage page() {
        return new GenericPage(PageManager.getPage());
    }

    @Then("the table {string} should have {int} rows")
    public void theTableShouldHaveRows(String tableSelector, int expectedRows) {
        assertThat(page().getElementCount(tableSelector + " tbody tr")).isEqualTo(expectedRows);
    }

    @Then("the table {string} should contain text {string}")
    public void theTableShouldContainText(String tableSelector, String expectedText) {
        assertThat(page().getText(tableSelector)).contains(expectedText);
    }

    @Then("the table {string} row {int} should contain {string}")
    public void theTableRowShouldContain(String tableSelector, int rowIndex, String expectedText) {
        Locator row = page().locator(tableSelector + " tbody tr").nth(rowIndex - 1);
        assertThat(row.textContent()).contains(expectedText);
    }

    @Then("the table {string} column {string} should contain values:")
    public void theTableColumnShouldContainValues(String tableSelector, String columnHeader, io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedValues = dataTable.asList();
        Locator headers = page().locator(tableSelector + " thead th");
        int columnIndex = -1;
        for (int i = 0; i < headers.count(); i++) {
            if (headers.nth(i).textContent().trim().equals(columnHeader)) {
                columnIndex = i + 1;
                break;
            }
        }
        assertThat(columnIndex).as("Column '%s' not found", columnHeader).isGreaterThan(0);

        List<String> actualValues = page()
                .locator(tableSelector + " tbody tr td:nth-child(" + columnIndex + ")")
                .allTextContents();
        assertThat(actualValues).containsAll(expectedValues);
    }

    @When("I click on row {int} in table {string}")
    public void iClickOnRowInTable(int rowIndex, String tableSelector) {
        page().locator(tableSelector + " tbody tr").nth(rowIndex - 1).click();
    }

    @When("I sort the table {string} by column {string}")
    public void iSortTheTableByColumn(String tableSelector, String columnHeader) {
        Locator headers = page().locator(tableSelector + " thead th");
        for (int i = 0; i < headers.count(); i++) {
            if (headers.nth(i).textContent().trim().equals(columnHeader)) {
                headers.nth(i).click();
                break;
            }
        }
    }
}

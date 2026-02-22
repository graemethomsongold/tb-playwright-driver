package com.framework.playwright.steps.api;

import com.framework.playwright.api.ApiClient;
import com.framework.playwright.api.ApiResponse;
import com.framework.playwright.hooks.ScenarioContext;

abstract class ApiStepSupport {

    protected ApiClient getApiClient() {
        ApiClient client = ScenarioContext.get(ScenarioContext.Keys.API_CLIENT, ApiClient.class);
        if (client == null) {
            throw new IllegalStateException(
                    "ApiClient not initialised — ensure the scenario is tagged @api");
        }
        return client;
    }

    protected ApiResponse getResponse() {
        ApiResponse response = ScenarioContext.get(ScenarioContext.Keys.API_RESPONSE, ApiResponse.class);
        if (response == null) {
            throw new IllegalStateException(
                    "No API response stored — send a request before asserting on the response");
        }
        return response;
    }
}

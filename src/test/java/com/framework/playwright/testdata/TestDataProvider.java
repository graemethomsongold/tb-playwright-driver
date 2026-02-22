package com.framework.playwright.testdata;

import com.framework.playwright.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Provides test data from JSON resource files.
 */
public final class TestDataProvider {

    private static final Logger log = LoggerFactory.getLogger(TestDataProvider.class);
    private static final String TEST_DATA_DIR = "testdata/";

    private TestDataProvider() {
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getTestData(String filename) {
        log.debug("Loading test data from: {}", filename);
        return JsonUtils.fromResource(TEST_DATA_DIR + filename, Map.class);
    }

    public static <T> T getTestData(String filename, Class<T> type) {
        return JsonUtils.fromResource(TEST_DATA_DIR + filename, type);
    }

    public static <T> List<T> getTestDataList(String filename, Class<T> elementType) {
        return JsonUtils.listFromResource(TEST_DATA_DIR + filename, elementType);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<Map<String, Object>> getTestDataList(String filename) {
        List rawList = JsonUtils.listFromResource(TEST_DATA_DIR + filename, Map.class);
        return (List<Map<String, Object>>) rawList;
    }
}

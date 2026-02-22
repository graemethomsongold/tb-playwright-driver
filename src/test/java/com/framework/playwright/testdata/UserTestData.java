package com.framework.playwright.testdata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * POJO representing user test data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTestData {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}

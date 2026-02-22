# TB Playwright Driver

An application-specific test suite built on top of the [TB Playwright Framework](https://github.com/graemethomson-gold/tb-playwright-framework). It uses Cucumber BDD scenarios to drive UI and API tests with Playwright, and produces Allure reports.

## Prerequisites

| Tool | Version |
|------|---------|
| Java | 17+ |
| Maven | 3.8+ |
| TB Playwright Framework | 1.0.0-SNAPSHOT (installed in local Maven repo) |

## Project Structure

```
src/test/
├── java/com/framework/playwright/
│   ├── pages/          # Page Object classes (LoginPage, HomePage, SearchPage, etc.)
│   ├── runners/        # JUnit suite runners (smoke, regression, api, full)
│   ├── steps/
│   │   ├── api/        # API step definitions
│   │   ├── common/     # Shared step definitions (navigation, forms, assertions, tables)
│   │   └── ui/         # UI step definitions
│   └── testdata/       # Test data providers
└── resources/
    ├── config/         # Environment configuration files
    ├── features/
    │   ├── api/        # API feature files
    │   └── ui/         # UI feature files
    └── testdata/       # JSON test data files
```

## Configuration

Environment configs live in `src/test/resources/config/`:

| File | Environment |
|------|-------------|
| `application.yml` | Base / defaults |
| `application-dev.yml` | Dev (default) |
| `application-staging.yml` | Staging |
| `application-production.yml` | Production |

Key settings in `application.yml`:

```yaml
baseUrl: https://dev.example.com
apiBaseUrl: https://api.dev.example.com

browser:
  type: chromium
  headless: true
  defaultTimeout: 30000
  navigationTimeout: 60000
  viewportWidth: 1920
  viewportHeight: 1080
  video: false
  tracing: false

screenshotOnFailure: true
retryCount: 1
```

## Running Tests

### Run all tests (default: dev env, chromium, headless)

```bash
mvn test
```

### Override environment, browser, or headless mode

```bash
mvn test -Denv=staging -Dbrowser=firefox -Dheadless=false
```

### Run by tag profile

| Profile | Tags run |
|---------|----------|
| `smoke` | `@smoke` |
| `regression` | `@regression` |
| `api` | `@api` |
| `mobile` | `@mobile` |

```bash
mvn test -Psmoke
mvn test -Pregression
mvn test -Papi
mvn test -Pmobile
```

### Run with a custom tag filter

```bash
mvn test -Dcucumber.filter.tags="@login and not @wip"
```

### Run a specific JUnit suite runner directly

```bash
mvn test -Dtest=RunSmokeTest
mvn test -Dtest=RunRegressionTest
mvn test -Dtest=RunApiTest
```

## Allure Reports

Generate and open the report after a test run:

```bash
mvn allure:report          # generate into target/site/allure-maven-plugin/
mvn allure:serve           # generate and open in browser
```

## Mobile Device Testing

Supported device profiles are defined in `src/test/resources/config/mobile-devices.yml`:

- iPhone 14
- Pixel 7
- iPad Pro

Run mobile scenarios with:

```bash
mvn test -Pmobile
```

## Feature Coverage

| Area | Feature file | Tags |
|------|-------------|------|
| Login | `features/ui/login.feature` | `@smoke @regression` |
| Home | `features/ui/home.feature` | — |
| Search | `features/ui/search.feature` | `@regression` |
| User API | `features/api/user_api.feature` | `@api @regression` |

## Adding New Tests

1. **Create a Page Object** in `pages/` extending `BasePage`.
2. **Write step definitions** in `steps/ui/` or `steps/api/`.
3. **Add a feature file** under `features/ui/` or `features/api/` with appropriate tags (`@smoke`, `@regression`, `@api`, `@mobile`).
4. Scenarios tagged `@wip` or `@flaky` are excluded from the default runner.

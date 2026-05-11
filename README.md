# SeleniumMavenMultiModuleFW

**Enterprise-Grade Selenium Java BDD Automation Framework** - A robust, scalable, multi-module Maven-based test automation framework designed for comprehensive testing across multiple product lines with Behavior-Driven Development (BDD) principles.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Framework Architecture](#framework-architecture)
- [Technology Stack](#technology-stack)
- [Module Structure](#module-structure)
- [Directory Organization](#directory-organization)
- [Getting Started](#getting-started)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Project Structure Details](#project-structure-details)
- [Best Practices](#best-practices)
- [Contributing](#contributing)

---

## 🎯 Project Overview

**SeleniumMavenMultiModuleFW** is a sophisticated test automation framework built with industry best practices for:

✅ **Multi-Product Testing** - Support for core product and derived product variations  
✅ **BDD Implementation** - Cucumber-based Gherkin syntax for readable test scenarios  
✅ **Scalability** - Modular design allowing independent module scaling  
✅ **Maintainability** - Centralized base classes, utilities, and hooks  
✅ **Reporting** - Integrated Allure reports for comprehensive test analytics  
✅ **Performance** - Parallel test execution with configurable thread pools  
✅ **API & UI Testing** - Selenium for UI + REST Assured for API testing  

---

## 🏗️ Framework Architecture

The framework follows a **three-tier modular architecture**:

```
┌─────────────────────────────────────────────────────────────┐
│                  PARENT MODULE (test-automation)             │
│                  - POM Management                            │
│                  - Dependency Centralization                 │
│                  - Build Configuration                       │
└────────────┬────────────────────────────────┬────────────────┘
             │                                │
    ┌────────▼─────────┐         ┌────────────▼──────────┐
    │ AUTOMATION        │         │   TEST MODULES        │
    │ FRAMEWORK         │         │   (Product Tests)     │
    │ (Shared Assets)   │         │                       │
    │                   │         ├─────────────────────┐ │
    │ • Base Classes    │◄────────┤ • Core Product      │ │
    │ • Utilities       │         │ • Derived Prod 1    │ │
    │ • Hooks           │         │ • Derived Prod 2    │ │
    │ • Configurations  │         └─────────────────────┘ │
    └───────────────────┘         └─────────────────────────┘
```

---

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Automation Tool** | Selenium WebDriver | 4.32.0 |
| **BDD Framework** | Cucumber-Java | 7.18.0 |
| **Test Runner** | TestNG | 7.12.0 |
| **API Testing** | REST Assured | 6.0.0 |
| **Browser Management** | WebDriverManager | 5.8.0 |
| **Reporting** | Allure | 2.29.0 |
| **Testing Platform** | JUnit Platform | 1.10.2 |
| **Build Tool** | Maven | 3.x+ |
| **Language** | Java | 8+ |

---

## 📦 Module Structure

### 1. **automation-framework** (Core Shared Module)
**Purpose:** Centralized automation framework containing reusable components

**Components:**

#### a) **Base Classes** (`base/`)
- `BasePageClass.java` - Foundation for all page object classes
  - Browser initialization and teardown
  - Common element interaction methods
  - Wait strategies
  - Logging mechanisms

- `DriverFactory.java` - Driver management
  - WebDriver instantiation
  - Browser configuration
  - Driver cleanup
  - Cross-browser support (Chrome, Firefox, Safari, Edge)

#### b) **Hooks** (`hooks/`)
- `Hooks.java` - Cucumber test lifecycle hooks
  - @Before - Test initialization
  - @After - Test cleanup & screenshot capture
  - Driver setup/teardown
  - Report generation triggers

#### c) **Utilities** (`utils/`)
- `ConfigReader.java` - Configuration management
  - Read environment properties
  - Application URLs
  - Browser configurations
  - Timeout settings

- `WaitUtil.java` - Smart wait implementations
  - Explicit waits
  - Implicit waits
  - Custom wait conditions
  - Element visibility/clickability checks

- `ScreenshotUtil.java` - Screenshot capture utilities
  - Capture on test failure
  - Generate evidence for reports
  - Allure integration

### 2. **core-product-tests** (Core Product Test Module)
**Purpose:** End-to-end tests for primary product

**Contains:**
- Feature files defining test scenarios
- Step definitions implementing Gherkin steps
- Page Object Model classes for core product
- Product-specific test runners
- Core business functionality test coverage

**Key Components:**
- `runner/` - Test execution runners (TestNG & Cucumber)
- `stepDefinitions/` - Step definition implementations
- `features/` - Gherkin feature files

### 3. **derived-product1-tests** (Derived Product 1 Tests)
**Purpose:** Test module for derived product variant 1

**Characteristics:**
- Inherits automation-framework capabilities
- Isolated test scenarios for product variant 1
- Product-specific configurations
- Independent test execution
- Allure reporting integration

### 4. **derived-product2-tests** (Derived Product 2 Tests)
**Purpose:** Test module for derived product variant 2

**Characteristics:**
- Standalone test suite for product variant 2
- CSV data-driven testing support (e.g., DP2_Footer_Links.csv)
- Product-specific page objects
- Separate reporting and artifacts

---

## 📂 Directory Organization

```
test-automation/
│
├── pom.xml (Parent POM - Dependency Management)
│
├── automation-framework/ (Shared Framework Module)
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── base/
│   │   │   │   │   ├── BasePageClass.java
│   │   │   │   │   └── DriverFactory.java
│   │   │   │   ├── hooks/
│   │   │   │   │   └── Hooks.java
│   │   │   │   └── utils/
│   │   │   │       ├── ConfigReader.java
│   │   │   │       ├── WaitUtil.java
│   │   │   │       └── ScreenshotUtil.java
│   │   │   └── resources/
│   │   │       └── config.properties
│   │   └── test/
│   │       ├── java/
│   │       └── resources/
│   └── target/
│
├── core-product-tests/ (Core Product Tests)
│   ├── pom.xml
│   ├── testng.xml (Test Suite Configuration)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── pages/ (Page Objects)
│   │   │   └── resources/
│   │   └── test/
│   │       ├── java/
│   │       │   ├── runner/ (Test Runners)
│   │       │   └── stepDefinitions/
│   │       └── resources/
│   │           └── features/ (Gherkin Feature Files)
│   └── target/
│       └── surefire-reports/
│
├── derived-product1-tests/ (Derived Product 1 Tests)
│   ├── pom.xml
│   ├── src/
│   ├── target/
│   │   ├── screenshots/ (Test Evidence)
│   │   ├── surefire-reports/
│   │   └── allure-results/
│   └── allure-results/
│
├── derived-product2-tests/ (Derived Product 2 Tests)
│   ├── pom.xml
│   ├── DP2_Footer_Links.csv (Test Data)
│   ├── src/
│   └── target/
│       ├── surefire-reports/
│       └── allure-results/
│
└── src/site/ (Documentation & Reports)
```

---

## 🚀 Getting Started

### Prerequisites

- **Java**: JDK 8 or higher installed
- **Maven**: 3.6.0 or higher
- **Git**: Version control
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **Browsers**: Chrome, Firefox, Safari, or Edge (WebDriverManager handles drivers automatically)

### Installation & Setup

#### 1. **Clone Repository**
```bash
git clone https://github.com/Savitapanwar/SeleniumMavenMultiModuleFW.git
cd SeleniumMavenMultiModuleFW/test-automation
```

#### 2. **Install Dependencies**
```bash
mvn clean install
```

#### 3. **Configure Environment**
Update `automation-framework/src/main/resources/config.properties`:
```properties
# Browser Configuration
browser=chrome
headless=false

# URL Configuration
baseUrl=https://your-application-url.com

# Timeout Configuration
implicitWait=10
explicitWait=20
pageLoadWait=30

# Allure Reports
allure.results.directory=target/allure-results
```

#### 4. **Verify Installation**
```bash
mvn --version
mvn clean verify
```

---

## ▶️ Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Module
```bash
# Core Product Tests
mvn clean test -pl core-product-tests

# Derived Product 1 Tests
mvn clean test -pl derived-product1-tests

# Derived Product 2 Tests
mvn clean test -pl derived-product2-tests
```

### Run Specific Test Suite
```bash
# Using TestNG XML
mvn clean test -f test-automation/core-product-tests/pom.xml -Dsuites=testng.xml
```

### Run with Tags (Cucumber)
```bash
# Run only @smoke tagged scenarios
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run @critical scenarios excluding @wip
mvn clean test -Dcucumber.filter.tags="@critical and not @wip"
```

### Parallel Execution
Tests are configured to run in parallel (5 threads by default). Configure in parent pom.xml:
```xml
<threadCount>5</threadCount>
<perCoreThreadCount>false</perCoreThreadCount>
```

### Generate Allure Reports
```bash
# Generate report
mvn allure:report

# Serve report locally
mvn allure:serve
```

---

## 📊 Project Structure Details

### Configuration Management

**config.properties** contains:
- Application URLs
- Browser settings
- Wait timeouts
- Test data paths
- Report configurations

Example structure:
```properties
# Application Settings
app.name=MyApplication
app.url=https://app.example.com

# Browser Settings
browser.type=Chrome
browser.headless=false

# Wait Times (in seconds)
wait.implicit=10
wait.explicit=20
wait.pageload=30

# Screenshot Path
screenshot.path=target/screenshots
```

### Page Object Model Pattern

All page classes extend `BasePageClass` and follow POM principles:

```java
public class LoginPage extends BasePageClass {
    // Element Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    
    // Page Actions
    public void enterEmail(String email) {
        enterText(emailField, email);
    }
    
    public void enterPassword(String password) {
        enterText(passwordField, password);
    }
    
    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage();
    }
}
```

### Step Definition Pattern

Step definitions bridge Gherkin scenarios with Java code:

```java
public class LoginSteps {
    private LoginPage loginPage;
    
    @Given("User navigates to login page")
    public void navigateToLogin() {
        loginPage.navigateTo();
    }
    
    @When("User enters {string} and {string}")
    public void loginWithCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
    
    @Then("User should see dashboard")
    public void verifyDashboard() {
        assertTrue(loginPage.isDashboardVisible());
    }
}
```

### Feature File Example

```gherkin
Feature: User Login Functionality

  Background:
    Given User navigates to login page

  @smoke @critical
  Scenario: Successful login with valid credentials
    When User enters "user@example.com" and "password123"
    Then User should see dashboard

  @regression
  Scenario: Login fails with invalid credentials
    When User enters "user@example.com" and "invalid"
    Then User should see error message "Invalid credentials"
```

---

## ✅ Best Practices

### 1. **Test Naming Conventions**
- Feature files: `feature_description.feature`
- Step classes: `FeatureSteps.java`
- Page classes: `PageNamePage.java`
- Test runners: `RunCucumberIT.java` (integration test suffix)

### 2. **Code Organization**
- Keep page objects focused (single responsibility)
- Use descriptive method names
- Organize locators at top of page class
- Use wait utilities instead of Thread.sleep()

### 3. **Test Data Management**
- Externalize test data in config files or CSV
- Use data-driven testing for repetitive scenarios
- Parameterize URLs, credentials, test values

### 4. **Reporting & Evidence**
- Screenshots captured on failure automatically
- Use Allure annotations for better reports
- Tag scenarios appropriately (@smoke, @regression, @critical)
- Include descriptive assertion messages

### 5. **Maintenance**
- Update locators when UI changes
- Review and refactor step definitions regularly
- Keep dependencies updated
- Document complex test logic with comments

### 6. **Performance**
- Use appropriate waits (not hardcoded delays)
- Leverage parallel execution
- Avoid unnecessary browser interactions
- Clean up resources in hooks

---

## 🔧 Troubleshooting

### Common Issues

**Issue: WebDriver not found**
```bash
# Solution: WebDriverManager handles this automatically
# If issues persist, check internet connectivity for driver downloads
```

**Issue: Element not found**
- Verify locator accuracy
- Check if element is within iframe
- Add explicit wait before interaction
- Use WaitUtil.waitForElementPresence()

**Issue: Test fails intermittently**
- Increase explicit wait times in config.properties
- Check for dynamic elements
- Verify element is not stale
- Use WaitUtil for reliable element interaction

**Issue: Allure reports not generating**
```bash
# Clear previous results
mvn clean

# Regenerate with verbose output
mvn clean test allure:report -X
```

---

## 📚 Resources & Documentation

- [Selenium Documentation](https://www.selenium.dev/)
- [Cucumber Documentation](https://cucumber.io/)
- [Allure Framework](https://docs.qameta.io/allure/)
- [TestNG Documentation](https://testng.org/)
- [REST Assured](https://rest-assured.io/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)

---

## 👨‍💼 Architecture Diagram

```
┌──────────────────────────────────────────────────────────────────────┐
│                     TEST EXECUTION LAYER                              │
│  (TestNG Runners, Cucumber Feature Files, Test Execution)            │
└───────────────────────┬──────────────────────────────────────────────┘
                        │
┌───────────────────────▼──────────────────────────────────────────────┐
│                   STEP DEFINITIONS LAYER                              │
│  (Gherkin to Java Translation, Test Logic Implementation)             │
└───────────────────────┬──────────────────────────────────────────────┘
                        │
┌───────────────────────▼──────────────────────────────────────────────┐
│                   PAGE OBJECTS LAYER                                  │
│  (UI Element Locators, Page-Specific Actions)                        │
└───────────────────────┬──────────────────────────────────────────────┘
                        │
┌───────────────────────▼──────────────────────────────────────────────┐
│              BASE CLASSES & UTILITIES LAYER                           │
│  • BasePageClass (Common Methods)                                    │
│  • DriverFactory (WebDriver Management)                              │
│  • WaitUtil (Smart Waits)                                            │
│  • ConfigReader (Configuration)                                      │
│  • ScreenshotUtil (Evidence Capture)                                 │
└───────────────────────┬──────────────────────────────────────────────┘
                        │
┌───────────────────────▼──────────────────────────────────────────────┐
│                  SELENIUM WEBDRIVER LAYER                             │
│  (Browser Automation, Element Interaction)                            │
└───────────────────────┬──────────────────────────────────────────────┘
                        │
                        ▼
             ┌─────────────────────────────────────────┐
             │   ACTUAL APPLICATIONS UNDER TEST       │
             │  • Core Product                         │
             │  • Derived Product 1                    │
             │  • Derived Product 2                    │
             └─────────────────────────────────────────┘
```

---

## 📝 Contributing

1. Create a feature branch: `git checkout -b feature/feature-name`
2. Make your changes
3. Commit with clear messages: `git commit -m "Add feature: description"`
4. Push to branch: `git push origin feature/feature-name`
5. Submit a Pull Request

### Coding Standards
- Follow Java naming conventions
- Write self-documenting code with comments
- Use meaningful variable/method names
- Keep methods concise and focused
- Add logging for debugging

---

## 📄 License

This project is proprietary and confidential.

---

## 📞 Support & Contact

For questions or issues, please reach out to the QA/Test Architecture team.

---

**Last Updated:** May 2026  
**Framework Version:** 0.0.1-SNAPSHOT  
**Maintainer:** QA Test Architecture Team

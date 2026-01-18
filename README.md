##BDD Selenium Automation Framework
#Project Overview

This project is a BDD-based Selenium automation framework built using Java, Cucumber, TestNG, and Maven.
It supports environment-based execution, multiple browsers, reusable utilities, and structured reporting.

#Tech Stack

Java

Selenium WebDriver

Cucumber (BDD)

TestNG

Maven

ChainTest Reporting

#Project Structure


```text
BDDECLIPSE
│
├── src
│   ├── main
│   │   └── java
│   │       ├── actions
│   │       ├── constants
│   │       ├── locators
│   │       └── utils
│   │
│   └── test
│       ├── java
│       │   ├── runners
│       │   └── stepdefinitions
│       │
│       └── resources
│           ├── config
│           ├── env
│           └── features
│
├── pom.xml
├── README.md
└── testng xml files
```


#src/main/java
actions=> Contains business-level methods that perform actions on application pages such as login, navigation, and validations.

constants=> Contains constant values used across the framework to avoid hardcoding.

locators=> Contains page element locators (By objects) for different application pages.

utils=> Contains reusable utility classes used across the framework.

	ConfigReader=> Loads and provides access to values from the global config.properties file.

	EnvironmentReader=> Loads and provides access to environment-specific property files (dev / qa / prod).

	DriverFactory=> Initializes, manages, and quits WebDriver instances using ThreadLocal for thread safety.

	GenericMethods=> Provides reusable Selenium actions such as clicks, text input, dropdown handling, waits, validations, alerts, and assertions.

	WaitMethods=> Provides explicit and implicit wait utility methods for elements and page load handling.

	Helper=> Provides basic WebDriver helper methods such as opening application URLs.

	ScreenshotUtils=> Captures and manages screenshots for failed test scenarios.

#src/test/java
runners=> Configures and runs Cucumber scenarios using TestNG with specified features, step definitions, tags, and reporting plugins.

stepdefinitions=>

	Hooks=> Handles test setup and teardown using Cucumber hooks, initializes browser sessions, and captures screenshots on failures.

	Step Definition Classes=> Maps Gherkin steps from feature files to corresponding Java methods for execution.
	
#src/test/resources
config=> Contains config.properties for global framework-level configuration values.

env=>Contains environment-specific property files such as dev.properties, qa.properties, and prod.properties.

features=>Contains Cucumber .feature files written in Gherkin syntax.

#Test Execution

Run tests for a specific environment:

	mvn test -Denv=qa
	mvn test -Denv=dev
	mvn test -Denv=prod
	
#Reporting

Cucumber HTML reports are generated under target/cucumber-reports

ChainTest reports are generated after execution

Failure screenshots are stored under screenshots/failures

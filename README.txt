# BDD Selenium Automation Framework

## Tech Stack
- Java
- Selenium WebDriver
- Cucumber (BDD)
- Maven
- TestNG
- ChainTest Reporting

## Project Structure
- DriverFactory → WebDriver initialization
- Hooks → Scenario setup & teardown
- EnvironmentReader → Environment-based configs
- env folder → dev / qa / prod properties

## Environments
- dev
- qa
- prod

## How to Run Tests

### Default (QA)
mvn test -e

### DEV
mvn test -Denv=dev

### PROD
mvn test -Denv=prod

## Configuration
All environment configurations are located under:
src/test/resources/env/

## Notes
- Browser and timeout are controlled via env properties
- ThreadLocal WebDriver is used for parallel execution

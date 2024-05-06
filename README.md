
# Frontend Test Automation - LVolcko

This repository contains Selenium-based automated tests for a web application. Follow these instructions to set up and run the tests.

## Downloading the Project

To download the project, you can clone it using git or download the zip file directly from GitHub.

```bash
git clone https://github.com/LVSoftwareTesting/frontend_test_automation_lvolcko.git
cd frontend_test_automation_lvolcko
```

Or download the zip file from the GitHub page and extract it to your desired directory.

## Installation

Before running the tests, ensure you have Java and Maven installed on your system. Selenium dependencies are managed through Maven, so no additional manual Selenium setup is required.

### Java Installation

1. Download and install Java JDK 11 from the [Oracle website](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or another JDK provider.
2. Set the JAVA_HOME environment variable to the path where JDK is installed.
3. Update your system's PATH to include the JDK's bin directory.

### Maven Installation

- Download and install Maven from the [Apache Maven Project](https://maven.apache.org/download.cgi).
- Ensure that the `M2_HOME` and `MAVEN_HOME` environment variables are set to your Maven directory.
- Add the bin directory of the created Maven directory to your PATH.

## Running Tests

To run the tests, use the following commands in the command line, depending on the type of tests you want to run:

- Run all tests:
  ```bash
  mvn test -Dcucumber.filter.tags="@Smoke"
  ```

- Run authentication tests:
  ```bash
  mvn test -Dcucumber.filter.tags="@Auth"
  ```

- Run product management tests:
  ```bash
  mvn test -Dcucumber.filter.tags="@ProductManagement"
  ```

For any further configuration or additional parameters, refer to the Maven and Cucumber documentation.

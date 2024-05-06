package com.frontend_test_automation.lv.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    	features = "src/test/resources/features",
    	glue = "com.frontend_test_automation.lv.steps",
		tags = "@Auth or @ProductManagement",
		plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
			"junit:target/cucumber-reports/Cucumber.xml",
			"html:target/cucumber-reports.html"}
)

public class TestRunner {
}
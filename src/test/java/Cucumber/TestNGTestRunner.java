package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="learning.StepDefinitions",
monochrome=true, tags = "@Regression", plugin= {"html:Reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}

package com.coffeemachine;

import junit.framework.TestCase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ServeCoffeeTest extends TestCase {
    // Tags: sprint:1 JIRA:CMM-1
    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "failed";
    public String featureName = "Serve coffee";

    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        actionwords.setDriver(driver);
    }

    protected void tearDown() throws Exception {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);

        driver.quit();
    }

    // Well, sometimes, you just get a coffee.
    // Tags: priority:0
    public void testSimpleUse() throws Exception {
        scenarioSetup("Simple use");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        status = "passed";
    }
}
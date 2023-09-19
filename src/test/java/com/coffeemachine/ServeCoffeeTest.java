package com.coffeemachine;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ServeCoffeeTest {
    // Tags: sprint:1 JIRA:CMM-1
    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "failed";
    public String featureName = "Serve coffee";

    @Before
    public void setUp() throws Exception {
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        actionwords.setDriver(driver);
    }

    @After
    public void tearDown() throws Exception {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        driver.quit();
    }

    // Well, sometimes, you just get a coffee.
    // Tags: priority:0
    @Test
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
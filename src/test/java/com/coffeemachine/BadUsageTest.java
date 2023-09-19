package com.coffeemachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BadUsageTest{

    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "failed";
    public String featureName = "Bad usage";

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

    // You keep getting coffee even if the "Empty grounds" message is displayed. That said it's not a fantastic idea, you'll get ground everywhere when you'll decide to empty it.
    // Tags: priority:2
    @Test
    public void testFullGroundsDoesNotBlockCoffee() throws Exception {
        scenarioSetup("Full grounds does not block coffee");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the grounds
        actionwords.iHandleEverythingExceptTheGrounds();
        // When I take "50" coffees
        actionwords.iTakeCoffeeNumberCoffees(50);
        // Then message "Empty grounds" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Empty grounds");
        // And coffee should be served
        actionwords.coffeeShouldBeServed();
        status = "passed";
    }
}
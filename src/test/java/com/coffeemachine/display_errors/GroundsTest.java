package com.coffeemachine.display_errors;

import junit.framework.TestCase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class GroundsTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "fail";
    public String featureName = "Grounds";

    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        actionwords.setDriver(driver);

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the grounds
        actionwords.iHandleEverythingExceptTheGrounds();
    }

    protected void tearDown() throws Exception {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        driver.quit();
    }

    // 
    // Tags: priority:0
    public void testMessageEmptyGroundsIsDisplayedAfter30CoffeesAreTaken() throws Exception {
        scenarioSetup("Message \"Empty grounds\" is displayed after 30 coffees are taken");

        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // Then message "Empty grounds" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Empty grounds");
        status = "pass";
    }
    // 
    // Tags: priority:1
    public void testWhenTheGroundsAreEmptiedMessageIsRemoved() throws Exception {
        scenarioSetup("When the grounds are emptied, message is removed");

        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // And I empty the coffee grounds
        actionwords.iEmptyTheCoffeeGrounds();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
        status = "pass";
    }
}
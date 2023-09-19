package com.coffeemachine;

import junit.framework.TestCase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class WaterTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "fail";
    public String featureName = "Water";

    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        actionwords.setDriver(driver);

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the water tank
        actionwords.iHandleEverythingExceptTheWaterTank();
    }

    protected void tearDown() throws Exception {
        ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
        driver.quit();
    }

    // 
    // Tags: priority:0
    public void testMessageFillWaterTankIsDisplayedAfter50CoffeesAreTaken() throws Exception {
        scenarioSetup("Message \"Fill water tank\" is displayed after 50 coffees are taken");

        // When I take "50" coffees
        actionwords.iTakeCoffeeNumberCoffees(50);
        // Then message "Fill tank" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill tank");
        status = "pass";
    }
    // 
    // Tags: priority:2
    public void testItIsPossibleToTake10CoffeesAfterTheMessageFillWaterTankIsDisplayed() throws Exception {
        scenarioSetup("It is possible to take 10 coffees after the message \"Fill water tank\" is displayed");

        // When I take "60" coffees
        actionwords.iTakeCoffeeNumberCoffees(60);
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should not be served
        actionwords.coffeeShouldNotBeServed();
        status = "pass";
    }
    // 
    // Tags: priority:0
    public void testWhenTheWaterTankIsFilledTheMessageDisappears() throws Exception {
        scenarioSetup("When the water tank is filled, the message disappears");

        // When I take "55" coffees
        actionwords.iTakeCoffeeNumberCoffees(55);
        // And I fill the water tank
        actionwords.iFillTheWaterTank();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
        status = "pass";
    }
}
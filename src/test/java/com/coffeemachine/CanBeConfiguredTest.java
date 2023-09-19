package com.coffeemachine;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CanBeConfiguredTest {
    // Tags: sprint:2
    public Actionwords actionwords;
    public WebDriver driver;
    public static String status = "failed";
    public String featureName = "Can be configured";

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

    // 
    // Tags: priority:1
    @Test
    public void testDisplaySettings() throws Exception {
        scenarioSetup("Display settings");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then displayed message is:
        actionwords.displayedMessageIs("Settings:\n - 1: water hardness\n - 2: grinder");
        status = "passed";
    }
    // 
    // Tags: priority:0
    @Test
    public void testDefaultSettings() throws Exception {
        scenarioSetup("Default settings");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then settings should be:
        actionwords.settingsShouldBe("| water hardness | 2      |\n| grinder        | medium |");
        status = "passed";
    }
}
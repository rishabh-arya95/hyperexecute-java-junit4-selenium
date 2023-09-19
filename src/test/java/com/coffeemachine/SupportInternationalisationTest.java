package com.coffeemachine;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class SupportInternationalisationTest {
    // Tags: sprint:3
    public Actionwords actionwords;
    public WebDriver driver;
    public String status = "failed";
    public String featureName = "Support internationalisation";

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
    public void testNoMessagesAreDisplayedWhenMachineIsShutDown() throws Exception {
        scenarioSetup("No messages are displayed when machine is shut down");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I shutdown the coffee machine
        actionwords.iShutdownTheCoffeeMachine();
        // Then message "" should be displayed
        actionwords.messageMessageShouldBeDisplayed("");
        status = "pass";
    }
    public void messagesAreBasedOnLanguage(String language, String readyMessage) throws Exception {
        // Tags: priority:1
        // Well, sometimes, you just get a coffee.
        scenarioSetup("Messages are based on language");
        // When I start the coffee machine using language "<language>"
        actionwords.iStartTheCoffeeMachineUsingLanguageLang(language);
        // Then message "<ready_message>" should be displayed
        actionwords.messageMessageShouldBeDisplayed(readyMessage);
        status = "pass";
    }

    @Test
    public void testMessagesAreBasedOnLanguageEnglish() throws Exception {
        messagesAreBasedOnLanguage("en", "Ready");
    }
    @Test
    public void testMessagesAreBasedOnLanguageFrench() throws Exception {
        messagesAreBasedOnLanguage("fr", "Pret");
    }
}
package com.coffeemachine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumDriverGetter {

    public WebDriver getDriver(String featureName, String testName) throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        caps.setCapability("name", "Coffee machine - " + featureName + "/" + testName);
        caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("tunnel",false);
        caps.setCapability("network",true);
        caps.setCapability("console",true);
        caps.setCapability("visual",true);
        String platformName = System.getenv("HYPEREXECUTE_PLATFORM") != null ? System.getenv("HYPEREXECUTE_PLATFORM") : "Windows 10";
        caps.setCapability("platform", platformName);
        return getRemoteDriver(caps);
    }

    private WebDriver getLocalDriver(DesiredCapabilities caps) {
        if (System.getenv("USE_HEADLESS_CHROME") != null) {
          String chromePath = System.getenv("CHROME_PATH");
          if (chromePath == null) {
            chromePath = "/usr/bin/google-chrome-stable";
          }

          final ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.setBinary(chromePath);
          chromeOptions.addArguments("--headless");
          chromeOptions.addArguments("--disable-gpu");

          caps.setJavascriptEnabled(true);
          caps.setCapability(
              ChromeOptions.CAPABILITY, chromeOptions
          );
        }

        return new ChromeDriver(caps);
    }

    private WebDriver getRemoteDriver(DesiredCapabilities caps) throws Exception {
        String username = System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY");
        URL hubUrl = null;

        try {
          hubUrl = new URL("https://" + username + ":" + authkey + "@hub.lambdatest.com/wd/hub");
        } catch (MalformedURLException e) {
          System.out.println("Invalid HUB URL");
        }

        return new RemoteWebDriver(hubUrl, caps);
    }
}

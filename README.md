# hps-java-junit + Selenium

 [![Build Status](https://travis-ci.org/hiptest/hps-java-junit-selenium.svg?branch=master)](https://travis-ci.org/hiptest/hps-java-junit-selenium)

Hiptest publisher samples with Java/JUnit + Selenium

In this repository you'll find tests generated in Junit format from [Hiptest](https://hiptest.net), using [Hiptest publisher](https://github.com/hiptest/hiptest-publisher).

The goals are:

 * to show how tests are exported in JUnit.
 * to check exports work out of the box (well, with implemented actionwords in java)

System under test
------------------

The SUT is a (not that much) simple coffee machine. You start it, you ask for a coffee and you get it, sometimes. But most of times you have to add water or beans, empty the grounds. You have an automatic expresso machine at work or at home? So you know how it goes :-)

Update tests
-------------

To update the tests:

    hiptest-publisher -c junit-selenium.conf --only=tests

The tests are generated in [``src/test/java/com/coffeemachine/``](https://github.com/hiptest/hps-java-junit-selenium/tree/master/src/test/java/com/coffeemachine)

Run tests locally with Selenium
-------------------------------

First, you must use [``maven``](http://maven.apache.org/) to build the project and run the tests:

    mvn package

The SUT implementation can be seen in [``src/web/coffee_machine.js``](https://github.com/hiptest/hps-java-junit-selenium/blob/master/java/src/web/coffee_machine.js)

By default test report is generated in ```target/surefire-reports/TEST-com.coffeemachine.*.xml```

Run tests using Selenium + Cross browser testing
------------------------------------------------

You will first need an account on [Cross browser testing](https://crossbrowsertesting.com). Once this is done, you will need to export a few variables to ensure you are using CrossBrowserTesting driver:

```shell
export REMOTE_DRIVER_USERNAME=<your username on Cross Browser Testing>
export REMOTE_DRIVER_PASSWORD=<your access key on Cross Browser Testing>
export REMOTE_DRIVER_URL=hub.crossbrowsertesting.com:80/wd/hub
export USE_REMOTE_DRIVER=true
export USE_CBT=true
```


You will also need to specify where the system under test is located, for example:

```shell
export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html
```

Run the tests as previously done:

    mvn package

This should now use CrossBrowserTesting to run the tests

Run tests using Selenium + SauceLabs
------------------------------------

You will first need an account on [SauceLabs](https://saucelabs.com). Once this is done, you will need to export a few variables to ensure you are using SauceLabs driver:

```shell
export REMOTE_DRIVER_USERNAME=<your username on SauceLabs>
export REMOTE_DRIVER_PASSWORD=<your access key on SauceLabs>
export REMOTE_DRIVER_URL=ondemand.saucelabs.com:443/wd/hub
export USE_REMOTE_DRIVER=true
export USE_SAUCELABS=true
```


You will also need to specify where the system under test is located, for example:

```shell
export COFFEE_MACHINE_LOCATION=https://hiptest.github.io/hps-java-junit-selenium/src/web/coffee_machine.html
```

Run the tests as previously done:

    mvn package

This should now use SauceLabs to run the tests

Running tests on Travis-CI
--------------------------

This repository is configured so tests are ran on Travis-CI. There are three environments: local Selenium web driver, SauceLabs and Cross Browser Testing.

If you do not want to run the tests on every plaform, edit the [``.travis.yml``](https://github.com/hiptest/hps-java-junit-selenium/blob/master/.travis.yml) file and remove the environment you do not want to get the tests ran against.

For SauceLabs and CBT, you can not use the ``REMOTE_DRIVER_USERNAME``and ``REMOTE_DRIVER_USERNAME`` variables. Instead, you should set the ``SAUCELABS_USERNAME``and ``SAUCELABS_PASSWORD`` or ``CBT_USENAME``and ``CBT_PASSWORD`` variables, depending on the platform you want to use to run your tests.

Once the build is started, you should see this kind of message in the logs:

    Running test on SauceLABS, using account; vincent-psarga
    Running test on CBT, using account; vincent%40hiptest.com

If your username does not appear, that means that the variables are not correctly set.

package com.googlecode.botdispatch.test;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.thoughtworks.selenium.Selenium;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

public class BotDispatchITest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(SeleniumGuiceberryEnv.class);

    public static void sleepShort() throws InterruptedException {
        sleepShort(1);
    }

    public static void sleepShort(int times) throws InterruptedException {
        Thread.sleep(900 * times);
    }

    @Inject
    Selenium selenium;

    @Inject
    TestBotRunner testBotRunner;

    @Test
    public void testSimpleAction() throws Exception {
        testBotRunner.startPeer();
        selenium.open("/test?name=David");
        selenium.waitForPageToLoad("30000");
        sleepShort(4);
        selenium.open("/test?name=Joshua");
        assertTrue(selenium.isTextPresent("Hello to you, David"));
        selenium.waitForPageToLoad("30000");
        sleepShort(4);
        selenium.open("/test?second=gNu");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("Hello to you, Joshua"));
        sleepShort(4);
        selenium.open("/test");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("GNU"));
        testBotRunner.stopPeer();
    }
}

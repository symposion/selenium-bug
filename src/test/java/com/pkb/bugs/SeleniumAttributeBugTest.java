package com.pkb.bugs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers
public class SeleniumAttributeBugTest {

    @Container
    private static final BrowserWebDriverContainer<?> WEB_DRIVER = new BrowserWebDriverContainer<>("selenium/standalone-chrome:4.10.0")
            .withCopyFileToContainer(MountableFile.forHostPath("./test.html"), "/test.html")
            .withCapabilities(new ChromeOptions());


    @Test
    public void testAttributeRetrieval() throws Exception {
        WebDriver driver = new RemoteWebDriver(WEB_DRIVER.getSeleniumAddress(), new ChromeOptions());

        try {
            driver
                    .navigate()
                    .to(new URL("file:///test.html"));

            var property = driver.findElement(By.id("test")).getDomProperty("href");
            var domAttribute = driver.findElement(By.id("test")).getDomAttribute("href");

            assertThat(property, equalTo(domAttribute));

        }
        finally {
            driver.close();
            driver.quit();
        }

    }
}

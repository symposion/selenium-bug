package com.pkb.bugs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SeleniumAttributeBug {

    public static void main(String[] args) throws Exception {
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());

        try {
            driver
                    .navigate()
                    .to(new URL("file:///test.html"));

            System.err.println(driver.findElement(By.id("test")).getAttribute("href"));
            System.err.println(driver.findElement(By.id("test")).getDomAttribute("href"));
            System.err.println(driver.findElement(By.id("test")).getDomProperty("href"));
        }
        finally {
            driver.close();
            driver.quit();
        }

    }
}

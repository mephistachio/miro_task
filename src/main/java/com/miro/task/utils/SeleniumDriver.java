package com.miro.task.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
    public static WebDriver driver;
    private static final Integer DEFAULT_SECONDS_WAIT = 10;


    public static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_SECONDS_WAIT, TimeUnit.SECONDS);

    }

    public static void closeDriver() {
        driver.close();
    }


    private static void setDriver(String driverPath) {
        String path = System.getenv(driverPath);
        if (path != null) {
            System.setProperty(driverPath, path);
        }
    }

    public static WebDriver resetDriver() {
        closeDriver();
        initializeDriver();
        return driver;
    }
}
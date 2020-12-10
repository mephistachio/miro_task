package com.miro.task.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotMaker {


    public static void makeScreenshot(WebDriver driver, String path) throws IOException {
        TakesScreenshot screenShot = (TakesScreenshot) driver;
        File source = screenShot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        FileUtils.copyFile(source, destination);
    }
}



package com.miro.task;

import com.miro.task.pages.HomePage;
import com.miro.task.pages.LoginPage;
import com.miro.task.params.LoginParams;
import com.miro.task.utils.ScreenShotMaker;
import com.miro.task.utils.SeleniumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.UUID;

public class MiroUserLoginTest {

    @BeforeEach
    public void setUp() {
        SeleniumDriver.initializeDriver();
    }

    @AfterEach
    public void cleanUp() {
        SeleniumDriver.closeDriver();
    }


    @Test
    public void LoginUserTest() throws InterruptedException, IOException, UnsupportedFlavorException {


        //Step 1

        WebDriver driver1 = SeleniumDriver.driver;
        LoginPage loginPage1 = new LoginPage(driver1);
        loginPage1.goToLoginPage();
        LoginParams testUser1 = new LoginParams("uimrobfizngoabupcl@miucce.com", "Qwerty123");
        loginPage1.loginToMiro(testUser1);
//Step 2
        HomePage homePage1 = new HomePage(driver1);
        homePage1.createNewBoard();
        homePage1.closePopUpWindow();
        homePage1.setBoardName("Board");
        String message = UUID.randomUUID().toString();
        homePage1.putSticker(message);
//Step 3

        String sharedUrl = homePage1.shareBoard();

        WebDriver driver2 = SeleniumDriver.resetDriver();
        LoginPage loginPage2 = new LoginPage(driver2);

        loginPage2.goToLoginPage();

        LoginParams testUser2 = new LoginParams("gnzktribxgpnuwtwyn@mhzayt.online", "Qwerty123");
        loginPage2.loginToMiro(testUser2);
        loginPage2.goToPage(sharedUrl);
        ScreenShotMaker.makeScreenshot(driver2, "./screenshot.png");

        System.out.printf("Verify Sticker Text: %s%n", message);

    }
}

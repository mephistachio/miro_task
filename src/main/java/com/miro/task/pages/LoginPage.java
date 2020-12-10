package com.miro.task.pages;

import com.miro.task.params.LoginParams;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private static final String PAGE_URL = "https://miro.com/login/";
    private static final By LOGIN_BUTTON = By.xpath("//button[@class='signup__submit']");
    private static final By WORK_EMAIL = By.xpath("//input[@id='email']");
    private static final By WORK_PWD = By.xpath("//input[@id='password']");
    private static final Integer WAIT = 2000;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToPage(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(WAIT);
    }

    public void goToLoginPage() throws InterruptedException {
        goToPage(PAGE_URL);


    }

    public String getTitle() {
        return driver.getTitle();
    }


    public void loginToMiro(LoginParams loginParams) throws InterruptedException {

        driver.findElement(WORK_EMAIL).sendKeys(loginParams.getWorkEmail());
        driver.findElement(WORK_PWD).sendKeys(loginParams.getPassword());
        driver.findElement(LOGIN_BUTTON)
                .click();
        Thread.sleep(WAIT);
    }

}


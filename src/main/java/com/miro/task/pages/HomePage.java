package com.miro.task.pages;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import sun.security.provider.SHA;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;



public class HomePage {
    private final WebDriver driver;

    private static final By NEW_BOARD = By.xpath("//*[@class='dashboard-templates__brick dashboard-templates__new-brick']");
    private static final By CREATE_BOARD_BTN = By.xpath("//button[text()='Create a shared board']");
    private static final By EDIT_BOARD_BTN = By.xpath("//*[@class='board-top-left-panel-controls']");
    private static final By EDIT_BOARD_TITLE = By.xpath("//*[@data-testid='board-info-modal-title']");
    private static final By SHARE_BOARD = By.xpath("//*[@class='board-top-share-button']");
    private static final By COPY_LINK = By.xpath("//*[@class='share-option__additional-content']/..//button[contains(@class,'copy-link-button')]");
    private static final By STICKER_BTN = By.xpath("//*[@data-plugin-id='STICKERS']");
    private static final By CLOSE_PU_BTN = By.xpath("//*[@data-autotest-id='rtbModalCloseAction']");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewBoard() throws InterruptedException {
        driver.findElement(NEW_BOARD).click();
        driver.findElement(CREATE_BOARD_BTN).click();
    }

    public void closePopUpWindow()
    {
        driver.findElement(CLOSE_PU_BTN).click();
    }

    public void setBoardName(String boardTitle) throws InterruptedException {

        driver.findElement(EDIT_BOARD_BTN).click();
        Actions action = new Actions(driver);
        WebElement title = driver.findElement(EDIT_BOARD_TITLE);
        action.moveToElement(title)
                .doubleClick()
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        title.sendKeys(boardTitle);
        action.moveToElement(title).sendKeys(Keys.ENTER).perform();
    }

    public String shareBoard() throws InterruptedException, IOException, UnsupportedFlavorException {
        driver.findElement(SHARE_BOARD).click();
        driver.findElement(COPY_LINK).click();
        return Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
    }

    public void putSticker(String message)
    {
        WebElement sticker = driver.findElement(STICKER_BTN);
        sticker.click();
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sticker, 100, 100).perform();
        actions.sendKeys(message).perform();
    }


}
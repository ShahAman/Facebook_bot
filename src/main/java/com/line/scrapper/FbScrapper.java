/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.scrapper;

import com.line.db.ScrapperDbController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Shahnewaz
 */
public class FbScrapper {
    ArrayList<String> data = null;
    ScrapperDbController datacontrol = new ScrapperDbController();
    private DriverLogs drive = new DriverLogs();
    private WebDriver driver = drive.getDriver();
    boolean checkClose = false;

    public void driverClose() {
        //driver.quit();
        driver.close();
        checkClose = true;
    }

    public void launchFb() {
        driver.navigate().to("https://www.facebook.com/");
    }
    
    public void doLogin()
    {
       // driver.navigate().to("https://www.facebook.com/");
        driver.findElement(By.id("email")).sendKeys(""); //Enter Your email in the quotes ""
        driver.findElement(By.id("pass")).sendKeys("");  //Enter Your password in the quotes ""
        driver.findElement(By.id("loginbutton")).click();
        System.out.println("Successfully logged in");
    }
    

    public void saveNotifications() throws InterruptedException {

        driver.navigate().to("https://www.facebook.com/");
        Thread.sleep(5000);
        System.out.println("Successfully logged in");
        Thread.sleep(5000);
        driver.findElement(By.id("fbNotificationsJewel")).click();
        System.out.println("Successfully Clicked notification");

        Thread.sleep(10000);

        ArrayList<WebElement> elements;
        elements = (ArrayList<WebElement>) driver.findElements(By.className("_4l_v"));
        System.out.println("Number of elements:" + elements.size());

        for (int i = 0; i < elements.size(); i++) {
            datacontrol.insertNotificationQuery(elements.get(i).getText());
        }

        if (datacontrol.executeNotifcatinBatch()) {
            System.out.println("Success");
        } else {
            System.out.println("failed");
        }

    }

    public void giveGroupPost() throws InterruptedException {

        driver.navigate().to("https://www.facebook.com/groups/529954927811259/");
        Thread.sleep(5000);

        String value = JOptionPane.showInputDialog(null, "Give me a status :");
        Thread.sleep(15000);
        WebElement element = driver.findElement(By.name("xhpc_message_text"));
        element.sendKeys(value);
        System.out.println("post reached");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@data-testid='react-composer-post-button']")).click();
        Thread.sleep(5000);

        datacontrol.insertPostQuery(driver.findElement(By.xpath("//a[@class='_5pcq']")).getAttribute("href")); //saving post link to database
        if (datacontrol.executeSaveLinks()) {
            System.out.println("Success");
        } else {
            System.out.println("failed");
        }
        Thread.sleep(5000);
    }

    public void giveGroupPostComment() throws InterruptedException {

        driver.navigate().to(datacontrol.getLastPost());
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@title='Leave a comment']")).click();//works

        String comVal = JOptionPane.showInputDialog(null, "Give me a comment :");
        Thread.sleep(15000);
        WebElement comment = driver.findElement(By.xpath("//div[@data-testid='UFI2ComposerInput/comment:rich-input']"));
        comment.sendKeys(comVal);
        //Thread.sleep(10000);
        comment.sendKeys(Keys.RETURN);

        Thread.sleep(60000);
        datacontrol.insertCommentQuery(driver.findElement(By.xpath("//a[@class='_6qw7']")).getAttribute("href")); //saving comment link to database
        if (datacontrol.executeSaveComLinks()) {
            System.out.println("Success");
        } else {
            System.out.println("failed");
        }

        Thread.sleep(5000);
    }

    public void giveGPostReply() throws InterruptedException {
        // Thread.sleep(15000);
        driver.navigate().to(datacontrol.getLastCom());
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@data-testid='UFI2Comment/reply-link']")).click();
        //Thread.sleep(10000);
        String repVal = JOptionPane.showInputDialog(null, "Give me a reply to latest comment:");
        Thread.sleep(20000);

        WebElement reply = driver.findElement(By.xpath("//div[@data-testid='UFI2ComposerInput/reply:rich-input']"));
        reply.sendKeys(repVal);
        //Thread.sleep(10000);
        reply.sendKeys(Keys.RETURN);

        Thread.sleep(5000);

        driver.close();
    }
}

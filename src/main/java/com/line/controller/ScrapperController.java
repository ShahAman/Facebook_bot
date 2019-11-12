/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.controller;

import com.line.db.DataController;
import com.line.model.FriendsModel;
import com.line.scrapper.DriverLogs;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Shahnewaz
 */
public class ScrapperController {
    private DriverLogs drive = new DriverLogs();
    private static WebDriver driver;
    private static boolean checklogin = false;
    private static boolean inFb = false;
    private ArrayList<String> friendLinks = null;
    ArrayList<FriendsModel> fModel;
    DataController dataControl = new DataController();
    private static boolean launched = false;
    
    
     public void lauchFB()
    {
       this.driver =  drive.getDriver();
       driver.navigate().to("https://www.facebook.com/");
       inFb = true;
    }
    
    public void doLogin()
    {
        /*if (inFb == false) {
            this.driver =  drive.getDriver();
            driver.navigate().to("https://www.facebook.com/");
            inFb = true;
        } else {
            if (checklogin == false) {
                driver.findElement(By.id("email")).sendKeys("shahnewazmd77@gmail.com");
                driver.findElement(By.id("pass")).sendKeys("625039");
                driver.findElement(By.id("loginbutton")).click();
                System.out.println("Successfully logged in");
                checklogin = true;
            } else {
                JOptionPane.showMessageDialog(null, "Already logged in");
            }
        }*/
        if(driver == null){
            this.driver =  drive.getDriver();    
        }
        driver.navigate().to("https://www.facebook.com/");
        By locator = By.xpath("//a[@title='Profile']");
        if(driver.findElements(locator).isEmpty()){
        driver.findElement(By.id("email")).sendKeys("shahnewazmd77@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("625039");
        driver.findElement(By.id("loginbutton")).click();
        System.out.println("Successfully logged in");
        inFb = true;
        }
        else
            JOptionPane.showMessageDialog(null, "Already logged in..");
    }
    
    public void closeBrowser()
    {
        if(inFb==true){
        driver.close();
        inFb = false;
        }
        else
           JOptionPane.showMessageDialog(null, "Already closed"); 
    }
    
    public void showFriends() throws InterruptedException
    {
         if(driver == null){
            this.driver =  drive.getDriver();    
        }
        driver.navigate().to("https://www.facebook.com/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@title='Profile']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//a[@data-tab-key='friends']")).click();
        Thread.sleep(10000);
        // ul class uiList _262m _4kg
        ArrayList<WebElement> elements;
        
        //elements = (ArrayList<WebElement>) driver.findElements(By.xpath("//ul[@class='uiList _262m _4kg']")); div class _5h60 _30f
        //elements = (ArrayList<WebElement>) driver.findElements(By.xpath("//div[@class='_3i9']"));
        elements = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
        System.out.println("Number of elements:" + elements.size());
        
       for(int i=0; i<elements.size(); i++)
        {
            dataControl.insertFQuery(elements.get(i).getText(), elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
            
        }
       
       
       /*String name, links;
       
       for(int i=0; i<elements.size(); i++)
        {
            name = elements.get(i).getText();
            links= elements.get(i).findElement(By.tagName("a")).getAttribute("href");
            fModel.add(new FriendsModel(name,links));
        }
       
       dataControl.insertQuery(fModel);
       System.out.println("inserted");
       */ 


       dataControl.executeBatch1();
       Thread.sleep(10000);
       //fbLaucher.getDataTable().setModel(dataControl.showFriendsData());
       inFb = true;
       
    }
    
    //div class _2fvv
    public void showGroups() throws InterruptedException
    {
          if(driver == null){
            this.driver =  drive.getDriver();    
        }
        driver.navigate().to("https://www.facebook.com/");
        inFb = true;
        Thread.sleep(15000);
        driver.findElement(By.xpath("//a[@title='Profile']")).click();
        String id = driver.findElement(By.xpath("//a[@title='Profile']")).getAttribute("href");
        System.out.println(id);
        String grouplink = id+"&sk=groups";
        Thread.sleep(5000);
        driver.navigate().to(grouplink);
        Thread.sleep(5000);
        ArrayList<WebElement> elements;
        elements = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='mbs fwb']"));
        System.out.println("Number of elements:" + elements.size());
        
        for(int i=0; i<elements.size(); i++)
        {
            //dataControl.insertFQuery(elements.get(i).getText(), elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
           // System.out.println(elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
            dataControl.insertGrQuery(elements.get(i).getText(), elements.get(i).findElement(By.tagName("a")).getAttribute("href"));
        }
        
        driver.findElement(By.xpath("//div[@data-click='home_icon']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//i[@class='img sp_hmhj_VzokAD sx_7becc2']")).click();
        Thread.sleep(10000);
        
        ArrayList<WebElement> MyGroups;
        MyGroups = (ArrayList<WebElement>) driver.findElements(By.xpath("//*[@class='_2yau']"));
        System.out.println("Number of elements:" + MyGroups.size());
        
        
        for(int i=1; i<MyGroups.size(); i++)
        {
            dataControl.insertGrQuery(MyGroups.get(i).getText(), MyGroups.get(i).getAttribute("href"));
           // System.out.println(MyGroups.get(i).getAttribute("href"));
            
        }
        
        dataControl.executeInBatch();
        Thread.sleep(10000);

       // fbLaucher.getDataTable().setModel(dataControl.showGroupData());
        
        
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.scrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Shahnewaz
 */
public class DriverLogs {
     
    
    public WebDriver getDriver()
    {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
       // options.add_argument("--disable-notifications")
       options.addArguments("--disable-notifications");
       //options.addArguments("--user-data-dir=C:\\Users\\line\\AppData\\Local\\Google\\Chrome\\User Data\\Default"); C:\facebookTest
        options.addArguments("--user-data-dir=C:\\Users\\Aman's PC\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
//options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}

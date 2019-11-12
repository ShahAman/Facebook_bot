/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.controller;

import com.line.db.DataController;
import com.line.model.FriendsModel;
import com.line.scrapper.DriverLogs;
import com.line.scrapper.FbScrapper;
import com.line.ui.FBLaucher;
import com.line.ui.Post;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Shahnewaz
 */
public class FBLauncherController {

    private FBLaucher fbLaucher;
   ScrapperController scCon = new ScrapperController();
    DataController dataControl = new DataController();
    private Post post = new Post();

    PostController postController;

    public FBLauncherController(FBLaucher fbLaucher) {
        this.fbLaucher = fbLaucher;

    }

    public void initLaucher() {
        fbLaucher.setVisible(true);

    }

    public void lauchFB() {
        scCon.lauchFB();
    }

    public void doLogin() {
        scCon.doLogin();
    }

    public void closeBrowser() {
        scCon.closeBrowser();
    }

    public void showFriends() throws InterruptedException {
        scCon.showFriends();
        fbLaucher.getDataTable().setModel(dataControl.showFriendsData());
    }

    //div class _2fvv
    public void showGroups() throws InterruptedException {
        scCon.showGroups();

        fbLaucher.getDataTable().setModel(dataControl.showGroupData());

    }

    public void goToPost() {
        postController = new PostController(post);
        postController.initPost();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.main;

import com.line.controller.FBLauncherController;
import com.line.ui.FBLaucher;

/**
 *
 * @author Shahnewaz
 */
public class App {
    public static void main(String[] args) {
       FBLaucher fbLaucher = new FBLaucher();
       FBLauncherController fbLauncherController = new FBLauncherController(fbLaucher);
       fbLauncherController.initLaucher();
       
    }
}

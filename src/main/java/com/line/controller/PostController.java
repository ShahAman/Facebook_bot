/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.controller;

import com.line.db.DataController;
import com.line.ui.Post;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Shahnewaz
 */
public class PostController {
    
    private Post post;
    static String x = null;
    static String name = null;
    DataController dataControl = new DataController();
    ArrayList<String> getNames = new ArrayList<>();
    ScrapperController scCon = new ScrapperController();

    String link = null;

    public PostController(Post post) {

        this.post = post;
    }

    public void initPost() {

        post.setVisible(true);
        post.getTableName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = String.valueOf(post.getTableName.getSelectedItem());
                System.out.println(x);
                getNames = dataControl.showCombodata(x);

                post.setNameData(getNames);
            }
        });
        post.getName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = String.valueOf(post.getName.getSelectedItem());
                System.out.println(name);
                //getNames = dataControl.showCombodata(x);
                link = dataControl.getLinks(x, name);
                System.out.println(link);
                post.link.setText(link);
            }
        });
    }
    
     public void postData() {
        

    }

}

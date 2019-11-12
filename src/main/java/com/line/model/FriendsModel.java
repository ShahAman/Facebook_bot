/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.model;

/**
 *
 * @author Shahnewaz
 */
public class FriendsModel {
    private String name;
    private String profileLinks;
    
    public FriendsModel(){}
    
    public FriendsModel(String name, String profileLinks)
    {
        this.name = name;
        this.profileLinks = profileLinks;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getProfileLinks()
    {
        return profileLinks;
    }
}


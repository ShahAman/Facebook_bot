/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Shahnewaz
 */
public class ScrapperDbController {
    ArrayList<String> insertNotificationQueries=new ArrayList<>();
    ArrayList<String> insertPostQueries=new ArrayList<>();
    ArrayList<String> insertCommentQueries=new ArrayList<>();
    
    public void insertNotificationQuery(String data)
    {
        String sql= "INSERT INTO notifications(n_data) VALUES ('"+data+"')";
        insertNotificationQueries.add(sql);
    }
    
    public void insertPostQuery(String data)
    {
       String sql= "INSERT INTO status_links(status_link) VALUES ('"+data+"')";
       insertPostQueries.add(sql);
    }
    
    public void insertCommentQuery(String data)
    {
       String sql= "INSERT INTO comment_links(comment_link) VALUES ('"+data+"')";
       insertCommentQueries.add(sql);
    }
    
    public String getLastPost()
    {
        String link = null;
        try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt = con.createStatement();
            String query = "SELECT status_link FROM `status_links` WHERE id = (SELECT MAX(id) FROM `status_links`)";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
             link = rs.getString("status_link");
            }
            //JOptionPane.showMessageDialog(null, query);
            return link;
        
        } catch (SQLException ex) {
            //Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getLastCom()
    {
        String link = null;
        try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt = con.createStatement();
            String query = "SELECT comment_link FROM `comment_links` WHERE id = (SELECT MAX(id) FROM `comment_links`)";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
             link = rs.getString("comment_link");
            }
            //JOptionPane.showMessageDialog(null, query);
            return link;
        
        } catch (SQLException ex) {
            //Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
     public boolean executeNotifcatinBatch() {
        try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt = con.createStatement();
            for(String query: insertNotificationQueries)
            {  
                //JOptionPane.showMessageDialog(null, query);
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public boolean executeSaveLinks() {
       try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt= con.createStatement();
            for(String query: insertPostQueries)
            {  
                //JOptionPane.showMessageDialog(null, query);
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public boolean executeSaveComLinks() {
       try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt= con.createStatement();
            for(String query: insertCommentQueries)
            {  
               // JOptionPane.showMessageDialog(null, query);
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(DataControl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}

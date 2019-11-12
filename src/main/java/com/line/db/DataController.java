/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.db;

import com.line.model.FriendsModel;
import com.line.ui.FBLaucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shahnewaz
 */
public class DataController {
    ArrayList<String> insertQueries=new ArrayList<>();
    ArrayList<String> insertGrQueries=new ArrayList<>();
    ArrayList<FriendsModel> fModel = new ArrayList<>();
    ArrayList<String> getNames=new ArrayList<>();
    String link = null;
    
    private PreparedStatement pst;
    private ResultSet rs;
   private Statement stmt;
    
    public void insertQuery(ArrayList<FriendsModel> fModel)
    {
       this.fModel =fModel;
        for(int i=0; i<fModel.size(); i++){
        String sql= "INSERT INTO friends(name,links) VALUES ('"+fModel.get(i).getName()+
                "','"+fModel.get(i).getProfileLinks()+"')";
        insertQueries.add(sql);
        }
    }
    
    
    public void insertFQuery(String name, String links)
    {
       
        String sql= "INSERT INTO friends(name,links) VALUES ('"+name+
                "','"+links+"')";
        
        insertQueries.add(sql);
        
    }
    
    public void insertGrQuery(String name, String links)
    {
       
        String sql= "INSERT INTO groups(name,links) VALUES ('"+name+
                "','"+links+"')";
        
        insertGrQueries.add(sql);
        
    }
    
    
    public boolean executeBatch1() {
        try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt = con.createStatement();
            for(String query: insertQueries)
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
    
    public boolean executeInBatch() {
        try {
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            Statement stmt = con.createStatement();
            for(String query: insertGrQueries)
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
    
    
    
    public DefaultTableModel showGroupData(){
        // this.fb = new FBLaucher();
         
         DefaultTableModel model = new DefaultTableModel(new String[] {"Name", "Links"},0)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
            }};
        try{
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            String query = "SELECT DISTINCT name,links FROM groups";
            pst= con.prepareStatement(query);
            rs=pst.executeQuery();

            while (rs.next()) {
               // String id= rs.getString("id");
                String name = rs.getString("name");
                String links = rs.getString("links");
                model.addRow(new Object[]{name, links});
            }
            //fb.getDataTable().setModel(model);
            
            return model;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public DefaultTableModel showFriendsData(){
        // this.fb = new FBLaucher();
         
         DefaultTableModel model = new DefaultTableModel(new String[] {"Name", "Links"},0)
            {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
            }};
        try{
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            String query = "SELECT DISTINCT name,links FROM friends";
            pst= con.prepareStatement(query);
            rs=pst.executeQuery();

            while (rs.next()) {
               // String id= rs.getString("id");
                String name = rs.getString("name");
                String links = rs.getString("links");
                model.addRow(new Object[]{name, links});
            }
            //fb.getDataTable().setModel(model);
            
            return model;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public ArrayList<String> showCombodata(String tableName)
    {
        getNames.clear();
         try{
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            String query = "SELECT DISTINCT name,links FROM "+tableName;
            pst= con.prepareStatement(query);
            rs=pst.executeQuery();

            while (rs.next()) {
               getNames.add(rs.getString("name"));
            }
            //fb.getDataTable().setModel(model);
            
            return getNames;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public String getLinks(String tableName, String name)
    {
       
         try{
            Connection con=DBConnection.getConnectorManager().getDBConnecton();
            stmt = con.createStatement();
            String query = "SELECT DISTINCT links FROM `"+tableName+"` WHERE name = '"+name+"'";
            
            rs=stmt.executeQuery(query);

            while (rs.next()) {
               link = rs.getString("links");
            }
            //fb.getDataTable().setModel(model);
            
            return link;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}

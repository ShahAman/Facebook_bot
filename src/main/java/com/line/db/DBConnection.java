/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.line.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shahnewaz
 */
public class DBConnection {
    private static DBConnection connectorManager = null;
    private Connection connection = null;


    public void createConnection() throws SQLException, ClassNotFoundException {

        connection =  DriverManager.
                    getConnection("jdbc:mysql://localhost/facebookbot?userUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
    }

    public Connection getDBConnecton() throws SQLException {
        if (connection == null) {
            try {
                createConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return connection;
    }

     public static DBConnection getConnectorManager() {
        if (connectorManager == null) {
            connectorManager = new DBConnection();
        }

        return connectorManager;
    }
    
}

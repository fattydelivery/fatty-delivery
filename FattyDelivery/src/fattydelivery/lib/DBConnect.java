/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fattydelivery.lib;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DBConnect {
    public static Connection connect() {
        String username = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/fattydelivery";
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            if(con != null) {
                System.out.println("Connect to MySQL successfully");
            } else {
                System.out.println("Connect to MySQL failed");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return con;
        }
    }
    public static void disconnect(Connection con) {
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void disconnect(Connection con, PreparedStatement stm) {
        if(stm!=null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

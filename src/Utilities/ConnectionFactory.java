/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author 1styrGroupC
 */
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String DB_URL = "jdbc:mysql://localhost:3306/electricity_billing_system?verifyServerCertificate=false&useSSL=true";
    public static final String USER = "root";
    public static final String PASS = "";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public ConnectionFactory() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conn;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}

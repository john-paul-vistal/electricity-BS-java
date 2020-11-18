/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interface.*;
import Models.*;
import Utilities.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author 1styrGroupC
 */
public class AdminController implements AdminInterface {

    public List<Admin> adminList = new ArrayList<>();
    public Admin admin = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        adminList.clear();
        String query = "SELECT * FROM admin ORDER BY lName ASC";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            admin = new Admin(rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password"),
                    rs.getString("fName"),
                    rs.getString("lName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getInt("level")
            );
            adminList.add(admin);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return adminList;
    }

    @Override
    public List<Admin> getAdmin(String search) throws SQLException {
        adminList.clear();
        String query = "SELECT * FROM admin WHERE (fName like '%" + search + "%' OR 	lName like '%" + search + "%') ORDER BY lName ASC";
        ResultSet rs = null;
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            admin = new Admin(rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password"),
                    rs.getString("fName"),
                    rs.getString("lName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getInt("level")
            );
            adminList.add(admin);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return adminList;
    }
    
    
    @Override
    public Admin getAdmin(int id) throws SQLException {
      String query = "SELECT * FROM admin WHERE id = '" + id + "'";
        ResultSet rs = null;
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            admin = new Admin(rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("password"),
                    rs.getString("fName"),
                    rs.getString("lName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getInt("level")
            );
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return admin;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admin (id,userName,password,fName,lName,mName,contactNumber,level)VALUES('" + admin.getId() + "','" + admin.getUserName() + "', '" + admin.getPassword() + "','" + admin.getfName() + "','" + admin.getlName() + "','" + admin.getmName() + "','" + admin.getContactNumber() + "','" + admin.getLevel() + "')";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
    }

    @Override
    public void updateAdmin(Admin admin) throws SQLException {
        String query = "UPDATE admin SET userName= '" + admin.getUserName()+ "', password= '" + admin.getPassword()+ "',fName= '" + admin.getfName()+ "',lName= '" + admin.getlName()+ "',mName= '" + admin.getmName()+ "',contactNumber= '" + admin.getContactNumber()+ "',level= '" + admin.getLevel()+ "'WHERE id = '" + admin.getId()+ "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }

    @Override
    public void deleteAdmin(int id) throws SQLException {
        String query = "DELETE FROM admin WHERE id ='" + id + "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }



}

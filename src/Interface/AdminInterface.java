/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Models.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author 1styrGroupC
 */
public interface AdminInterface {
    public List<Admin> getAllAdmins() throws SQLException;
    public List<Admin> getAdmin(String search) throws SQLException;
    public Admin getAdmin(int id) throws SQLException;
    public void addAdmin(Admin admin) throws SQLException;
    public void updateAdmin(Admin admin) throws SQLException;
    public void deleteAdmin(int id) throws SQLException;
}

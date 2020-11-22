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
public interface BillInterface {
    public List<Bill> getAllBill() throws SQLException;
    public List<Bill> getAllUnpaidBill() throws SQLException;
    public List<Bill> searchBill(String electricityLineNo) throws SQLException;
    public List<Bill> searchUnpaidBill(String electricityLineNo) throws SQLException;
    public Bill getBill(int id) throws SQLException;
    public void addBill(Bill bill) throws SQLException;
    public void updateBill(Bill bill) throws SQLException;
    public void deleteBill(int id) throws SQLException;
}

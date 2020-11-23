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
public class BillController implements BillInterface {

    public List<Bill> billList = new ArrayList<>();
    public Bill bill = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public List<Bill> getAllBill() throws SQLException {
        billList.clear();
        String query = "SELECT * FROM bills ORDER BY id ASC";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            bill = new Bill(rs.getInt("id"),
                    rs.getInt("electricityLineNo"),
                    rs.getInt("reading"),
                    rs.getDouble("amountDue"),
                    rs.getInt("status"),
                    rs.getString("recordedDate"),
                    rs.getString("dueDate"),
                    rs.getInt("recordedBy")
            );
            billList.add(bill);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return billList;
    }

    @Override
    public List<Bill> searchBill(String electricityLineNo) throws SQLException {
        billList.clear();
        String query = "SELECT * FROM bills WHERE electricityLineNo = '" + electricityLineNo + "'";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            bill = new Bill(rs.getInt("id"),
                    rs.getInt("electricityLineNo"),
                    rs.getInt("reading"),
                    rs.getDouble("amountDue"),
                    rs.getInt("status"),
                    rs.getString("recordedDate"),
                    rs.getString("dueDate"),
                    rs.getInt("recordedBy")
            );
            billList.add(bill);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return billList;
    }
    @Override
    public List<Bill> searchUnpaidBill(String electricityLineNo) throws SQLException {
        billList.clear();
        String query = "SELECT * FROM bills WHERE electricityLineNo = '" + electricityLineNo + "' AND status != 1";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            bill = new Bill(rs.getInt("id"),
                    rs.getInt("electricityLineNo"),
                    rs.getInt("reading"),
                    rs.getDouble("amountDue"),
                    rs.getInt("status"),
                    rs.getString("recordedDate"),
                    rs.getString("dueDate"),
                    rs.getInt("recordedBy")
            );
            billList.add(bill);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return billList;
    }

    @Override
    public Bill getBill(int id) throws SQLException {
        billList.clear();
        String query = "SELECT * FROM bills WHERE id = " + id + "";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            bill = new Bill(rs.getInt("id"),
                    rs.getInt("electricityLineNo"),
                    rs.getInt("reading"),
                    rs.getDouble("amountDue"),
                    rs.getInt("status"),
                    rs.getString("recordedDate"),
                    rs.getString("dueDate"),
                    rs.getInt("recordedBy")
            );
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return bill;
    }

    @Override
    public void addBill(Bill bill) throws SQLException {
        String query = "INSERT INTO bills (id,electricityLineNo,reading,amountDue,status,recordedDate,dueDate,recordedBy)VALUES('" + bill.getId() + "','" + bill.getElectricityLineNo() + "', '" + bill.getReading() + "','" + bill.getAmountDue() + "','" + bill.getStatus() + "','" + bill.getRecordedDate() + "','" + bill.getDueDate() + "','" + bill.getRecordedBy() + "')";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
    }

    @Override
    public void updateBill(Bill bill) throws SQLException {
        String query = "UPDATE bills SET electricityLineNo = '" + bill.getElectricityLineNo() + "', reading= '" + bill.getReading() + "',amountDue= '" + bill.getAmountDue() + "',status= '" + bill.getStatus() + "',recordedDate= '" + bill.getDueDate() + "',dueDate= '" + bill.getDueDate() + "',recordedBy= '" + bill.getRecordedBy() + "'WHERE id = '" + bill.getId() + "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }

    @Override
    public void deleteBill(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bill> getAllUnpaidBill() throws SQLException {
        billList.clear();
        String query = "SELECT * FROM bills WHERE status != 1 ORDER BY id ASC";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            bill = new Bill(rs.getInt("id"),
                    rs.getInt("electricityLineNo"),
                    rs.getInt("reading"),
                    rs.getDouble("amountDue"),
                    rs.getInt("status"),
                    rs.getString("recordedDate"),
                    rs.getString("dueDate"),
                    rs.getInt("recordedBy")
            );
            billList.add(bill);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return billList;
    }

}

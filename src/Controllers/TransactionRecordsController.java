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
public class TransactionRecordsController implements TransactionRecordInterface {

    public List<TransactionRecords> transactionList = new ArrayList<>();
    public TransactionRecords transaction = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public List<TransactionRecords> getAllTransactioin() throws SQLException {
//        adminList.clear();
//        String query = "SELECT * FROM admin ORDER BY lName ASC";
//
//        conn = ConnectionFactory.getConnection();
//        state = conn.createStatement();
//        rs = state.executeQuery(query);
//        while (rs.next()) {
//            admin = new Admin(rs.getInt("id"),
//                    rs.getString("userName"),
//                    rs.getString("password"),
//                    rs.getString("fName"),
//                    rs.getString("lName"),
//                    rs.getString("mName"),
//                    rs.getString("contactNumber"),
//                    rs.getInt("level")
//            );
//            adminList.add(admin);
//        }
//        DBUtil.close(conn);
//        DBUtil.close(state);
//        DBUtil.close(rs);
//
        return transactionList;
    }

    @Override
    public void addTransactionRecord(TransactionRecords transaction) throws SQLException {
        String query = "INSERT INTO transactionrecords (id,electricityLine,billNumber,totalAmount,renderedCash,cashChange,penalty,recordedby,recordedDate) VALUES('" + transaction.getId() + "','" + transaction.getLineNo()+ "', '" + transaction.getBillNumber()+ "','" + transaction.getTotalAmount()+ "','" + transaction.getCashRendered()+ "','" + transaction.getCashChange()+"','" + transaction.getPenalty()+ "','" + transaction.getRecordedBy()+ "','" + transaction.getRecordedDate()+ "')";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
    }

}

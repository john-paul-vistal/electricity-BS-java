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
import java.util.HashMap;

/**
 *
 * @author 1styrGroupC
 */
public class TransactionRecordsController implements TransactionRecordInterface {

    public List<TransactionRecords> transactionList = new ArrayList<>();
    public HashMap<Integer, Double> monthlyIncome = new HashMap<>();
    public TransactionRecords transaction = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public List<TransactionRecords> getAllTransactioin() throws SQLException {
        transactionList.clear();
        String query = "SELECT * FROM transactionrecords ORDER BY recordedDate ASC";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            transaction = new TransactionRecords(rs.getInt("id"),
                    rs.getInt("electricityLine"),
                    rs.getInt("billNumber"),
                    rs.getInt("totalAmount"),
                    rs.getInt("renderedCash"),
                    rs.getInt("cashChange"),
                    rs.getInt("penalty"),
                    rs.getInt("recordedBy"),
                    rs.getString("recordedDate")
            );
            transactionList.add(transaction);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return transactionList;
    }

    @Override
    public void addTransactionRecord(TransactionRecords transaction) throws SQLException {
        String query = "INSERT INTO transactionrecords (id,electricityLine,billNumber,totalAmount,renderedCash,cashChange,penalty,recordedby,recordedDate) VALUES('" + transaction.getId() + "','" + transaction.getLineNo() + "', '" + transaction.getBillNumber() + "','" + transaction.getTotalAmount() + "','" + transaction.getCashRendered() + "','" + transaction.getCashChange() + "','" + transaction.getPenalty() + "','" + transaction.getRecordedBy() + "','" + transaction.getRecordedDate() + "')";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
    }

    @Override
    public HashMap<Integer, Double> getMonthlyIncome() throws SQLException {

        transactionList.clear();
        String query = "SELECT DISTINCT(YEAR(`recordedDate`)) as year , SUM(`totalAmount`) as total FROM transactionrecords GROUP BY year";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            monthlyIncome.put(rs.getInt("year"), rs.getDouble("total"));
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
        return monthlyIncome;
    }
    
}


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
public interface TransactionRecordInterface {
    public List<TransactionRecords> getAllTransactioin() throws SQLException;
    public void addTransactionRecord(TransactionRecords transaction) throws SQLException;
    public HashMap<Integer, Double> getMonthlyIncome() throws SQLException;
}

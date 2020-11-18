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
public interface HouseholdInterface {
    public List<Household> getAllHousehold() throws SQLException;
    public List<Household> getHouseHold(String search) throws SQLException;
    public Household getHousehold(int id) throws SQLException;
    public void addHousehold(Household house) throws SQLException;
    public void updateHousehold(Household house) throws SQLException;
    public void deleteHousehold(int electricityLineNo) throws SQLException;
}

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
public class HouseholdController implements HouseholdInterface {

    public List<Household> householdList = new ArrayList<>();
    public Household household = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public List<Household> getAllHousehold() throws SQLException {
        householdList.clear();
        String query = "SELECT * FROM household ORDER BY fName ASC ";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            household = new Household(rs.getInt("electricityLineNo"),
                    rs.getString("lName"),
                    rs.getString("fName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getString("address"),
                    rs.getString("streetNumber")
            );
            householdList.add(household);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return householdList;
    }

    @Override
    public List<Household> getHouseHold(String search) throws SQLException {
        householdList.clear();
        String query = "SELECT * FROM household WHERE (fName like '%" + search + "%' OR lName like '%" + search + "%') ORDER BY fName ASC ";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            household = new Household(rs.getInt("electricityLineNo"),
                    rs.getString("lName"),
                    rs.getString("fName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getString("address"),
                    rs.getString("streetNumber")
            );
            householdList.add(household);
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return householdList;
    }

    @Override
    public Household getHousehold(int householdID) throws SQLException {
        String query = "SELECT * FROM household WHERE electricityLineNo = '" + householdID + "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            household = new Household(rs.getInt("electricityLineNo"),
                    rs.getString("lName"),
                    rs.getString("fName"),
                    rs.getString("mName"),
                    rs.getString("contactNumber"),
                    rs.getString("address"),
                    rs.getString("streetNumber")
            );
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return household;
    }

    @Override
    public void addHousehold(Household house) throws SQLException {
        String query = "INSERT INTO household (electricityLineNo,fName,lName,mName,contactNumber,address,streetNumber)VALUES('" + house.getElectricityLineNo() + "','" + house.getfName() + "','" + house.getlName() + "','" + house.getmName() + "','" + house.getContactNumber() + "','" + house.getAddress() + "','" + house.getStreetNumber() + "')";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);
    }

    @Override
    public void updateHousehold(Household house) throws SQLException {
        String query = "UPDATE household SET fName= '" + house.getfName() + "', lName= '" + house.getlName() + "', mName= '" + house.getmName() + "', contactNumber= '" + house.getContactNumber()+ "', address= '" + house.getAddress()+ "',streetNumber = '"+house.getStreetNumber()+"'WHERE electricityLineNo = '"+house.getElectricityLineNo()+"'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }

    @Override
    public void deleteHousehold(int electricityLineNo) throws SQLException {
        String query = "DELETE FROM household WHERE electricityLineNo ='" + electricityLineNo + "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }

}

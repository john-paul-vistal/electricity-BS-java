/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interface.SettingsInterface;
import Utilities.ConnectionFactory;
import Utilities.DBUtil;
import Models.Settings;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1styrGroupC
 */
public class SettingsControllers implements SettingsInterface {

    public List<Settings> settingsList = new ArrayList<>();
    public Settings settings = null;
    private Connection conn;
    private Statement state;
    private ResultSet rs = null;

    @Override
    public Settings getElectricityRate() throws SQLException {
        String query = "SELECT * FROM settings WHERE name = 'electricityRate'";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            settings = new Settings(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("amount")
            );
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return settings;
    }

    @Override
    public Settings getVat() throws SQLException {
        String query = "SELECT * FROM settings WHERE name = 'vat'";

        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            settings = new Settings(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("amount")
            );
        }
        DBUtil.close(conn);
        DBUtil.close(state);
        DBUtil.close(rs);

        return settings;
    }

    @Override
    public void updateSettings(Settings settings) throws SQLException {
        String query = "UPDATE settings SET amount= '" + settings.getValue() + "',name= '" + settings.getName() + "' WHERE name = '" + settings.getName() + "'";
        conn = ConnectionFactory.getConnection();
        state = conn.createStatement();
        state.executeUpdate(query);
        DBUtil.close(conn);
        DBUtil.close(state);
    }

}

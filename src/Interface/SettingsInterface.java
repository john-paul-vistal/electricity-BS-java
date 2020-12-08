/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Models.Settings;
import java.sql.SQLException;

/**
 *
 * @author 1styrGroupC
 */
public interface SettingsInterface {
    public Settings getElectricityRate() throws SQLException;
    public Settings getVat() throws SQLException;
    public void updateSettings(Settings settings) throws SQLException;
}

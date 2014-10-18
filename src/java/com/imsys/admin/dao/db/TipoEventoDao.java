/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.TipoEvento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian
 */
public class TipoEventoDao {

    public ArrayList<TipoEvento> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM M_TIPOSEVE ORDER BY NCODTIPOEVE ASC ";
        ArrayList<TipoEvento> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                TipoEvento lec = new TipoEvento();
                lec.setNccodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                lec.setVcdeseve(result.getString("VCDESEVE"));
                lec.setLaaplicacaja(Boolean.parseBoolean(result.getString("LAPLICACAJA")) ? "Si" : "No");
                lec.setLaaplicamed(Boolean.parseBoolean(result.getString("LAPLICAMED")) ? "Si" : "No");

                results.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoEventoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            c.close();
            if (result != null) {
                result.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return results;
    }

    public TipoEvento loadByCode(Connection c, String code) throws SQLException {
        String sql = "SELECT * FROM M_TIPOSEVE WHERE (NCODTIPOEVE = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        TipoEvento valueObject = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, code);
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject = new TipoEvento();
                valueObject.setNccodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                valueObject.setVcdeseve(result.getString("VCDESEVE"));
                valueObject.setLaaplicacaja(Boolean.parseBoolean(result.getString("LAPLICACAJA")) ? "Si" : "No");
                valueObject.setLaaplicamed(Boolean.parseBoolean(result.getString("LAPLICAMED")) ? "Si" : "No");
            }
            return valueObject;
        } finally {
            c.close();
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}

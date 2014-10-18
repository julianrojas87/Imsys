/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Politica;
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
public class PoliticaDao {
    public ArrayList<Politica> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM M_POLIROLL ORDER BY VCCODRUTINA ASC ";
        ArrayList<Politica> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                Politica valueObject = new Politica();
                valueObject.setVccodrutina(result.getString("VCCODRUTINA"));
                valueObject.setLopcion(Boolean.parseBoolean(result.getString("LOPCION")) ? "Si" : "No");
                valueObject.setLgrabar(Boolean.parseBoolean(result.getString("LGRABAR")) ? "Si" : "No");
                valueObject.setLborrar(Boolean.parseBoolean(result.getString("LBORRAR")) ? "Si" : "No");
                valueObject.setLimprimir(Boolean.parseBoolean(result.getString("LIMPRIMIR")) ? "Si" : "No");
                valueObject.setLsimex(Boolean.parseBoolean(result.getString("LSIMEX")) ? "Si" : "No");

                results.add(valueObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PoliticaDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Politica loadbyCode(Connection c, String code) throws SQLException {
        String sql = "SELECT * FROM M_POLIROLL WHERE (VCCODRUTINA = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        Politica valueObject = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, code);
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject = new Politica();
                valueObject.setVccodrutina(result.getString("VCCODRUTINA"));
                valueObject.setLopcion(Boolean.parseBoolean(result.getString("LOPCION")) ? "Si" : "No");
                valueObject.setLgrabar(Boolean.parseBoolean(result.getString("LGRABAR")) ? "Si" : "No");
                valueObject.setLborrar(Boolean.parseBoolean(result.getString("LBORRAR")) ? "Si" : "No");
                valueObject.setLimprimir(Boolean.parseBoolean(result.getString("LIMPRIMIR")) ? "Si" : "No");
                valueObject.setLsimex(Boolean.parseBoolean(result.getString("LSIMEX")) ? "Si" : "No");
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

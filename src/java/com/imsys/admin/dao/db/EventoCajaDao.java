/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.EventoCaja;
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
public class EventoCajaDao {
    
    public ArrayList<EventoCaja> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM MOV_EVECAJA ORDER BY DFECHAEVE ASC ";
        ArrayList<EventoCaja> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                EventoCaja event = new EventoCaja();
                event.setDfechaeve(result.getString("DFECHAEVE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoCajaDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<EventoCaja> getByFecha(Connection c, String fecha) throws SQLException {
        String sql = "SELECT * FROM MOV_EVECAJA WHERE (DFECHAEVE = ? ) ";
        ArrayList<EventoCaja> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, fecha);
            result = stmt.executeQuery();
            
            while (result.next()) {
                EventoCaja event = new EventoCaja();
                event.setDfechaeve(result.getString("DFECHAEVE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoCajaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            c.close();
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return results;
    }
    
    public ArrayList<EventoCaja> getByCodigo(Connection c, int codigo) throws SQLException {
        String sql = "SELECT * FROM MOV_EVECAJA WHERE (NCODTIPOEVE = ? ) ";
        ArrayList<EventoCaja> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, codigo);
            result = stmt.executeQuery();
            
            while (result.next()) {
                EventoCaja event = new EventoCaja();
                event.setDfechaeve(result.getString("DFECHAEVE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoCajaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            c.close();
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return results;
    }
}

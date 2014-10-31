/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.EventoMedidor;
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
public class EventoMedidorDao {
    
    public ArrayList<EventoMedidor> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM MOV_EVEMEDIDOR ORDER BY DFECHAEVEMED DESC ";
        ArrayList<EventoMedidor> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                EventoMedidor event = new EventoMedidor();
                event.setDfechaevemed(result.getString("DFECHAEVEMED"));
                event.setVcserie(result.getString("VCSERIE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                event.setVcdescripcion(result.getString("VCDESCRIPCION"));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoMedidorDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<EventoMedidor> getByFecha(Connection c, String fecha) throws SQLException {
        String sql = "SELECT * FROM MOV_EVEMEDIDOR WHERE (DFECHAEVEMED = ? ) ";
        ArrayList<EventoMedidor> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, fecha);
            result = stmt.executeQuery();
            
            while (result.next()) {
                EventoMedidor event = new EventoMedidor();
                event.setDfechaevemed(result.getString("DFECHAEVEMED"));
                event.setVcserie(result.getString("VCSERIE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                event.setVcdescripcion(result.getString("VCDESCRIPCION"));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoMedidorDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<EventoMedidor> getBySerie(Connection c, String serie) throws SQLException {
        String sql = "SELECT * FROM MOV_EVEMEDIDOR WHERE (VCSERIE = ? ) ";
        ArrayList<EventoMedidor> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, serie);
            result = stmt.executeQuery();
            
            while (result.next()) {
                EventoMedidor event = new EventoMedidor();
                event.setDfechaevemed(result.getString("DFECHAEVEMED"));
                event.setVcserie(result.getString("VCSERIE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                event.setVcdescripcion(result.getString("VCDESCRIPCION"));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoMedidorDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ArrayList<EventoMedidor> getByCodigo(Connection c, int codigo) throws SQLException {
        String sql = "SELECT * FROM MOV_EVEMEDIDOR WHERE (NCODTIPOEVE = ? ) ";
        ArrayList<EventoMedidor> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, codigo);
            result = stmt.executeQuery();
            
            while (result.next()) {
                EventoMedidor event = new EventoMedidor();
                event.setDfechaevemed(result.getString("DFECHAEVEMED"));
                event.setVcserie(result.getString("VCSERIE"));
                event.setNcodtipoeve(Integer.parseInt(result.getString("NCODTIPOEVE")));
                event.setVcdescripcion(result.getString("VCDESCRIPCION"));
                results.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturaDao.class.getName()).log(Level.SEVERE, null, ex);
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

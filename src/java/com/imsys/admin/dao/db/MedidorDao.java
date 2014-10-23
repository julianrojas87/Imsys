/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Medidor;
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
public class MedidorDao {

    public ArrayList<Medidor> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM M_EQUIPOS ORDER BY NDIR ASC ";
        ArrayList<Medidor> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                Medidor medidor = new Medidor();
                medidor.setVcserie(result.getString("VCSERIE"));
                medidor.setNdir(Integer.parseInt(result.getString("NDIR")));
                results.add(medidor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedidorDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Medidor> getBySerie(Connection c, String serie) throws SQLException {
        String sql = "SELECT * FROM M_EQUIPOS WHERE (VCSERIE = ? ) ";
        ArrayList<Medidor> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, serie);
            result = stmt.executeQuery();

            while (result.next()) {
                Medidor medidor = new Medidor();
                medidor.setVcserie(result.getString("VCSERIE"));
                medidor.setNdir(Integer.parseInt(result.getString("NDIR")));
                results.add(medidor);
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

    public ArrayList<Medidor> getByDir(Connection c, int dir) throws SQLException {
        String sql = "SELECT * FROM M_EQUIPOS WHERE (NDIR = ? ) ";
        ArrayList<Medidor> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, dir);
            result = stmt.executeQuery();

            while (result.next()) {
                Medidor medidor = new Medidor();
                medidor.setVcserie(result.getString("VCSERIE"));
                medidor.setNdir(Integer.parseInt(result.getString("NDIR")));
                results.add(medidor);
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
}

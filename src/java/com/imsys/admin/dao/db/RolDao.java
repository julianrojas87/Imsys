/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Rol;
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
public class RolDao {
    
    public ArrayList<Rol> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM M_ROLL ORDER BY NCODROLL ASC ";
        ArrayList<Rol> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                Rol valueObject = new Rol();
                valueObject.setNcodroll(Integer.parseInt(result.getString("NCODROLL")));
                valueObject.setVcdesroll(result.getString("VCDESROLL"));
                valueObject.setLrlimusuario(Boolean.parseBoolean(result.getString("LRLIMUSUARIO")) ? "Si" : "No");
                valueObject.setLroperario(Boolean.parseBoolean(result.getString("LROPERARIO")) ? "Si" : "No");

                results.add(valueObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public Rol loadbyCode(Connection c, int code) throws SQLException {
        String sql = "SELECT * FROM M_ROLL WHERE (NCODROLL = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        Rol valueObject = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, code);
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject = new Rol();
                valueObject.setNcodroll(Integer.parseInt(result.getString("NCODROLL")));
                valueObject.setVcdesroll(result.getString("VCDESROLL"));
                valueObject.setLrlimusuario(Boolean.parseBoolean(result.getString("LRLIMUSUARIO")) ? "Si" : "No");
                valueObject.setLroperario(Boolean.parseBoolean(result.getString("LROPERARIO")) ? "Si" : "No");
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
    
    public void eraseTable(Connection cx) throws SQLException {
        String sql = "DELETE FROM M_ROLL";
        PreparedStatement stmt = null;

        try {
            stmt = cx.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public synchronized void reload(Connection cx, ArrayList<Rol> roles) throws SQLException {
        PreparedStatement stmt = null;

        for (Rol r : roles) {
            String sql = "INSERT INTO M_ROLL ( NCODROLL, VCDESROLL, LRLIMUSUARIO, "
                    + "LROPERARIO) VALUES (?, ?, ?, ?) ";
            stmt = cx.prepareStatement(sql);

            stmt.setInt(1, r.getNcodroll());
            stmt.setString(2, r.getVcdesroll());
            stmt.setString(3, r.getLrlimusuario());
            stmt.setString(4, r.getLroperario());
            
            stmt.executeUpdate();
        }
        
        cx.close();
    }
}

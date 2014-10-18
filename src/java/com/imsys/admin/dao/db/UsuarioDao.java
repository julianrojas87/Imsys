/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Usuario;
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
public class UsuarioDao {

    public ArrayList<Usuario> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM M_USUARIOS ORDER BY VCCODUSER ASC ";
        ArrayList<Usuario> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                Usuario valueObject = new Usuario();
                valueObject.setVccoduser(result.getString("VCCODUSER"));
                valueObject.setVcnombre(result.getString("VCNOMBRE"));
                valueObject.setVcpass(result.getString("VCPASS"));
                valueObject.setVcroll(result.getString("VCROLL"));
                valueObject.setVcnit(result.getString("VCNIT"));
                valueObject.setLactivo(Boolean.parseBoolean(result.getString("LACTIVO")) ? "Si" : "No");

                results.add(valueObject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public Usuario loadbyName(Connection c, String name) throws SQLException {
        String sql = "SELECT * FROM M_USUARIOS WHERE (VCNOMBRE = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        Usuario valueObject = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, name);
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject = new Usuario();
                valueObject.setVccoduser(result.getString("VCCODUSER"));
                valueObject.setVcnombre(result.getString("VCNOMBRE"));
                valueObject.setVcpass(result.getString("VCPASS"));
                valueObject.setVcroll(result.getString("VCROLL"));
                valueObject.setVcnit(result.getString("VCNIT"));
                valueObject.setLactivo(Boolean.parseBoolean(result.getString("LACTIVO")) ? "Si" : "No");
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
    
    public Usuario loadbyCode(Connection c, String code) throws SQLException {
        String sql = "SELECT * FROM M_USUARIOS WHERE (VCCODUSER = ? ) ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        Usuario valueObject = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, code);
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject = new Usuario();
                valueObject.setVccoduser(result.getString("VCCODUSER"));
                valueObject.setVcnombre(result.getString("VCNOMBRE"));
                valueObject.setVcpass(result.getString("VCPASS"));
                valueObject.setVcroll(result.getString("VCROLL"));
                valueObject.setVcnit(result.getString("VCNIT"));
                valueObject.setLactivo(Boolean.parseBoolean(result.getString("LACTIVO")) ? "Si" : "No");
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

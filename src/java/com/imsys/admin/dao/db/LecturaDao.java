/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Lectura;
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
public class LecturaDao {

    public ArrayList<Lectura> listAll(Connection c) throws SQLException {

        String sql = "SELECT * FROM MOV_LECTURAS ORDER BY TSFECHA DESC ";
        ArrayList<Lectura> results = new ArrayList();
        ResultSet result = null;
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            result = stm.executeQuery();

            while (result.next()) {
                Lectura lec = new Lectura();
                lec.setTsfecha(result.getString("TSFECHA"));
                lec.setVcserie(result.getString("VCSERIE"));
                lec.setNidmedidor(result.getInt("NIDMEDIDOR"));
                lec.setVcfrecuencia(result.getString("VCFRECUENCIA"));
                lec.setVcvoltaje(result.getString("VCVOLTAJE"));
                lec.setVccorriente(result.getString("VCCORRIENTE"));
                lec.setVcpotactiva(result.getString("VCPOTACTIVA"));
                lec.setVcpotreactiva(result.getString("VCPOTREACTIVA"));
                lec.setVcpotaparente(result.getString("VCPOTAPARENTE"));
                lec.setVcfactorpot(result.getString("VCFACTORPOT"));
                lec.setVceneactiva(result.getString("VCENEACTIVA"));
                lec.setVcenereactiva(result.getString("VCENEREACTIVA"));
                lec.setVccalceneact(result.getString("VCCALCENEACT"));
                lec.setVccalcenereact(result.getString("VCCALCENEREA"));
                lec.setLenviado(result.getInt("LENVIADO"));

                results.add(lec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturaDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Lectura> getById(Connection c, String id) throws SQLException {
        String sql = "SELECT * FROM MOV_LECTURAS WHERE (NIDMEDIDOR = ? ) ";
        ArrayList<Lectura> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, id);
            result = stmt.executeQuery();
            
            while (result.next()) {
                Lectura lec = new Lectura();
                lec.setTsfecha(result.getString("TSFECHA"));
                lec.setVcserie(result.getString("VCSERIE"));
                lec.setNidmedidor(result.getInt("NIDMEDIDOR"));
                lec.setVcfrecuencia(result.getString("VCFRECUENCIA"));
                lec.setVcvoltaje(result.getString("VCVOLTAJE"));
                lec.setVccorriente(result.getString("VCCORRIENTE"));
                lec.setVcpotactiva(result.getString("VCPOTACTIVA"));
                lec.setVcpotreactiva(result.getString("VCPOTREACTIVA"));
                lec.setVcpotaparente(result.getString("VCPOTAPARENTE"));
                lec.setVcfactorpot(result.getString("VCFACTORPOT"));
                lec.setVceneactiva(result.getString("VCENEACTIVA"));
                lec.setVcenereactiva(result.getString("VCENEREACTIVA"));
                lec.setVccalceneact(result.getString("VCCALCENEACT"));
                lec.setVccalcenereact(result.getString("VCCALCENEREA"));
                lec.setLenviado(result.getInt("LENVIADO"));

                results.add(lec);
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
    
    public ArrayList<Lectura> getBySerie(Connection c, String serie) throws SQLException {
        String sql = "SELECT * FROM MOV_LECTURAS WHERE (VCSERIE = ? ) ";
        ArrayList<Lectura> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, serie);
            result = stmt.executeQuery();
            
            while (result.next()) {
                Lectura lec = new Lectura();
                lec.setTsfecha(result.getString("TSFECHA"));
                lec.setVcserie(result.getString("VCSERIE"));
                lec.setNidmedidor(result.getInt("NIDMEDIDOR"));
                lec.setVcfrecuencia(result.getString("VCFRECUENCIA"));
                lec.setVcvoltaje(result.getString("VCVOLTAJE"));
                lec.setVccorriente(result.getString("VCCORRIENTE"));
                lec.setVcpotactiva(result.getString("VCPOTACTIVA"));
                lec.setVcpotreactiva(result.getString("VCPOTREACTIVA"));
                lec.setVcpotaparente(result.getString("VCPOTAPARENTE"));
                lec.setVcfactorpot(result.getString("VCFACTORPOT"));
                lec.setVceneactiva(result.getString("VCENEACTIVA"));
                lec.setVcenereactiva(result.getString("VCENEREACTIVA"));
                lec.setVccalceneact(result.getString("VCCALCENEACT"));
                lec.setVccalcenereact(result.getString("VCCALCENEREA"));
                lec.setLenviado(result.getInt("LENVIADO"));

                results.add(lec);
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
    
    public ArrayList<Lectura> getByFecha(Connection c, String fecha) throws SQLException {
        String sql = "SELECT * FROM MOV_LECTURAS WHERE (TSFECHA = ? ) ";
        ArrayList<Lectura> results = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = c.prepareStatement(sql);
            stmt.setString(1, fecha);
            result = stmt.executeQuery();
            
            while (result.next()) {
                Lectura lec = new Lectura();
                lec.setTsfecha(result.getString("TSFECHA"));
                lec.setVcserie(result.getString("VCSERIE"));
                lec.setNidmedidor(result.getInt("NIDMEDIDOR"));
                lec.setVcfrecuencia(result.getString("VCFRECUENCIA"));
                lec.setVcvoltaje(result.getString("VCVOLTAJE"));
                lec.setVccorriente(result.getString("VCCORRIENTE"));
                lec.setVcpotactiva(result.getString("VCPOTACTIVA"));
                lec.setVcpotreactiva(result.getString("VCPOTREACTIVA"));
                lec.setVcpotaparente(result.getString("VCPOTAPARENTE"));
                lec.setVcfactorpot(result.getString("VCFACTORPOT"));
                lec.setVceneactiva(result.getString("VCENEACTIVA"));
                lec.setVcenereactiva(result.getString("VCENEREACTIVA"));
                lec.setVccalceneact(result.getString("VCCALCENEACT"));
                lec.setVccalcenereact(result.getString("VCCALCENEREA"));
                lec.setLenviado(result.getInt("LENVIADO"));

                results.add(lec);
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

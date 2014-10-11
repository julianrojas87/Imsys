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

        String sql = "SELECT * FROM MOV_LECTURAS ORDER BY TSFECHA ASC ";
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
                lec.setVcidmedidor(result.getString("VCIDMEDIDOR"));
                lec.setVcvoltaje(result.getString("VCVOLTAJE"));
                lec.setVccorriente(result.getString("VCCORRIENTE"));
                lec.setVcpotactiva(result.getString("VCPOTACTIVA"));
                lec.setVcpotreactiva(result.getString("VCPOTREACTIVA"));
                lec.setVcpotaparente(result.getString("VCPOTAPARENTE"));
                lec.setVcfactorpot(result.getString("VCFACTORPOT"));
                lec.setVceneactiva(result.getString("VCENEACTIVA"));
                lec.setVcenereactiva(result.getString("VCENEREACTIVA"));
                lec.setLenviado(result.getString("LENVIADO"));

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

}

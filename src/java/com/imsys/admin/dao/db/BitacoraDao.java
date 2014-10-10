/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Bitacora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author julian
 */
public class BitacoraDao {

    public void addEntry(Connection c, Bitacora valueObject) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO MOV_BITACORA (TSFECHA,VCOPERACION,VCCODUSER,"
                    + "VCRUTA) VALUES (?, ?, ?, ?) ";
            stmt = c.prepareStatement(sql);

            stmt.setString(1, valueObject.getTsfecha());
            stmt.setString(2, valueObject.getVcoperacion());
            stmt.setString(3, valueObject.getVccoduser());
            stmt.setString(4, valueObject.getVcruta());

            int rowcount = stmt.executeUpdate();
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            c.close();
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.db;

import com.imsys.admin.dao.entity.Parametros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian
 */
public class ParametrosDao {

    public Parametros getParams(Connection c) throws SQLException {
        String sql = "SELECT * FROM M_PARAMSYS ";
        Parametros params = null;
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            stmt = c.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                params = new Parametros();
                params.setVcnit(result.getString("VCNIT"));
                params.setVccodsubest(result.getString("VCCODSUBEST"));
                params.setVccodcto(result.getString("VCCODCTO"));
                params.setVccodtrans(result.getString("VCCODTRANS"));
                params.setVccodcaja(result.getString("VCCODCAJA"));
                params.setVciplocal(result.getString("VCIPLOCAL"));
                params.setVcipwan(result.getString("VCIPWAN"));
                params.setLred(result.getInt("LRED"));
                params.setNptohttp(result.getInt("NPTOHTTP"));
                params.setNptosocket(result.getInt("NPTOSOCKET"));
                params.setNfrecdescarga(result.getInt("NFRECDESCARGA"));
                params.setVcdeltainicmmto(result.getString("VCDELTAINICMMTO"));
                params.setVcpuerto(result.getString("VCPUERTO"));
                params.setNvelocidad(result.getInt("NVELOCIDAD"));
                params.setNdatabits(result.getInt("NDATABITS"));
                params.setVcparidad(result.getString("VCPARIDAD"));
                params.setNbitsparada(result.getInt("NBITSPARADA"));
                params.setVccodific(result.getString("VCCODIFIC"));
                params.setNlatitud(result.getInt("NLATITUD"));
                params.setNlongitud(result.getInt("NLONGITUD"));
                params.setVcservlet(result.getString("VCSERVLET"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParametrosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            c.close();
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return params;
    }

    public void eraseTable(Connection cx) throws SQLException {
        String sql = "DELETE FROM M_PARAMSYS";
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

    public synchronized void insert(Connection cx, Parametros params) {
        try {
            String sql = "INSERT INTO M_PARAMSYS ( VCNIT, VCCODSUBEST, VCCODCTO, "
                    + "VCCODTRANS, VCCODCAJA, VCIPLOCAL, VCIPWAN, LRED, NPTOHTTP, NPTOSOCKET, NFRECDESCARGA, "
                    + "VCDELTAINICMMTO, VCPUERTO, NVELOCIDAD, NDATABITS, VCPARIDAD, NBITSPARADA, VCCODIFIC, "
                    + "NLATITUD, NLONGITUD, VCSERVLET) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            
            PreparedStatement stmt = cx.prepareStatement(sql);
            
            stmt.setString(1, params.getVcnit());
            stmt.setString(2, params.getVccodsubest());
            stmt.setString(3, params.getVccodcto());
            stmt.setString(4, params.getVccodtrans());
            stmt.setString(5, params.getVccodcaja());
            stmt.setString(6, params.getVciplocal());
            stmt.setString(7, params.getVcipwan());
            stmt.setString(8, Integer.toString(params.getLred()));
            stmt.setString(9, Integer.toString(params.getNptohttp()));
            stmt.setString(10, Integer.toString(params.getNptosocket()));
            stmt.setString(11, Integer.toString(params.getNfrecdescarga()));
            stmt.setString(12, params.getVcdeltainicmmto());
            stmt.setString(13, params.getVcpuerto());
            stmt.setString(14, Integer.toString(params.getNvelocidad()));
            stmt.setString(15, Integer.toString(params.getNdatabits()));
            stmt.setString(16, params.getVcparidad());
            stmt.setString(17, Integer.toString(params.getNbitsparada()));
            stmt.setString(18, params.getVccodific());
            stmt.setString(19, Float.toString(params.getNlatitud()));
            stmt.setString(20, Float.toString(params.getNlongitud()));
            stmt.setString(21, params.getVcservlet());
            
            stmt.executeUpdate();
            
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParametrosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

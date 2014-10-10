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

/**
 *
 * @author julian
 */
public class UsuarioDao {
    
    public Usuario createValueObject(){
        return new Usuario();
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
                  valueObject.setLactivo(result.getString("LACTIVO")); 
              }
              
              return valueObject;
          } finally {
              c.close();
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }
}

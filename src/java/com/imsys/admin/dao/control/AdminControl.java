/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.control;

import com.imsys.admin.dao.db.BitacoraDao;
import com.imsys.admin.dao.db.LecturaDao;
import com.imsys.admin.dao.db.UsuarioDao;
import com.imsys.admin.dao.entity.Bitacora;
import com.imsys.admin.dao.entity.Lectura;
import com.imsys.admin.dao.entity.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian
 */
public class AdminControl {

    private Usuario u;
    private Bitacora b;
    private Lectura l;
    private UsuarioDao uDao;
    private BitacoraDao bDao;
    private LecturaDao lDao;
    private Connection cx;

    public AdminControl() {
        u = new Usuario();
        b= new Bitacora();
        l = new Lectura();
        uDao = new UsuarioDao();
        bDao = new BitacoraDao();
        lDao = new LecturaDao();
    }
    
    public Usuario validateLogin(String user, String pass){
        getConnection();
        Usuario us = null;
        try {
            u = uDao.loadbyName(cx, user);
            if(u != null){
                if(u.getVcpass().equals(pass)){
                    us = u;
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return us;
    }
    
    public void addBitacoraEntry(String operation, String usercode, String route){
        getConnection();
        Calendar cal = Calendar.getInstance();
        String date = cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR)
                +"--"+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
        try {
            b.setTsfecha(date);
            b.setVcoperacion(operation);
            b.setVccoduser(usercode);
            b.setVcruta(route);
            bDao.addEntry(cx, b);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Usuario getUserbyName(String name){
        getConnection();
        try {
            u = uDao.loadbyName(cx, name);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public ArrayList<Lectura> getLecturas(){
        getConnection();
        ArrayList<Lectura> lecturas = new ArrayList();
        try {
            lecturas = lDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lecturas;
    }
    
    public ArrayList<Lectura> getLecturaBy(String parameter, String value){
        getConnection();
        ArrayList<Lectura> lecturas = new ArrayList();
        if(parameter.equals("fecha")){
            try{
                lecturas = lDao.getByFecha(cx, value);
            } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        if(parameter.equals("serie")){
            try{
                lecturas = lDao.getBySerie(cx, value);
            } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        if(parameter.equals("idmedidor")){
            try{
                lecturas = lDao.getById(cx, value);
            } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        
        return lecturas;
    }

    private void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            cx = DriverManager.getConnection("jdbc:sqlite:/home/julian/Desktop/SQLite_Test");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

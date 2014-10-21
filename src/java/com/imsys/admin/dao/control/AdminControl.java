/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.control;

import com.imsys.admin.dao.db.BitacoraDao;
import com.imsys.admin.dao.db.EventoCajaDao;
import com.imsys.admin.dao.db.EventoMedidorDao;
import com.imsys.admin.dao.db.LecturaDao;
import com.imsys.admin.dao.db.PoliticaDao;
import com.imsys.admin.dao.db.RolDao;
import com.imsys.admin.dao.db.TipoEventoDao;
import com.imsys.admin.dao.db.UsuarioDao;
import com.imsys.admin.dao.entity.Bitacora;
import com.imsys.admin.dao.entity.EventoCaja;
import com.imsys.admin.dao.entity.EventoMedidor;
import com.imsys.admin.dao.entity.Lectura;
import com.imsys.admin.dao.entity.Politica;
import com.imsys.admin.dao.entity.Rol;
import com.imsys.admin.dao.entity.TipoEvento;
import com.imsys.admin.dao.entity.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julian
 */
public class AdminControl {

    private Connection cx;
    
    public void addBitacoraEntry(String operation, String usercode, String route) {
        getConnection();
        Bitacora b = new Bitacora();
        BitacoraDao bDao = new BitacoraDao();
        Calendar cal = Calendar.getInstance();
        String date = cal.get(Calendar.DATE) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)
                + "--" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
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

    public Usuario validateLogin(String user, String pass) {
        getConnection();
        Usuario u = new Usuario();
        Usuario us = new Usuario();
        UsuarioDao uDao = new UsuarioDao();
        try {
            u = uDao.loadbyName(cx, user);
            if (u != null) {
                if (u.getVcpass().equals(pass)) {
                    us = u;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        getConnection();
        ArrayList<Usuario> usuarios = new ArrayList();
        UsuarioDao uDao = new UsuarioDao();
        try {
            usuarios = uDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public Usuario getUserbyName(String name) {
        getConnection();
        Usuario u = new Usuario();
        UsuarioDao uDao = new UsuarioDao();
        try {
            u = uDao.loadbyName(cx, name);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public Usuario getUserbyCode(String code) {
        getConnection();
        Usuario u = new Usuario();
        UsuarioDao uDao = new UsuarioDao();
        try {
            u = uDao.loadbyCode(cx, code);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public ArrayList<Lectura> getLecturas() {
        getConnection();
        ArrayList<Lectura> lecturas = new ArrayList();
        LecturaDao lDao = new LecturaDao();
        try {
            lecturas = lDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lecturas;
    }

    public ArrayList<Lectura> getLecturaBy(String parameter, String value) {
        getConnection();
        ArrayList<Lectura> lecturas = new ArrayList();
        LecturaDao lDao = new LecturaDao();
        if (parameter.equals("fecha")) {
            try {
                lecturas = lDao.getByFecha(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (parameter.equals("serie")) {
            try {
                lecturas = lDao.getBySerie(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (parameter.equals("idmedidor")) {
            try {
                lecturas = lDao.getById(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lecturas;
    }

    public ArrayList<EventoCaja> getEventc() {
        getConnection();
        ArrayList<EventoCaja> events = new ArrayList();
        EventoCajaDao ecDao = new EventoCajaDao();
        try {
            events = ecDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    public ArrayList<EventoMedidor> getEventm() {
        getConnection();
        ArrayList<EventoMedidor> events = new ArrayList();
        EventoMedidorDao emDao = new EventoMedidorDao();
        try {
            events = emDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    public ArrayList<EventoCaja> getEventCBy(String parameter, String value) {
        getConnection();
        ArrayList<EventoCaja> eventsc = new ArrayList();
        EventoCajaDao ecDao = new EventoCajaDao();
        if (parameter.equals("fecha")) {
            try {
                eventsc = ecDao.getByFecha(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (parameter.equals("codigo")) {
            try {
                eventsc = ecDao.getByCodigo(cx, Integer.parseInt(value));
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return eventsc;
    }

    public ArrayList<EventoMedidor> getEventMBy(String parameter, String value) {
        getConnection();
        ArrayList<EventoMedidor> eventsm = new ArrayList();
        EventoMedidorDao emDao = new EventoMedidorDao();
        if (parameter.equals("fecha")) {
            try {
                eventsm = emDao.getByFecha(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (parameter.equals("codigo")) {
            try {
                eventsm = emDao.getByCodigo(cx, Integer.parseInt(value));
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (parameter.equals("serie")) {
            try {
                eventsm = emDao.getBySerie(cx, value);
            } catch (SQLException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return eventsm;
    }

    public ArrayList<TipoEvento> getTipoEventos() {
        getConnection();
        ArrayList<TipoEvento> events = new ArrayList();
        TipoEventoDao emDao = new TipoEventoDao();
        try {
            events = emDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

    public TipoEvento getTipoEventoByCode(String value) {
        getConnection();
        TipoEvento eventsc = new TipoEvento();
        TipoEventoDao ecDao = new TipoEventoDao();
        try {
            eventsc = ecDao.loadByCode(cx, value);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventsc;
    }
    
    public ArrayList<Rol> getRoles() {
        getConnection();
        ArrayList<Rol> roles = new ArrayList();
        RolDao rDao = new RolDao();
        try {
            roles = rDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }
    
    public Rol getRolbyCode(String code) {
        getConnection();
        Rol u = new Rol();
        RolDao uDao = new RolDao();
        try {
            u = uDao.loadbyCode(cx, code);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public ArrayList<Politica> getPoliticas() {
        getConnection();
        ArrayList<Politica> politicas = new ArrayList();
        PoliticaDao pDao = new PoliticaDao();
        try {
            politicas = pDao.listAll(cx);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return politicas;
    }
    
    public Politica getPoliticabyCode(String code) {
        getConnection();
        Politica p = new Politica();
        PoliticaDao pDao = new PoliticaDao();
        try {
            p = pDao.loadbyCode(cx, code);
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    private void getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            Properties props = new Properties();
            props.load(new FileInputStream(new File(System.getProperty("user.home") + "/database.cfg")));
            cx = DriverManager.getConnection(props.getProperty("DATA_BASE_LOCATION") + props.getProperty("DATA_BASE_NAME"));
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

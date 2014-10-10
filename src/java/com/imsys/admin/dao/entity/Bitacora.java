/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.dao.entity;

import java.io.Serializable;

/**
 *
 * @author julian
 */
public class Bitacora implements Serializable{
    
    private String tsfecha;
    private String vcoperacion;
    private String vccoduser;
    private String vcruta;
    
    public Bitacora(){}
    
    public Bitacora (String fecha){
        this.tsfecha = fecha;
    }

    public String getTsfecha() {
        return tsfecha;
    }

    public void setTsfecha(String tsfecha) {
        this.tsfecha = tsfecha;
    }

    public String getVcoperacion() {
        return vcoperacion;
    }

    public void setVcoperacion(String vcoperacion) {
        this.vcoperacion = vcoperacion;
    }

    public String getVccoduser() {
        return vccoduser;
    }

    public void setVccoduser(String vccoduser) {
        this.vccoduser = vccoduser;
    }

    public String getVcruta() {
        return vcruta;
    }

    public void setVcruta(String vcruta) {
        this.vcruta = vcruta;
    }  
}

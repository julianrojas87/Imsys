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
public class EventoCaja implements Serializable{
    
    private String dfechaeve;
    private int ncodtipoeve;
    private String vcdescripcion;

    public String getDfechaeve() {
        return dfechaeve;
    }

    public void setDfechaeve(String dfechaeve) {
        this.dfechaeve = dfechaeve;
    }

    public int getNcodtipoeve() {
        return ncodtipoeve;
    }

    public void setNcodtipoeve(int ncodtipoeve) {
        this.ncodtipoeve = ncodtipoeve;
    }    

    public String getVcdescripcion() {
        return vcdescripcion;
    }

    public void setVcdescripcion(String vcdescripcion) {
        this.vcdescripcion = vcdescripcion;
    }
}

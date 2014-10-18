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
public class TipoEvento implements Serializable{
    
    private int nccodtipoeve;
    private String vcdeseve;
    private String laaplicacaja;
    private String laaplicamed;

    public int getNccodtipoeve() {
        return nccodtipoeve;
    }

    public void setNccodtipoeve(int nccodtipoeve) {
        this.nccodtipoeve = nccodtipoeve;
    }

    public String getVcdeseve() {
        return vcdeseve;
    }

    public void setVcdeseve(String vcdeseve) {
        this.vcdeseve = vcdeseve;
    }

    public String getLaaplicacaja() {
        return laaplicacaja;
    }

    public void setLaaplicacaja(String laaplicacaja) {
        this.laaplicacaja = laaplicacaja;
    }

    public String getLaaplicamed() {
        return laaplicamed;
    }

    public void setLaaplicamed(String laaplicamed) {
        this.laaplicamed = laaplicamed;
    }
}

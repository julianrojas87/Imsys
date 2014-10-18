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
public class Politica implements Serializable{
    
    private String vccodrutina;
    private String lopcion;
    private String lgrabar;
    private String lborrar;
    private String limprimir;
    private String lsimex;

    public String getVccodrutina() {
        return vccodrutina;
    }

    public void setVccodrutina(String vccodrutina) {
        this.vccodrutina = vccodrutina;
    }

    public String getLopcion() {
        return lopcion;
    }

    public void setLopcion(String lopcion) {
        this.lopcion = lopcion;
    }

    public String getLgrabar() {
        return lgrabar;
    }

    public void setLgrabar(String lgrabar) {
        this.lgrabar = lgrabar;
    }

    public String getLborrar() {
        return lborrar;
    }

    public void setLborrar(String lborrar) {
        this.lborrar = lborrar;
    }

    public String getLimprimir() {
        return limprimir;
    }

    public void setLimprimir(String limprimir) {
        this.limprimir = limprimir;
    }

    public String getLsimex() {
        return lsimex;
    }

    public void setLsimex(String lsimex) {
        this.lsimex = lsimex;
    }
}

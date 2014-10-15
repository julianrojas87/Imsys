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
public class EventoMedidor implements Serializable{
    
    private String dfechaevemed;
    private String vcserie;
    private int ncodtipoeve;

    public String getDfechaevemed() {
        return dfechaevemed;
    }

    public void setDfechaevemed(String dfechaevemed) {
        this.dfechaevemed = dfechaevemed;
    }

    public String getVcserie() {
        return vcserie;
    }

    public void setVcserie(String vcserie) {
        this.vcserie = vcserie;
    }

    public int getNcodtipoeve() {
        return ncodtipoeve;
    }

    public void setNcodtipoeve(int ncodtipoeve) {
        this.ncodtipoeve = ncodtipoeve;
    }
}

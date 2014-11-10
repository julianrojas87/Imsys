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
public class Medidor implements Serializable{
    
    private String vcserie;
    private int ndir;
    private int lestado;

    public String getVcserie() {
        return vcserie;
    }

    public void setVcserie(String vcserie) {
        this.vcserie = vcserie;
    }

    public int getNdir() {
        return ndir;
    }

    public void setNdir(int ndir) {
        this.ndir = ndir;
    }

    public int getLestado() {
        return lestado;
    }

    public void setLestado(int lestado) {
        this.lestado = lestado;
    }
}

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
public class Rol implements Serializable{
    
    private int ncodroll;
    private String vcdesroll;
    private String lrlimusuario;
    private String lroperario;

    public int getNcodroll() {
        return ncodroll;
    }

    public void setNcodroll(int ncodroll) {
        this.ncodroll = ncodroll;
    }

    public String getVcdesroll() {
        return vcdesroll;
    }

    public void setVcdesroll(String vcdesroll) {
        this.vcdesroll = vcdesroll;
    }

    public String getLrlimusuario() {
        return lrlimusuario;
    }

    public void setLrlimusuario(String lrlimusuario) {
        this.lrlimusuario = lrlimusuario;
    }

    public String getLroperario() {
        return lroperario;
    }

    public void setLroperario(String lroperario) {
        this.lroperario = lroperario;
    }
}

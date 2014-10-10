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
public class Usuario implements Serializable{
    
    private String vccoduser;
    private String vcnombre;
    private String vcpass;
    private String vcroll;
    private String vcnit;
    private String lactivo;
    
    public Usuario(){
    }
    
    public Usuario(String coduser){
        this.vccoduser = coduser;
    }

    public String getVccoduser() {
        return vccoduser;
    }

    public void setVccoduser(String vccoduser) {
        this.vccoduser = vccoduser;
    }

    public String getVcnombre() {
        return vcnombre;
    }

    public void setVcnombre(String vcnombre) {
        this.vcnombre = vcnombre;
    }

    public String getVcpass() {
        return vcpass;
    }

    public void setVcpass(String vcpass) {
        this.vcpass = vcpass;
    }

    public String getVcroll() {
        return vcroll;
    }

    public void setVcroll(String vcroll) {
        this.vcroll = vcroll;
    }

    public String getVcnit() {
        return vcnit;
    }

    public void setVcnit(String vcnit) {
        this.vcnit = vcnit;
    }

    public String getLactivo() {
        return lactivo;
    }

    public void setLactivo(String lactivo) {
        this.lactivo = lactivo;
    }
}

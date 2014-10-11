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
public class Lectura implements Serializable{
    
    private String tsfecha;
    private String vcserie;
    private String vcidmedidor;
    private String vcvoltaje;
    private String vccorriente;
    private String vcpotactiva;
    private String vcpotreactiva;
    private String vcpotaparente;
    private String vcfactorpot;
    private String vceneactiva;
    private String vcenereactiva;
    private String lenviado;

    public Lectura(){}
    
    public String getTsfecha() {
        return tsfecha;
    }

    public void setTsfecha(String tsfecha) {
        this.tsfecha = tsfecha;
    }

    public String getVcserie() {
        return vcserie;
    }

    public void setVcserie(String vcserie) {
        this.vcserie = vcserie;
    }

    public String getVcidmedidor() {
        return vcidmedidor;
    }

    public void setVcidmedidor(String vcidmedidor) {
        this.vcidmedidor = vcidmedidor;
    }

    public String getVcvoltaje() {
        return vcvoltaje;
    }

    public void setVcvoltaje(String vcvoltaje) {
        this.vcvoltaje = vcvoltaje;
    }

    public String getVccorriente() {
        return vccorriente;
    }

    public void setVccorriente(String vccorriente) {
        this.vccorriente = vccorriente;
    }

    public String getVcpotactiva() {
        return vcpotactiva;
    }

    public void setVcpotactiva(String vcopotactiva) {
        this.vcpotactiva = vcopotactiva;
    }

    public String getVcpotreactiva() {
        return vcpotreactiva;
    }

    public void setVcpotreactiva(String vcpotreactiva) {
        this.vcpotreactiva = vcpotreactiva;
    }

    public String getVcpotaparente() {
        return vcpotaparente;
    }

    public void setVcpotaparente(String vcpotaparente) {
        this.vcpotaparente = vcpotaparente;
    }

    public String getVcfactorpot() {
        return vcfactorpot;
    }

    public void setVcfactorpot(String vcfactorpot) {
        this.vcfactorpot = vcfactorpot;
    }

    public String getVceneactiva() {
        return vceneactiva;
    }

    public void setVceneactiva(String vceneactiva) {
        this.vceneactiva = vceneactiva;
    }

    public String getVcenereactiva() {
        return vcenereactiva;
    }

    public void setVcenereactiva(String vcenereactiva) {
        this.vcenereactiva = vcenereactiva;
    }

    public String getLenviado() {
        return lenviado;
    }

    public void setLenviado(String lenviado) {
        this.lenviado = lenviado;
    }   
}

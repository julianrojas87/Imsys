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
    private int nidmedidor;
    private String vcvoltaje;
    private String vccorriente;
    private String vcpotactiva;
    private String vcpotreactiva;
    private String vcpotaparente;
    private String vcfactorpot;
    private String vceneactiva;
    private String vcenereactiva;
    private String vccalceneact;
    private String vccalcenereact;
    private int lenviado;
    private String vcfrecuencia;

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

    public int getNidmedidor() {
        return nidmedidor;
    }

    public void setNidmedidor(int nidmedidor) {
        this.nidmedidor = nidmedidor;
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

    public int getLenviado() {
        return lenviado;
    }

    public void setLenviado(int lenviado) {
        this.lenviado = lenviado;
    }

    public String getVccalceneact() {
        return vccalceneact;
    }

    public void setVccalceneact(String vccalceneact) {
        this.vccalceneact = vccalceneact;
    }

    public String getVccalcenereact() {
        return vccalcenereact;
    }

    public void setVccalcenereact(String vccalcenereact) {
        this.vccalcenereact = vccalcenereact;
    }

    public String getVcfrecuencia() {
        return vcfrecuencia;
    }

    public void setVcfrecuencia(String vcfrecuencia) {
        this.vcfrecuencia = vcfrecuencia;
    }
}

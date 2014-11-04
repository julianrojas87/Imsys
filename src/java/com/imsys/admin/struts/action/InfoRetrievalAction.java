/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Lectura;
import com.imsys.admin.dao.entity.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class InfoRetrievalAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private String dateini;
    private String datefin;
    private InputStream fileInputStream;
    private String filename;

    public String displayRetrieval() {
        session.setAttribute("mainopt", "infoRetrieval");
        return SUCCESS;
    }

    public String configureRetrieval() throws ParseException, FileNotFoundException, UnsupportedEncodingException {
        AdminControl ac = new AdminControl();
        ArrayList<Lectura> downlecs = new ArrayList();

        if (this.getDateini().length() > 0 && this.getDatefin().length() > 0) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ini = format.parse(this.getDateini());
            Date fin = format.parse(this.getDatefin());

            if (fin.after(ini) || fin.equals(ini)) {
                ArrayList<Lectura> all = ac.getLecturas();
                for (Lectura l : all) {
                    String day = l.getTsfecha().split(" ")[0].split("/")[2];
                    String month = l.getTsfecha().split(" ")[0].split("/")[1];
                    String year = l.getTsfecha().split(" ")[0].split("/")[0];
                    
                    String ld1 = day + "/" + month + "/" + year;
                    Date ld = format.parse(ld1);
                    if ((ld.after(ini) || ld.equals(ini)) && (ld.before(fin) || ld.equals(fin))) {
                        downlecs.add(l);
                    }
                }
            } else {
                session.setAttribute("mainopt", "infoRetrieval");
                session.setAttribute("msj", "Especifique una Fecha Inicial "
                        + "anterior a la Fecha Final para realizar la busqueda");
                return ERROR;
            }
        } else {
            session.setAttribute("mainopt", "infoRetrieval");
            session.setAttribute("msj", "Ingrese tanto la Fecha Inicial "
                    + "como la Fecha Final para realizar la busqueda");
            return ERROR;
        }

        PrintWriter writer = new PrintWriter(System.getProperty("user.home") + "/backup.txt");
        //writer.println("|   Fecha  |ID Medidor|  Serial  |Voltaje|Corriente|Pot Activa|Pot Reactiva|"
        //        + "Pot Aparente|Factor Pot|Ene Activa|Ene Reactiva|Ene Act Calc|Ene React Calc|");
        //writer.println("|----------|----------|----------|-------|---------|----------|------------|------------|----------|"
        //        + "----------|------------|------------|--------------|");
        for (Lectura l : downlecs) {
            writer.println(l.getTsfecha() + ";" + l.getNidmedidor() + ";" + l.getVcfrecuencia() + ";" + l.getVcserie() + ";" + 
                    l.getVcvoltaje() + ";" + l.getVccorriente() + ";" + l.getVcpotactiva() + ";" + l.getVcpotreactiva() + ";" + 
                    l.getVcpotaparente() + ";" + l.getVcfactorpot() + ";" + l.getVceneactiva() + ";" + l.getVcpotreactiva() + ";" + 
                    l.getVccalceneact() + ";" + l.getVccalcenereact() + ";" + l.getLenviado());
            /*writer.println("|" + String.format("%-10s", l.getTsfecha()) + "|" + String.format("%-10s", l.getVcidmedidor()) + "|"
                    + String.format("%-10s", l.getVcserie()) + "|" + String.format("%-7s", l.getVcvoltaje()) + "|"
                    + String.format("%-9s", l.getVccorriente()) + "|" + String.format("%-10s", l.getVcpotactiva()) + "|"
                    + String.format("%-12s", l.getVcpotreactiva()) + "|" + String.format("%-12s", l.getVcpotaparente()) + "|"
                    + String.format("%-10s", l.getVcfactorpot()) + "|" + String.format("%-10s", l.getVceneactiva()) + "|"
                    + String.format("%-12s", l.getVcenereactiva()) + "|" + String.format("%-12s", l.getVccalceneact()) + "|"
                    + String.format("%-14s", l.getVccalcenereact()) + "|");
            writer.println("|----------|----------|----------|-------|---------|----------|------------|------------|----------|"
                    + "----------|------------|------------|--------------|");*/
        }
        writer.close();
        File downloadble = new File(System.getProperty("user.home") + "/backup.txt");
        this.setFileInputStream(new FileInputStream(downloadble));
        this.setFilename("Lecturas_" + this.getDateini() + "----" + this.getDatefin() + ".txt");
        downloadble.delete();
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario ["+ u.getVcnombre()+"] descargó las lecturas comprendidas entre ["+this.getDateini()+"] "
                + "y ["+this.getDatefin()+"]", u.getVccoduser(), "Main/Procesos/RecuperacionInfo");
        return SUCCESS;
    }

    public String getDateini() {
        return dateini;
    }

    public void setDateini(String dateini) {
        this.dateini = dateini;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }
}

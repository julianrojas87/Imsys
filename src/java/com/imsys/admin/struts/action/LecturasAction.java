/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Lectura;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class LecturasAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private String btnopt;
    private String date;
    private String idmedidor;
    private String serie;

    public String getLecturas() {
        AdminControl ac = new AdminControl();
        ArrayList<Lectura> lecs = ac.getLecturas();
        List<Lectura> displecs = new ArrayList();
        if (lecs.size() > 10) {
            displecs = lecs.subList(0, 10);
        } else {
            for (Lectura l : lecs) {
                displecs.add(l);
            }
        }
        session.setAttribute("lecturas", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", 1);
        session.setAttribute("mainopt", "lecturas");
        return SUCCESS;
    }

    public String displayN() {
        AdminControl ac = new AdminControl();
        ArrayList<Lectura> lecs = ac.getLecturas();
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Lectura> displecs = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (lecs.size() > 10) {
                displecs = lecs.subList(0, 10);
            } else {
                for (Lectura l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("lecturas", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "lecturas");
        return SUCCESS;
    }

    public String searchLecturas() {
        ArrayList<Lectura> displecs = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;
        
        if (this.getIdmedidor().length() > 0) {
            inputs = true;
            displecs = ac.getLecturaBy("idmedidor", this.getIdmedidor());
        }
        
        if (this.getSerie().length() > 0) {
            inputs = true;
            if (displecs.size() > 0) {
                ArrayList<Lectura> temp = new ArrayList();
                for (Lectura l : displecs) {
                    if (l.getVcserie().equals(this.getSerie())) {
                        temp.add(l);
                    }
                }
                displecs = temp;
            } else {
                displecs = ac.getLecturaBy("serie", this.getSerie());
            }
        }

        if (this.getDate().length() > 0) {
            inputs = true;
            if (displecs.size() > 0) {
                System.out.println("Entro a buscar por fecha desps de una busqueda anterior!!");
                String reformatDate = this.getDate().split("-")[2] + "/"
                        + this.getDate().split("-")[1] + "/" + this.getDate().split("-")[0];
                ArrayList<Lectura> temp1 = new ArrayList();
                for (Lectura l : displecs) {
                    if (l.getTsfecha().equals(reformatDate)) {
                        temp1.add(l);
                    }
                }
                displecs = temp1;
            } else {
                System.out.println("Entro a buscar por fecha!!");
                String reformatDate = this.getDate().split("-")[2] + "/"
                        + this.getDate().split("-")[1] + "/" + this.getDate().split("-")[0];
                displecs = ac.getLecturaBy("fecha", reformatDate);
                System.out.println("Tamaño de Displecs: "+displecs.size());
            }
        }
        
        if (displecs.size() > 0) {
            if(displecs.size() > 10){
                session.setAttribute("displecs", displecs.subList(0, 10));
            } else{
                List<Lectura> temp = new ArrayList();
                for(Lectura l : displecs){
                    temp.add(l);
                }
                session.setAttribute("displecs", temp);
            }
            session.setAttribute("searchlecturas", displecs);
            session.setAttribute("totallecs", displecs.size());
            session.setAttribute("actuallec", 1);
            session.setAttribute("mainopt", "searchLecturas");
            return SUCCESS;
        } else if(inputs){
            session.setAttribute("mainopt", "lecturas");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else{
            session.setAttribute("mainopt", "lecturas");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }

    }

    public String displaySearchN() {
        ArrayList<Lectura> lecs = (ArrayList<Lectura>) session.getAttribute("searchlecturas");
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Lectura> displecs = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (lecs.size() > 10) {
                displecs = lecs.subList(0, 10);
            } else {
                for (Lectura l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("displecs", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "searchLecturas");
        return SUCCESS;
    }

    public String getBtnopt() {
        return btnopt;
    }

    public void setBtnopt(String btnopt) {
        this.btnopt = btnopt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdmedidor() {
        return idmedidor;
    }

    public void setIdmedidor(String idmedidor) {
        this.idmedidor = idmedidor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }

}

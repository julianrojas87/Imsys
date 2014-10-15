/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.EventoCaja;
import com.imsys.admin.dao.entity.EventoMedidor;
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
public class EventosAction extends ActionSupport implements ServletRequestAware {
    
    private HttpSession session;
    private String btnopt;
    private String date;
    private String code;
    private String serie;
    
    public String getEventosCaja() {
        AdminControl ac = new AdminControl();
        ArrayList<EventoCaja> eventc = ac.getEventc();
        List<EventoCaja> dispeventsc = new ArrayList();
        if (eventc.size() > 10) {
            dispeventsc = eventc.subList(0, 10);
        } else {
            for (EventoCaja ec : eventc) {
                dispeventsc.add(ec);
            }
        }
        session.setAttribute("eventosCaja", dispeventsc);
        session.setAttribute("totalevec", eventc.size());
        session.setAttribute("actualevec", 1);
        session.setAttribute("mainopt", "eventosCaja");
        return SUCCESS;
    }
    
    public String getEventosMedidor() {
        AdminControl ac = new AdminControl();
        ArrayList<EventoMedidor> eventm = ac.getEventm();
        List<EventoMedidor> dispeventsm = new ArrayList();
        if (eventm.size() > 10) {
            dispeventsm  = eventm.subList(0, 10);
        } else {
            for (EventoMedidor em : eventm) {
                dispeventsm.add(em);
            }
        }
        session.setAttribute("eventosMedidor", dispeventsm);
        session.setAttribute("totalevem", eventm.size());
        session.setAttribute("actualevem", 1);
        session.setAttribute("mainopt", "eventosMedidor");
        return SUCCESS;
    }
    
    public String displayEventsCN(){
        AdminControl ac = new AdminControl();
        ArrayList<EventoCaja> eventc = ac.getEventc();
        int total = (eventc.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<EventoCaja> dispeventsc = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    dispeventsc.add(eventc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (eventc.size() > 10) {
                dispeventsc = eventc.subList(0, 10);
            } else {
                for (EventoCaja ec : eventc) {
                    dispeventsc.add(ec);
                }
            }
        }
        session.setAttribute("eventosCaja", dispeventsc);
        session.setAttribute("totalevec", eventc.size());
        session.setAttribute("actualevec", opt);
        session.setAttribute("mainopt", "eventosCaja");
        return SUCCESS;
    }
    
    public String displayEventsMN(){
        AdminControl ac = new AdminControl();
        ArrayList<EventoMedidor> eventm = ac.getEventm();
        int total = (eventm.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<EventoMedidor> dispeventsm = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    dispeventsm.add(eventm.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (eventm.size() > 10) {
                dispeventsm = eventm.subList(0, 10);
            } else {
                for (EventoMedidor em : eventm) {
                    dispeventsm.add(em);
                }
            }
        }
        session.setAttribute("eventosMedidor", dispeventsm);
        session.setAttribute("totalevem", eventm.size());
        session.setAttribute("actualevem", opt);
        session.setAttribute("mainopt", "eventosMedidor");
        return SUCCESS;
    }
    
    public String searchEventsC() {
        ArrayList<EventoCaja> dispeventsc = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;
        
        if (this.getDate().length() > 0) {
            inputs = true;
            String reformatDate = this.getDate().split("-")[2] + "/"
                        + this.getDate().split("-")[1] + "/" + this.getDate().split("-")[0];
            dispeventsc = ac.getEventCBy("fecha", reformatDate);
        }
        
        if (this.getCode().length() > 0) {
            inputs = true;
            if (dispeventsc.size() > 0) {
                ArrayList<EventoCaja> temp = new ArrayList();
                for (EventoCaja ec : dispeventsc) {
                    if (ec.getNcodtipoeve() == Integer.parseInt(this.getCode())) {
                        temp.add(ec);
                    }
                }
                dispeventsc = temp;
            } else {
                dispeventsc = ac.getEventCBy("codigo", this.getCode());
            }
        }
        
        if (dispeventsc.size() > 0) {
            if(dispeventsc.size() > 10){
                session.setAttribute("dispeventsc", dispeventsc.subList(0, 10));
            } else{
                List<EventoCaja> temp = new ArrayList();
                for(EventoCaja ec : dispeventsc){
                    temp.add(ec);
                }
                session.setAttribute("dispeventsc", temp);
            }
            session.setAttribute("searcheventsc", dispeventsc);
            session.setAttribute("totalevec", dispeventsc.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchEventC");
            return SUCCESS;
        } else if(inputs){
            session.setAttribute("mainopt", "eventosCaja");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else{
            session.setAttribute("mainopt", "eventosCaja");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }
    }
    
    public String displaySearchEveCN() {
        ArrayList<EventoCaja> eventsc = (ArrayList<EventoCaja>) session.getAttribute("searcheventsc");
        int total = (eventsc.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<EventoCaja> dispeventsc = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    dispeventsc.add(eventsc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (eventsc.size() > 10) {
                dispeventsc = eventsc.subList(0, 10);
            } else {
                for (EventoCaja ec : eventsc) {
                    dispeventsc.add(ec);
                }
            }
        }
        session.setAttribute("dispeventsc", dispeventsc);
        session.setAttribute("totalevec", eventsc.size());
        session.setAttribute("actualevec", opt);
        session.setAttribute("mainopt", "searchEventC");
        return SUCCESS;
    }
    
    public String searchEventsM() {
        ArrayList<EventoMedidor> dispeventsm = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;
        
        if (this.getDate().length() > 0) {
            inputs = true;
            String reformatDate = this.getDate().split("-")[2] + "/"
                        + this.getDate().split("-")[1] + "/" + this.getDate().split("-")[0];
            dispeventsm = ac.getEventMBy("fecha", reformatDate);
        }
        
        if (this.getCode().length() > 0) {
            inputs = true;
            if (dispeventsm.size() > 0) {
                ArrayList<EventoMedidor> temp = new ArrayList();
                for (EventoMedidor em : dispeventsm) {
                    if (em.getNcodtipoeve() == Integer.parseInt(this.getCode())) {
                        temp.add(em);
                    }
                }
                dispeventsm = temp;
            } else {
                dispeventsm = ac.getEventMBy("codigo", this.getCode());
            }
        }
        
        if (this.getSerie().length() > 0) {
            inputs = true;
            if (dispeventsm.size() > 0) {
                ArrayList<EventoMedidor> temp = new ArrayList();
                for (EventoMedidor em : dispeventsm) {
                    if (em.getVcserie().equals(this.getSerie())) {
                        temp.add(em);
                    }
                }
                dispeventsm = temp;
            } else {
                dispeventsm = ac.getEventMBy("serie", this.getSerie());
            }
        }
        
        if (dispeventsm.size() > 0) {
            if(dispeventsm.size() > 10){
                session.setAttribute("dispeventsm", dispeventsm.subList(0, 10));
            } else{
                List<EventoMedidor> temp = new ArrayList();
                for(EventoMedidor em : dispeventsm){
                    temp.add(em);
                }
                session.setAttribute("dispeventsm", temp);
            }
            session.setAttribute("searcheventsm", dispeventsm);
            session.setAttribute("totalevem", dispeventsm.size());
            session.setAttribute("actualevem", 1);
            session.setAttribute("mainopt", "searchEventM");
            return SUCCESS;
        } else if(inputs){
            session.setAttribute("mainopt", "eventosMedidor");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else{
            session.setAttribute("mainopt", "eventosMedidor");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }
    }
    
    public String displaySearchEveMN() {
        ArrayList<EventoMedidor> eventsm = (ArrayList<EventoMedidor>) session.getAttribute("searcheventsm");
        int total = (eventsm.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<EventoMedidor> dispeventsm = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 10; i++) {
                try {
                    dispeventsm.add(eventsm.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            if (eventsm.size() > 10) {
                dispeventsm = eventsm.subList(0, 10);
            } else {
                for (EventoMedidor em: eventsm) {
                    dispeventsm.add(em);
                }
            }
        }
        session.setAttribute("dispeventsm", dispeventsm);
        session.setAttribute("totalevem", eventsm.size());
        session.setAttribute("actualevem", opt);
        session.setAttribute("mainopt", "searchEventM");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

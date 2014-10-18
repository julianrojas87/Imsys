/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.EventoCaja;
import com.imsys.admin.dao.entity.EventoMedidor;
import com.imsys.admin.dao.entity.TipoEvento;
import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private String dateini;
    private String datefin;
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
            dispeventsm = eventm.subList(0, 10);
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

    public String displayEventsCN() {
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsc.add(eventc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
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

    public String displayEventsMN() {
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsm.add(eventm.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
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

    public String searchEventsC() throws ParseException {
        ArrayList<EventoCaja> dispeventsc = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;

        if (this.getDateini().length() > 0 && this.getDatefin().length() > 0) {
            inputs = true;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ini = format.parse(this.getDateini());
            Date fin = format.parse(this.getDatefin());
            if (fin.after(ini) || fin.equals(ini)) {
                ArrayList<EventoCaja> all = ac.getEventc();
                for (EventoCaja l : all) {
                    Date ld = format.parse(l.getDfechaeve());
                    if ((ld.after(ini) || ld.equals(ini)) && (ld.before(fin) || ld.equals(fin))) {
                        dispeventsc.add(l);
                    }
                }
            } else {
                session.setAttribute("mainopt", "eventosCaja");
                session.setAttribute("msj", "Especifique una Fecha Inicial "
                        + "anterior a la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
        } else {
            if (this.getDateini().length() > 0
                    || this.getDatefin().length() > 0) {
                session.setAttribute("mainopt", "eventosCaja");
                session.setAttribute("msj", "Ingrese tanto la Fecha Inicial "
                        + "como la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
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
            if (dispeventsc.size() > 10) {
                session.setAttribute("dispeventsc", dispeventsc.subList(0, 10));
            } else {
                List<EventoCaja> temp = new ArrayList();
                for (EventoCaja ec : dispeventsc) {
                    temp.add(ec);
                }
                session.setAttribute("dispeventsc", temp);
            }
            session.setAttribute("searcheventsc", dispeventsc);
            session.setAttribute("totalevec", dispeventsc.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchEventC");
            return SUCCESS;
        } else if (inputs) {
            session.setAttribute("mainopt", "eventosCaja");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else {
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsc.add(eventsc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
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

    public String searchEventsM() throws ParseException {
        ArrayList<EventoMedidor> dispeventsm = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;

        if (this.getDateini().length() > 0 && this.getDatefin().length() > 0) {
            inputs = true;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ini = format.parse(this.getDateini());
            Date fin = format.parse(this.getDatefin());
            if (fin.after(ini) || fin.equals(ini)) {
                ArrayList<EventoMedidor> all = ac.getEventm();
                for (EventoMedidor l : all) {
                    Date ld = format.parse(l.getDfechaevemed());
                    if ((ld.after(ini) || ld.equals(ini)) && (ld.before(fin) || ld.equals(fin))) {
                        dispeventsm.add(l);
                    }
                }
            } else {
                session.setAttribute("mainopt", "eventosMedidor");
                session.setAttribute("msj", "Especifique una Fecha Inicial "
                        + "anterior a la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
        } else {
            if (this.getDateini().length() > 0
                    || this.getDatefin().length() > 0) {
                session.setAttribute("mainopt", "eventosMedidor");
                session.setAttribute("msj", "Ingrese tanto la Fecha Inicial "
                        + "como la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
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
            if (dispeventsm.size() > 10) {
                session.setAttribute("dispeventsm", dispeventsm.subList(0, 10));
            } else {
                List<EventoMedidor> temp = new ArrayList();
                for (EventoMedidor em : dispeventsm) {
                    temp.add(em);
                }
                session.setAttribute("dispeventsm", temp);
            }
            session.setAttribute("searcheventsm", dispeventsm);
            session.setAttribute("totalevem", dispeventsm.size());
            session.setAttribute("actualevem", 1);
            session.setAttribute("mainopt", "searchEventM");
            return SUCCESS;
        } else if (inputs) {
            session.setAttribute("mainopt", "eventosMedidor");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else {
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsm.add(eventsm.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt =1;
            if (eventsm.size() > 10) {
                dispeventsm = eventsm.subList(0, 10);
            } else {
                for (EventoMedidor em : eventsm) {
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

    public String getTipoEventos() {
        AdminControl ac = new AdminControl();
        ArrayList<TipoEvento> eventc = ac.getTipoEventos();
        List<TipoEvento> dispeventsc = new ArrayList();
        if (eventc.size() > 10) {
            dispeventsc = eventc.subList(0, 10);
        } else {
            for (TipoEvento ec : eventc) {
                dispeventsc.add(ec);
            }
        }
        session.setAttribute("tipoEventos", dispeventsc);
        session.setAttribute("totalevec", eventc.size());
        session.setAttribute("actualevec", 1);
        session.setAttribute("mainopt", "tipoEventos");
        return SUCCESS;
    }

    public String displayTipoEventN() {
        AdminControl ac = new AdminControl();
        ArrayList<TipoEvento> eventc = ac.getTipoEventos();
        int total = (eventc.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<TipoEvento> dispeventsc = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsc.add(eventc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
            if (eventc.size() > 10) {
                dispeventsc = eventc.subList(0, 10);
            } else {
                for (TipoEvento ec : eventc) {
                    dispeventsc.add(ec);
                }
            }
        }
        session.setAttribute("tipoEventos", dispeventsc);
        session.setAttribute("totalevec", eventc.size());
        session.setAttribute("actualevec", opt);
        session.setAttribute("mainopt", "tipoEventos");
        return SUCCESS;
    }

    public String searchTipoEvents() {
        List<TipoEvento> te = new ArrayList();
        AdminControl ac = new AdminControl();

        if (this.getCode().length() > 0) {
            TipoEvento t = ac.getTipoEventoByCode(this.getCode());
            if(t != null){
                te.add(t);
            }
        } else {
            session.setAttribute("mainopt", "tipoEventos");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }

        if (te.size() > 0) {
            session.setAttribute("tipoEventos", te);
            session.setAttribute("searchtipoevents", te);
            session.setAttribute("totalevec", te.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchTipoEventos");
            return SUCCESS;
        } else {
            session.setAttribute("mainopt", "tipoEventos");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        }
    }
    
    public String displaySearchTipoEventN() {
        ArrayList<TipoEvento> eventc = (ArrayList<TipoEvento>) session.getAttribute("searchtipoevents");
        int total = (eventc.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<TipoEvento> dispeventsc = new ArrayList();
        if (opt > 1 && total > 1) {
            if (opt > total) {
                opt--;
            }
            int lim = 11 * (opt - 1) - 1;
            for (int i = lim; i < lim + 11; i++) {
                try {
                    dispeventsc.add(eventc.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
            if (eventc.size() > 10) {
                dispeventsc = eventc.subList(0, 10);
            } else {
                for (TipoEvento ec : eventc) {
                    dispeventsc.add(ec);
                }
            }
        }
        session.setAttribute("tipoEventos", dispeventsc);
        session.setAttribute("totalevec", eventc.size());
        session.setAttribute("actualevec", opt);
        session.setAttribute("mainopt", "searchTipoEventos");
        return SUCCESS;
    }

    public String getBtnopt() {
        return btnopt;
    }

    public void setBtnopt(String btnopt) {
        this.btnopt = btnopt;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Lectura;
import com.imsys.admin.dao.entity.Usuario;
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
public class LecturasAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private String btnopt;
    private String dateini;
    private String datefin;
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
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario ["+ u.getVcnombre()+"] consultó las lecturas.", u.getVccoduser(), "Main/Consultas/Lecturas");
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
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

    public String searchLecturas() throws ParseException {
        ArrayList<Lectura> displecs = new ArrayList();
        AdminControl ac = new AdminControl();
        boolean inputs = false;

        if (this.getDateini().length() > 0 && this.getDatefin().length() > 0) {
            inputs = true;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date ini = format.parse(this.getDateini());
            Date fin = format.parse(this.getDatefin());
            if (fin.after(ini) || fin.equals(ini)) {
                ArrayList<Lectura> all = ac.getLecturas();
                for (Lectura l : all) {
                    Date ld = format.parse(l.getTsfecha());
                    if ((ld.after(ini) || ld.equals(ini)) && (ld.before(fin) || ld.equals(fin))) {
                        displecs.add(l);
                    }
                }
            } else {
                session.setAttribute("mainopt", "lecturas");
                session.setAttribute("msj", "Especifique una Fecha Inicial "
                        + "anterior a la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
        } else {
            if (this.getDateini().length() > 0
                    || this.getDatefin().length() > 0) {
                session.setAttribute("mainopt", "lecturas");
                session.setAttribute("msj", "Ingrese tanto la Fecha Inicial "
                        + "como la Fecha Final para realizar la busqueda");
                return SUCCESS;
            }
        }

        if (this.getIdmedidor().length() > 0) {
            inputs = true;
            if (displecs.size() > 0) {
                ArrayList<Lectura> temp = new ArrayList();
                for (Lectura l : displecs) {
                    if (l.getVcidmedidor() == Integer.parseInt(this.getIdmedidor())) {
                        temp.add(l);
                    }
                }
                displecs = temp;
            } else {
                inputs = true;
                displecs = ac.getLecturaBy("idmedidor", this.getIdmedidor());
            }
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

        if (displecs.size() > 0) {
            if (displecs.size() > 10) {
                session.setAttribute("displecs", displecs.subList(0, 10));
            } else {
                List<Lectura> temp = new ArrayList();
                for (Lectura l : displecs) {
                    temp.add(l);
                }
                session.setAttribute("displecs", temp);
            }
            session.setAttribute("searchlecturas", displecs);
            session.setAttribute("totallecs", displecs.size());
            session.setAttribute("actuallec", 1);
            session.setAttribute("mainopt", "searchLecturas");
            return SUCCESS;
        } else if (inputs) {
            session.setAttribute("mainopt", "lecturas");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        } else {
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
            for (int i = lim; i < lim + 11; i++) {
                try {
                    displecs.add(lecs.get(i));
                } catch (IndexOutOfBoundsException e) {
                    i = lim + 10;
                }
            }
        } else {
            opt = 1;
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

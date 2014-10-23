/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Politica;
import com.imsys.admin.dao.entity.Rol;
import com.imsys.admin.dao.entity.Usuario;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class UsersAction extends ActionSupport implements ServletRequestAware {

    private String btnopt;
    private String code;
    private HttpSession session;

    public String getUsuarios() {
        AdminControl ac = new AdminControl();
        ArrayList<Usuario> lecs = ac.getUsuarios();
        List<Usuario> displecs = new ArrayList();
        if (lecs.size() > 10) {
            displecs = lecs.subList(0, 10);
        } else {
            for (Usuario l : lecs) {
                displecs.add(l);
            }
        }
        session.setAttribute("usuarios", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", 1);
        session.setAttribute("mainopt", "usuarios");
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] consultó los Usuarios.",
                u.getVccoduser(), "Main/Consultas/Usuarios");
        return SUCCESS;
    }

    public String displayUserN() {
        AdminControl ac = new AdminControl();
        ArrayList<Usuario> lecs = ac.getUsuarios();
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Usuario> displecs = new ArrayList();
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
                for (Usuario l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("usuarios", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "usuarios");
        return SUCCESS;
    }

    public String searchUsers() {
        List<Usuario> te = new ArrayList();
        AdminControl ac = new AdminControl();

        if (this.getCode().length() > 0) {
            Usuario t = ac.getUserbyCode(this.getCode());
            if (t != null) {
                te.add(t);
            }
        } else {
            session.setAttribute("mainopt", "usuarios");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }

        if (te.size() > 0) {
            session.setAttribute("usuarios", te);
            session.setAttribute("searchusers", te);
            session.setAttribute("totalevec", te.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchUsers");
            return SUCCESS;
        } else {
            session.setAttribute("mainopt", "usuarios");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        }
    }

    public String displaySearchUserN() {
        ArrayList<Usuario> lecs = (ArrayList<Usuario>) session.getAttribute("searchusers");
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Usuario> displecs = new ArrayList();
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
                for (Usuario l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("usuarios", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "searchUsers");
        return SUCCESS;
    }

    public String getRoles() {
        AdminControl ac = new AdminControl();
        ArrayList<Rol> lecs = ac.getRoles();
        List<Rol> displecs = new ArrayList();
        if (lecs.size() > 10) {
            displecs = lecs.subList(0, 10);
        } else {
            for (Rol l : lecs) {
                displecs.add(l);
            }
        }
        session.setAttribute("roles", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", 1);
        session.setAttribute("mainopt", "roles");
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] consultó los Roles.", u.getVccoduser(), "Main/Consultas/Roles");
        return SUCCESS;
    }

    public String displayRolN() {
        AdminControl ac = new AdminControl();
        ArrayList<Rol> lecs = ac.getRoles();
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Rol> displecs = new ArrayList();
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
                for (Rol l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("roles", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "roles");
        return SUCCESS;
    }

    public String searchRoles() {
        List<Rol> te = new ArrayList();
        AdminControl ac = new AdminControl();

        if (this.getCode().length() > 0) {
            try {
                Rol t = ac.getRolbyCode(Integer.parseInt(this.getCode()));
                if (t != null) {
                    te.add(t);
                }
            } catch (NumberFormatException e) {
                session.setAttribute("mainopt", "roles");
                session.setAttribute("msj", "El Parámetro de busqueda Código solo acepta valore numéricos");
                return SUCCESS;
            }
        } else {
            session.setAttribute("mainopt", "roles");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }

        if (te.size() > 0) {
            session.setAttribute("roles", te);
            session.setAttribute("searchroles", te);
            session.setAttribute("totalevec", te.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchRoles");
            return SUCCESS;
        } else {
            session.setAttribute("mainopt", "roles");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        }
    }

    public String displaySearchRolN() {
        ArrayList<Rol> lecs = (ArrayList<Rol>) session.getAttribute("searchroles");
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Rol> displecs = new ArrayList();
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
                for (Rol l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("roles", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "searchRoles");
        return SUCCESS;
    }

    public String getPoliticas() {
        AdminControl ac = new AdminControl();
        ArrayList<Politica> lecs = ac.getPoliticas();
        List<Politica> displecs = new ArrayList();
        if (lecs.size() > 10) {
            displecs = lecs.subList(0, 10);
        } else {
            for (Politica l : lecs) {
                displecs.add(l);
            }
        }
        session.setAttribute("politicas", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", 1);
        session.setAttribute("mainopt", "politicas");
        Usuario u = (Usuario) session.getAttribute("userObject");
        ac.addBitacoraEntry("El usuario [" + u.getVcnombre() + "] consultó las Políticas.", u.getVccoduser(), "Main/Consultas/Politicas");
        return SUCCESS;
    }

    public String displayPoliticaN() {
        AdminControl ac = new AdminControl();
        ArrayList<Politica> lecs = ac.getPoliticas();
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Politica> displecs = new ArrayList();
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
                for (Politica l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("politicas", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "politicas");
        return SUCCESS;
    }

    public String searchPoliticas() {
        List<Politica> te = new ArrayList();
        AdminControl ac = new AdminControl();

        if (this.getCode().length() > 0) {
            Politica t = ac.getPoliticabyCode(this.getCode());
            if (t != null) {
                te.add(t);
            }
        } else {
            session.setAttribute("mainopt", "politicas");
            session.setAttribute("msj", "Ingrese por lo menos un parámetro de busqueda");
            return SUCCESS;
        }

        if (te.size() > 0) {
            session.setAttribute("politicas", te);
            session.setAttribute("searchpoliticas", te);
            session.setAttribute("totalevec", te.size());
            session.setAttribute("actualevec", 1);
            session.setAttribute("mainopt", "searchPoliticas");
            return SUCCESS;
        } else {
            session.setAttribute("mainopt", "politicas");
            session.setAttribute("msj", "No existen resultados para esta busqueda");
            return SUCCESS;
        }
    }

    public String displaySearchPoliticaN() {
        ArrayList<Politica> lecs = (ArrayList<Politica>) session.getAttribute("searchpoliticas");
        int total = (lecs.size() / 10) + 1;
        int opt = Integer.parseInt(this.getBtnopt());
        List<Politica> displecs = new ArrayList();
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
                for (Politica l : lecs) {
                    displecs.add(l);
                }
            }
        }
        session.setAttribute("politicas", displecs);
        session.setAttribute("totallecs", lecs.size());
        session.setAttribute("actuallec", opt);
        session.setAttribute("mainopt", "searchPoliticas");
        return SUCCESS;
    }

    public String getBtnopt() {
        return btnopt;
    }

    public void setBtnopt(String btnopt) {
        this.btnopt = btnopt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }

}

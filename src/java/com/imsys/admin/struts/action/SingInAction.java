/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.imsys.admin.dao.control.AdminControl;
import com.imsys.admin.dao.entity.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class SingInAction extends ActionSupport implements ServletRequestAware{
    
    private String username;
    private String password;
    private HttpSession session;
    
    public String singin(){
        // check the entered userName and password
        if(getUsername() == null || getUsername().length() < 1){
            session.setAttribute("msj", "Ingrese el Nombre de Usuario.");
            return INPUT;
        } else if(getPassword() == null || getPassword().length() < 1){
            session.setAttribute("msj", "Ingrese la Contraseña.");
            return INPUT;
        } else{
            AdminControl ac = new AdminControl();
            Usuario u = ac.validateLogin(getUsername(), getPassword());
            if(u != null){
                if(u.getLactivo().equals("false")){
                    session.setAttribute("msj", "El usuario se encuentra bloqueado. Por favor, comuníquese con el administrador.");
                    return INPUT;
                } else{
                    session.setAttribute("username", getUsername());
                    session.setAttribute("userObject", u);
                    session.setAttribute("mainopt", "home");
                    ac.addBitacoraEntry("El usuario ["+ u.getVcnombre()+"] ingresó al sistema.", u.getVccoduser(), "Main/SingIn");
                    return SUCCESS;
                }
            } else{
                session.setAttribute("msj", "El nombre de usuario o contraseña no es valido.");
                return INPUT;
            }
        }
    }
     
    public String singout() {
        // remove username from the session
        if (session.getAttribute("username") != null) {
            String name = (String) session.getAttribute("username");
            session.removeAttribute("username");
            AdminControl ac = new AdminControl();
            Usuario u = ac.getUserbyName(name);
            ac.addBitacoraEntry("El usuario ["+ u.getVcnombre()+"] salió del sistema.", u.getVccoduser(), "Main/SingOut");
        }
        return SUCCESS;
    }
    
    public String goHome(){
        session.setAttribute("mainopt", "home");
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }
}

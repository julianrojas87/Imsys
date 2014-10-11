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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class LecturasAction extends ActionSupport implements ServletRequestAware {
    
    private HttpSession session;
    
    public String getLecturas(){
        AdminControl ac = new AdminControl();
        ArrayList<Lectura> lecs = ac.getLecturas();
        session.setAttribute("lecturas", lecs);
        session.setAttribute("mainopt", "lecturas");
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }
    
}

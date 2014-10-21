/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author julian
 */
public class AdminSysLoadAction extends ActionSupport implements ServletRequestAware {

    private HttpSession session;
    private InputStream inputStream;

    public String manageAjaxRequest() {
        inputStream = new ByteArrayInputStream(Integer.toString((int) session.getAttribute("loading")).getBytes());
        return SUCCESS;
    }

    public String displayLoadInfo() {
        session.setAttribute("loading", 0);
        session.setAttribute("mainopt", "infoLoad");
        return SUCCESS;
    }

    public String loadInfo() throws InterruptedException {
        session.setAttribute("mainopt", "loading");
        Thread.sleep(2000);
        session.setAttribute("loading", 25);
        Thread.sleep(2000);
        session.setAttribute("loading", 50);
        Thread.sleep(2000);
        session.setAttribute("loading", 75);
        Thread.sleep(2000);
        session.setAttribute("loading", 100);
        Thread.sleep(1000);
        session.setAttribute("msj", "Proceso completado!!");
        session.setAttribute("mainopt", "infoLoad");
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.session = hsr.getSession();
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}

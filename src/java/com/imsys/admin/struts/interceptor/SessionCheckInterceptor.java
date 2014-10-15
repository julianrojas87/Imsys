/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imsys.admin.struts.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;

/**
 *
 * @author julian
 */
public class SessionCheckInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map<String,Object> sessionMap = ai.getInvocationContext().getSession();

        if (sessionMap == null || sessionMap.isEmpty()
                || sessionMap.get("username") == null) {
            sessionMap.put("msj", "Su sesión ha expirado, ingrese de nuevo");
            return "sessionexpired";
        }
        String actionResult = ai.invoke();
        return actionResult;
    }
}

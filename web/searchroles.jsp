<%-- 
    Document   : searchroles
    Created on : Oct 17, 2014, 3:23:55 PM
    Author     : julian
--%>

<%@page import="com.imsys.admin.dao.entity.Rol"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    List<Rol> displecs = (List<Rol>) session.getAttribute("roles");
    int total = (int) session.getAttribute("totallecs");
    int numBotones = (total / 10) + 1;
    int actuallec = (int) session.getAttribute("actuallec");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table.css">
    </head>
    <body>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th colspan="4">Consulta de Roles</th>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <s:form theme="simple" action="/SearchRoles">
                                C&oacute;digo de Rol: <s:textfield theme="simple" name="code"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="/imsys/resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>C&oacute;digo de Rol</th>
                        <th>Descripci&oacute;n</th>
                        <th>Rlim. Usuario</th>
                        <th>R. Operarion</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span>Previous</span>
                                            <%int prev = actuallec - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span><%=numBotones%></span>
                                            <s:param name="btnopt"><%=numBotones%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                    } else {
                                        for (int i = 0; i < numBotones; i++) {
                                            int num = i + 1;
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchRolN">
                                            <span>Next</span>
                                            <%int next = actuallec + 1;%>
                                            <s:param name="btnopt"><%=next%></s:param>
                                        </s:a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                    <%
                        int mod = 0;
                        for (Rol l : displecs) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=l.getNcodroll()%></td>
                        <td><%=l.getVcdesroll()%></td>
                        <td><%=l.getLrlimusuario()%></td>
                        <td><%=l.getLroperario()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=l.getNcodroll()%></td>
                        <td><%=l.getVcdesroll()%></td>
                        <td><%=l.getLrlimusuario()%></td>
                        <td><%=l.getLroperario()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>


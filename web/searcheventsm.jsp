<%-- 
    Document   : searcheventsm
    Created on : Oct 15, 2014, 10:18:24 AM
    Author     : julian
--%>

<%@page import="java.util.List"%>
<%@page import="com.imsys.admin.dao.entity.EventoMedidor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    List<EventoMedidor> dispeventsm = (List<EventoMedidor>) session.getAttribute("dispeventsm");
    int total = (int) session.getAttribute("totalevem");
    int numBotones = (total / 10) + 1;
    int actualevem = (int) session.getAttribute("actualevem");
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
                        <th colspan="3">Consulta de Eventos de Medidor</th>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <s:form theme="simple" action="/SearchEventsM">
                                Fecha: <s:textfield theme="simple" name="date" type="date"/>
                                C&oacute;digo Evento: <s:textfield theme="simple" name="code"/>
                                Serial Medidor: <s:textfield theme="simple" name="serie"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="/imsys/resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>Fecha</th>
                        <th>C&oacute;digo de Evento</th>
                        <th>Serial de Medidor</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span>Previous</span>
                                            <%int prev = actualevem - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
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
                                        <s:a action="/DisplaySearchEventMN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchEventMN">
                                            <span>Next</span>
                                            <%int next = actualevem + 1;%>
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
                        for (EventoMedidor em : dispeventsm) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=em.getDfechaevemed()%></td>
                        <td><%=em.getNcodtipoeve()%></td>
                        <td><%=em.getVcserie()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=em.getDfechaevemed()%></td>
                        <td><%=em.getNcodtipoeve()%></td>
                        <td><%=em.getVcserie()%></td>
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

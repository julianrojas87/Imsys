<%-- 
    Document   : searcheventsm
    Created on : Oct 15, 2014, 10:18:24 AM
    Author     : julian
--%>

<%@page import="java.util.List"%>
<%@page import="com.imsys.admin.dao.entity.EventoMedidor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<%
    List<EventoMedidor> dispeventsm = (List<EventoMedidor>) session.getAttribute("dispeventsm");
    int total = ((Integer) session.getAttribute("totalevem")).intValue();
    int numBotones = (total / 10) + 1;
    int actualevem = ((Integer) session.getAttribute("actualevem")).intValue();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table.css">
        <sj:head jquerytheme="cupertino"/>
    </head>
    <body>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th colspan="4">Consulta de Eventos de Medidor</th>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <s:form theme="simple" action="/SearchEventsM">
                                Fecha Inicial: <sj:datepicker displayFormat="dd/mm/yy" size="5" theme="simple" id="date1" name="dateini" changeMonth="true" changeYear="true"/>
                                Fecha Final:<sj:datepicker displayFormat="dd/mm/yy" size="5" theme="simple" id="date2" name="datefin" changeMonth="true" changeYear="true"/>
                                C&oacute;digo Evento: <s:textfield theme="simple" name="code" size="5"/>
                                Serial Medidor: <s:textfield theme="simple" name="serie" size="5"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>Fecha</th>
                        <th>C&oacute;digo de Evento</th>
                        <th>Serial de Medidor</th>
                        <th>Descripci&oacute;n</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
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
                        <td><%=em.getVcdescripcion()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=em.getDfechaevemed()%></td>
                        <td><%=em.getNcodtipoeve()%></td>
                        <td><%=em.getVcserie()%></td>
                        <td><%=em.getVcdescripcion()%></td>
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

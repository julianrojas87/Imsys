<%-- 
    Document   : searcheventsc
    Created on : Oct 15, 2014, 9:43:27 AM
    Author     : julian
--%>

<%@page import="com.imsys.admin.dao.entity.EventoCaja"%>
<%@page import="java.util.List"%>
<%@page import="com.imsys.admin.dao.entity.EventoMedidor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<%
    List<EventoCaja> dispeventsc = (List<EventoCaja>) session.getAttribute("dispeventsc");
    int total = ((Integer) session.getAttribute("totalevec")).intValue();
    int numBotones = (total / 10) + 1;
    int actualevec = ((Integer) session.getAttribute("actualevec")).intValue();
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
                        <th colspan="3">Consulta de Eventos de Caja</th>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <s:form theme="simple" action="/SearchEventsC">
                                FFecha Inicial: <sj:datepicker displayFormat="dd/mm/yy" size="10" theme="simple" id="date1" name="dateini" changeMonth="true" changeYear="true"/>
                                Fecha Final:<sj:datepicker displayFormat="dd/mm/yy" size="10" theme="simple" id="date2" name="datefin" changeMonth="true" changeYear="true"/>
                                C&oacute;digo Evento: <s:textfield theme="simple" name="code"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>Fecha</th>
                        <th>C&oacute;digo de Evento</th>
                        <th>Descripci&oacute;n</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span>Previous</span>
                                            <%int prev = actualevec - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
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
                                        <s:a action="/DisplaySearchEventCN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchEventCN">
                                            <span>Next</span>
                                            <%int next = actualevec + 1;%>
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
                        for (EventoCaja ec : dispeventsc) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=ec.getDfechaeve()%></td>
                        <td><%=ec.getNcodtipoeve()%></td>
                        <td><%=ec.getVcdescripcion()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=ec.getDfechaeve()%></td>
                        <td><%=ec.getNcodtipoeve()%></td>
                        <td><%=ec.getVcdescripcion()%></td>
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

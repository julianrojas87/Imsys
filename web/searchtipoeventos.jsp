<%-- 
    Document   : searchtipoeventos
    Created on : Oct 17, 2014, 2:20:48 PM
    Author     : julian
--%>

<%@page import="com.imsys.admin.dao.entity.TipoEvento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    List<TipoEvento> displecs = (List<TipoEvento>) session.getAttribute("tipoEventos");
    int total = (int) session.getAttribute("totalevec");
    int numBotones = (total / 10) + 1;
    int actuallec = (int) session.getAttribute("actualevec");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th colspan="4">Consulta de Tipos de Eventos</th>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <s:form theme="simple" action="/SearchTipoEvents">
                                C&oacute;digo Evento: <s:textfield theme="simple" name="code"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="/imsys/resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>C&oacute;digo de Evento</th>
                        <th>Descripci&oacute;n</th>
                        <th>Aplica para Caja</th>
                        <th>Aplica para Medidor</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span>Previous</span>
                                            <%int prev = actuallec - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
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
                                        <s:a action="/DisplaySearchTipoEventN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchTipoEventN">
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
                        for (TipoEvento ec : displecs) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=ec.getNccodtipoeve()%></td>
                        <td><%=ec.getVcdeseve()%></td>
                        <td><%=ec.getLaaplicacaja()%></td>
                        <td><%=ec.getLaaplicamed()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=ec.getNccodtipoeve()%></td>
                        <td><%=ec.getVcdeseve()%></td>
                        <td><%=ec.getLaaplicacaja()%></td>
                        <td><%=ec.getLaaplicamed()%></td>
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

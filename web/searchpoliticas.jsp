<%-- 
    Document   : searchpoliticas
    Created on : Oct 17, 2014, 4:38:43 PM
    Author     : julian
--%>

<%@page import="com.imsys.admin.dao.entity.Politica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
    List<Politica> displecs = (List<Politica>) session.getAttribute("politicas");
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
                        <th colspan="6">Consulta de Pol&iacute;ticas</th>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <s:form theme="simple" action="/SearchPoliticas">
                                C&oacute;digo de Rol: <s:textfield theme="simple" name="code"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="/imsys/resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>C&oacute;digo de Rutina</th>
                        <th>Opci&oacute;n</th>
                        <th>Grabar</th>
                        <th>Borrar</th>
                        <th>Imprimir</th>
                        <th>Simex</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="6">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span>Previous</span>
                                            <%int prev = actuallec - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
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
                                        <s:a action="/DisplaySearchPoliticaN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplaySearchPoliticaN">
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
                        for (Politica l : displecs) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=l.getVccodrutina()%></td>
                        <td><%=l.getLopcion()%></td>
                        <td><%=l.getLgrabar()%></td>
                        <td><%=l.getLborrar()%></td>
                        <td><%=l.getLimprimir()%></td>
                        <td><%=l.getLsimex()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=l.getVccodrutina()%></td>
                        <td><%=l.getLopcion()%></td>
                        <td><%=l.getLgrabar()%></td>
                        <td><%=l.getLborrar()%></td>
                        <td><%=l.getLimprimir()%></td>
                        <td><%=l.getLsimex()%></td>
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

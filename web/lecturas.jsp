<%-- 
    Document   : lecturas
    Created on : Oct 11, 2014, 5:11:33 PM
    Author     : julian
--%>

<%@page import="java.util.List"%>
<%@page import="com.imsys.admin.dao.entity.Lectura"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    List<Lectura> displecs = (List<Lectura>) session.getAttribute("lecturas");
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
                        <td colspan="11">
                            <s:form theme="simple" action="/SearchLecs">
                                Fecha: <s:textfield theme="simple" name="date" type="date"/>
                                ID Medidor: <s:textfield theme="simple" name="idmedidor"/>
                                Serie Medidor: <s:textfield theme="simple" name="serie"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="/imsys/resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>Fecha</th>
                        <th>ID Medidor</th>
                        <th>Serie</th>
                        <th>Voltaje</th>
                        <th>Corriente</th>
                        <th>Potencia Activa</th>
                        <th>Potencia Reactiva</th>
                        <th>Potencia Aparente</th>
                        <th>Factor Potencia</th>
                        <th>Energ&iacute;a Activa</th>
                        <th>Energ&iacute;a Reactiva</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="11">
                            <div id="paging">
                                <ul>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span>Previous</span>
                                            <%int prev = actuallec - 1;%>
                                            <s:param name="btnopt"><%=prev%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                        if (numBotones > 10) {
                                    %>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span>1</span>
                                            <s:param name="btnopt">1</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span>2</span>
                                            <s:param name="btnopt">2</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span>3</span>
                                            <s:param name="btnopt">3</s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        ...
                                    </li>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span><%=numBotones - 2%></span>
                                            <s:param name="btnopt"><%=numBotones - 2%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplayLecN">
                                            <span><%=numBotones - 1%></span>
                                            <s:param name="btnopt"><%=numBotones - 1%></s:param>
                                        </s:a>
                                    </li>
                                    <li>
                                        <s:a action="/DisplayLecN">
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
                                        <s:a action="/DisplayLecN">
                                            <span><%=num%></span>
                                            <s:param name="btnopt"><%=num%></s:param>
                                        </s:a>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                    <li>
                                        <s:a action="/DisplayLecN">
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
                        for (Lectura l : displecs) {
                            mod++;
                            if (mod % 2 == 0) {
                    %>
                    <tr class="alt">
                        <td><%=l.getTsfecha()%></td>
                        <td><%=l.getVcidmedidor()%></td>
                        <td><%=l.getVcserie()%></td>
                        <td><%=l.getVcvoltaje()%></td>
                        <td><%=l.getVccorriente()%></td>
                        <td><%=l.getVcpotactiva()%></td>
                        <td><%=l.getVcpotreactiva()%></td>
                        <td><%=l.getVcpotaparente()%></td>
                        <td><%=l.getVcfactorpot()%></td>
                        <td><%=l.getVceneactiva()%></td>
                        <td><%=l.getVcenereactiva()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=l.getTsfecha()%></td>
                        <td><%=l.getVcidmedidor()%></td>
                        <td><%=l.getVcserie()%></td>
                        <td><%=l.getVcvoltaje()%></td>
                        <td><%=l.getVccorriente()%></td>
                        <td><%=l.getVcpotactiva()%></td>
                        <td><%=l.getVcpotreactiva()%></td>
                        <td><%=l.getVcpotaparente()%></td>
                        <td><%=l.getVcfactorpot()%></td>
                        <td><%=l.getVceneactiva()%></td>
                        <td><%=l.getVcenereactiva()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <c:if test="${msj!=null}">
            <script>
                self.alert("<%=session.getAttribute("msj")%>");
                <%
                    session.setAttribute("msj", null);
                %>
            </script>
        </c:if>
    </body>
</html>

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
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    List<Lectura> displecs = (List<Lectura>) session.getAttribute("lecturas");
    int total = ((Integer) session.getAttribute("totallecs")).intValue();
    int numBotones = (total / 10) + 1;
    int actuallec = ((Integer) session.getAttribute("actuallec")).intValue();
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
                        <th colspan="13">Consulta de Lecturas</th>
                    </tr>
                    <tr>
                        <td colspan="13">
                            <s:form theme="simple" action="/SearchLecs">
                                Fecha Inicial: <sj:datepicker displayFormat="dd/mm/yy" size="10" theme="simple" id="date1" name="dateini" changeMonth="true" changeYear="true"/>
                                Fecha Final:<sj:datepicker displayFormat="dd/mm/yy" size="10" theme="simple" id="date2" name="datefin" changeMonth="true" changeYear="true"/>
                                ID Medidor: <s:textfield theme="simple" name="idmedidor" size="10"/>
                                Serial Medidor: <s:textfield theme="simple" name="serie" size="10"/>
                                <s:submit theme="simple" width="25" height="25" type="image" value="search" src="resources/img/buttons/search-icon.jpg"/>
                            </s:form>
                        </td>
                    </tr>
                    <tr>
                        <th>Fecha</th>
                        <th>ID Medidor</th>
                        <th>Serial de Medidor</th>
                        <th>Tensi&oacute;n (Vol)</th>
                        <th>Corriente (A)</th>
                        <th>Potencia Activa (kW)</th>
                        <th>Potencia Reactiva (kVAr)</th>
                        <th>Potencia Aparente (VA)</th>
                        <th>Factor Potencia</th>
                        <th>Energ&iacute;a Activa (kWh)</th>
                        <th>Energ&iacute;a Reactiva (kVArh)</th>
                        <th>Energ&iacute;a Activa Calculada</th>
                        <th>Energ&iacute;a Reactiva Calculada</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="13">
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
                        <td><%=l.getNidmedidor()%></td>
                        <td><%=l.getVcserie()%></td>
                        <td><%=l.getVcvoltaje()%></td>
                        <td><%=l.getVccorriente()%></td>
                        <td><%=l.getVcpotactiva()%></td>
                        <td><%=l.getVcpotreactiva()%></td>
                        <td><%=l.getVcpotaparente()%></td>
                        <td><%=l.getVcfactorpot()%></td>
                        <td><%=l.getVceneactiva()%></td>
                        <td><%=l.getVcenereactiva()%></td>
                        <td><%=l.getVccalceneact()%></td>
                        <td><%=l.getVccalcenereact()%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td><%=l.getTsfecha()%></td>
                        <td><%=l.getNidmedidor()%></td>
                        <td><%=l.getVcserie()%></td>
                        <td><%=l.getVcvoltaje()%></td>
                        <td><%=l.getVccorriente()%></td>
                        <td><%=l.getVcpotactiva()%></td>
                        <td><%=l.getVcpotreactiva()%></td>
                        <td><%=l.getVcpotaparente()%></td>
                        <td><%=l.getVcfactorpot()%></td>
                        <td><%=l.getVceneactiva()%></td>
                        <td><%=l.getVcenereactiva()%></td>
                        <td><%=l.getVccalceneact()%></td>
                        <td><%=l.getVccalcenereact()%></td>
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

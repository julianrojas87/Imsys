<%-- 
    Document   : lecturas
    Created on : Oct 11, 2014, 5:11:33 PM
    Author     : julian
--%>

<%@page import="com.imsys.admin.dao.entity.Lectura"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<Lectura> lecturas = (ArrayList<Lectura>)session.getAttribute("lecturas");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table.css">
    </head>
    <body>
        <div class="datagrid"><table>
                <thead>
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
                                        <a href="#"><span>Previous</span></a>
                                    </li>
                                    <li>
                                        <a href="#" class="active"><span>1</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span>2</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span>3</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span>4</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span>5</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span>Next</span></a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </tfoot>
                <tbody>
                    <%
                    int mod = 0;
                    for(Lectura l : lecturas){
                        mod++;
                        if(mod % 2 == 0){
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
                        } else{
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
    </body>
</html>

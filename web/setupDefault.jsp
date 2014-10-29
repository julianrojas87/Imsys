<%-- 
    Document   : setupDefault
    Created on : Oct 22, 2014, 4:07:17 PM
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table_2.css">
    </head>
    <body>
        <div class="datagrid">
            <s:form theme="simple" action="/UpdateSetup">
                <table>
                    <thead>
                        <tr>
                            <th colspan="2">Default Setup</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                NIT Empresa: <s:textfield name="nitempresa"/><br>
                                C&oacute;digo Subestaci&oacute;n: <s:textfield name="codsubest"/><br> 
                                C&oacute;digo Circuito: <s:textfield name="codcto"/><br> 
                                C&oacute;digo Transformador: <s:textfield name="codtrans"/><br> 
                                C&oacute;digo Caja: <s:textfield name="codcaja"/><br>
                                Puerto HTTP: <s:textfield name="ptohttp"/><br>
                                Puerto Socket: <s:textfield name="ptosocket"/><br>
                                Direcci&oacute;n IP Local: <s:textfield name="diriploc"/><br>
                                Direcci&oacute;n IP WAN: <s:textfield name="diripwan"/><br>
                                Tipo de Red: WAN<s:checkbox name="wan"/> LAN <s:checkbox name="lan"/>
                            </td>
                            <td>
                                Admin Servlet: <s:textfield name="servlet"/><br>
                                Frecuencia de Lectura (minutos): <s:textfield name="freclec"/><br>
                                Delta de Mantenimiento (minutos): <s:textfield name="deltaman"/><br>
                                Puerto Serial: <s:textfield name="ptoserial"/><br>
                                Codificaci&oacute;n: <s:textfield name="codific"/><br> 
                                Velocidad (Kbps): <s:select cssStyle="WIDTH:153px" name="velocidad" list="speeds"/><br> 
                                Bits de Datos: <s:select cssStyle="WIDTH:153px" name="bitsdatos" list="databits"/><br> 
                                Paridad: <s:select cssStyle="WIDTH:153px" name="paridad" list="parity"/><br>
                                Bits de Parada: <s:select cssStyle="WIDTH:153px" name="bitsparada" list="stopbits"/><br>
                                Coordenadas GPS: Lat: <s:textfield size="10" name="latitud"/> Long: <s:textfield size="10" name="longitud"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2"><s:submit value="Actualizar"/><br> </th>
                        </tr>
                    </tbody>
                </table>
            </s:form>
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

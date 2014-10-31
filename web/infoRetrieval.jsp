<%-- 
    Document   : infoRetrieval
    Created on : Oct 18, 2014, 5:04:04 PM
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table_1.css">
        <sj:head jquerytheme="cupertino"/>
    </head>
    <body>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th>Recuperaci&oacute;n de Informaci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <h3>
                                Generaci&oacute;n y descarga de archivo .txt
                                <br>que contiene las lecturas de los medidores
                                <br>registradas entre las fechas especificadas a continuaci&oacute;n
                            </h3>
                            <h4>
                                <br>El Formato del archivo descargado es el siguiente:
                                <br>Fecha;ID Medidor;Serial;Voltaje;Corriente;Pot Activa;Pot Reactiva;Pot Aparente;Factor Pot;Ene Activa;Ene Reactiva;Ene Act Calc;Ene React Calc;Enviado
                            </h4>
                            <s:form theme="simple" action="/RetrieveInfo">
                                Fecha Inicial: <sj:datepicker displayFormat="dd/mm/yy" theme="simple" id="date1" name="dateini" changeMonth="true" changeYear="true"/>
                                Fecha Final: <sj:datepicker displayFormat="dd/mm/yy" theme="simple" id="date2" name="datefin" changeMonth="true" changeYear="true"/>
                                <br><br><s:submit value="Descargar"/>
                            </s:form>
                        </td>
                    </tr>
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

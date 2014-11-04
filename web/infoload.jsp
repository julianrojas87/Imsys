<%-- 
    Document   : infoload
    Created on : Oct 20, 2014, 9:09:30 AM
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/Table_1.css">
    </head>
    <body>
        <div class="datagrid">
            <table>
                <thead>
                    <tr>
                        <th>Carga de Informaci&oacute;n desde Sistema Administrativo</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <h3>
                                Carga de informaci&oacute;n de Medidores, Usuarios, Roles y 
                                <br>Políticas desde el Sistema Administrativo
                            </h3>
                            <s:form theme="simple" action="/LoadInfo">
                                <br><br><s:submit value="Cargar Información"/>
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
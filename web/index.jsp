<%-- 
    Document   : index
    Created on : Oct 6, 2014, 1:24:08 PM
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>IMSYS</title>
        <style type="text/css">
            @import "resources/css/style.css";
            .Estilo4 {color: #FF0000}
        </style>
    </head>
    <body style="background-color: #ffffff;">
        <div  style="position: relative;  top: 100px;" >
            <table border="0" align="center" width="53" bgcolor="white">
                <tr>
                    <td align="center" colspan="2">
                        <div><img src="resources/img/imsys.jpg" width="496" height="146" alt="IMSYS" /></div>
                    </td>
                </tr>
                <tr>
                    <td align="center"><div><h4>Desarrollo de ideas sin fronteras</h4></div></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <h1>Web App de Sistema Embebido</h1>
                        </div>
                        <h5 align="center"> Version 0.1</h5>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <s:form action="/SingIn" styleId="form1">
                            <table border="0" align="center">
                                <tr>
                                    <td>
                                        <div align="right">
                                            <s:textfield name="username" label="Nombre de Usuario"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div align="right">
                                            <s:password name="password" label="Contraseña"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div align="right">
                                            <s:submit value="Entrar"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </s:form>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <span>Desarrollado por: <a href="http://www.metrolinkeu.com" target="_blank"><strong>IMSYS</strong></a> </span>
                            <br/>
                            <span>&copy; 2014 Todos los derechos reservados. </span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div align="center">
                            <img src="resources/img/footer.png" width="653" height="29" alt="IMSYS" />
                        </div>
                    </td>
                </tr>
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

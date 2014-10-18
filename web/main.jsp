<%-- 
    Document   : test
    Created on : Oct 9, 2014, 12:00:45 PM
    Author     : julian
--%>
<%@page import="java.util.Calendar"%>
<%@page import="com.imsys.admin.dao.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    Usuario u = (Usuario) session.getAttribute("userObject");
    String rol = u.getVcroll();
    Calendar cal = Calendar.getInstance();
    String date = cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR);
%>
<html>
    <head>
        <title>IMSYS WEB</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css/simex-1.css"/>
        <link rel="stylesheet" href="resources/css/Menu.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="banner">
            <table width="100%" border="0" align="center">
                <tr>
                    <td align="left" width="451" height="100"><div><img src="resources/img/imsys.jpg" width="451" height="100" alt="IMSYS"/></div></td>
                    <td align="right" width="100%">
                        <div id="texto"><strong>Bienvenido: <%=(String) session.getAttribute("username")%></strong>&nbsp;&nbsp;
                            <br/>Rol de usuario: <strong> <%=rol%></strong>
                            <br/>Fecha de ingreso al sistema: <strong><%=date%></strong>
                            <br/>[<a href="/imsys/SingOut">Cerrar sesi&oacute;n</a>]
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <table width="93%" border="0" align="center">
            <tr>
                <td align="left" width="200">
                    <div id='cssmenu'>
                        <ul>
                            <li><a href='/imsys/GoHome'><span>Home</span></a></li>
                            <li class='has-sub'><a href='#'><span>Archivos</span></a>
                                <ul>
                                    <li><a href="#"><span>Setup Default</span></a>
                                    <li><a href="/imsys/VerMedidores"><span>Medidores</span></a>
                                </ul>
                            </li>
                            <li class='has-sub'><a href='#'><span>Procesos</span></a>
                                <ul>
                                    <li><a href="#"><span>Recuperaci&oacute;n de Informaci&oacute;n</span></a>
                                    <li><a href="#"><span>Carga desde Sistema Central</span></a>
                                </ul>
                            </li>
                            <li class='has-sub'><a href='#'><span>Consultas</span></a>
                                <ul>
                                    <li><a href="/imsys/VerLecturas"><span>Consulta de Lecturas</span></a>
                                    <li class="has-sub"><a href="#"><span>Consulta de Eventos</span></a>
                                        <ul>
                                            <li><a href="/imsys/VerEventosCaja"><span>Eventos de Caja</span></a>
                                            <li><a href="/imsys/VerEventosMedidor"><span>Eventos de Medidores</span></a>
                                        </ul>
                                    <li><a href="/imsys/VerTipoEventos"><span>Consulta de Tipos de Eventos</span></a>
                                    <li><a href="/imsys/VerUsuarios"><span>Consulta de Usuarios</span></a>
                                    <li><a href="/imsys/VerRoles"><span>Consulta de Roles</span></a>
                                    <li><a href="/imsys/VerPoliticas"><span>Consulta de Pol&iacute;ticas</span></a>
                                </ul>
                            </li>
                            <li class='has-sub'><a href='#'><span>Utilidades</span></a>
                                <ul>
                                    <li><a href="#"><span>Versi&oacute;n</span></a>
                                </ul>
                            </li>
                        </ul> 
                    </div>
                </td>
                <td width="797px">
                    <%
                    String opt = (String) session.getAttribute("mainopt");
                    if(opt.equals("home")){
                    %>
                        <%@include file="/home.jsp"%>
                    <%
                    } else if(opt.equals("lecturas")){
                    %>
                        <%@include file="/lecturas.jsp"%>
                    <%
                    } else if(opt.equals("searchLecturas")){
                    %>
                        <%@include file="/searchlecs.jsp"%>
                    <%
                    } else if(opt.equals("eventosCaja")){
                    %>
                        <%@include file="/eventosCaja.jsp"%>
                    <%
                    } else if(opt.equals("eventosMedidor")){
                    %>
                        <%@include file="/eventosMedidor.jsp"%>
                    <%
                    } else if(opt.equals("searchEventC")){
                    %>
                        <%@include file="/searcheventsc.jsp"%>
                    <%
                    } else if(opt.equals("searchEventM")){
                    %>
                        <%@include file="/searcheventsm.jsp"%>
                    <%
                    } else if(opt.equals("tipoEventos")){
                    %>
                        <%@include file="/tipoeventos.jsp"%>
                    <%
                    } else if(opt.equals("searchTipoEventos")){
                    %>
                        <%@include file="/searchtipoeventos.jsp"%>
                    <%
                    } else if(opt.equals("usuarios")){
                    %>
                        <%@include file="/usuarios.jsp"%>
                    <%
                    } else if(opt.equals("searchUsers")){
                    %>
                        <%@include file="/searchusers.jsp"%>
                    <%
                    } else if(opt.equals("roles")){
                    %>
                        <%@include file="/roles.jsp"%>
                    <%
                    } else if(opt.equals("searchRoles")){
                    %>
                        <%@include file="/searchroles.jsp"%>
                    <%
                    } else if(opt.equals("politicas")){
                    %>
                        <%@include file="/politicas.jsp"%>
                    <%
                    } else if(opt.equals("searchPoliticas")){
                    %>
                        <%@include file="/searchpoliticas.jsp"%>
                    <%
                    }
                    %>
                </td>
                <td></td>
            </tr>
        </table>

        <c:if test="${msj!=null}">
            <script>
                self.alert("<%=session.getAttribute("msj")%>");
                <%  session.setAttribute("msj", null);%>
            </script>
        </c:if>
    </body>
</html>

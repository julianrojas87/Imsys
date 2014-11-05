<%-- 
    Document   : test
    Created on : Oct 9, 2014, 12:00:45 PM
    Author     : julian
--%>
<%@page import="java.util.Calendar"%>
<%@page import="com.imsys.admin.dao.entity.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    Usuario u = (Usuario) session.getAttribute("userObject");
    String rol = (String) session.getAttribute("rol");
    String date = (String) session.getAttribute("date");
%>
<html>
    <head>
        <title>IMSYS WEB</title>
        <link rel="shortcut icon" href="resources/img/logo-vertical.jpg"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/css/simex-1.css"/>
        <link rel="stylesheet" href="resources/css/Menu.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div>
            <table width="85%" border="0">
                <tr>
                    <td align="center"><div><img src="resources/img/logo-horizontal.jpg" width="451" height="200" alt="IMSYS"/></div></td>
                    <td align="right" width="100%">
                        <div id="texto"><strong>Bienvenido: <%=(String) session.getAttribute("username")%></strong>&nbsp;&nbsp;
                            <br/>Rol de usuario: <strong> <%=rol%></strong>
                            <br/>Fecha de ingreso al sistema: <strong><%=date%></strong>
                            <br/>[<s:a action="/SingOut" >Cerrar sesi&oacute;n</s:a>]
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
                                <li><s:a action="/GoHome"><span>Home</span></s:a></li>
                                <li class='has-sub'><a href='#'><span>Archivos</span></a>
                                    <ul>
                                        <li><s:a action="/VerSetup"><span>Configuraci&oacute;n</span></s:a>
                                    <li><s:a action="/VerMedidores"><span>Medidores</span></s:a>
                                    </ul>
                                </li>
                                <li class='has-sub'><a href='#'><span>Procesos</span></a>
                                    <ul>
                                        <li><s:a action="/InfoRetrieval"><span>Recuperaci&oacute;n de Informaci&oacute;n</span></s:a>
                                    <li><s:a action="/VerLoadInfo"><span>Carga desde Sistema Central</span></s:a>
                                    </ul>
                                </li>
                                <li class='has-sub'><a href='#'><span>Consultas</span></a>
                                    <ul>
                                        <li><s:a action="/VerLecturas"><span>Consulta de Lecturas</span></s:a>
                                        <li class="has-sub"><a href="#"><span>Consulta de Eventos</span></a>
                                            <ul>
                                                <li><s:a action="/VerEventosCaja"><span>Eventos de Caja</span></s:a>
                                            <li><s:a action="/VerEventosMedidor"><span>Eventos de Medidores</span></s:a>
                                            </ul>
                                        <li><s:a action="/VerTipoEventos"><span>Consulta de Tipos de Eventos</span></s:a>
                                    <li><s:a action="/VerUsuarios"><span>Consulta de Usuarios</span></s:a>
                                    <li><s:a action="/VerRoles"><span>Consulta de Roles</span></s:a>
                                    <li><s:a action="/VerPoliticas"><span>Consulta de Pol&iacute;ticas</span></s:a>
                                    </ul>
                                </li>
                                <li class='has-sub'><a href='#'><span>Utilidades</span></a>
                                    <ul>
                                        <li><s:a action="/VerVersion"><span>Versi&oacute;n</span></s:a>
                                    </ul>
                                </li>
                            </ul> 
                        </div>
                    </td>
                    <td width="797px">
                    <%
                        String opt = (String) session.getAttribute("mainopt");
                        if (opt.equals("home")) {
                    %>
                    <jsp:include page="home.jsp" /> 
                    <%
                    } else if (opt.equals("lecturas")) {
                    %>
                    <jsp:include page="lecturas.jsp" /> 
                    <%
                    } else if (opt.equals("searchLecturas")) {
                    %>
                    <jsp:include page="searchlecs.jsp" /> 
                    <%
                    } else if (opt.equals("eventosCaja")) {
                    %>
                    <jsp:include page="eventosCaja.jsp" /> 
                    <%
                    } else if (opt.equals("eventosMedidor")) {
                    %>
                    <jsp:include page="eventosMedidor.jsp" /> 
                    <%
                    } else if (opt.equals("searchEventC")) {
                    %>
                    <jsp:include page="searcheventsc.jsp" /> 
                    <%
                    } else if (opt.equals("searchEventM")) {
                    %>
                    <jsp:include page="searcheventsm.jsp" /> 
                    <%
                    } else if (opt.equals("tipoEventos")) {
                    %>
                    <jsp:include page="tipoeventos.jsp" /> 
                    <%
                    } else if (opt.equals("searchTipoEventos")) {
                    %>
                    <jsp:include page="searchtipoeventos.jsp" /> 
                    <%
                    } else if (opt.equals("usuarios")) {
                    %>
                    <jsp:include page="usuarios.jsp" /> 
                    <%
                    } else if (opt.equals("searchUsers")) {
                    %>
                    <jsp:include page="searchusers.jsp" /> 
                    <%
                    } else if (opt.equals("roles")) {
                    %>
                    <jsp:include page="roles.jsp" /> 
                    <%
                    } else if (opt.equals("searchRoles")) {
                    %>
                    <jsp:include page="searchroles.jsp" /> 
                    <%
                    } else if (opt.equals("politicas")) {
                    %>
                    <jsp:include page="politicas.jsp" /> 
                    <%
                    } else if (opt.equals("searchPoliticas")) {
                    %>
                    <jsp:include page="searchpoliticas.jsp" /> 
                    <%
                    } else if (opt.equals("infoRetrieval")) {
                    %>
                    <jsp:include page="infoRetrieval.jsp" /> 
                    <%
                    } else if (opt.equals("infoLoad")) {
                    %>
                    <jsp:include page="infoload.jsp" /> 
                    <%
                    } else if (opt.equals("loading")) {
                    %>
                    <jsp:include page="loading.jsp" /> 
                    <%
                    } else if (opt.equals("setup")) {
                    %>
                    <%@include file="/setupDefault.jsp"%>
                    <%
                    } else if (opt.equals("medidores")) {
                    %>
                    <jsp:include page="medidores.jsp" /> 
                    <%
                    } else if (opt.equals("searchMeters")) {
                    %>
                    <jsp:include page="searchmedidores.jsp" /> 
                    <%
                    } else if (opt.equals("version")) {
                    %>
                    <jsp:include page="version.jsp" /> 
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

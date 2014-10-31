<%-- 
    Document   : loading
    Created on : Oct 20, 2014, 9:27:00 AM
    Author     : julian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="resources/css/Table_1.css">
        <link rel="stylesheet" href="resources/css/normalizePG.css">
        <link rel="stylesheet" href="resources/css/progressbar.css">
        <script src="resources/js/jquery.js" type="text/javascript"></script>
        <script src="resources/js/modernizr.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                if (!Modernizr.meter) {
                    alert('Sorry your brower does not support HTML5 progress bar');
                } else {
                    var progressbar = $('#progressbar'),
                            max = progressbar.attr('max');
                    var refresh = setInterval(function () {
                        call();
                    }, 50);
                }

                function call() {
                    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlObj = new XMLHttpRequest();
                    } else {// code for IE6, IE5
                        xmlObj = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlObj.onreadystatechange = function () {
                        if (xmlObj.readyState == 4 && xmlObj.status == 200) {
                            if (xmlObj.responseText == '200') {
                                clearInterval(refresh);
                                window.location.reload();
                            } else {
                                addValue = progressbar.val(xmlObj.responseText);
                                $('.progress-value').html(xmlObj.responseText + '%');

                                if (xmlObj.responseText == max) {
                                    clearInterval(refresh);
                                    location.reload();
                                }
                            }
                        }
                    };
                    xmlObj.open("GET", "ajaxRequest", true);
                    xmlObj.send();
                }

            });
        </script>
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
                                Carga de informaci&oacute;n de Usuarios, Roles y 
                                <br>Políticas desde el Sistema Administrativo
                            </h3>
                            <div class="demo-wrapper html5-progress-bar">
                                <div class="progress-bar-wrapper">
                                    <progress id="progressbar" value="0" max="100"></progress>
                                    <span class="progress-value">0%</span>
                                </div>  
                            </div>  
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>

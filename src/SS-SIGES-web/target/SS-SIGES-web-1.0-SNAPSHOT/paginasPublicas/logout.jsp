<%-- 
    Document   : logout
    Created on : 18-feb-2015, 14:44:49
    Author     : Usuario
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Logout</title>
    </head>
    <body>
        <%
            java.util.Date d = new java.util.Date();
            String redirectURL = "/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.xhtml?code="+d.getTime();
            response.sendRedirect(redirectURL);
        %>
    </body>
</html>

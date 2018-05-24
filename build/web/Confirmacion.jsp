<%-- 
    Document   : Confirmacion
    Created on : May 24, 2018, 10:08:40 AM
    Author     : kmont
--%>

<%@page import="entidades.Film"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago</title>
        <%
             Film film=(Film) request.getAttribute("film");
             String metodo = (String)request.getAttribute("metodoPago");
        %>
    </head>
    <body>
        <h1>Operacion realizada con exito</h1>
        <label>Pelicula</label>
        <label><%=film.getTitle()%></label>
        <label>Costo total</label>
        <label><%=film.getPrecio()%></label>
         <label>Metodo pago</label>
        <label><%=metodo%></label>
    </body>
</html>

<%-- 
    Document   : ListarPelis
    Created on : 23-may-2018, 15:53:11
    Author     : LABO08
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Film"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Peliculas disponibles</title>
        <%
            List<Film> lista = (List<Film>) request.getAttribute("lista");
        %>
        <script>
            function main() {
                console.log("ya corgó todo" + document.getElementById("table"));

            }

        </script>
    </head>
    <body onload="main()">
        <h1>Peliculas</h1>
        <form ACTION="Controlador" METHOD=GET>  
            <input type="hidden" name="Instruccion" value="pagar">
            <table id="table">
                <tr>
                    <th>Codigo Pelicula</th>
                    <th>title</th>
                    <th>description</th>
                    <th>language</th>
                    <th>Duration</th>
                    <th>Rating</th>
                    <th>Disponible</th>
                    <th>precio</th>
                    <th>rentar</th
                </tr>
                <% for (Film pro : lista) {%>
                <tr>
                    <td class="filas"><%=pro.getId()%></td>
                    <td class="filas"><%=pro.getTitle()%></td>
                    <td class="filas"><%=pro.getDescription()%></td>
                    <td class="filas"><%=pro.getLanguage()%></td>
                    <td class="filas"><%=pro.getLength().toString()%></td>
                    <td class="filas"><%=pro.getRating()%></td>
                    <td class="filas"><%=pro.getDisponible()%></td>
                    <td class="filas"><%=pro.getPrecio()%></td>
                    <td class="filas"><input type="checkbox" name = "check" value = "<%=pro.getId()%>"</td>
                </tr>
                <%}%>   
            </table>
            <hr/>
            <input type="submit"  value="Rentar">
        </form>
    </body>
</html>

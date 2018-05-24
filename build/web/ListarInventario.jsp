<%-- 
    Document   : ListarInventario
    Created on : 23-may-2018, 18:02:58
    Author     : LABO08
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Inventory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventario</title>
        <%List<Inventory> lista = (List<Inventory>) request.getAttribute("lista");%>
    </head>
    <body>
        <h1>Mostrar inventario</h1>
        <form ACTION="Controlador" METHOD=GET>  
            <input type="hidden" name="Instruccion" value="inventario">
        <table id="table">
            <tr>
                <th>Codigo</th>
                <th>Titulo</th>
                <th>Precio</th>
                <th>Descripcion</th>.
            </tr>
            <% for (Inventory pro : lista) {%>
            <tr>
                <td class="filas"><%=pro.getId()%></td>
                <td class="filas"><%=pro.getFilm().getTitle()%></td>
                <td class="filas"><%=pro.getFilm().getPrecio() %></td>
                <td class="filas"><%=pro.getFilm().getDescription()%></td>
                <td><input type="checkbox" name = "check" value = "<%=pro.getId()%>"></td>
            </tr>
            <%}%>   
        </table>
        <h3>Elegir Metodo de Pago</h3>
            <input type="radio" name="pagar" value="CASH"> CASH
            <input type="radio" name="pagar" value="CREDIT"> CREDIT
            <input type="radio" name="pagar" value="DEBT"> DEBT
            <input type="radio" name="pagar" value="PAYPAL"> PAYPAL
        <input type="submit" value="Rentar">
        </form>
    </body>
</html>

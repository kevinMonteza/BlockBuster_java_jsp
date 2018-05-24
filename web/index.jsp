<%-- 
    Document   : Vista
    Created on : 23-may-2018, 15:26:44
    Author     : LABO08
--%>

<%@page import="entidades.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
             List<Category> lista = (List<Category>) request.getAttribute("lista");
        %>
        <title>BlockBuster</title>
    </head>
    <body>
        <h1>BlockBuster</h1><hr/>
        <h2>Ingrese categoria</h2>
        
        <form ACTION="Controlador" METHOD=GET>  
            <input type="hidden" name="Instruccion" value="peliculas"/>
           <select name="Categoria">
                <%  for (Category cat : lista) {%>
                <option value=<%=cat.getId()%> ><%=cat.getName() %></option>
                <%}%>
            </select>
           <!-- <input type="submit" />   
                <select name="Categoria">
                    <option value="a">a</option>
                    <option value="b">b</option>
                    <option value="c">c</option>
                </select> -->
                 <input type="submit" value="Buscar" />  

        </form>

    </body>
</html>

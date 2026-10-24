<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Cliente</title>
</head>
<body>
    <h2>MODIFICAR CLIENTE</h2>
    
    <%
        Cliente c = (Cliente) request.getAttribute("cliente");
        if (c != null) {
    %>
    <form method="post" action="${pageContext.request.contextPath}/ClienteControlador">
        <input type="hidden" name="id" value="<%= c.getId() %>" />
        
        ID: <b><%= c.getId() %></b><br><br>
        DNI: <input type="text" name="dni" value="<%= c.getDni() %>" maxlength="8" required /><br><br>
        Apellidos: <input type="text" name="apellidos" value="<%= c.getApellidos() %>" required /><br><br>
        Nombres: <input type="text" name="nombres" value="<%= c.getNombres() %>" required /><br><br>
        Email: <input type="email" name="email" value="<%= c.getEmail() %>" required /><br><br>
        Celular: <input type="text" name="celular" value="<%= c.getCelular() %>" maxlength="9" required /><br><br>
        Dirección: <input type="text" name="direccion" value="<%= c.getDireccion() %>" required /><br><br>
        
        <input type="submit" name="btnActualizar" value="Actualizar Cliente" />
    </form>
    <% } %>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Inicio</a>
<!-- o si regresa a la lista -->
<a href="${pageContext.request.contextPath}/ClienteControlador?accion=listar">Volver a la Lista</a>
</body>
</html>
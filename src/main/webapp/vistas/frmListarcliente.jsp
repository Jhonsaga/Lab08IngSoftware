<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, modelo.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body bgcolor="#FFFFCC" text="#000000">
    <h1 align="center" style="color: red;">LISTA DE CLIENTES</h1>
    
    <%
        String mensaje = request.getParameter("m");
        if (mensaje != null) {
    %>
        <script>alert("<%= mensaje %>");</script>
    <%
        }
    %>

    <table width="900" border="1" align="center" cellpadding="5" cellspacing="0">
        <thead>
            <tr bgcolor="#E0E0E0">
                <th>ID</th>
                <th>DNI</th>
                <th>Apellidos</th>
                <th>Nombres</th>
                <th>Email</th>
                <th>Celular</th>
                <th>Dirección</th>
                <th>Acción</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
                if (lista != null) {
                    for (Cliente c : lista) {
            %>
            <tr>
                <td align="center"><%= c.getId() %></td>
                <td align="center"><%= c.getDni() %></td>
                <td><%= c.getApellidos() %></td>
                <td><%= c.getNombres() %></td>
                <td><%= c.getEmail() %></td>
                <td align="center"><%= c.getCelular() %></td>
                <td><%= c.getDireccion() %></td>
                <td align="center">
                <a href="${pageContext.request.contextPath}/ClienteControlador?accion=modificarForm&id=<%= c.getId() %>">Modificar</a>
                </td>
                <td align="center">
                <a href="${pageContext.request.contextPath}/ClienteControlador?accion=eliminar&id=<%= c.getId() %>" onclick="return confirm('¿Desea eliminar este cliente?');">Eliminar</a>
            </td>
</td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    <br>
    <p align="center">
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Inicio</a>
</p>
</body>
</html>
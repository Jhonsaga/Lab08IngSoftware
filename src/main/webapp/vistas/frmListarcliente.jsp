<%@page import="java.util.List"%>
<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Gestión de Clientes</h2>

    <% if (request.getParameter("m") != null) { %>
        <div class="alert alert-info" id="mensajeAlerta">
            <%= request.getParameter("m") %>
        </div>
    <% } %>

    <a href="${pageContext.request.contextPath}/vistas/frmRegistrarcliente.jsp" class="btn btn-success mb-3">+ Nuevo Cliente</a>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-secondary mb-3">Inicio</a>

    <table class="table table-bordered table-striped" id="tablaClientes">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>DNI</th>
                <th>Apellidos</th>
                <th>Nombres</th>
                <th>Email</th>
                <th>Celular</th>
                <th>Dirección</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
                if (lista != null) {
                    for (Cliente c : lista) {
            %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getDni() %></td>
                <td><%= c.getApellidos() %></td>
                <td><%= c.getNombres() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getCelular() %></td>
                <td><%= c.getDireccion() %></td>
                <td>
                    <a href="ClienteControlador?accion=modificarForm&id=<%= c.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                    <a href="ClienteControlador?accion=eliminar&id=<%= c.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
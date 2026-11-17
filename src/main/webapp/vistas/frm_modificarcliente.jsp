<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Modificar Cliente</h2>
    <hr>
    <%
        Cliente c = (Cliente) request.getAttribute("cliente");
    %>
    <form action="${pageContext.request.contextPath}/ClienteControlador" method="POST">
        <input type="hidden" name="id" value="<%= c.getId() %>">

        <div class="mb-3">
            <label class="form-label">DNI:</label>
            <input type="text" name="dni" class="form-control" maxlength="8" value="<%= c.getDni() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Apellidos:</label>
            <input type="text" name="apellidos" class="form-control" value="<%= c.getApellidos() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombres:</label>
            <input type="text" name="nombres" class="form-control" value="<%= c.getNombres() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" value="<%= c.getEmail() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Celular:</label>
            <input type="text" name="celular" class="form-control" maxlength="9" value="<%= c.getCelular() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Dirección:</label>
            <input type="text" name="direccion" class="form-control" value="<%= c.getDireccion() %>" required>
        </div>

        <button type="submit" name="btnActualizar" value="Actualizar" class="btn btn-warning">Guardar Cambios</button>
        <a href="${pageContext.request.contextPath}/ClienteControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</body>
</html>
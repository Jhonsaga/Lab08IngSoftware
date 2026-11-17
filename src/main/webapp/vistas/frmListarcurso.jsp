<%@page import="java.util.List"%>
<%@page import="modelo.Curso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Cursos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Gestión de Cursos</h2>

    <% if (request.getParameter("m") != null) { %>
        <div class="alert alert-info" id="mensajeAlerta">
            <%= request.getParameter("m") %>
        </div>
    <% } %>

    <a href="${pageContext.request.contextPath}/vistas/frmRegistrarcurso.jsp" class="btn btn-success mb-3">+ Nuevo Curso</a>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-secondary mb-3">Inicio</a>

    <table class="table table-bordered table-striped" id="tablaCursos">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Código</th>
                <th>Nombre</th>
                <th>Créditos</th>
                <th>Docente</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Curso> lista = (List<Curso>) request.getAttribute("listaCursos");
                if (lista != null) {
                    for (Curso c : lista) {
            %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getCodigo() %></td>
                <td><%= c.getNombre() %></td>
                <td><%= c.getCreditos() %></td>
                <td><%= c.getDocente() %></td>
                <td>
                    <a href="CursoControlador?accion=modificarForm&id=<%= c.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                    <a href="CursoControlador?accion=eliminar&id=<%= c.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
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
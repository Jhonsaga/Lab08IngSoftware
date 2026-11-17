<%@page import="modelo.Curso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Modificar Curso</h2>
    <hr>
    <%
        Curso c = (Curso) request.getAttribute("curso");
    %>
    <form action="${pageContext.request.contextPath}/CursoControlador" method="POST">
        <input type="hidden" name="id" value="<%= c.getId() %>">

        <div class="mb-3">
            <label class="form-label">Código del Curso:</label>
            <input type="text" name="codigo" class="form-control" value="<%= c.getCodigo() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre del Curso:</label>
            <input type="text" name="nombre" class="form-control" value="<%= c.getNombre() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Créditos:</label>
            <input type="number" name="creditos" class="form-control" min="1" max="10" value="<%= c.getCreditos() %>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Docente:</label>
            <input type="text" name="docente" class="form-control" value="<%= c.getDocente() %>" required>
        </div>

        <button type="submit" name="btnActualizar" value="Actualizar" class="btn btn-warning">Guardar Cambios</button>
        <a href="${pageContext.request.contextPath}/CursoControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</body>
</html>
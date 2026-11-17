<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <h2>Registrar Nuevo Curso</h2>
    <hr>
    <form action="${pageContext.request.contextPath}/CursoControlador" method="POST">
        <div class="mb-3">
            <label class="form-label">Código del Curso:</label>
            <input type="text" name="codigo" class="form-control" required placeholder="Ej: IS-801">
        </div>

        <div class="mb-3">
            <label class="form-label">Nombre del Curso:</label>
            <input type="text" name="nombre" class="form-control" required placeholder="Ej: Ingeniería de Software II">
        </div>

        <div class="mb-3">
            <label class="form-label">Créditos:</label>
            <input type="number" name="creditos" class="form-control" min="1" max="10" required value="4">
        </div>

        <div class="mb-3">
            <label class="form-label">Docente:</label>
            <input type="text" name="docente" class="form-control" required placeholder="Ej: Ing. Carlos Mendoza">
        </div>

        <button type="submit" name="btnRegistrar" value="Registrar" class="btn btn-primary">Registrar</button>
        <a href="${pageContext.request.contextPath}/CursoControlador?accion=listar" class="btn btn-secondary">Volver al Listado</a>
    </form>
</body>
</html>